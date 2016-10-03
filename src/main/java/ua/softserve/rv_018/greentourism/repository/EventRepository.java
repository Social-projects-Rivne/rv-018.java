package ua.softserve.rv_018.greentourism.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	@Query(value="select event.* "
			+ "from event inner join point on event.point_id = point.id "
			+ "where point.latitude >= :south_west_latitude "
			+ "and point.longitude >= :south_west_longitude "
			+ "and point.latitude <= :north_east_latitude "
			+ "and point.longitude <= :north_east_longitude"
			, nativeQuery=true)
	List<Event> findBetweenTwoPoints(
			@Param("south_west_latitude") Float southWestLatitude,
			@Param("south_west_longitude") Float southWestLongitude,
			@Param("north_east_latitude") Float northEastLatitude,
			@Param("north_east_longitude") Float northEastLongitude);

	@Query(value="select event.* from event "
			+ "where if((:start_date_param <= event.date_start) "
			+ "and (:end_date_param >= event.date_start) "
			+ "and (:end_date_param <= event.date_end), "
			+ "event.date_start between event.date_start and :end_date_param, "
			+ "if((:start_date_param >= event.date_start) "
			+ "and (:start_date_param <= event.date_end) "
			+ "and (:end_date_param >= event.date_start) "
			+ "and (:end_date_param <= event.date_end), "
			+ "1=1, "
			+ "if((:start_date_param <= event.date_end) "
			+ "and (:start_date_param >= event.date_start) "
			+ "and (:end_date_param >= event.date_end) "
			+ "and (:end_date_param >= event.date_start), "
			+ "event.date_end between :start_date_param and event.date_end, "
			+ "if((:start_date_param <= event.date_start) "
			+ "and (:start_date_param <= event.date_end) "
			+ "and (:end_date_param >= event.date_end) "
			+ "and (:end_date_param >= event.date_start), "
			+ "1=1, 'false'))))"
			, nativeQuery=true)
	List<Event> findBetweenTwoDates(
			@Param("start_date_param") Date startDate,
			@Param("end_date_param") Date endDate);

	Event findById(int id);

	@Query(value="select event.* from event where event.owner_id = :user_id"
			, nativeQuery=true)
	List<Event> findByUserId(
			@Param ("user_id") Long id);
}

package ua.softserve.rv_018.greentourism.repository;

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
}

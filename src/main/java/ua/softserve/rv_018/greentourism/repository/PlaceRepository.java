package ua.softserve.rv_018.greentourism.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.User;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
	List<Place> findByNameIgnoreCaseContaining(String name);
	
	@Query(value="select place.* "
			+ "from place inner join point on place.point_id = point.id "
			+ "where point.latitude >= :south_west_latitude "
			+ "and point.longitude >= :south_west_longitude "
			+ "and point.latitude <= :north_east_latitude "
			+ "and point.longitude <= :north_east_longitude"
			, nativeQuery=true)
	List<Place> findBetweenTwoPoints(
			@Param("south_west_latitude") Float southWestLatitude,
			@Param("south_west_longitude") Float southWestLongitude,
			@Param("north_east_latitude") Float northEastLatitude,
			@Param("north_east_longitude") Float northEastLongitude);
	
	@Query(value="select place.* from place where place.owner_id = :user_id"
			, nativeQuery=true)
	List<Place> findByUserId(
			@Param ("user_id") Long id);
}

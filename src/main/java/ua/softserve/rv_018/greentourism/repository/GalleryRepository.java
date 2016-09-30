package ua.softserve.rv_018.greentourism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
	@Query("select g from Gallery as g inner join g.place as pl where pl in :places")
	List<Gallery> findByPlaces(
			@Param ("places") List<Place> places);
	
	@Query("select g from Gallery as g inner join g.place as pl where pl in :place")
	List<Gallery> findByPlace(
			@Param ("place") Place place);
	
	@Query(value = "select gallery.* from gallery where gallery.place_item_id = :placeId", nativeQuery=true)
	List<Gallery> findByPlaceItemId(
			@Param ("placeId") int placeId);

	@Query("select g from Gallery as g inner join g.event as ev where ev in :events")
	List<Gallery> findByEvents(
			@Param ("events") List<Event> events);

	@Query("select g from Gallery as g inner join g.event as ev where ev in :event")
	List<Gallery> findByEvent(
			@Param ("event") Event event);
}

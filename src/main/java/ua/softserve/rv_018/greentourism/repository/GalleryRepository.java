package ua.softserve.rv_018.greentourism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.softserve.rv_018.greentourism.model.Attachment;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.User;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
	@Query("select g from Gallery as g inner join g.place as pl where pl in :places")
	List<Gallery> findByPlaces(
			@Param ("places") List<Place> places);
}

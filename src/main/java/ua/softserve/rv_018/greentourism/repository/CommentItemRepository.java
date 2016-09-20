package ua.softserve.rv_018.greentourism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.model.Place;

public interface CommentItemRepository extends JpaRepository<CommentItem, Integer> {
	@Query("select c from CommentItem as c inner join c.place as pl where pl in :places")
	List<CommentItem> findByPlaces(
			@Param ("places") List<Place> places);
	
	@Query("select c from CommentItem as c inner join c.place as pl where pl in :place")
	List<CommentItem> findByPlace(
			@Param ("place") Place place);
}

package ua.softserve.rv_018.greentourism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.softserve.rv_018.greentourism.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value="SELECT comment_item.comment_id, comment_item.place_item_id, comment_item.table_type, "
			+ "comment.id, comment.owner_id,"
			+ " place.id, user_detail.id"
			+ "FROM comment_item, comment, place, user_detail"
			+ "WHERE comment.id = comment_item.comment_id"
			+ "AND place.id = comment_item.place_item_id"
			+ "AND comment_item.table_type = 'place'"
			+ "AND user_detail.id = comment.owner_id;"
			, nativeQuery=true)
	List<Comment> findByPlaceId(
			@Param ("place_item_id") int id);
}
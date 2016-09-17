package ua.softserve.rv_018.greentourism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.softserve.rv_018.greentourism.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{
	@Query(value="select gallery.attach_id"
			+ "from gallery where place.id = gallery.item_id AND gallery.table_type = 'place'"
			, nativeQuery=true)
	List<Attachment> findByPlaceId(
			@Param ("item_id") int id);
}


package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Attachment;

public interface AttachmentService {
	/**
     * Persists a Attachment entity in the data store.
     * @param attachment A Attachment object to be persisted.
     * @return The persisted Attachment entity.
     */
	Attachment create(Attachment attachment);
	
	/**
     * Find all Attachment entities.
     * @return A List of Attachment objects.
     */
	List<Attachment> findByPlaceId(int id);
	
	List<Attachment> findByPlaceItemId(int id);
	
	List<Attachment> findByEventItemId(int id);
}

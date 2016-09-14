package ua.softserve.rv_018.greentourism.service;

import ua.softserve.rv_018.greentourism.model.Attachment;

public interface AttachmentService {
	/**
     * Persists a Attachment entity in the data store.
     * @param attachment A Attachment object to be persisted.
     * @return The persisted Attachment entity.
     */
	Attachment create(Attachment attachment);
}

package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Comment;

public interface CommentService {
	/**
     * Persists a Comment entity in the data store.
     * @param comment A Comment object to be persisted.
     * @return The persisted Comment entity.
     */
	Comment create(Comment comment);
	
	/**
     * Find all Comment entities by place Id.
     * @return A List of Comment objects.
     */
	List<Comment> findByPlaceId(int id);
}

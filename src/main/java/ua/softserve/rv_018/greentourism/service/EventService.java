package ua.softserve.rv_018.greentourism.service;

import ua.softserve.rv_018.greentourism.model.Event;

public interface EventService {
	/**
     * Persists a Event entity in the data store.
     * @param event A Event object to be persisted.
     * @return The persisted Event entity.
     */
    Event create(Event event); 
}

package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;
import ua.softserve.rv_018.greentourism.model.Place;

public interface PlaceService {
	/**
     * Find all Places entities.
     * @return A Collection of Places objects.
     */
    Collection<Place> findAll();
    Collection<Place> findByNameIgnoreCaseContaining(String name);
}

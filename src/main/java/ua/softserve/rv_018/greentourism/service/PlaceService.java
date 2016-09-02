package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;

public interface PlaceService {
	/**
     * Find all Place entities.
     * @return A List of Place objects.
     */
	List<Place> findAll();
	
	/**
     * Find all Place entities by name using filter.
     * @return A List of Place objects.
     */
	List<Place> findByName(String name, boolean checkWholeWord, boolean checkIgnoreCase);

	/**
     * Find all Point entities between two coordinates.
     * @return A List of Point objects.
     */
	List<Point> findPlacePointsBetweenTwoCoordinates(Point southWest, Point northEast);
	
	/**
     * Persists a Place entity in the data store.
     * @param place A Place object to be persisted.
     * @return The persisted Place entity.
     */
    Place create(Place place); 
    
    Place findOne(Integer id);
}

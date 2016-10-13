package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;

public interface PlaceService {
	/**
     * Find a single Place entity by primary key identifier.
     * @param id A Integer primary key identifier.
     * @return A Place or <code>null</code> if none found.
     */
    Place findOne(int id);
	
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
     * Find all Place entities between two coordinates.
     * @return A List of Place objects.
     */
	List<Place> findPlacesBetweenTwoCoordinates(Point southWest, Point northEast);
	
	/**
     * Persists a Place entity in the data store.
     * @param place A Place object to be persisted.
     * @return The persisted Place entity.
     */
    Place create(Place place); 
    
    /**
     * Updates a previously persisted Place entity in the data store.
     * @param place A Place object to be updated.
     * @return The updated Place entity.
     */
    Place update(Place place);

    /**
     * Find Place entities.
     * @return A Place objects.
     */
    Place findOne(Integer id);
    /**
     * Find all Place entities by user Id.
     * @return A List of Place objects.
     */
    List<Place> findByUserId(Long id);
    /**
     * Find all Place entities with attachments by user Id.
     * @return A List of Place objects.
     */
	List<Place> findByUserIdWithAttachments(Long id);
}

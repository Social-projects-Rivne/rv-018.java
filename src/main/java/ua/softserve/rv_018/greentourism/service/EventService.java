package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Point;

public interface EventService {
	/**
     * Find all Event entities.
     * @return A List of Event objects.
     */
	List<Event> findAll();
	
	/**
     * Find all Event entities by name using filter.
     * @return A List of Event objects.
     */
	List<Event> findByName(String name, boolean checkWholeWord, boolean checkIgnoreCase);

	/**
     * Find all Event entities between two coordinates.
     * @return A List of Event objects.
     */
	List<Point> getEventPointsBetweenTwoCoordinates(Point southWest, Point northEast);
}

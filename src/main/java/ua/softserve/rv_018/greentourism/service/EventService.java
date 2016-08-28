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
     * Find all Points entities between two coordinates.
     * @return A List of Event objects.
     */
	List<Point> findEventPointsBetweenTwoCoordinates(Point southWest, Point northEast);
}

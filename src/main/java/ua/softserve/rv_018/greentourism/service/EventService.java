package ua.softserve.rv_018.greentourism.service;

import java.sql.Date;
import java.util.List;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Place;
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
	
	/**
     * Find all Points entities between two dates.
     * @return A List of Event objects.
     */
	List<Point> findEventPointsBetweenTwoDates(Date startDate, Date endDate);
	
	/**
     * Persists a Event entity in the data store.
     * @param event A Event object to be persisted.
     * @return The persisted Event entity.
     */
    Event create(Event event);

	/**
	 * Find all Event entities by user Id.
	 * @return A List of Event objects.
	 */
	List<Event> findByUserId(Long id);

	/**
	 * Find all Event entities with attachments by user Id.
	 * @return A List of Event objects.
	 */
	List<Event> findByUserIdWithAttachments(Long id);

	/**
	 * Find a single Event entity by primary key identifier.
	 * @param id A Integer primary key identifier.
	 * @return A Event or <code>null</code> if none found.
	 */
	Event findOne(Integer id);
	
	/**
     * Find all Event entities by name using filter.
     * @return A List of Event objects.
     */
	List<Event> findByName(String name, boolean checkWholeWord, boolean checkIgnoreCase);
}

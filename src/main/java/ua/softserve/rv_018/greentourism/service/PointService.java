package ua.softserve.rv_018.greentourism.service;

import ua.softserve.rv_018.greentourism.model.Point;

public interface PointService {
	/**
	 * Create Point from the string coordinate
	 * @param coordinate A string in format latitude%longitude
	 * @return A created point
	 */
	Point createPoint(String coordinate);
	
	/**
     * Find a single Point entity by primary key identifier.
     * @param id A Integer primary key identifier.
     * @return A Point or <code>null</code> if none found.
     */
    Point findOne(Integer id);
}

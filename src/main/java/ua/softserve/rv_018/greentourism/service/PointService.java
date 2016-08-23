package ua.softserve.rv_018.greentourism.service;

import ua.softserve.rv_018.greentourism.model.Point;

public interface PointService {
	/**
	 * Create Point from the string coordinate
	 * @param coordinate A string in format latitude%longitude
	 * @return A created point
	 */
	Point createPoint(String coordinate);
}

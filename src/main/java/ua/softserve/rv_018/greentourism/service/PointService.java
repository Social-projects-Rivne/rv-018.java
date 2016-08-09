package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

import ua.softserve.rv_018.greentourism.model.Point;

public interface PointService {
	/**
     * Find all Points entities.
     * @return A Collection of Points objects.
     */
    Collection<Point> findAll();
}

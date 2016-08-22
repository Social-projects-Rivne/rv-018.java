package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ua.softserve.rv_018.greentourism.model.Point;

@Service
public class PointServiceImpl implements PointService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Point createPoint(String coordinate) {
		logger.info("> createPoint");
		
		System.out.println("121: " + coordinate);
		
		Point point = new Point();
		
		String [] coordinates = coordinate.split(":");
		if (coordinates.length != 2) {
			logger.debug("Given coordinate in wrong format or empty");
			System.out.println("122");
			
			point.setEmpty(true);
			
			return point;
		}
		
		System.out.println("123");
		
		point.setLatitude(Float.valueOf(coordinates[0]));
		point.setLongitude(Float.valueOf(coordinates[1]));
		
		logger.info("< createPoint");
		
		return point;
	}
}

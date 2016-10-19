package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.PointRepository;

@Service
public class PointServiceImpl implements PointService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PointRepository pointRepository;

	@Override
	public Point createPoint(String coordinate) {
		logger.info("> Point createPoint");
		
		Point point = new Point();
		
		String [] coordinates = coordinate.split(":");
		if (coordinates.length != 2) {
			logger.debug("Given coordinate in wrong format or empty");
			
			point.setEmpty(true);
			
			return point;
		}
		
		point.setLatitude(Float.valueOf(coordinates[0]));
		point.setLongitude(Float.valueOf(coordinates[1]));
		
		logger.info("< Point createPoint");
		
		return point;
	}
	
	@Override
	public Point findOne(Integer id) {
		logger.info("> Point findOne id:{}", id);

        Point point = pointRepository.findOne(id);

        logger.info("< Point findOne id:{}", id);

        return point;
	}
}

package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.PointRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class PointServiceImpl implements PointService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The Spring Data repository for Points entities.
     */
    @Autowired
    private PointRepository pointRepository;

	@Override
	public Collection<Point> findAll() {
		logger.info("> Point findAll");

        Collection<Point> points = pointRepository.findAll();

        logger.info("< Point findAll");
        
        return points;
	}
}

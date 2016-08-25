package ua.softserve.rv_018.greentourism.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.EventRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class EventServiceImpl implements EventService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The Spring Data repository for Events entities.
     */
    @Autowired
    private EventRepository eventRepository;

	@Override
	public List<Event> findAll() {
		logger.info("> Event findAll");

		List<Event> events = eventRepository.findAll();

        logger.info("< Event findAll");
        
        return events;
	}
	
	@Override
	public List<Event> findByName(String name, boolean checkIgnoreCase, boolean checkContaining) {
		logger.info("> Event findByName");

		List<Event> events = new ArrayList<>();
		
		if (checkIgnoreCase && checkContaining) {
			events = eventRepository.findByNameIgnoreCaseContaining(name);
		}
		// here will be other findByName... methods due to checkIgnoreCase && checkWholeWord values

        logger.info("< Event findByName");

		return events;
	}

	@Override
	public List<Point> getEventPointsBetweenTwoCoordinates(Point southWest, Point northEast) {
		logger.info("> Event getEventPointsBetweenTwoCoordinates");

		List<Event> events = eventRepository.findBetweenTwoPoints(
				southWest.getLatitude(), southWest.getLongitude(),
				northEast.getLatitude(), northEast.getLongitude());
		List<Point> points = getPointsFromEvents(events);
		
        logger.info("< Event getEventPointsBetweenTwoCoordinates");

		return points;
	}

	private List<Point> getPointsFromEvents(List<Event> events) {
		List<Point> points = new ArrayList<>();
		
		for (Event event : events) {
			points.add(event.getPoint());
		}
		
		return points;
	}

//	@Override
//	public List<Event> findByName(String name, boolean checkWholeWord, boolean checkIgnoreCase) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

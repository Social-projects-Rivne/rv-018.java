package ua.softserve.rv_018.greentourism.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.EventRepository;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;

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

	/**
	 * The Spring Data repository for Attachment entities.
	 */
	@Autowired
	private GalleryRepository galleryRepository;

	@Override
	public Event create(Event event) {
		logger.info("> Event create");
		
		Event savedEvent = eventRepository.save(event);
		
		logger.info("> Event create");
		
		return savedEvent;
	}

	public List<Event> findAll() {
		logger.info("> Event findAll");

		List<Event> events = eventRepository.findAll();

        logger.info("< Event findAll");
        
        return events;
	}

	@Override
	public List<Point> findEventPointsBetweenTwoCoordinates(Point southWest, Point northEast) {
		logger.info("> Event findEventPointsBetweenTwoCoordinates");

		List<Event> events = eventRepository.findBetweenTwoPoints(
				southWest.getLatitude(), southWest.getLongitude(),
				northEast.getLatitude(), northEast.getLongitude());
		List<Point> points = getPointsFromEvents(events);
		
        logger.info("< Event findEventPointsBetweenTwoCoordinates");

		return points;
	}

	private List<Point> getPointsFromEvents(List<Event> events) {
		List<Point> points = new ArrayList<>();
		
		for (Event event : events) {
			points.add(event.getPoint());
		}
		
		return points;
	}
	
	@Override
	public List<Point> findEventPointsBetweenTwoDates(Date startDate, Date endDate) {
		logger.info("> Event findEventPointsBetweenTwoDates");

		List<Event> events = eventRepository.findBetweenTwoDates(startDate, endDate);
		List<Point> points = getPointsFromEvents(events);
		
        logger.info("< Event findEventPointsBetweenTwoDates");

		return points;
	}

	@Override
	public List<Event> findByUserId(Long id) {
		logger.info("> Event findByUserId");

		List<Event> events = eventRepository.findByUserId(id);

		logger.info("< Place findByUserId");

		return events;
	}

	@Override
	public List<Event> findByUserIdWithAttachments(Long id) {
		logger.info("> Event findByUserIdWithAttachment");

		List<Event> events = eventRepository.findByUserId(id);

		List<Gallery> galleries = galleryRepository.findByEvents(events);
		for (Gallery gallery : galleries) {
			if (gallery == null) {
				continue;
			}

			for (Event event : events) {
				if (event.getId() == gallery.getEvent().getId()) {
					event.getAttachments().add(gallery.getAttachment());
				}
			}
		}

		logger.info("< Place findByUserIdWithAttachment");

		return events;
	}

	@Override
	public Event findOne(int id) {
		logger.info("> Event findOne id:{}", id);

		Event event = eventRepository.findById(id);

		logger.info("< Event findOne id:{}", id);

		return event;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Event update(Event event) {
		logger.info("> Event update id:{}", event.getId());

		// Ensure the entity object to be updated exists in the repository to
		// prevent the default behavior of save() which will persist a new
		// entity if the entity matching the id does not exist
		Event eventToUpdate = findOne(event.getId());
		if (eventToUpdate == null) {
			// Cannot update Event that hasn't been persisted
			logger.error("Attempted to update a Event, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}

		if (event.getName() != null) {
			eventToUpdate.setName(event.getName());
		}
		if (event.getDescription() != null) {
			eventToUpdate.setDescription(event.getDescription());
		}
		
		Event updatedEvent = eventRepository.save(eventToUpdate);

		logger.info("< Event update id:{}", updatedEvent.getId());

		return updatedEvent;
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
	public List<Event> findByUserToken(String token) {
		logger.info("> Event findByUserToken token:{}", token);

		List<Event>  events = eventRepository.findByUserToken(token);

        logger.info("< Event findByUserToken token:{}", token);

        return events;
	}
}

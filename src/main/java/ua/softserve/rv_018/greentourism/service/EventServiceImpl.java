package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Event;
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
	public Event create(Event event) {
		logger.info("> Event create");
		
		Event savedEvent = eventRepository.save(event);
		
		logger.info("> Event create");
		
		return savedEvent;
	}
}

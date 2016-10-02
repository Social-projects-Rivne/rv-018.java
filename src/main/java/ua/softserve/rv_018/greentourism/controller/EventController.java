package ua.softserve.rv_018.greentourism.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;
import ua.softserve.rv_018.greentourism.service.EventService;
import ua.softserve.rv_018.greentourism.service.PointService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(value = "/api/event")
@Controller
public class EventController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PointService pointService;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private GalleryRepository galleryRepository;
	/**
     * Web service endpoint to create Event entity.
     * The service returns the created Event entity url.
     *
     * @return A ResponseEntity containing a url of created Event.
     */
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
	public ResponseEntity<?> createEvent(@RequestBody Event event) {
		logger.info("> createEvent");
	    
        Event savedEvent = eventService.create(event);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedEvent.getId()).toUri());
        
        logger.info("< createEvent");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}
	
	/**
	 * Web service endpoint to fetch all Event entities. The service returns
	 * the list of Event entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Event objects.
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> getEvents() {

		logger.info("> Get all events from the database");

		List<Event> events = eventService.findAll();

		logger.info("< Get all events from the database");

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	/**
	 * Web service endpoint to fetch all Event points between two coordinates.
	 * The service returns the list of Point entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Point objects.
	 */
	@RequestMapping(value = "/point", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> findEventPointsBetweenTwoCoordinates(
			@RequestParam (value="south-west", required=true) String southWestParam,
    		@RequestParam (value="north-east", required=true) String northEastParam) {
		logger.info("> Get event points between (" + southWestParam + " - " + northEastParam);
		
		List<Point> points = new ArrayList<>();
		
		Point southWest = pointService.createPoint(southWestParam);
		Point northEast = pointService.createPoint(northEastParam);
		
		if (southWest.isEmpty() || northEast.isEmpty()) {
			logger.debug("< Bad Request for getting event points between (" + southWestParam + " - " + northEastParam);
			return new ResponseEntity<>(points, HttpStatus.BAD_REQUEST);
		}
		
		points = eventService.findEventPointsBetweenTwoCoordinates(southWest, northEast);

		logger.info("< Get event points between (" + southWestParam + " - " + northEastParam);

		return new ResponseEntity<>(points, HttpStatus.OK);
	}
	
	/**
	 * Web service endpoint to fetch all Event points in given period of time.
	 * The service returns the list of Point entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Point objects.
	 */
	@RequestMapping(value = "/pointBetweenDates", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> findEventPointsBetweenTwoDates(
			@RequestParam (value="start-date", required=true) String startDateParam,
    		@RequestParam (value="end-date", required=true) String endDateParam) {
		java.sql.Date startDateSql = null;
		java.sql.Date endDateSql = null;

		try {
			Date startDateUtil = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH).parse(startDateParam);
			Date endDateUtil = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH).parse(endDateParam);
			startDateSql = new java.sql.Date(startDateUtil.getTime());
			endDateSql = new java.sql.Date(endDateUtil.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("> Get event points between (" + startDateParam + " - " + endDateParam);
		
		List<Point> points = new ArrayList<>();
		
		points = eventService.findEventPointsBetweenTwoDates(startDateSql, endDateSql);

		logger.info("< Get event points between (" + startDateParam + " - " + endDateParam);

		return new ResponseEntity<>(points, HttpStatus.OK);
	}

	/**
	 * Web service endpoint to fetch a single Event entity by User primary key
	 * identifier.
	 * <p>
	 * If found, the Event is returned as JSON with HTTP status 302.
	 * <p>
	 * If not found, the service returns an empty response body with HTTP status
	 * 404.
	 *
	 * @param id A Long URL path variable containing the User primary key
	 *           identifier.
	 * @return A ResponseEntity containing a single Event object, if found,
	 * and a HTTP status code as described in the method comment.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = {"application/json"})
	public ResponseEntity<?> getEventByUser(
			@PathVariable ("id") long id) {
		logger.info("> getPlace id:{}", id);

		List<Event> events = eventService.findByUserIdWithAttachments(id);

		logger.info("< getPlaceByUser id:{}", id);

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	/**
	 * Web service endpoint to fetch a single Event entity by primary key
	 * identifier.
	 * <p>
	 * If found, the Event is returned as JSON with HTTP status 302.
	 * <p>
	 * If not found, the service returns an empty response body with HTTP status
	 * 404.
	 *
	 * @param id A Long URL path variable containing the Event primary key
	 *           identifier.
	 * @return A ResponseEntity containing a single Event object, if found,
	 * and a HTTP status code as described in the method comment.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = {"application/json"})
	public ResponseEntity<?> getEvent(@PathVariable int id) {
		logger.info("> getEvent id:{}", id);

		Event event = eventService.findOne(id);
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Gallery> galleries = galleryRepository.findByEvent(event);
		for (Gallery gallery : galleries) {
			if (gallery == null) {
				continue;
			}
			if (event.getId() == gallery.getEvent().getId()) {
				event.getAttachments().add(gallery.getAttachment());
			}
		}

		logger.info("< getEvent id:{}", id);

		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	/**
	    * Web service endpoint to update a single Event entity. 
	    * <p>
	    * If updated successfully, the persisted Event is returned as JSON with
	    * HTTP status 200.
	    * <p>
	    * If not found, the service returns an empty response body and HTTP status
	    * 404.
	    * <p>
	    * If not updated successfully, the service returns an empty response body
	    * with HTTP status 500.
	    *
	    * @param event The Event object to be updated.
	    * @return A ResponseEntity containing a single Event object, if updated
	    * successfully, and a HTTP status code as described in the method
	    * comment.
	    */
	   @RequestMapping(value="/{id}", method=RequestMethod.PUT,
	           headers = "Accept=application/json", produces = {"application/json"})
	   public ResponseEntity<?> updateEvent(@PathVariable int id, @RequestBody Event event) {
	       logger.info("> updateEvent id:{}", event.getId());

	       if (eventService.findOne(id) == null) {
	           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
	        
	       Event updatedEvent = eventService.update(event);

	       logger.info("< updateEvent id:{}", event.getId());
	       
	       return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
	   }
}

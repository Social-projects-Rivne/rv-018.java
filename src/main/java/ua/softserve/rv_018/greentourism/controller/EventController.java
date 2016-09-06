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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Point;
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

}

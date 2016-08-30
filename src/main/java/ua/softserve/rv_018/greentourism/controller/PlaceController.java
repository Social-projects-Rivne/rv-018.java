package ua.softserve.rv_018.greentourism.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PlaceService;
import ua.softserve.rv_018.greentourism.service.PointService;

@RequestMapping(value = "/api/place")
@Controller
public class PlaceController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PointService pointService;

	/**
	 * Web service endpoint to fetch all Place entities. The service returns
	 * the list of Place entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Place objects.
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> getPlaces() {

		logger.info("> Get all places from the database");

		List<Place> places = placeService.findAll();

		logger.info("< Get all places from the database");

		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	/**
	 * Web service endpoint to fetch all Place points between two coordinates.
	 * The service returns the list of Point entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Point objects.
	 */
	@RequestMapping(value = "/point", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> getPlacePointsBetweenTwoCoordinates(
			@RequestParam (value="south-west", required=true) String southWestParam,
    		@RequestParam (value="north-east", required=true) String northEastParam) {
		logger.info("> Get place point between (" + southWestParam + " - " + northEastParam);
		
		List<Point> points = new ArrayList<>();
		
		Point southWest = pointService.createPoint(southWestParam);
		Point northEast = pointService.createPoint(northEastParam);
		
		if (southWest.isEmpty() || northEast.isEmpty()) {
			logger.debug("< Bad Request for getting place points between (" + southWestParam + " - " + northEastParam);
			return new ResponseEntity<>(points, HttpStatus.BAD_REQUEST);
		}
		
		points = placeService.findPlacePointsBetweenTwoCoordinates(southWest, northEast);

		logger.info("< Get place point between (" + southWestParam + " - " + northEastParam);

		return new ResponseEntity<>(points, HttpStatus.OK);
	}

	/**
     * Web service endpoint to fetch all Places entities by name.
     * The service returns the list of Place entities as JSON.
     *
     * @return A ResponseEntity containing a List of Place objects.
     */
    @RequestMapping(value = "/filter/name", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPlacesByName(
    		@RequestParam String name,
    		@RequestParam (value="ignorecase", required=false, defaultValue="false") Boolean ignoreCase,
    		@RequestParam (value="wholeword", required=false, defaultValue="false") Boolean wholeWord) {
    	logger.info("> Get places by filter (ignorecase=" + ignoreCase + ", wholeword=" + wholeWord);

    	List<Place> places = placeService.findByName(name, ignoreCase, !wholeWord);
    	
    	logger.info("< Get places by filter (ignorecase=" + ignoreCase + ", wholeword=" + wholeWord);
        
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

	/**
     * Web service endpoint to create Places entity.
     * The service returns the created Place entity url.
     *
     * @return A ResponseEntity containing a url of created Place.
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
    public ResponseEntity<?> createPlace(@RequestBody Place place) {
    	logger.info("> createPlace");
    
        Place savedPlace = placeService.create(place);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedPlace.getId()).toUri());
        
        logger.info("< createPlace");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}

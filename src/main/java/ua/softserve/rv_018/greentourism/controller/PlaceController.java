package ua.softserve.rv_018.greentourism.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

		logger.info("> getPlaces");

		List<Place> places = placeService.findAll();

		logger.info("< getPlaces");

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
		
		points = placeService.getPlacePointsBetweenTwoCoordinates(southWest, northEast);

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
    	logger.info("> getPlaces");

    	List<Place> places = new ArrayList<>();
    	
    	places = placeService.findByName(name, ignoreCase, !wholeWord);
    	
        logger.info("< getPlaces");
        
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}

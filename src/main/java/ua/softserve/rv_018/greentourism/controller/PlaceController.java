package ua.softserve.rv_018.greentourism.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.softserve.rv_018.greentourism.model.LatLng;
import ua.softserve.rv_018.greentourism.model.LatLngBounds;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PlaceService;

@RestController
public class PlaceController {

	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaceService placeService;

	private ArrayList<Point> currentViewportPoints;

	/**
     * Web service endpoint to fetch all Places entities. The service returns
     * the collection of Places entities as JSON.
     * @return 
     *
     * @return A ResponseEntity containing a Collection of Places objects.
     */
    @RequestMapping(value = "/api/place", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPlaces() {
    	
    	logger.info("> getPlaces");
    	
        Collection<Place> places = placeService.findAll();
        
        logger.info("< getPlaces");
        
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/currentMapViewportPoints", method = RequestMethod.POST,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> currentMapViewportPoints(@RequestBody LatLngBounds viewportBounds) {
        logger.info("> get current map viewport places");
                
        currentViewportPoints = new ArrayList<Point>();
        
        Collection<Place> places = placeService.findAll();
        for (Place place : places){
        	Point point = place.getPoint();
        	if (viewportBounds.contains(new LatLng(point.getLangtitude(), point.getLongtitude()))){
        		currentViewportPoints.add(place.getPoint());
        	}
        }

        logger.info("< get current map viewport places");
        
        return new ResponseEntity<>(currentViewportPoints, HttpStatus.OK);
    }

}

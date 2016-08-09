package ua.softserve.rv_018.greentourism.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PointService;

@RestController
public class PointController {

	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PointService pointService;

	/**
     * Web service endpoint to fetch all Points entities. The service returns
     * the collection of Points entities as JSON.
     * @return 
     *
     * @return A ResponseEntity containing a Collection of Points objects.
     */
    @RequestMapping(value = "/api/point", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPoints() {
    	
    	logger.info("> getPoints");
    	
        Collection<Point> points = pointService.findAll();
        
        logger.info("< getPoints");
        
        return new ResponseEntity<>(points, HttpStatus.OK);
    }
}

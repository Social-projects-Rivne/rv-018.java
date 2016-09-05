package ua.softserve.rv_018.greentourism.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.PointService;

@RestController
public class PointController {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /**
     * The RoleService business service.
     */
    @Autowired
    private PointService pointService;
    
    /**
     * Web service endpoint to fetch a single Point entity by primary key
     * identifier.
     * <p>
     * If found, the Point is returned as JSON with HTTP status 302.
     * <p>
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     *
     * @param id A Integer URL path variable containing the Point primary key
     *           identifier.
     * @return A ResponseEntity containing a single Point object, if found,
     * and a HTTP status code as described in the method comment.
     */
    @RequestMapping(value = "/point/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPoint(@PathVariable Integer id) {
        logger.info("> getPoint id:{}", id);

        Point point = pointService.findOne(id);
        if (point == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getPoint id:{}", id);
        
        return new ResponseEntity<>( point, HttpStatus.OK);
    }
}

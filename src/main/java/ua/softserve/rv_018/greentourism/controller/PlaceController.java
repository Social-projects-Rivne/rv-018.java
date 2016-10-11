package ua.softserve.rv_018.greentourism.controller;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.model.Role;
import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.repository.CommentItemRepository;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;
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
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	@Autowired
	private CommentItemRepository commentItemRepository;

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
	 * Web service endpoint to fetch all Place points between two coordinates.
	 * The service returns the list of Point entities as JSON.
	 * 
	 * @return A ResponseEntity containing a List of Point objects.
	 */
	@RequestMapping(value = "/places_coordinates", method = RequestMethod.GET,
			headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> getPlacesBetweenTwoCoordinates(
			@RequestParam (value="south-west", required=true) String southWestParam,
    		@RequestParam (value="north-east", required=true) String northEastParam) {
		logger.info("> Get places between (" + southWestParam + " - " + northEastParam);
		
		List<Place> places = new ArrayList<>();
		
		Point southWest = pointService.createPoint(southWestParam);
		Point northEast = pointService.createPoint(northEastParam);
		
		if (southWest.isEmpty() || northEast.isEmpty()) {
			logger.debug("< Bad Request for getting places between (" + southWestParam + " - " + northEastParam);
			return new ResponseEntity<>(places, HttpStatus.BAD_REQUEST);
		}
		
		places = placeService.findPlacesBetweenTwoCoordinates(southWest, northEast);

		logger.info("< Get places between (" + southWestParam + " - " + northEastParam);

		return new ResponseEntity<>(places, HttpStatus.OK);
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
    
   @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPlaceByUser(
    		@PathVariable ("id") long id) {
        logger.info("> getPlace id:{}", id);

        List<Place> places = placeService.findByUserIdWithAttachments(id);
        
        logger.info("< getPlaceByUser id:{}", id);
        
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
   
    /**
    * Web service endpoint to fetch a single Place entity by primary key
    * identifier.
    * <p>
    * If found, the Place is returned as JSON with HTTP status 302.
    * <p>
    * If not found, the service returns an empty response body with HTTP status
    * 404.
    *
    * @param id A Long URL path variable containing the Place primary key
    *           identifier.
    * @return A ResponseEntity containing a single Place object, if found,
    * and a HTTP status code as described in the method comment.
    */
   @RequestMapping(value = "/{id}", method = RequestMethod.GET,
           headers = "Accept=application/json", produces = {"application/json"})
   public ResponseEntity<?> getPlace(@PathVariable int id) {
       logger.info("> getPlace id:{}", id);

       Place place = placeService.findOne(id);
       if (place == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       List<Gallery> galleries = galleryRepository.findByPlace(place);
		for (Gallery gallery : galleries) {
			if (gallery == null) {
				continue;
			}
			if (place.getId() == gallery.getPlace().getId()) {
				place.getAttachments().add(gallery.getAttachment());
			}
		}
		
		List<CommentItem> commentItems = commentItemRepository.findByPlace(place);
		for (CommentItem commentItem : commentItems) {
			if (commentItem == null) {
				continue;
			}
			if (place.getId() == commentItem.getPlace().getId()) {
				place.getComments().add(commentItem.getComment());
			}
		}
		
       logger.info("< getPlace id:{}", id);
       
       return new ResponseEntity<>(place, HttpStatus.OK);
   }
   /**
    * Web service endpoint to update a single Place entity. 
    * <p>
    * If updated successfully, the persisted Place is returned as JSON with
    * HTTP status 200.
    * <p>
    * If not found, the service returns an empty response body and HTTP status
    * 404.
    * <p>
    * If not updated successfully, the service returns an empty response body
    * with HTTP status 500.
    *
    * @param place The Place object to be updated.
    * @return A ResponseEntity containing a single Place object, if updated
    * successfully, and a HTTP status code as described in the method
    * comment.
    */
   @RequestMapping(value="/{id}", method=RequestMethod.PUT,
           headers = "Accept=application/json", produces = {"application/json"})
   public ResponseEntity<?> updatePlace(@PathVariable int id, @RequestBody Place place) {
       logger.info("> updatePlace id:{}", place.getId());

       if (placeService.findOne(id) == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        
       Place updatedPlace = placeService.update(place);

       logger.info("< updatePlace id:{}", place.getId());
       
       return new ResponseEntity<>(updatedPlace, HttpStatus.OK);
   }
}
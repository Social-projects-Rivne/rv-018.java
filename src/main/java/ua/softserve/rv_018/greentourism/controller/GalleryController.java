package ua.softserve.rv_018.greentourism.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.softserve.rv_018.greentourism.config.authentication.TokenAuthenticationUtil;
import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.GalleryService;

@RequestMapping(value = "/api/gallery")
@Controller
public class GalleryController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private TokenAuthenticationUtil tokenUtil;
	/**
     * Web service endpoint to create Gallery entity.
     * The service returns the created Gallery entity url.
     *
     * @return A ResponseEntity containing a url of created Gallery.
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
    public ResponseEntity<?> createGallery(@RequestHeader("Authorization") String authorization, @RequestBody Gallery gallery) {
    	logger.info("> createGallery");
    	
    	User user = tokenUtil.getUserFromHeader(authorization);
		if (gallery.getPlace() != null) {
			Place place = gallery.getPlace();
			place.setUser(user);
			gallery.setPlace(place);
		} else {
			Event event = gallery.getEvent();
			event.setUser(user);
			gallery.setEvent(event);
		}
    	
        Gallery savedGallery = galleryService.create(gallery);
        
        if (gallery.getTableType() == "place");
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedGallery.getId()).toUri());
        
        logger.info("< savedGallery");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}

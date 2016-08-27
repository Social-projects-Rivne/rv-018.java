package ua.softserve.rv_018.greentourism.controller;

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

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.service.EventService;

@RequestMapping(value = "/api/event")
@Controller
public class EventController {
	
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
}

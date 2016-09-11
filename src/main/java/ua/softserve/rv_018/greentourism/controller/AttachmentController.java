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

import ua.softserve.rv_018.greentourism.model.Attachment;
import ua.softserve.rv_018.greentourism.service.AttachmentService;

@RequestMapping(value = "/api/attachment")
@Controller
public class AttachmentController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AttachmentService attachmentService;
	/**
     * Web service endpoint to create Attachment entity.
     * The service returns the created Attachment entity url.
     *
     * @return A ResponseEntity containing a url of created Attachment.
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
    public ResponseEntity<?> createAttachment(@RequestBody Attachment attachment) {
    	logger.info("> createPlace");
    
        Attachment savedAttachment = attachmentService.create(attachment);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedAttachment.getId()).toUri());
        
        logger.info("< savedAttachment");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}

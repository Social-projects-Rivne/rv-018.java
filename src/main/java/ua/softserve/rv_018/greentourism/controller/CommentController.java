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
import ua.softserve.rv_018.greentourism.model.Comment;
import ua.softserve.rv_018.greentourism.service.CommentService;

@RequestMapping(value = "/api/comment")
@Controller
public class CommentController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentService commentService;
	
	/**
     * Web service endpoint to create Comment entity.
     * The service returns the created Comment entity url.
     *
     * @return A ResponseEntity containing a url of created Comment.
     */
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
	public ResponseEntity<?> createComment(@RequestBody Comment comment) {
		logger.info("> createComment");
	    
        Comment savedComment = commentService.create(comment);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedComment.getId()).toUri());
        
        logger.info("< savedComment");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}
}

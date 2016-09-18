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

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.service.CommentItemService;

@RequestMapping(value = "/api/comment_item")
@Controller
public class CommentItemController {
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentItemService commentItemService;
	/**
     * Web service endpoint to create CommentItem entity.
     * The service returns the created CommentItem entity url.
     *
     * @return A ResponseEntity containing a url of created CommentItem.
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = {"application/json" })
    public ResponseEntity<?> createCommentItem(@RequestBody CommentItem commentItem) {
    	logger.info("> createCommentItem");
    
        CommentItem savedCommentItem = commentItemService.create(commentItem);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        	.buildAndExpand(savedCommentItem.getId()).toUri());
        
        logger.info("< savedCommentItem");
    
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}

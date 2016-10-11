package ua.softserve.rv_018.greentourism.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The UserController class is a RESTful web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>
 * which, by default, contains a ResponseEntity converted into JSON with an
 * associated HTTP status code.
 *
 * Created by Administrator on 6/24/2016.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /**
     * The RoleService business service.
     */
    @Autowired
    private UserService userService;

    /**
     * Web service endpoint to fetch a single User entity by primary key
     * identifier.
     * <p>
     * If found, the User is returned as JSON with HTTP status 302.
     * <p>
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     *
     * @param id A Long URL path variable containing the User primary key
     *           identifier.
     * @return A ResponseEntity containing a single User object, if found,
     * and a HTTP status code as described in the method comment.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        logger.info("> getUser id:{}", id);

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getUser id:{}", id);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Web service endpoint to fetch all User entities. The service returns
     * the collection of User entities as JSON.
     * @return 
     *
     * @return A ResponseEntity containing a Collection of User objects.
     */
    @RequestMapping(method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getUsers() {
    	
    	logger.info("> getUsers");
    	
        Collection<User> users = userService.findAll();
        
        logger.info("< getUsers");
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Web service endpoint to create a single User entity. The HTTP request
     * body is expected to contain a User object in JSON format. The
     * User is persisted in the data repository.
     * <p>
     * If created successfully, the persisted User is returned as JSON with
     * HTTP status 201.
     * <p>
     * If not created successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param user The User object to be created.
     * @return A ResponseEntity containing a single User object, if created
     * successfully, and a HTTP status code as described in the method
     * comment.
     */
    @RequestMapping(method = RequestMethod.POST,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletRequest request) {
        logger.info("> createUser");
        
        String domain = request.getHeader("host");
        
        User savedUser = userService.create(user, domain); 
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri());

        logger.info("< createUser");
        
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     * Web service endpoint to update a single User entity. The HTTP request
     * body is expected to contain a Role object in JSON format. The
     * Role is updated in the data repository.
     * <p>
     * If updated successfully, the persisted User is returned as JSON with
     * HTTP status 200.
     * <p>
     * If not found, the service returns an empty response body and HTTP status
     * 404.
     * <p>
     * If not updated successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param user The User object to be updated.
     * @return A ResponseEntity containing a single User object, if updated
     * successfully, and a HTTP status code as described in the method
     * comment.
     */
    @RequestMapping(value="/{id}", method=RequestMethod.PUT,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("> updateUser id:{}", user.getId());

        if (userService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        User updatedUser = userService.update(user);

        logger.info("< updateUser id:{}", user.getId());
        
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * Web service endpoint to delete a single User entity. The HTTP request
     * body is empty. The primary key identifier of the User to be deleted
     * is supplied in the URL as a path variable.
     * <p>
     * If deleted successfully, the service returns an empty response body with
     * HTTP status 204.
     * <p>
     * If not deleted successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param id A Long URL path variable containing the User primary key
     *           identifier.
     * @return A ResponseEntity with an empty response body and a HTTP status
     * code as described in the method comment.
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        logger.info("> deleteUser id:{}", id);

        userService.delete(id);

        logger.info("< deleteUser id:{}", id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Web service endpoint to verify a User's Email. The primary key 
     * identifier of the User which Email has to be verified and the confirmation
     * token is supplied in the URL as a path variable.
     * <p>
     * If confirmed successfully, the service redirects User to his profile page
     * <p>
     * If User's id or verification token are invalid,
     * the service returns an empty response body with HTTP status 404.
     *
     * @param token A String URL path variable containing the User primary key and 
     * verification token.
     * @return A ResponseEntity with an empty response body and a HTTP status
     * code as described in the method comment.
     * @throws IOException 
     */
    @RequestMapping(value="/confirmation/{token}", method=RequestMethod.GET,
    		headers = "Accept=application/json", produces = {"application/json"})
    public void confirmEmail(@PathVariable String token, HttpServletResponse resp,
    		                 HttpServletRequest req) throws IOException {
    	logger.info("> confirmEmail token:{}", token);
    	
    	userService.confirmEmail(token);
    	
    	logger.info("< confirmEmail token:{}", token);
    		
    	resp.sendRedirect("http://" + req.getHeader("host") + "/#/profile/" + Long.parseLong(token.substring(0, 4)));
    }
}
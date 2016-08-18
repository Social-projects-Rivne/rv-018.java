package ua.softserve.rv_018.greentourism.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceService;

/**
 * The PlaceController class is a RESTful web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>
 * which, by default, contains a ResponseEntity converted into JSON with an
 * associated HTTP status code.
 *
 * Created by Administrator on 11/08/2016.
 */
@RestController
public class PlaceController {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /**
     * The RoleService business service.
     */
    @Autowired
    private PlaceService placeService;

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
    @RequestMapping(value = "/place/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getPlace(@PathVariable Long id) {
        logger.info("> getPlace id:{}", id);

        Place place = placeService.findOnePlace(id);
        if (place == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getPlace id:{}", id);
        
        return new ResponseEntity<>(place, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(HttpServletResponse response) throws Exception {

      ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

      try {
        BufferedImage image = ImageIO.read(new File("C:/dev/projects/eclipse-projects/GreenTourism3/src/main/webapp/images/Some_Image.jpg"));
        ImageIO.write(image, "jpeg", jpegOutputStream);
      } catch (IllegalArgumentException e) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }

      byte[] imgByte = jpegOutputStream.toByteArray();

      response.setHeader("Cache-Control", "no-store");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("Expires", 0);
      response.setContentType("image/jpeg");
      ServletOutputStream responseOutputStream = response.getOutputStream();
      responseOutputStream.write(imgByte);
      responseOutputStream.flush();
      responseOutputStream.close();
    }
}


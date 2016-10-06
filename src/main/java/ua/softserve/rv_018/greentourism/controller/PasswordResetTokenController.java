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

import ua.softserve.rv_018.greentourism.model.PasswordResetToken;
import ua.softserve.rv_018.greentourism.service.PasswordResetTokenService;

@RestController
@RequestMapping(value = "/user")
public class PasswordResetTokenController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@RequestMapping(value = "/{token}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getToken(@PathVariable String token) {
        logger.info("> getToken string:{}", token);

        PasswordResetToken passwordResetToken = passwordResetTokenService.findeByToken(token);
        if (passwordResetToken == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< passwordResetToken token:{}", token);
        
        return new ResponseEntity<>(passwordResetToken, HttpStatus.OK);
    }

}

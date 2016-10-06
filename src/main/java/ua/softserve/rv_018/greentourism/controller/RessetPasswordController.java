package ua.softserve.rv_018.greentourism.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import ua.softserve.rv_018.greentourism.error.UserNotFoundException;
import ua.softserve.rv_018.greentourism.model.PasswordResetToken;
import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.util.GenericResponse;
import ua.softserve.rv_018.greentourism.util.PasswordDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.softserve.rv_018.greentourism.service.PasswordResetTokenService;
import ua.softserve.rv_018.greentourism.service.SecurityUserService;
import ua.softserve.rv_018.greentourism.service.UserService;

@RequestMapping(value = "/user")
@Controller
public class RessetPasswordController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private SecurityUserService securityUserService ;
    
    public RessetPasswordController() {
        super();
    }
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST,
    		 headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> resetPassword(@RequestBody String email, HttpServletRequest request){
    	
    	User user = userService.findByEmail(email);
    	if (user == null) {
    		throw new UserNotFoundException();
    	}
    	
    	final String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
    	
    	return new ResponseEntity<>("e-mail was sent", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
    	 final String result = securityUserService.validatePasswordResetToken(id, token);
         if (result != null) {
             model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
             return "redirect:/" + locale.getLanguage();
         }
         
         return "/changePassword";
    }
     
     
   /*     return "redirect:/updatePassword.html";*/
    
    
    
    
    @RequestMapping(value = "/savePassword/{token}", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
    } 
    
    
/*    @RequestMapping(value="/savePassword/{token}", method=RequestMethod.PUT,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> updateUser(@PathVariable String password, @RequestBody User user) {
        logger.info("> updateUser id:{}", user.getId());
        
        User updatedUser = userService.changeUserPassword(user, password);;

        logger.info("< updateUser email:{}", user.getEmail());
        
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }*/
    
 // ============== NON-API ============

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/#/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
	
}

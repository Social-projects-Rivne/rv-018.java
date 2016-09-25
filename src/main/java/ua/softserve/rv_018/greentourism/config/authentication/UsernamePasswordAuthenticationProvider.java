package ua.softserve.rv_018.greentourism.config.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

@Component
public class UsernamePasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
	
	@Autowired
	private UserService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

//        User parsedUser = tokenUtil.parseToken(token);
//
//        if (parsedUser == null) {
//            try {
//				throw new Exception("Athentication fails!");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }

        return new User();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		User user = userService.findByEmail(email);
		if (user == null) {
			return null;
		}
		String expectedUserPassword = user.getPassword();
		AUTHORITIES.add(new SimpleGrantedAuthority(user.getRole().getName()));
		if (authentication.getCredentials().equals(expectedUserPassword)) {
			System.out.println("Successfull athentication");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authentication.getName(), authentication.getCredentials(), AUTHORITIES);
			token.setDetails(user.getId());
			return token;
		} else
			System.out.println("Successfull athentication");
			return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
}

package ua.softserve.rv_018.greentourism.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;

@Component
public class UsernamePasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

        User parsedUser = tokenUtil.parseToken(token);

        if (parsedUser == null) {
            try {
				throw new Exception("Athentication fails!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return parsedUser;
	}

}

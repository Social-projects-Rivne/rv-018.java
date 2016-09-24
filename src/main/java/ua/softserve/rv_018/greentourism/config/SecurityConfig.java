package ua.softserve.rv_018.greentourism.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import ua.softserve.rv_018.greentourism.config.authentication.UsernamePasswordAuthenticationEntryPoint;

@ComponentScan("ua.softserve.rv_018.greentourism.config")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsernamePasswordAuthenticationEntryPoint usernamePasswordAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(usernamePasswordAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(usernamePasswordAuthenticationFilter(), LogoutFilter.class);

	}

	@Bean
	public AppAuthenticationFilter usernamePasswordAuthenticationFilter() {
		return new AppAuthenticationFilter();
	}

}

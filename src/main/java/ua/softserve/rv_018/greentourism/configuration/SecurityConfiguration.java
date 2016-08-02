//package ua.softserve.rv_018.greentourism.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	private CsrfTokenRepository csrfTokenRepository() {
//		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//		repository.setSessionAttributeName("_csrf");
//
//		return repository;
//	}
//
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf()
//	     .csrfTokenRepository(csrfTokenRepository());
//		
//		http
//			.csrf().csrfTokenRepository(csrfTokenRepository())
//			.and()
//			.authorizeRequests()
//			.antMatchers("/", "/main").access("hasRole('ADMIN')")
//			.and().formLogin().loginPage("/login")
//			.usernameParameter("ssoId").passwordParameter("password");
////		http.authorizeRequests()
////			.antMatchers("/", "/home").permitAll()
////			.antMatchers("/admin/**").access("hasRole('ADMIN')")
////			.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
////			.and().formLogin().loginPage("/login")
////			.usernameParameter("ssoId").passwordParameter("password")
////			.and().exceptionHandling().accessDeniedPage("/Access_Denied");
//	}
//}

package rgeoroceanu.cardealership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringSecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.antMatcher("/api/**")
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/**").hasAuthority("API")
			.and()
			.httpBasic()
			.realmName("API"); 
		}


	}

	@Configuration
	public static class CmsWebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf()
			.requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login.html"))
			.and()
			.authorizeRequests()
			.antMatchers("/cms**").hasAnyAuthority("CMS,ADMIN")
			.and()
			.formLogin()
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/cms") 
			.loginProcessingUrl("/login")
			.loginPage("/login.html")
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login.html")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true); 
		}

		@Override 
		public void configure(WebSecurity web) throws Exception { 
			web.ignoring().antMatchers("/*.css"); 
			web.ignoring().antMatchers("/*.js"); 
			web.ignoring().antMatchers("/VAADIN/**");
			web.ignoring().antMatchers("/api/v2/api-docs");
			web.ignoring().antMatchers("/api/swagger-resources/**");
			web.ignoring().antMatchers("/api/swagger-ui.html");
			web.ignoring().antMatchers("/api/webjars/**");
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		PasswordEncoder encoder = new BCryptPasswordEncoder(); 
		return encoder; 
	}
}
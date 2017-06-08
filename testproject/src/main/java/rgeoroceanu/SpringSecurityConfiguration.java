package rgeoroceanu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login.html"))
		.and()
		.authorizeRequests() 
		.antMatchers("/cms**").authenticated()
		//.hasRole("ADMIN")
		.and()
		.formLogin()
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/cms") 
		.loginProcessingUrl("/login")
		.loginPage("/login.html")
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Override 
	public void configure(WebSecurity web) throws Exception { 
		web.ignoring().antMatchers("/*.css"); 
		web.ignoring().antMatchers("/*.js"); 
		web.ignoring().antMatchers("/VAADIN/**");
	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		PasswordEncoder encoder = new BCryptPasswordEncoder(); 
		return encoder; 
	}
}
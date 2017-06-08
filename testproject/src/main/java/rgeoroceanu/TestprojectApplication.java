package rgeoroceanu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

import rgeoroceanu.cms.localization.Localizer;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan({"rgeoroceanu.model.business", "rgeoroceanu.model.converter"})
public class TestprojectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestprojectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TestprojectApplication.class, args);
	}
	
	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
	    return dispatcherServlet;
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
	    registration.addUrlMappings("/api/*", "/cms/*");
	    return registration;
	}
	
	@Bean
	@Scope("singleton")
	public Localizer localizer() {
		return Localizer.getInstance();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		ret.setBasename("classpath:localization/loca");
		ret.setDefaultEncoding("UTF-8");
		return ret;
	}
	
	@Bean
	public String version() {
		return "0.0.1-SNAPSHOT";
	}
}

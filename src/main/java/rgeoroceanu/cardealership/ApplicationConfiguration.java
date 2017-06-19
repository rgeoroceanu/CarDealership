package rgeoroceanu.cardealership;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.common.base.Predicate;

import rgeoroceanu.cardealership.cms.localization.Localizer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan({"rgeoroceanu..cardealership.model.business", "rgeoroceanu.cardealership.model.converter"})
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class ApplicationConfiguration {

	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		return dispatcherServlet;
	}

	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.addUrlMappings( "/cms/*", "/api/*");
		return registration;
	}

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setRepositoryDetectionStrategy(RepositoryDetectionStrategies.ANNOTATED);
			}
		};
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

	@Bean
	public Docket api(ServletContext servletContext) { 
		AuthorizationScope[] authScopes = new AuthorizationScope[0];

		SecurityReference securityReference = SecurityReference.builder()
				.reference("API")
				.scopes(authScopes)
				.build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("API"));

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")
				.securitySchemes(auth)
				.securityContexts(securityContexts)
				.apiInfo(apiInfo())
				.select()                                  
				.apis(customRequestHandlers()) 
				.paths(paths())
				.build();                                           
	}

	private Predicate<String> paths() {
		return or(
				regex("/cars.*"),
				regex("/dealership.*"));
	}
	
	private Predicate<RequestHandler> customRequestHandlers() {     
	    return new Predicate<RequestHandler>() {
	        @Override
	        public boolean apply(RequestHandler input) {
	            Set<RequestMethod> methods = input.supportedMethods();
	            return methods.contains(RequestMethod.GET);
	        }
	    };
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Car Dealership API")
				.description("Car Dealership demo API")
				.version("1.0").build();
	}
}

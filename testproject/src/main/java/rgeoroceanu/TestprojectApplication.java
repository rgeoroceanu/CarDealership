package rgeoroceanu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import rgeoroceanu.cms.tools.Localizer;

@SpringBootApplication
public class TestprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestprojectApplication.class, args);
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
}

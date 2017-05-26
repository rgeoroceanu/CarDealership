package rgeoroceanu.model.business;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "config")
@Component
public class Configuration {
	
	private String webServerUrl;
	private String imagesPath;
}

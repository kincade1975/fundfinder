package hr.betaware.fundfinder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "base")
@Getter @Setter
public class BaseProperties {

	private String dateTimeFormat;

}

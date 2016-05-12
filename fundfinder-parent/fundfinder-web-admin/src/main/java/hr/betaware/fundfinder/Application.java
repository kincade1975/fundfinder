package hr.betaware.fundfinder;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import hr.betaware.fundfinder.jpa.SpringSecurityAuditorAware;

@ComponentScan({"hr.betaware.fundfinder"})
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.addListeners(new ApplicationPidFileWriter("application.pid"));
		springApplication.run(args);
	}

	@Bean
	AuditorAware<String> springSecurityAuditorAware() {
		return new SpringSecurityAuditorAware();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}

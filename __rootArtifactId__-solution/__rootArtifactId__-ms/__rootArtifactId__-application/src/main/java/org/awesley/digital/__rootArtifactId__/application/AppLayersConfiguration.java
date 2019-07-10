package org.awesley.digital.__rootArtifactId__.application;

import org.awesley.digital.__rootArtifactId__.application.security.config.WebSecurityConfig;
import org.awesley.digital.__rootArtifactId__.persistence.configuration.PersistenceConfiguration;
import org.awesley.digital.__rootArtifactId__.resources.configuration.ResourcesConfiguration;
import org.awesley.digital.__rootArtifactId__.service.configuration.ServicesConfiguration;
import org.awesley.infra.applicativecontext.ApplicativeContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan()
@Import(value = {
		WebSecurityConfig.class,
		ResourcesConfiguration.class, 
		ServicesConfiguration.class,
		PersistenceConfiguration.class,
		ApplicativeContextConfiguration.class
})
public class AppLayersConfiguration {

}

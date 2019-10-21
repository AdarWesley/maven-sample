package org.awesley.digital.__rootArtifactId__.config;

import org.awesley.digital.__rootArtifactId__.Entity1TestHelper;
import org.awesley.digital.__rootArtifactId__.application.CxfAppConfiguration;
import org.awesley.digital.__rootArtifactId__.application.JWTAuthorizationRequestInterceptor;
import org.awesley.digital.__rootArtifactId__.application.security.config.WebSecurityConfig;
import org.awesley.digital.__rootArtifactId__.resources.configuration.ResourcesConfiguration;
import org.awesley.digital.__rootArtifactId__.service.configuration.ServicesConfiguration;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.applicativecontext.ApplicativeContextConfiguration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class, 
		DataSourceTransactionManagerAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class,
		JtaAutoConfiguration.class,
		TransactionAutoConfiguration.class,
		WebMvcAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class,
		SpringDataWebAutoConfiguration.class
})
@ComponentScan()
//		basePackages = {"org.awesley.digital.login"}, 
//		basePackageClasses = {
//			CxfAppConfiguration.class,
//			AppLayersConfiguration.class
//		}
//	)
@Import(value = {
		CxfAppConfiguration.class,
		WebSecurityConfig.class,
		ResourcesConfiguration.class, 
		ServicesConfiguration.class,
		// PersistenceConfiguration.class,
		ApplicativeContextConfiguration.class
})
public class TestConfiguration {

	@Bean
	public JWTAuthorizationRequestInterceptor jwtAuthorizationRequestInterceptor() {
		return new JWTAuthorizationRequestInterceptor();
	}
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Entity1 entity1() {
		return new Entity1Test(); 
	}
	
	public class Entity1Test implements Entity1 {

		long id;
		String name;
		
		@Override
		public Long getID() {
			return id;
		}

		@Override
		public void setID(Long id) {
			this.id = (id != null)? id : 0L;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(String name) {
			this.name = name;
		}

	}

	@Bean("org.awesley.digital.__rootArtifactId__.Entity1TestHelper")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Entity1TestHelper entity1TestHelper() {
		return new Entity1TestHelper();
	}
}

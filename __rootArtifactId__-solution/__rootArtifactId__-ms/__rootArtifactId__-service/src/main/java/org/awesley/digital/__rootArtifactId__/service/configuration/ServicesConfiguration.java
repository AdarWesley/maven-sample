package org.awesley.digital.__rootArtifactId__.service.configuration;

import org.awesley.digital.__rootArtifactId__.service.implementation.Entity1CreateService;
import org.awesley.digital.__rootArtifactId__.service.implementation.Entity1FindService;
import org.awesley.digital.__rootArtifactId__.service.implementation.Entity1GetService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1CreateService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1FindService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1GetService;
import org.awesley.infra.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

	@Bean
	public IEntity1GetService entity1GetService(){
		return new Entity1GetService();
	}
	
	@Bean
	public IEntity1FindService entity1FindService() {
		return new Entity1FindService();
	}
	
	@Bean
	public IEntity1CreateService entity1CreateService() {
		return new Entity1CreateService();
	}
	
	@Bean
	public JwtTokenUtil jwtTokenUtil(
			@Value("${jwt.secret:secret}") String secret, 
    		@Value("${jwt.expiration:#{60*60*24*7}}") Long expiration,
    		@Value("${jwt.algorithm:}") String algorithm,
    		@Value("${jwt.base64EncodedSigningKey:}") String base64EncodedSigningKey,
    		@Value("${jwt.base64EncodedValidationKey:}") String base64EncodedValidationKey) {
		return new JwtTokenUtil(secret, expiration, algorithm, base64EncodedSigningKey, base64EncodedValidationKey);
	}
}

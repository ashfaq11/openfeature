package com.trinet.openfeature.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;

 
@Configuration
public class Configurations {
	Logger logger = LoggerFactory.getLogger(Configurations.class);

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	
	
	
	
	
	
	@Bean
	FlagdProvider flagdProvider(@Value("${flagd.jsonfile.path}") String path) {
		FlagdOptions options =
		        FlagdOptions.builder()
		            .withGlobalTelemetry(true).offlineFlagSourcePath(path)
		            .resolverType(Config.Resolver.IN_PROCESS)
		            .build();
		return new FlagdProvider(options);
	}
//	@Bean
//	ObjectMapper objectMapper() {
//		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//	}

}

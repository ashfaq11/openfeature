package com.trinet.openfeature.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.ImmutableContext;

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
	FlagdProvider flagdProvider(@Value("${flagd.jsonfile.path}") String path) throws Exception {
		FlagdOptions options = FlagdOptions.builder().withGlobalTelemetry(true).offlineFlagSourcePath(path)
				.resolverType(Config.Resolver.IN_PROCESS).build();
		FlagdProvider flagdProvider = new FlagdProvider(options);
	    EvaluationContext clientCtx = new ImmutableContext();
		flagdProvider.initialize(clientCtx);
		return flagdProvider;
	}

}

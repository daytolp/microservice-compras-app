package com.paymentchain;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}


	@Bean
	public CommandLineRunner openApiGroups(
			RouteDefinitionLocator locator,
			SwaggerUiConfigParameters swaggerUiParameters) {
		return args -> Objects.requireNonNull(locator
						.getRouteDefinitions().collectList().block())
				.stream()
				.map(RouteDefinition::getId)
				.filter(id -> id.matches(".*-service"))
				.map(id -> id.replace("-service", ""))
				.forEach(swaggerUiParameters::addGroup);
	}
}

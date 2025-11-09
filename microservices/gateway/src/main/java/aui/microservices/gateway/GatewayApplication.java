package aui.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("categories", r -> r.path("/api/categories/**")
						.uri("http://localhost:8082"))
				.route("items", r -> r.path("/api/items/**")
						.uri("http://localhost:8081"))
				// przykład: nie wystawiaj wewnętrznych endpointów (brak routingu dla /internal/**)
				.build();
	}

}

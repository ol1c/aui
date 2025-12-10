package aui.microservices.gateway;
import org.springframework.core.env.Environment;

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
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, Environment env) {
		String categoryUrl = env.getProperty("MICROSERVICES_CATEGORY_URL", "http://category-service:8082");
		String itemUrl = env.getProperty("MICROSERVICES_ITEM_URL", "http://item-service:8081");

		return builder.routes()
				.route("categories", r -> r.path("/api/categories/**").uri(categoryUrl))
				.route("items", r -> r.path("/api/items/**").uri(itemUrl))
				.build();
	}

}

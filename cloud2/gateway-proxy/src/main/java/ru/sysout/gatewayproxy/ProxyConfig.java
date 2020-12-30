package ru.sysout.gatewayproxy;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;


//@Configuration
class ProxyConfig {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("random_animal_route",
                        route -> route.path("/random-animal/**")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://random-animal"))
                .route("zoo_route",
                        route -> route.path("/zoo/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://zoo"))
                .build();
    }
}

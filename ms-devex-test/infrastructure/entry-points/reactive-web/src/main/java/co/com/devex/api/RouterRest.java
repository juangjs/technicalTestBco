package co.com.devex.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterRest {
	@Bean
	RouterFunction<ServerResponse> routerFunctionOrders(OrdersHandler ordersHandler) {
		return RouterFunctions.route()
				.GET("/api/devex/orders/getOrder", RequestPredicates.queryParam("orderId", t -> true),
						ordersHandler::getOrderById)    
				.POST("/api/devex/orders/createOrder",ordersHandler::createOrder)
				.PUT("/api/devex/orders/updateOrder", ordersHandler::updateOrder)
				.build();
	}
}

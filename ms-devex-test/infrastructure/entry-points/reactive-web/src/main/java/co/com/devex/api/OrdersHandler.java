package co.com.devex.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.devex.api.mappers.OrderMapper;
import co.com.devex.api.model.request.createOrder.RequestDataReq;
import co.com.devex.api.model.request.updateOrder.OrderUpdateReq;
import co.com.devex.api.validations.ValidatorOrdersRequest;
import co.com.devex.usecase.orders.OrdersUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrdersHandler {
	private final OrdersUseCase ordersUseCase;
	private final ValidatorOrdersRequest validatorOrdersRequest;
	private final OrderMapper orderMapper;
	
	public Mono<ServerResponse> getOrderById(ServerRequest serverRequest) {
		return Mono.just(serverRequest)
				.flatMap(request -> {
					validatorOrdersRequest.validateGetOrderRequest(serverRequest);
					return ordersUseCase.getOrderById(request.queryParam("orderId").get())
							.flatMap(order -> ServerResponse.ok().bodyValue(order));
					})
				.switchIfEmpty(Mono.defer(() -> ServerResponse.badRequest()
						.bodyValue("No existe una orden con el id ".concat(serverRequest.queryParam("orderId").get()))))
				.onErrorResume(err -> ServerResponse.badRequest().bodyValue(err.getMessage()));
	}

	public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(RequestDataReq.class)
				.flatMap(orderMapper::toApiRequestModel)
				.flatMap(ordersUseCase::createOrder)
				.flatMap(order -> ServerResponse.ok().bodyValue(order))
				.onErrorResume(err -> ServerResponse.badRequest().bodyValue(err.getMessage()));
	}
	
	public Mono<ServerResponse> updateOrder(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(OrderUpdateReq.class)
				.flatMap(orderMapper::toOrderUpdateApiModel)
				.flatMap(ordersUseCase::updateOrder)
				.flatMap(order -> ServerResponse.ok().bodyValue(order))
				.onErrorResume(err -> ServerResponse.badRequest().bodyValue(err.getMessage()));
	}

}

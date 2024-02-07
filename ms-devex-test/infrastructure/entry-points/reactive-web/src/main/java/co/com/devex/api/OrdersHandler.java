package co.com.devex.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.devex.api.mappers.OrderMapper;
import co.com.devex.api.model.request.createOrder.RequestDataReq;
import co.com.devex.api.validations.ValidatorOrdersRequest;
import co.com.devex.usecase.orders.OrdersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
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
					return ordersUseCase.selectOrder(request.queryParam("orderId").get())
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
		return ordersUseCase.selectOrder(serverRequest.queryParam("orderId").get())
				.flatMap(order -> ServerResponse.ok().bodyValue(order));
	}

}

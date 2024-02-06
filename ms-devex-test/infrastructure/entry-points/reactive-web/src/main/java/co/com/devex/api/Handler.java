package co.com.devex.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.devex.usecase.orders.OrdersUseCase;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
private final OrdersUseCase ordersUseCase;
//private  final UseCase2 useCase2;

public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
	return ordersUseCase.selectOrder(serverRequest.queryParam("orderId").get())
			.flatMap(order -> ServerResponse.ok().bodyValue(order));
}

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}

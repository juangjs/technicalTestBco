package co.com.devex.usecase.orders;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class OrdersUseCase {
	private final IOrdersRepository iOrdersRepository;
	
	public Mono<Orders> createOrder() {
		return iOrdersRepository.createOrder(null);
	}
	
	public Mono<Orders> updateOrder() {
		return iOrdersRepository.updateOrder(null);
	}
	
	public Mono<Orders> selectOrder() {
		return iOrdersRepository.getOrder(null);
	}
}

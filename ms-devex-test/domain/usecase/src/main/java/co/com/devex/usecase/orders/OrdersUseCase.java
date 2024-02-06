package co.com.devex.usecase.orders;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.model.utils.IUtilMethods;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class OrdersUseCase {
	private final IOrdersRepository iOrdersRepository;
	private final IUtilMethods iUtilMethods;
	
	public Mono<Orders> createOrder() {
		return iOrdersRepository.createOrder(null);
	}
	
	public Mono<Orders> updateOrder() {
		return iOrdersRepository.updateOrder(null);
	}
	
	public Mono<Orders> selectOrder(String orderId) {
		return iOrdersRepository.getOrder(Long.valueOf(orderId));
	}
}

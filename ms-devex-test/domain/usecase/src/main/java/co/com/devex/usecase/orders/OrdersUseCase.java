package co.com.devex.usecase.orders;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.api.createorders.OrderUpdateApi;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.usecase.orders.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class OrdersUseCase {
	private final IOrdersRepository iOrdersRepository;
	private final MappersUseCase mappersUseCase;
	private final ProductsUseCase productsUseCase;
	//private final IUtilMethods iUtilMethods;
	
	public Mono<Orders> createOrder(OrdersApi apiOrders) {
		return mappersUseCase.toCreateOrders(apiOrders)
				.flatMap(iOrdersRepository::saveOrder);
	}
	
	public Mono<Orders> updateOrder(OrderUpdateApi orderUpdateApi) {
		return iOrdersRepository.getOrder(Long.valueOf(orderUpdateApi.getOrderId()))
				.flatMap(order -> {
					order.setState(orderUpdateApi.getState());
					return Mono.just(order);
				}).flatMap(iOrdersRepository::updateOrder);
	}
	
	public Mono<Orders> selectOrder(String orderId) {
		return iOrdersRepository.getOrder(Long.valueOf(orderId));
	}
	
	public Mono<Orders> createProductByOrder(OrdersApi apiOrders) {
		return null;
	}
}

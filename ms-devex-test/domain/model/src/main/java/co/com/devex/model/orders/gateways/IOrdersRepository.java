package co.com.devex.model.orders.gateways;

import co.com.devex.model.orders.Orders;
import reactor.core.publisher.Mono;

public interface IOrdersRepository {
	Mono<Orders> getOrder(String OrderId);
	Mono<Void> updateOrder(Orders orders);
}

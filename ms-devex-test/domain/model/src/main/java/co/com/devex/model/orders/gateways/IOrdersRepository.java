package co.com.devex.model.orders.gateways;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import reactor.core.publisher.Mono;

public interface IOrdersRepository {
	Mono<Orders> getOrder(Long orderId);
	Mono<Orders> updateOrder(Orders orders);
	Mono<Orders> saveOrder(Orders orders);
	Mono<ProductOrders> createProductByOrden(ProductOrders productOrders);
}

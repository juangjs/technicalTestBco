package co.com.devex.model.products.gateways;

import co.com.devex.model.orders.Orders;
import reactor.core.publisher.Mono;

public interface IProductsRepository {
	Mono<Orders> getProdut(String productName);
}

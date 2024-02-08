package co.com.devex.model.orders.gateways;

import java.util.List;

import co.com.devex.model.orders.ProductOrders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductsOrderRepository {
	Mono <ProductOrders> createProductOrders(ProductOrders roductOrders);
	Flux<ProductOrders> saveAllProductsByOrder (List<ProductOrders> productOrders);
}

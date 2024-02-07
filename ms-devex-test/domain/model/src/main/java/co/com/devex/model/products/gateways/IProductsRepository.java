package co.com.devex.model.products.gateways;

import co.com.devex.model.products.Products;
import reactor.core.publisher.Mono;

public interface IProductsRepository {
	Mono<Products> getProdut(String productName);
}

package co.com.devex.model.products.gateways;

import co.com.devex.model.products.Products;
import reactor.core.publisher.Flux;


public interface IProductsRepository {
	Flux<Products> getAllProductsByName(Flux<String> productName);
	Flux<Products> getAllProductsById(Flux<Long> productId);
}

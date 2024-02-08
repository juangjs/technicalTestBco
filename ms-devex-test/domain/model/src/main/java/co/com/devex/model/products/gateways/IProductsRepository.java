package co.com.devex.model.products.gateways;

import co.com.devex.model.orders.api.createorders.ProductOrderApi;
import co.com.devex.model.products.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IProductsRepository {
	Mono<Products> getProdut(String productName);
	Flux<Products> getAllProducts(Flux<String> productName);
	Flux<Products> getAllProduct(Flux<String> productName);
}

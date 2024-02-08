package co.com.devex.usecase.orders.products;

import co.com.devex.model.orders.api.createorders.ProductOrderApi;
import co.com.devex.model.products.Products;
import co.com.devex.model.products.gateways.IProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductsUseCase {
	
	private final IProductsRepository iProductsRepository;
	
	public Mono<Products> getProduct(String productName){
		return iProductsRepository.getProdut(productName);
	}
	
	public Flux<Products> getAllProductByName(Flux<String> productName){
		return iProductsRepository.getAllProducts(productName);
	}
	
	public Flux<Products> getAllProduct(Flux<String> productName){
		return iProductsRepository.getAllProducts(productName);
	}
}

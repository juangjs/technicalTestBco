package co.com.devex.usecase.orders.products;

import java.util.ArrayList;
import java.util.List;

import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.products.Products;
import co.com.devex.model.products.gateways.IProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductsUseCase {
	private final IProductsRepository iProductsRepository;
	
	public Mono<List<Products>> getAllProductByName(OrdersApi ordersApi) {
		ArrayList<String> productNameList = new ArrayList<>();
		ordersApi.getProducts().forEach(p -> productNameList.add(p.getProductName()));
		return iProductsRepository.getAllProductsByName(Flux.fromIterable(productNameList)).collectList();
	}

	public Flux<Products> getAllProductById(Flux<Long> productId){
		return iProductsRepository.getAllProductsById(productId);
	}
}

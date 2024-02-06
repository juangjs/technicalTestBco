package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.products.gateways.IProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductsAdapter implements IProductsRepository {
	
	@Override
	public Mono<Orders> getProdut(String productName) {
		return null;
	}
}

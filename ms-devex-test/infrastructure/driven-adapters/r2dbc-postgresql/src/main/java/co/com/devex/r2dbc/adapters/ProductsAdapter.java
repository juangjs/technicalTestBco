package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.products.Products;
import co.com.devex.model.products.gateways.IProductsRepository;
import co.com.devex.r2dbc.entities.repositories.IProductEntityRepository;
import co.com.devex.r2dbc.helper.Mappers;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ProductsAdapter implements IProductsRepository {
	private final Mappers mappers;
	private final IProductEntityRepository iProductEntityRepository;
	
	@Override
	public Flux<Products> getAllProductsByName(Flux<String> productName) {
		return iProductEntityRepository.findAllProductsByName(productName)
				.map(mappers::toProductsModel);
	}

	@Override
	public Flux<Products> getAllProductsById(Flux<Long> productId) {
		return iProductEntityRepository.findProductByID(productId)
				.map(mappers::toProductsModel);
	}
}

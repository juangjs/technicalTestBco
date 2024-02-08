package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.products.Products;
import co.com.devex.model.products.gateways.IProductsRepository;
import co.com.devex.r2dbc.entities.repositories.IProductEntityRepository;
import co.com.devex.r2dbc.helper.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductsAdapter implements IProductsRepository {
	private final Mappers mappers;
	private final IProductEntityRepository iProductEntityRepository;
	
	@Override
	public Mono<Products> getProdut(String productName) {
		log.info(productName);
		return iProductEntityRepository.findProductByName(productName)
				.map(mappers::toProductsModel)
				.switchIfEmpty(Mono.defer(() ->{
					log.warn("El producto {} no existe",productName);
					return Mono.empty();
				}))
				.onErrorResume(err -> {
					log.error("Error al consultar el producto {}. Info: {}", productName,err.getMessage());
					return Mono.error(err);
				});
	}

	@Override
	public Flux<Products> getAllProducts(Flux<String> productName) {
		return iProductEntityRepository.findAllProductsByName(productName)
				.map(mappers::toProductsModel);
	}

	@Override
	public Flux<Products> getAllProduct(Flux<String> productName) {
		// TODO Auto-generated method stub
		return iProductEntityRepository.findAll().map(mappers::toProductsModel);
	}
}

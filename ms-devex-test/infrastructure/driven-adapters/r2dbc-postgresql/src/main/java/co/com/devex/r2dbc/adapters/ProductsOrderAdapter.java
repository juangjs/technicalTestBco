package co.com.devex.r2dbc.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.gateways.IProductsOrderRepository;
import co.com.devex.r2dbc.entities.repositories.IProductOrdersEntityRepository;
import co.com.devex.r2dbc.helper.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductsOrderAdapter implements IProductsOrderRepository {
	private final IProductOrdersEntityRepository iProductOrdersEntityRepository;
	private final Mappers mappers;

	@Override
	public Mono<ProductOrders> createProductOrders(ProductOrders productOrders) {
		log.info("ProductOrders {}", productOrders.toString());
		return iProductOrdersEntityRepository.save(mappers.toProductOrdersEntity(productOrders))
				.map(mappers::toProductOrdersModel)
				.switchIfEmpty(Mono.defer(()->{
					log.info("Es vacio");
					return Mono.empty();
				}))
				.onErrorResume(err -> {
					log.error(err.getMessage());
					return Mono.error(err);
				});
	}

	@Override
	public Flux<ProductOrders> saveAllProductsByOrder(List<ProductOrders> productOrders) {
		return iProductOrdersEntityRepository
				.saveAll(Flux.fromIterable(mappers.toProductOrdersListEntity(productOrders)))
				.map(mappers::toProductOrdersModel);
	}

	@Override
	public Flux<ProductOrders> findProductsByOrderId(Long orderId) {
		return iProductOrdersEntityRepository.findByOrderId(orderId)
				.map(mappers::toProductOrdersModel);
	}
}

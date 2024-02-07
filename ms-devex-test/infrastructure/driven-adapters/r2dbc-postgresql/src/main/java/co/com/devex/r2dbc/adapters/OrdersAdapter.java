package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.r2dbc.entities.repositories.IOrdersEntityRepository;
import co.com.devex.r2dbc.helper.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrdersAdapter implements IOrdersRepository {
	private final Mappers mappers;
	private final IOrdersEntityRepository iOrdersEntityRepository;

	@Override
	public Mono<Orders> getOrder(Long orderId) {
		return iOrdersEntityRepository.findById(orderId)
				.map(mappers::toOrdersModel)
				.switchIfEmpty(Mono.defer(() ->{
					log.warn("La orden {} no existe",orderId);
					return Mono.empty();
				}))
				.onErrorResume(err -> {
					log.error("Error al consultar orden con id {}. Info: {}", orderId, err.getMessage());
					return Mono.error(err);
				});
	}

	@Override
	public Mono<Orders> saveOrder(Orders orders) {
		return iOrdersEntityRepository.saveOrder(orders)
				.map(mappers::toOrdersModel);
	}

	@Override
	public Mono<ProductOrders> createProductByOrden(ProductOrders productOrders) {
		return iOrdersEntityRepository.saveProductByOrder(productOrders)
				.map(mappers::toProductOrdersModel);
	}

	@Override
	public Mono<Orders> updateOrder(Orders orders) {
		return iOrdersEntityRepository.save(mappers.toOrdersEntity(orders))
				.map(mappers::toOrdersModel);
	}
}

package co.com.devex.r2dbc.adapters;

import org.springframework.stereotype.Component;

import co.com.devex.model.orders.Orders;
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
				.onErrorResume(err -> {
					log.error("Error to find order with orderId {}. Info: {}", orderId, err.getMessage());
					return Mono.error(err);
				});
	}

	@Override
	public Mono<Orders> updateOrder(Orders orders) {
		return null;
	}

	@Override
	public Mono<Orders> createOrder(Orders orders) {
		return null;
	}
}

package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.devex.r2dbc.entities.OrdersEntity;
import reactor.core.publisher.Mono;

public interface IOrdersEntityRepository extends ReactiveCrudRepository<OrdersEntity, Long>{
	Mono<OrdersEntity> findById(@Param("id") Long id);
}

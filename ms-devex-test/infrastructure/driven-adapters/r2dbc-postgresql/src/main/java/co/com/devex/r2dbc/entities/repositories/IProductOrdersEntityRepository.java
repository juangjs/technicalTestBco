package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.devex.r2dbc.entities.ProductOrdersEntity;
import reactor.core.publisher.Flux;

public interface IProductOrdersEntityRepository extends ReactiveCrudRepository<ProductOrdersEntity, Long>{
	
	Flux<ProductOrdersEntity> findByOrderId(@Param("orderId") int orderId);

}

package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import co.com.devex.r2dbc.entities.ProductsEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IProductEntityRepository extends ReactiveCrudRepository<ProductsEntity, Long>{
	
	Mono<ProductsEntity> findProductByName(@Param("name") Long name);
	
	@Query("select * from public.tbl_producto")
	Flux<ProductsEntity> findAllProducts();
}

package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.devex.r2dbc.entities.ProductsEntity;
import reactor.core.publisher.Flux;

public interface IProductEntityRepository extends ReactiveCrudRepository<ProductsEntity, Long>{
	
	@Query("select * from public.tbl_producto A where A.id in (:productId)")
	Flux<ProductsEntity> findProductByID(Flux<Long> productId);
	
	@Query("select * from public.tbl_producto A where A.nombre in (:productName)")
	Flux<ProductsEntity> findAllProductsByName(Flux<String> productName);
}

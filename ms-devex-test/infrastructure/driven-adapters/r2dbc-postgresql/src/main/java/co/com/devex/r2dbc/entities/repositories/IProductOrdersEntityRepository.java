package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


import co.com.devex.model.orders.ProductOrders;
import co.com.devex.r2dbc.entities.ProductOrdersEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductOrdersEntityRepository extends ReactiveCrudRepository<ProductOrdersEntity, Long>{
	
	Flux<ProductOrdersEntity> findByOrderId(@Param("orderId") int orderId);
	
	@Query("insert into tbl_pedidos_productos (id,fk_pedido,fk_producto,cantidad,total)"
			+ " values(nextval('tbl_pedidos_productos_id_seq1'),:#{#orderId.getOrderId()},:#{#orderId.getProductId()},:#{#orderId.getProductQuantity()},:#{#orderId.getTotalCost()})"
			+ " RETURNING id,fk_pedido,fk_producto,cantidad,total")
	Mono<ProductOrdersEntity> saveByProductOrders(@Param("orderId") ProductOrders orderId);
	
	/*@Query("insert into tbl_pedidos_productos (id,fk_pedido,fk_producto,cantidad,total)"
			+ " values(nextval('tbl_pedidos_productos_id_seq1'),:#{#orderId.getOrderId()},:#{#orderId.getProductId()},:#{#orderId.getProductQuantity()},:#{#orderId.getTotalCost()})"
			+ " RETURNING id,fk_pedido,fk_producto,cantidad,total")
	Flux<ProductOrdersEntity> saveAllProductByOrders(Flux<ProductOrders> orderId);*/
	
	/*@Query("insert into tbl_pedidos_productos (id,fk_pedido,fk_producto,cantidad,total)"
			+ " values (:productOrders)"
			+ " RETURNING id,fk_pedido,fk_producto,cantidad,total")
	Flux<ProductOrdersEntity> saveAllProductByOrders(Flux<ProductOrdersEntity> productOrders);*/

}

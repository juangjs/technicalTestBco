package co.com.devex.r2dbc.entities.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.devex.model.orders.Orders;
import co.com.devex.r2dbc.entities.OrdersEntity;
import co.com.devex.r2dbc.entities.ResumeOrderEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrdersEntityRepository extends ReactiveCrudRepository<OrdersEntity, Long> {
	
	Mono<OrdersEntity> findById(@Param("id") Long id);

	@Query("insert into tbl_pedido (id,fecha_creacion,fecha_modificacion,estado,detalle,nombre_cliente,documento_cliente,tipo_documento_cliente,"
			+ " telefono_cliente,correo_cliente,direccion_cliente,fk_usuario_comercial)"
			+ " values (nextval('tbl_pedido_id_seq'),:#{#order.getCreateDate()},:#{#order.getUpdateDate()},:#{#order.getState()},:#{#order.getDetail()},:#{#order.getCustomerName()},:#{#order.getCustomerDocument()},:#{#order.getCustomerDocumentType()},"
			+ " :#{#order.getCustomerPhone()},:#{#order.getCustomerMail()},:#{#order.getCustomerAddress()},:#{#order.getUserId()}) RETURNING id,fecha_creacion,fecha_modificacion,estado,detalle,nombre_cliente,documento_cliente,tipo_documento_cliente,telefono_cliente,correo_cliente,direccion_cliente,fk_usuario_comercial")
	Mono<OrdersEntity> saveOrder(@Param("order") Orders order);
	
	
	@Query("select C.nombre,C.precio,A.cantidad,A.total "
			+ "from tbl_pedidos_productos A "
			+ "inner join tbl_producto C "
			+ "on C.id = A.fk_producto "
			+ "where A.fk_pedido = :orderId")
	Flux<ResumeOrderEntity> getAllProductbyOrder(@Param("orderId") Orders orderId);
}

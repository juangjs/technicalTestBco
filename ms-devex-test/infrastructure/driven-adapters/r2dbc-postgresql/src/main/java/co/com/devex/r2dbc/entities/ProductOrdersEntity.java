package co.com.devex.r2dbc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "public.tbl_pedidos_productos")
public class ProductOrdersEntity {
	@Id
	@Column(value = "id")
	private Long id;
	
	@Column(value = "fk_pedido")
	private Long orderId;
	
	@Column(value = "fk_producto")
	private Long productId;
	
	@Column(value = "cantidad")
	private int productQuantity;
	
	@Column(value = "total")
	private int totalCost;
}

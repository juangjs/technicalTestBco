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
@Table(value = "public.tbl_producto")
public class ProductsEntity {
	@Id
	@Column(value = "id")
	private Long id;
	
	@Column(value = "nombre")
	private String name;
	
	@Column(value = "descripcion")
	private String description;
	
	@Column(value = "precio")
	private int price;
	
	@Column(value = "referencia")
	private String reference;
	
	@Column(value = "cantidad_stock")
	private int stockquantity;
}

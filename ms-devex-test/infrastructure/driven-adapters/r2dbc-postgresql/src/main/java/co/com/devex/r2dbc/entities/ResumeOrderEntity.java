package co.com.devex.r2dbc.entities;


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
@Table(value = "public.tbl_pedido")
public class ResumeOrderEntity {
	
	@Column(value = "nombre")
	private String productName;
	
	@Column(value = "precio")
	private int price;
	
	@Column(value = "cantidad")
	private int quantity;
	
	@Column(value = "total")
	private int total;
}

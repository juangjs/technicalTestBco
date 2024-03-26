package co.com.devex.model.orders.response.getorder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductOrderDetail {
	private String product;
	private String descripcion;
	private String quantity;
	private String price;
	private String total; 	
}

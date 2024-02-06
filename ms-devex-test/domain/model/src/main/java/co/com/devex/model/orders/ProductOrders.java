package co.com.devex.model.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductOrders {
	private Long id;
	private Long orderId;
	private Long productId;
	private int productQuantity;
	private int totalCost;
}

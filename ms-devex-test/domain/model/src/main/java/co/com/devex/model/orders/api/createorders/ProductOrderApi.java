package co.com.devex.model.orders.api.createorders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductOrderApi {
	private String productName;
	private int quantity;
}

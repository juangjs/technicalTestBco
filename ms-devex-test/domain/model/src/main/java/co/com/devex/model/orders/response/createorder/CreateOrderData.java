package co.com.devex.model.orders.response.createorder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateOrderData {
	private Long orderId;
	private String orderStatus;
}

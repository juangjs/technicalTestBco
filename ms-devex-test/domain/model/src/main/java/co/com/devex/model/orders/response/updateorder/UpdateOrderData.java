package co.com.devex.model.orders.response.updateorder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateOrderData {
	private Long orderId;
	private String newStatus;
}

package co.com.devex.model.orders.response.updateorder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateOrder {
	private UpdateOrderData data;
}

package co.com.devex.model.orders.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Order {
	private OderData data;
}

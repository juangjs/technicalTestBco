package co.com.devex.model.orders.response.getorder;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OderData {
	private OrderDetails description;
	private List<ProductOrderDetail> products;
}

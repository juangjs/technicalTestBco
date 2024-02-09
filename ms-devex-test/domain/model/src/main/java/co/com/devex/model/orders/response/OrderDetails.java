package co.com.devex.model.orders.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrderDetails {
	private String orderID;
	private String customer;
	private String document;
	private String creationDate;
	private String detail;
	private String totalCost;
	private String status;
}

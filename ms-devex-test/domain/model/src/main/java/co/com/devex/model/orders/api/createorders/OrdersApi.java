package co.com.devex.model.orders.api.createorders;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrdersApi {
	private String detail;
	private String customerName;
	private String customerDocument;
	private String customerDocumentType;
	private String customerPhone;
	private String customerMail;
	private String customerAddress;
	private String userId;
	private ArrayList<ProductOrderApi> products;
}

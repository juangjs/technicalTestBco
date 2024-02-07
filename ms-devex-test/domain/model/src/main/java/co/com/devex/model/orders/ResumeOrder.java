package co.com.devex.model.orders;

import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class ResumeOrder {
	private String productName;
	private int price;
	private int quantity;
	private int total;
}

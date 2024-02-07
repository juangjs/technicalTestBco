package co.com.devex.model.products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Products {
	private Long id;
	private String name;
	private String description;
	private int price;
	private String reference;
	private int stockquantity;
}

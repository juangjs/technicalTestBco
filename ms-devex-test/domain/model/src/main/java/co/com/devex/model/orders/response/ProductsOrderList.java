package co.com.devex.model.orders.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductsOrderList {
	private List<ProductOrderDetail> productList;

}

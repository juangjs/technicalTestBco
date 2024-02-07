package co.com.devex.api.model.request.createOrder;

import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationReq {
	@Valid
	@NotNull(message = "object customer required")
	private CustomerReq customer;
	
	@Valid
	@NotNull(message = "object products required")
	private ArrayList<ProductsOrderReq> products;
	
	@Valid
	@NotNull(message = "object user required")
	private UserOrderReq user;
	
	@Valid
	@NotNull(message = "orderDetail user required")
	private String orderDetail;
}

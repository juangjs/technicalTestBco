package co.com.devex.api.model.request.updateOrder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrderUpdateReq {
	@Valid
	@NotNull(message = "orderId required")
	@NotEmpty(message = "orderId not empty")
	@NotBlank(message = "orderId not blank")
	private String orderId;
	
	@Valid
	@NotNull(message = "customerPhone required")
	@NotEmpty(message = "customerName not empty")
	@NotBlank(message = "customerName not blank")
	private String State;
}

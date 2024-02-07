package co.com.devex.api.model.request.createOrder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductsOrderReq {
	@Valid
	@NotNull(message = "productName required")
	private String productName;
	
	@Valid
	@NotNull(message = "quantity required")
	@Pattern(regexp = "\\d", message = "cantidad debe se solo numeros")
	@NotEmpty(message = "customerName not empty")
	@NotBlank(message = "customerName not balnk")
	private String quantity;
}

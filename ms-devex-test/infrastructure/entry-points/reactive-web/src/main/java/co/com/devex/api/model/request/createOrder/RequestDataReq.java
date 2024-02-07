package co.com.devex.api.model.request.createOrder;

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
public class RequestDataReq {
	@Valid
	@NotNull(message = "object data required")
	private OrderCreationReq data;
}

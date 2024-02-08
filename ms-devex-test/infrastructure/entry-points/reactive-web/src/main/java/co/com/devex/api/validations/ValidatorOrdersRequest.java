package co.com.devex.api.validations;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidatorOrdersRequest {
	private final Pattern order = Pattern.compile("\\d");

	public void validateGetOrderRequest(ServerRequest serverRequest) {
		var orderId = serverRequest.queryParam("orderId").get();
		if (!order.matcher(orderId).matches()) {
			throw new IllegalArgumentException("orderID " + orderId + " inválido");
		}
	}
}

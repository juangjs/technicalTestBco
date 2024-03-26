package co.com.devex.usecase.orders;

import java.time.LocalDateTime;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MappersUseCase {

	public Mono<Orders> toCreateOrders(OrdersApi apiOrders){
		return Mono.just(Orders.builder()
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.status("PENDING")
				.detail(apiOrders.getDetail())
				.customerName(apiOrders.getCustomerName())
				.customerDocument(apiOrders.getCustomerDocument())
				.customerDocumentType(apiOrders.getCustomerDocumentType())
				.customerPhone(apiOrders.getCustomerPhone())
				.customerMail(apiOrders.getCustomerMail())
				.customerAddress(apiOrders.getCustomerAddress())
				.userId(apiOrders.getUserId())
				.build());
	}
}

package co.com.devex.api.mappers;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import co.com.devex.api.model.request.createOrder.RequestDataReq;
import co.com.devex.api.model.request.updateOrder.OrderUpdateReq;
import co.com.devex.model.orders.api.createorders.OrderUpdateApi;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.orders.api.createorders.ProductOrderApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
@Log4j2
@Component
@RequiredArgsConstructor
public class OrderMapper {
	
	
	public Mono<OrdersApi> toApiRequestModel (RequestDataReq requestDataReq) {
		ArrayList<ProductOrderApi> product = new ArrayList<>();
		
		requestDataReq.getData().getProducts().forEach(p -> product.add(ProductOrderApi.builder()
				.productName(p.getProductName())
				.quantity(Integer.parseInt(p.getQuantity()))
				.build()));
		
		return Mono.just(OrdersApi.builder()
				.detail(requestDataReq.getData().getOrderDetail())
				.customerName(requestDataReq.getData().getCustomer().getCustomerName())
				.customerDocument(requestDataReq.getData().getCustomer().getCustomerDocument())
				.customerDocumentType(requestDataReq.getData().getCustomer().getCustomerDocumentType())
				.customerPhone(requestDataReq.getData().getCustomer().getCustomerPhone())
				.customerMail(requestDataReq.getData().getCustomer().getCustomerMail())
				.customerAddress(requestDataReq.getData().getCustomer().getCustomerAddress())
				.userId(requestDataReq.getData().getUser().getDocumentUser())
				.products(product)
				.build());
	}
	
	public Mono<OrderUpdateApi> toOrderUpdateApiModel(OrderUpdateReq orderUpdateReq){
		return Mono.just(OrderUpdateApi.builder()
				.orderId(orderUpdateReq.getOrderId())
				.state(orderUpdateReq.getState())
				.build());
	}
}

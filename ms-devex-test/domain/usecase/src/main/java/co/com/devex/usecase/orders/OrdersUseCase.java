package co.com.devex.usecase.orders;

import java.util.logging.Logger;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.api.createorders.OrderUpdateApi;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.usecase.orders.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class OrdersUseCase {
	private static final Logger log = Logger.getLogger(OrdersUseCase.class.getName());
	private final IOrdersRepository iOrdersRepository;
	private final MappersUseCase mappersUseCase;
	private final ProductsUseCase productsUseCase;
	//private final IUtilMethods iUtilMethods;
	
	public Mono<Orders> createOrder(OrdersApi apiOrders) {
		return mappersUseCase.toCreateOrders(apiOrders)
				.flatMap(iOrdersRepository::saveOrder)
				.flatMap(order -> createProductByOrder(apiOrders,order));
	}
	
	public Mono<Orders> updateOrder(OrderUpdateApi orderUpdateApi) {
		return iOrdersRepository.getOrder(Long.valueOf(orderUpdateApi.getOrderId()))
				.flatMap(order -> {
					order.setState(orderUpdateApi.getState());
					return Mono.just(order);
				}).flatMap(iOrdersRepository::updateOrder);
	}
	
	public Mono<Orders> selectOrder(String orderId) {
		return iOrdersRepository.getOrder(Long.valueOf(orderId));
	}
	
	public Mono<Orders> createProductByOrder(OrdersApi apiOrders, Orders order) {
		/*for (var i=0; i<apiOrders.getProducts().size(); i++) {
			var quantity = apiOrders.getProducts().get(i).getQuantity();
			
			productsUseCase.getProduct(apiOrders.getProducts().get(i).getProductName())
					.flatMap(product -> {
						log.info("product "+product.getName());
						return iOrdersRepository.createProductByOrden(ProductOrders.builder()
								.orderId(order.getId())
								.productId(product.getId())
								.productQuantity(quantity)
								.totalCost(quantity * product.getPrice())
								.build());
					});
		}
		return Mono.just(order);*/
		
		apiOrders.getProducts().forEach(p -> {
			productsUseCase.getProduct(p.getProductName())
			.flatMap(product -> {
				log.info("product" +product);
				return iOrdersRepository.createProductByOrden(ProductOrders.builder()
						.orderId(order.getId())
						.productId(product.getId())
						.productQuantity(p.getQuantity())
						.totalCost(p.getQuantity() * product.getPrice())
						.build());
			});
		});
		return Mono.just(order);
	}
}

package co.com.devex.usecase.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.api.createorders.OrderUpdateApi;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.model.orders.gateways.IProductsOrderRepository;
import co.com.devex.model.products.Products;
import co.com.devex.usecase.orders.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrdersUseCase {
	private static final Logger log = Logger.getLogger(OrdersUseCase.class.getName());
	private final IOrdersRepository iOrdersRepository;
	private final IProductsOrderRepository iProductsOrderRepository;
	private final MappersUseCase mappersUseCase;
	private final ProductsUseCase productsUseCase;
	
	public Mono<Orders> createOrder(OrdersApi ordersApi) {
		return mappersUseCase.toCreateOrders(ordersApi)
				.flatMap(iOrdersRepository::saveOrder)
				.flatMap(order -> {
					return getAllProductInfoByOrder(ordersApi)
							.flatMap(products -> formatProductsByOrder(ordersApi,order,products))
							.flatMap(formatedProducts -> iProductsOrderRepository
									.saveAllProductsByOrder(formatedProducts).collectList())
							.flatMap(productsByList -> Mono.just(order));
				});
		
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
	
	private Mono<List<ProductOrders>> formatProductsByOrder(OrdersApi ordersApi, Orders orders, List<Products> productsList) {
		ArrayList<ProductOrders> formatedProductsList = new ArrayList<>();
		ordersApi.getProducts().forEach(product -> {
			var productId = productsList.stream().filter(p -> p.getName().equals(product.getProductName())).findAny()
					.get().getId();
			var price = productsList.stream().filter(p -> p.getName().equals(product.getProductName())).findAny().get()
					.getPrice();
			var quantity = product.getQuantity();	
			
			formatedProductsList.add(ProductOrders.builder()
					.orderId(orders.getId())
					.productId(productId)
					.productQuantity(quantity)
					.totalCost(quantity * price)
					.build());
		});
        return Mono.just(formatedProductsList);
	}
	
	private Mono<List<Products>> getAllProductInfoByOrder(OrdersApi ordersApi) {
		ArrayList<String> productNameList = new ArrayList<>();
		ordersApi.getProducts().forEach(p -> productNameList.add(p.getProductName()));
		return productsUseCase.getAllProductByName(Flux.fromIterable(productNameList))
				.collectList();
	}
}

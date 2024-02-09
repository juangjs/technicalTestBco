package co.com.devex.usecase.orders;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.api.createorders.OrderUpdateApi;
import co.com.devex.model.orders.api.createorders.OrdersApi;
import co.com.devex.model.orders.gateways.IOrdersRepository;
import co.com.devex.model.orders.response.OderData;
import co.com.devex.model.orders.response.Order;
import co.com.devex.model.orders.response.OrderDetails;
import co.com.devex.model.orders.response.ProductOrderDetail;
import co.com.devex.model.products.Products;
import co.com.devex.usecase.orders.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrdersUseCase {
	private final IOrdersRepository iOrdersRepository;
	private final MappersUseCase mappersUseCase;
	private final ProductsUseCase productsUseCase;
	private final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	public Mono<Order> createOrder(OrdersApi ordersApi) {
		return mappersUseCase.toCreateOrders(ordersApi).flatMap(iOrdersRepository::saveOrder).flatMap(order -> {
			return getAllProductInfoByOrder(ordersApi).flatMap(products -> {
				return formatProductsByOrder(ordersApi, order, products)
						.map(productsByList -> buildProductOrderDetail(products, productsByList))
						.flatMap(productOrderDetail -> buildOrderResponse(order, productOrderDetail));
			});
		});

	}
	
	private Mono<Order> buildOrderResponse(Orders orders, List<ProductOrderDetail> productsByOrderList){
		ArrayList<Integer> totalByProduct = new ArrayList<>();
		productsByOrderList.forEach(p -> totalByProduct.add(Integer.valueOf(p.getTotal())));
		return Mono.just(Order.builder()
				.data(OderData.builder()
						.description(OrderDetails.builder()
								.orderID(Long.toString(orders.getId()) )
								.customer(orders.getCustomerName())
								.document(orders.getCustomerDocument())
								.creationDate(orders.getCreateDate().format(CUSTOM_FORMATTER))
								.detail(orders.getDetail())
								.totalCost(Integer.toString(totalByProduct.stream().mapToInt(Integer::intValue).sum()))
								.status(orders.getState())
								.build())
						.products(productsByOrderList)
						.build())
				.build());
	}
	
	private List<ProductOrderDetail> buildProductOrderDetail(List<Products> products,
			List<ProductOrders> productOrders) {
		ArrayList<ProductOrderDetail> productOrderDetailList = new ArrayList<>();
		productOrders.forEach(p -> productOrderDetailList.add(ProductOrderDetail.builder()
				.product(products.stream().filter(a -> a.getId().equals(p.getProductId())).findFirst().get().getName())
				.descripcion(products
						.stream().filter(a -> a.getId().equals(p.getProductId())).findFirst().get().getDescription())
				.quantity(Integer.toString(p.getProductQuantity()))
				.price(Integer.toString(
						products.stream().filter(a -> a.getId().equals(p.getProductId())).findFirst().get().getPrice()))
				.total(Integer.toString(p.getTotalCost())).build()));
		return productOrderDetailList;
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
			var price = productsList.stream().filter(p -> p.getName().equals(product.getProductName())).findAny().get()
					.getPrice();	
			formatedProductsList.add(ProductOrders.builder()
					.orderId(orders.getId())
					.productId(productsList.stream().filter(p -> p.getName().equals(product.getProductName())).findAny()
							.get().getId())
					.productQuantity(product.getQuantity())
					.totalCost(product.getQuantity() * price)
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

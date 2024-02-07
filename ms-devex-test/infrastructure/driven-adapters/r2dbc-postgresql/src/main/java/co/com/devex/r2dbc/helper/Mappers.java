package co.com.devex.r2dbc.helper;


import org.springframework.stereotype.Component;

import co.com.devex.model.orders.Orders;
import co.com.devex.model.orders.ProductOrders;
import co.com.devex.model.orders.ResumeOrder;
import co.com.devex.model.products.Products;
import co.com.devex.model.users.Users;
import co.com.devex.r2dbc.entities.OrdersEntity;
import co.com.devex.r2dbc.entities.ProductOrdersEntity;
import co.com.devex.r2dbc.entities.ProductsEntity;
import co.com.devex.r2dbc.entities.ResumeOrderEntity;
import co.com.devex.r2dbc.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Component
@RequiredArgsConstructor
public class Mappers {
	
	public Orders toOrdersModel (OrdersEntity ordersEntity) {
		return Orders.builder()
				.id(ordersEntity.getId())
				.createDate(ordersEntity.getCreateDate())
				.updateDate(ordersEntity.getUpdateDate())
				.state(ordersEntity.getState())
				.detail(ordersEntity.getDetail())
				.customerName(ordersEntity.getCustomerName())
				.customerDocument(ordersEntity.getCustomerDocument())
				.customerDocumentType(ordersEntity.getCustomerDocumentType())
				.customerPhone(ordersEntity.getCustomerPhone())
				.customerMail(ordersEntity.getCustomerMail())
				.customerAddress(ordersEntity.getCustomerAddress())
				.userId(ordersEntity.getUserId())
				.build();
	}
	
	public OrdersEntity toOrdersEntity (Orders orders) {
		log.info("OrdersEntity {}", orders.toString());
		return OrdersEntity.builder()
				.id(orders.getId())
				.createDate(orders.getCreateDate())
				.updateDate(orders.getUpdateDate())
				.state(orders.getState())
				.detail(orders.getDetail())
				.customerName(orders.getCustomerName())
				.customerDocument(orders.getCustomerDocument())
				.customerDocumentType(orders.getCustomerDocumentType())
				.customerPhone(orders.getCustomerPhone())
				.customerMail(orders.getCustomerMail())
				.customerAddress(orders.getCustomerAddress())
				.userId(orders.getUserId())
				.build();
	}
	
	public ProductOrders toProductOrdersModel (ProductOrdersEntity productOrdersEntity) {
		return ProductOrders.builder()
				.id(productOrdersEntity.getId())
				.orderId(productOrdersEntity.getOrderId())
				.productId(productOrdersEntity.getProductId())
				.productQuantity(productOrdersEntity.getProductQuantity())
				.totalCost(productOrdersEntity.getTotalCost())
				.build();
	}
	
	public ProductOrdersEntity toProductOrdersEntity(ProductOrders productOrders) {
		return ProductOrdersEntity.builder()
				.id(productOrders.getId())
				.orderId(productOrders.getOrderId())
				.productId(productOrders.getProductId())
				.productQuantity(productOrders.getProductQuantity())
				.totalCost(productOrders.getTotalCost())
				.build();
	}
	
	public Products toProductsModel (ProductsEntity productsEntity){
		log.info("productsEntity.getName() {}",productsEntity.getName());
		return Products.builder()
				.id(productsEntity.getId())
				.name(productsEntity.getName())
				.description(productsEntity.getDescription())
				.price(productsEntity.getPrice())
				.reference(productsEntity.getReference())
				.stockquantity(productsEntity.getStockquantity())
				.build();
	}
	
	public ProductsEntity toProductsEntity (Products products){
		return ProductsEntity.builder()
				.id(products.getId())
				.name(products.getName())
				.description(products.getDescription())
				.price(products.getPrice())
				.reference(products.getReference())
				.stockquantity(products.getStockquantity())
				.build();
	}
	
	public Users toUserModel(UserEntity userEntity) {
		return Users.builder()
				.userDocument(userEntity.getUserDocument())
				.userDocumentType(userEntity.getUserDocumentType())
				.userName(userEntity.getUserName())
				.userPhone(userEntity.getUserPhone())
				.userMail(userEntity.getUserMail())
				.build();
	}
	
	public UserEntity toUserEntity(Users users) {
		return UserEntity.builder()
				.userDocument(users.getUserDocument())
				.userDocumentType(users.getUserDocumentType())
				.userName(users.getUserName())
				.userPhone(users.getUserPhone())
				.userMail(users.getUserMail())
				.build();
	}
	
	public ResumeOrder toResumeOrderModel(ResumeOrderEntity resumeOrderEntity) {
		return ResumeOrder.builder()
				.productName(resumeOrderEntity.getProductName())
				.price(resumeOrderEntity.getPrice())
				.quantity(resumeOrderEntity.getQuantity())
				.total(resumeOrderEntity.getTotal())
				.build();
	}
	
	public ResumeOrderEntity toResumeOrderEntity(ResumeOrder resumeOrder) {
		return ResumeOrderEntity.builder()
				.productName(resumeOrder.getProductName())
				.price(resumeOrder.getPrice())
				.quantity(resumeOrder.getQuantity())
				.total(resumeOrder.getTotal())
				.build();
	}
}

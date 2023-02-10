package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.OrderPaymentTypeEnum;
import global.OrderReceiveMethodEnum;
import global.OrderStatusEnum;
import entity.Order;
import entity.Product;
import model.OrderItemDTO;
import util.Utility;

public class OrderMapper extends AbstractMapper<Order> {

	private static final Logger logger = LogManager.getLogger(OrderMapper.class);

	@Override
	public Order map(ResultSet result) {
		try {
			String orderID = result.getString("id");
			String orderNumber = result.getString("order_number");
			String orderDate = result.getString("order_date");
			String checkoutEmail = result.getString("checkout_email");
			String checkoutFullname = result.getString("checkout_fullname");
			String checkoutPhone = result.getString("checkout_phone");
			String receiverFullname = result.getString("receiver_fullname");
			String receiverPhone = result.getString("receiver_phone");
			String receiverAddress = result.getString("receiver_address");
			OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum.valueOf(result.getString("receive_method"));
			OrderStatusEnum status = OrderStatusEnum.valueOf(result.getString("order_status"));
			double shipping = result.getDouble("shipping");
			double total = result.getDouble("total");
			OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.valueOf(result.getString("payment_type"));
			String paymentDate = result.getString("payment_date");
			String paymentID = result.getString("payment_id");

			return new Order(orderID, orderNumber, orderDate, checkoutEmail, checkoutFullname, checkoutPhone,
					receiverFullname, receiverPhone, receiverAddress, receiveMethod, status, shipping, total,
					paymentType, paymentDate, paymentID);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public PreparedStatement map(PreparedStatement statement, Order object) {
		try {
			statement.setString(1, object.getOrderNumber());
			statement.setString(2, Utility.convertDMYToYMD(object.getOrderDate()));
			statement.setString(3, object.getUserID());
			statement.setString(4, object.getCheckOutEmail());
			statement.setString(5, object.getCheckOutFullname());
			statement.setString(6, object.getCheckOutPhone());
			statement.setString(7, object.getReceiverFullname());
			statement.setString(8, object.getReceiverPhone());
			statement.setString(9, object.getReceiverAddress());
			statement.setString(10, object.getReceiveMethod().toString());
			statement.setString(11, object.getOrderStatus().toString());
			statement.setDouble(12, object.getShippingCost());
			statement.setDouble(13, object.getTotalCost());
			statement.setString(14, object.getPaymentType().toString());
			statement.setString(15, Utility.convertDMYToYMD(object.getPaymentDate()));
			statement.setString(16, object.getPaymentID());

			return statement;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	public OrderItemDTO mapOrderItem(ResultSet result) {
		try {
			String productID = result.getString("product_id");
			String productName = result.getString("product_name");
			String productDescription = result.getString("product_description");
			String productImgSrc = result.getString("product_img_src");
			double price = result.getDouble("price");
			int quantity = result.getInt("quantity");
			Product product = new Product(productID, productName, productDescription, price, productImgSrc);

			return new OrderItemDTO(product, quantity);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public PreparedStatement mapOrderItem(PreparedStatement insertStm, OrderItemDTO item, int orderID) {
		try {
			insertStm.setInt(1, orderID);
			insertStm.setString(2, item.getProduct().getId());
			insertStm.setString(3, item.getProduct().getName());
			insertStm.setString(4, item.getProduct().getDescription());
			insertStm.setString(5, item.getProduct().getImgSrc());
			insertStm.setDouble(6, item.getProduct().getNewPrice());
			insertStm.setInt(7, item.getQuantity());

			return insertStm;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}

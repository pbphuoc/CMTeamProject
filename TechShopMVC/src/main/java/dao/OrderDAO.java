package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;
import entity.Order;
import entity.Product;
import model.OrderItemDTO;
import util.Utility;
import util.Utility.QueryResult;

public class OrderDAO {
	private static final String INSERT_ORDER_SQL = "INSERT INTO orders " + "(order_number, order_date,"
			+ " user_id, checkout_email, checkout_fullname, checkout_phone,"
			+ " receiver_fullname, receiver_phone, receiver_address, receive_method,"
			+ " order_status, shipping, total," + " payment_type, payment_date, payment_fullname, payment_source,"
			+ " billing_fullname, billing_address, billing_phone) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, product_name, product_description, product_img_src, price, quantity) "
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_ORDER_BY_USERID_SQL = "SELECT * FROM orders WHERE user_id = ?;";
	private static final String SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL = "SELECT * FROM orders WHERE checkout_email = ? AND order_number = ?;";
	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";

	private String generateOrderNumber(String date, int second) {
		if (second < 0)
			second = second * -1;
		String first4DigitInSecond = (second * 10000) + "";
		return date + first4DigitInSecond.substring(0, 8);
	}

	private long calculateTotal(String shipping, List<OrderItemDTO> orderItems) {
		long totalCost = 0;
		try {
			totalCost = Long.parseLong(shipping);
			for (OrderItemDTO item : orderItems) {
				totalCost += item.getProduct().getNewPrice() * item.getQuantity();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return totalCost;
	}

	public Order insertOrder(String userID, String checkOutEmail, String checkOutFullname, String checkOutPhone,
			String receiverFullname, String receiverPhone, String receiverAddress, OrderReceiveMethodEnum receiveMethod,
			String shipping, List<OrderItemDTO> orderItems, OrderPaymentTypeEnum paymentType, String paymentDate,
			String paymentFullname, String paymentSource, String billingFullname, String billingAddress,
			String billingPhone) {
		Order order = null;
		PreparedStatement insertStm = null;
		ResultSet generatedKeys = null;
		Connection connection = Utility.getConnection();
		long totalCost = calculateTotal(shipping, orderItems);
		ZoneId z = ZoneId.of("Australia/Sydney");
		ZonedDateTime zdt = ZonedDateTime.now(z);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String orderDate = formatter.format(zdt);
		OrderStatusEnum orderStatus = OrderStatusEnum.RECEIVED;
		String orderNumber = generateOrderNumber("" + zdt.getDayOfMonth() + zdt.getMonthValue() + zdt.getYear(),
				zdt.getNano());
		System.out.println("Order Date: " + formatter.format(zdt));
		try {
			insertStm = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
			int currentParam = 0;
			insertStm.setString(++currentParam, orderNumber);
			insertStm.setString(++currentParam, orderDate);
			insertStm.setString(++currentParam, userID);
			insertStm.setString(++currentParam, checkOutEmail);
			insertStm.setString(++currentParam, checkOutFullname);
			insertStm.setString(++currentParam, checkOutPhone);
			insertStm.setString(++currentParam, receiverFullname);
			insertStm.setString(++currentParam, receiverPhone);
			insertStm.setString(++currentParam, receiverAddress);
			insertStm.setString(++currentParam, receiveMethod.toString());
			insertStm.setString(++currentParam, orderStatus.toString());
			insertStm.setDouble(++currentParam, Double.parseDouble(shipping));
			insertStm.setDouble(++currentParam, totalCost);
			insertStm.setString(++currentParam, paymentType.toString());
			insertStm.setString(++currentParam, paymentDate);
			insertStm.setString(++currentParam, paymentFullname);
			insertStm.setString(++currentParam, paymentSource);
			insertStm.setString(++currentParam, billingFullname);
			insertStm.setString(++currentParam, billingAddress);
			insertStm.setString(++currentParam, billingPhone);
			if (Utility.getResultCode(insertStm.executeUpdate()) == Utility.QueryResult.UNSUCCESSFUL) {
				System.out.println("Insert Order failed");
				return order;
			} else {
				generatedKeys = insertStm.getGeneratedKeys();
				if (generatedKeys.next()) {
					System.out.println("Insert Order successful: " + generatedKeys.getInt(1));
					order = new Order(generatedKeys.getInt(1) + "", orderNumber, orderDate, checkOutEmail,
							checkOutFullname, checkOutPhone, receiverFullname, receiverPhone, receiverAddress,
							receiveMethod, orderStatus, Double.parseDouble(shipping), totalCost, paymentType,
							paymentDate, paymentFullname, paymentSource, billingFullname, billingAddress, billingPhone);
					if (insertOrderItem(generatedKeys.getInt(1), orderItems) == Utility.QueryResult.UNSUCCESSFUL) {
						order = null;
						System.out.println("Insert Order Item failed");
					}
					return order;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			Utility.close(connection, insertStm, generatedKeys);
		}
		return order;
	}

	public Utility.QueryResult insertOrderItem(int orderID, List<OrderItemDTO> orderItems) {
		PreparedStatement insertStm = null;
		Connection connection = Utility.getConnection();
		try {
			insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL, Statement.RETURN_GENERATED_KEYS);
			int batchCount = 0;
			int successCount = 0;
			for (OrderItemDTO item : orderItems) {
				int currentParam = 0;
				insertStm.setInt(++currentParam, orderID);
				insertStm.setString(++currentParam, item.getProduct().getId());
				insertStm.setString(++currentParam, item.getProduct().getName());
				insertStm.setString(++currentParam, item.getProduct().getDescription());
				insertStm.setString(++currentParam, item.getProduct().getImgSrc());
				insertStm.setDouble(++currentParam, item.getProduct().getNewPrice());
				insertStm.setInt(++currentParam, item.getQuantity());
				insertStm.addBatch();
				++batchCount;
				if (batchCount % 1000 == 0 || batchCount == orderItems.size()) {
					successCount = insertStm.executeBatch().length;
					if (successCount != batchCount)
						return Utility.QueryResult.UNSUCCESSFUL;
					else
						batchCount = 0;
				}
			}
			return Utility.QueryResult.SUCCESSFUL;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			Utility.close(connection, insertStm, null);
		}
		return Utility.QueryResult.UNSUCCESSFUL;
	}

	public List<OrderItemDTO> getOrderItemByOrderID(String id) {
		List<OrderItemDTO> items = new ArrayList<OrderItemDTO>();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {
			selectStm = connection.prepareStatement(SELECT_ORDERITEM_BY_ORDERID_SQL);
			selectStm.setString(1, id);
			result = selectStm.executeQuery();
			while (result.next()) {
				String productID = result.getString("product_id");
				String productName = result.getString("product_name");
				String productDescription = result.getString("product_description");
				String productImgSrc = result.getString("product_img_src");
				double price = result.getDouble("price");
				int quantity = result.getInt("quantity");
				Product product = new Product(productID, productName, productDescription, price, productImgSrc);
				items.add(new OrderItemDTO(product, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return items;
	}

	public List<Order> getOrderByUserOrEmailAndOrderNumber(String userID, String email, String orderNum) {
		PreparedStatement selectStm = null;
		List<Order> orders = new ArrayList<Order>();
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {
			if (!userID.equalsIgnoreCase("")) {
				selectStm = connection.prepareStatement(SELECT_ORDER_BY_USERID_SQL);
				selectStm.setString(1, userID);
			} else {
				selectStm = connection.prepareStatement(SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL);
				selectStm.setString(1, email);
				selectStm.setString(2, orderNum);
			}
			System.out.println("query: " + selectStm);
			result = selectStm.executeQuery();
			while (result.next()) {
				String orderID = result.getString("id");
				String orderNumber = result.getString("order_number");
				String orderDate = result.getString("order_date");
				String checkoutEmail = result.getString("checkout_email");
				String checkoutFullname = result.getString("checkout_fullname");
				String checkoutPhone = result.getString("checkout_phone");
				String receiverFullname = result.getString("receiver_fullname");
				String receiverPhone = result.getString("receiver_phone");
				String receiverAddress = result.getString("receiver_address");
				OrderReceiveMethodEnum receiveMethod = OrderReceiveMethodEnum
						.valueOf(result.getString("receive_method"));
				OrderStatusEnum status = OrderStatusEnum.valueOf(result.getString("order_status"));
				double shipping = result.getDouble("shipping");
				double total = result.getDouble("total");
				OrderPaymentTypeEnum paymentType = OrderPaymentTypeEnum.valueOf(result.getString("payment_type"));
				String paymentDate = result.getString("payment_date");
				String paymentFullname = result.getString("payment_fullname");
				String paymentSource = result.getString("payment_source");
				String billingFullname = result.getString("billing_fullname");
				String billingAddress = result.getString("billing_address");
				String billingPhone = result.getString("billing_phone");
				orders.add(new Order(orderID, orderNumber, orderDate, checkoutEmail, checkoutFullname, checkoutPhone,
						receiverFullname, receiverPhone, receiverAddress, receiveMethod, status, shipping, total,
						paymentType, paymentDate, paymentFullname, paymentSource, billingFullname, billingAddress,
						billingPhone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return orders;
	}
}

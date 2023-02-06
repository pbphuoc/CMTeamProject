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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;
import entity.Order;
import entity.Product;
import model.OrderItemDTO;
import util.Utility;

public class OrderDAO {
	private static final String INSERT_ORDER_SQL = "INSERT INTO orders (order_number, order_date,"
			+ " user_id, checkout_email, checkout_fullname, checkout_phone,"
			+ " receiver_fullname, receiver_phone, receiver_address, receive_method,"
			+ " order_status, shipping, total, payment_type, payment_date, payment_id) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, product_name, product_description, product_img_src, price, quantity) "
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_ORDER_BY_USERID_SQL = "SELECT * FROM orders WHERE user_id = ?;";
	private static final String SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL = "SELECT * FROM orders WHERE checkout_email = ? AND order_number = ?;";
	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";
	private static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?;";

	private static final Logger logger = LogManager.getLogger(OrderDAO.class);

	private static OrderDAO orderDAO;

	private OrderDAO() {
	}

	public static OrderDAO getOrderDAO() {
		if (orderDAO == null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}

	private String generateOrderNumber(String paymentID, int nano) {
		if (nano < 0)
			nano = nano * -1;
		StringBuffer sb = new StringBuffer();
		char ch[] = paymentID.substring(paymentID.length() / 2).toCharArray();
		for (int i = 0; i < ch.length; i++) {
			String hexString = Integer.toHexString(ch[i]);
			sb.append(hexString);
		}
		String hexaPaymentID = sb.toString();
		long seed = System.currentTimeMillis();
		Random rng = new Random(seed);
		String result = hexaPaymentID + (nano * rng.nextInt());
		return result.length() > 15 ? result.substring(0, 15) : result;
	}

	public Order insertOrder(String userID, String checkOutEmail, String checkOutFullname, String checkOutPhone,
			String receiverFullname, String receiverPhone, String receiverAddress, OrderReceiveMethodEnum receiveMethod,
			double shipping, double total, List<OrderItemDTO> orderItems, OrderPaymentTypeEnum paymentType,
			String paymentDate, String paymentID) {
		Order order = null;
		PreparedStatement insertStm = null;
		ResultSet generatedKeys = null;
		Connection connection = Utility.getConnection();
		boolean successful = false;
		try {
			ZoneId z = ZoneId.of("Australia/Sydney");
			ZonedDateTime zdt = ZonedDateTime.now(z);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String orderDate = formatter.format(zdt);
			String orderNumber = generateOrderNumber(paymentID, zdt.getNano());
			OrderStatusEnum orderStatus = OrderStatusEnum.RECEIVED;
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
			insertStm.setDouble(++currentParam, shipping);
			insertStm.setDouble(++currentParam, total);
			insertStm.setString(++currentParam, paymentType.toString());
			insertStm.setString(++currentParam, paymentDate);
			insertStm.setString(++currentParam, paymentID);
			logger.debug("Insert Order query: " + insertStm.toString());
			if (Utility.getResultCode(insertStm.executeUpdate()) == Utility.QueryResult.SUCCESSFUL) {
				generatedKeys = insertStm.getGeneratedKeys();
				if (generatedKeys.next()) {
					order = new Order(generatedKeys.getInt(1) + "", orderNumber, orderDate, checkOutEmail,
							checkOutFullname, checkOutPhone, receiverFullname, receiverPhone, receiverAddress,
							receiveMethod, orderStatus, shipping, total, paymentType, paymentDate, paymentID);
					if (insertOrderItem(generatedKeys.getInt(1), orderItems) == Utility.QueryResult.UNSUCCESSFUL) {
						order = null;
					} else {
						successful = true;
					}
				}
			}
			logger.debug("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Number %s - UserID %s - Payer Email %s - Payment Status %s - Items %s",
							orderNumber, userID, checkOutEmail, paymentType, orderItems.toString()));
			return order;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(
					String.format("Insert Order failed" + ": UserID %s - Payer Email %s - Payment Status %s - Items %s",
							userID, checkOutEmail, paymentType, orderItems.toString()));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(
					String.format("Insert Order failed" + ": UserID %s - Payer Email %s - Payment Status %s - Items %s",
							userID, checkOutEmail, paymentType, orderItems.toString()));
		} finally {
			Utility.close(connection, insertStm, generatedKeys);
		}
		return order;
	}

	public Utility.QueryResult insertOrderItem(int orderID, List<OrderItemDTO> orderItems) {
		PreparedStatement insertStm = null;
		Connection connection = Utility.getConnection();
		int[] batchResults = null;
		List<String> executedQueries = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String toString() {
				return super.toString() + System.getProperty("line.separator");
			}
		};
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
				executedQueries.add(insertStm.toString());
				insertStm.addBatch();
				++batchCount;
				if (batchCount % 1000 == 0 || batchCount == orderItems.size()) {
					batchResults = insertStm.executeBatch();
					successCount = batchResults.length;
					if (successCount != batchCount) {
						logger.debug(String.format("Insert Order Item Batch failed: Query %s - Result %s ",
								executedQueries.toString(), Arrays.toString(batchResults)));
						return Utility.QueryResult.UNSUCCESSFUL;
					} else {
						logger.debug(String.format("Insert Order Item Batch successful: Query %s - Result %s ",
								executedQueries.toString(), Arrays.toString(batchResults)));
						batchCount = 0;
						executedQueries.clear();
					}
				}
			}
			return Utility.QueryResult.SUCCESSFUL;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert Order Item Batch failed: Query %s - Result %s ",
					executedQueries.toString(), Arrays.toString(batchResults)));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert Order Item failed: Order ID %s - Order Items %s ", orderID,
					orderItems.toString()));
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
			logger.debug("Get Order Item DTO query: " + selectStm.toString());
			while (result.next()) {
				String productID = result.getString("product_id");
				String productName = result.getString("product_name");
				String productDescription = result.getString("product_description");
				String productImgSrc = result.getString("product_img_src");
				double price = result.getDouble("price");
				int quantity = result.getInt("quantity");
				Product product = new Product(productID, productName, productDescription, price, productImgSrc);
				items.add(new OrderItemDTO(product, quantity));
				logger.debug(String.format(
						"Get Order Item DTO: Product ID %s - Product Name %s - Product Description %s - Product Image Source %s - Product Price %f - Quantity %d",
						productID, productName, productDescription, productImgSrc, price, quantity));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get Order Item DTO failed %s", id));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get Order Item DTO failed %s", id));
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
			logger.debug("Get Order By User || Email && Order Number: " + selectStm.toString());
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
				String paymentID = result.getString("payment_id");
				orders.add(new Order(orderID, orderNumber, orderDate, checkoutEmail, checkoutFullname, checkoutPhone,
						receiverFullname, receiverPhone, receiverAddress, receiveMethod, status, shipping, total,
						paymentType, paymentDate, paymentID));
				logger.debug(String.format(
						"Get Order By User || Email && Order Number: Order Number %s - UserID %s - Payer Email %s - Payment Status %s - Order Status %s",
						orderNum, userID, email, paymentType, status));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format(
					"Get Order By User || Email && Order Number failed Order Number %s - UserID %s - Payer Email %s",
					orderNum, userID, email));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format(
					"Get Order By User || Email && Order Number failed Order Number %s - UserID %s - Payer Email %s",
					orderNum, userID, email));
		} finally {
			Utility.close(connection, selectStm, result);
		}
		return orders;
	}

	public boolean deleteOrder(String id) {
		PreparedStatement deleteStm = null;
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		boolean successful = false;
		try {
			deleteStm = connection.prepareStatement(DELETE_ORDER);
			deleteStm.setString(1, id);
			deleteStm.executeQuery();
			if (Utility.getResultCode(deleteStm.executeUpdate()) == Utility.QueryResult.SUCCESSFUL) {
				successful = true;
			}
			logger.debug("Delete Order " + (successful ? "successful" : "failed") + String.format(": Order ID %s", id));
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Delete Order failed" + String.format(": Order ID %s", id));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error("Delete Order failed" + String.format(": Order ID %s", id));
		} finally {
			Utility.close(connection, deleteStm, result);
		}
		return successful;
	}
}

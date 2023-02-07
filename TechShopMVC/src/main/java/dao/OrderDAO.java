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
			+ " order_status, shipping, total, payment_type) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, product_name, product_description, product_img_src, price, quantity) "
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_ORDER_BY_USERID_SQL = "SELECT * FROM orders WHERE user_id = ?;";
	private static final String SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL = "SELECT * FROM orders WHERE checkout_email = ? AND order_number = ?;";
	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";
	private static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?;";
	private static final String UPDATE_ORDER = "UPDATE orders SET order_status = ?, payment_date = ?, payment_id = ? WHERE id = ?;";

	private static final Logger logger = LogManager.getLogger(OrderDAO.class);

	private static OrderDAO orderDAO;

	private OrderDAO() {
	}

	public static OrderDAO getOrderDAO() {
		if (orderDAO == null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}

	private String generateOrderNumber(int nano) {
		long seed = System.currentTimeMillis();
		Random rng = new Random(seed);
		int result = nano * rng.nextInt() * rng.nextInt();
		if (result < 0)
			result = result * -1;
		String orderNumber = result + "";
		return orderNumber.length() > 15 ? orderNumber.substring(0, 15) : orderNumber;
	}

	public Object[] insertOrder(String userID, Order order, List<OrderItemDTO> orderItems) {
		PreparedStatement insertStm = null;
		ResultSet generatedKeys = null;
		Connection connection = Utility.getConnection();

		List<OrderItemDTO> insertedOrderItems = orderItems;
		boolean successful = false;

		try {
			connection.setAutoCommit(false);
			insertStm = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);

			ZoneId z = ZoneId.of("Australia/Sydney");
			ZonedDateTime zdt = ZonedDateTime.now(z);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String orderDate = formatter.format(zdt);
			String orderNumber = generateOrderNumber(zdt.getNano());

			insertStm = mapInsertOrderData(insertStm, orderNumber, orderDate, userID, order);

			logger.debug("Insert Order query: " + insertStm.toString());
			if (insertStm.executeUpdate() != 0) {
				generatedKeys = insertStm.getGeneratedKeys();
				if (generatedKeys.next()) {

					successful = insertOrderItem(connection, generatedKeys.getInt(1),
							orderItems);
					
					if (successful) {
						order.setId(generatedKeys.getInt(1) + "");
						order.setOrderNumber(orderNumber);
						order.setOrderDate(orderDate);
						insertedOrderItems = orderItems;
						System.out.println("inserted order item: " + insertedOrderItems.toString());
					} else {
						insertedOrderItems = null;
					}
				}
			}

			logger.debug("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));

			return new Object[] { successful, order, insertedOrderItems };
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));
//			Utility.rollBack(connection);
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));
//			Utility.rollBack(connection);
			e.printStackTrace();
		} finally {
			Utility.close(connection, insertStm, generatedKeys);
		}
		return null;
	}

	public PreparedStatement mapInsertOrderData(PreparedStatement insertStm, String orderNumber, String orderDate,
			String userID, Order order) {
		try {
			insertStm.setString(1, orderNumber);
			insertStm.setString(2, orderDate);
			insertStm.setString(3, userID);
			insertStm.setString(4, order.getCheckOutEmail());
			insertStm.setString(5, order.getCheckOutFullname());
			insertStm.setString(6, order.getCheckOutPhone());
			insertStm.setString(7, order.getReceiverFullname());
			insertStm.setString(8, order.getReceiverPhone());
			insertStm.setString(9, order.getReceiverAddress());
			insertStm.setString(10, order.getReceiveMethod().toString());
			insertStm.setString(11, order.getOrderStatus().toString());
			insertStm.setDouble(12, order.getShippingCost());
			insertStm.setDouble(13, order.getTotalCost());
			insertStm.setString(14, order.getPaymentType().toString());
			return insertStm;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public PreparedStatement mapInsertOrderItemData(PreparedStatement insertStm, OrderItemDTO item, int orderID) {
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
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

//	public boolean retryFailedQueries(String failedQuery, OrderItemDTO failedItem, int orderID) {
//		Statement retryStm = null;
//		PreparedStatement minimalDataStm = null;
//		Connection connection = Utility.getConnection();
//		boolean successful = false;
//		String logMessage = "";
//		try {
//			retryStm = connection.createStatement();
//			if (retryStm.executeUpdate(failedQuery) == 0) {
//				minimalDataStm = connection.prepareStatement(INSERT_ORDERITEM_MINIMAL_DATA_SQL);
//				minimalDataStm.setInt(1, orderID);
//				minimalDataStm.setString(2, failedItem.getProduct().getId());
//				minimalDataStm.setDouble(3, failedItem.getProduct().getNewPrice());
//				minimalDataStm.setInt(4, failedItem.getQuantity());
//
//				if (minimalDataStm.executeUpdate() == 0) {
//					logMessage = String.format(
//							"Insert Order Item Batch failed even after Full Data Retry and Minimal Data Try: Query %s - Order Item Detail %s ",
//							failedQuery, failedItem);
//				} else {
//					logMessage = String.format(
//							"Minimal Data Insert Order Item Retry succesful - Still need to update with Product Name, Description and Image Source: Query %s - Order Item Detail %s ",
//							failedQuery, failedItem);
//					successful = true;
//				}
//			} else {
//				logMessage = String.format(
//						"Full Data Insert Order Item Retry succesful: Query %s - Order Item Detail %s ", failedQuery,
//						failedItem);
//				successful = true;
//			}
//			logger.debug(logMessage);
//			return successful;
//		} catch (SQLException e) {
//			logger.error(e.getMessage());
//			logger.error(String.format(
//					"Insert Order Item Batch failed even after Full Data Retry and Minimal Data Try: Query %s - Order Item Detail %s ",
//					failedQuery, failedItem));
//		} catch (NullPointerException e) {
//			logger.error(e.getMessage());
//			logger.error(String.format(
//					"Insert Order Item Batch failed even after Full Data Retry and Minimal Data Try: Query %s - Order Item Detail %s ",
//					failedQuery, failedItem));
//		} finally {
//			Utility.close(retryStm);
//			Utility.close(connection, minimalDataStm, null);
//		}
//		return false;
//	}

	public boolean insertOrderItem(Connection connection, int orderID, List<OrderItemDTO> orderItems) {
		PreparedStatement insertStm = null;
		List<String> allExecutedQueries = new ArrayList<String>();

		try {
			insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL);
			int totalBatchCount = 0;
			for (OrderItemDTO item : orderItems) {
				insertStm = mapInsertOrderItemData(insertStm, item, orderID);
				if (insertStm != null) {
					allExecutedQueries.add(insertStm.toString());
					insertStm.addBatch();
					++totalBatchCount;
					if (totalBatchCount % 1000 == 0 || totalBatchCount == orderItems.size()) {
						insertStm.executeBatch();
					}
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert Order Item Batch failed: Query %s", allExecutedQueries.toString()));
			Utility.rollBack(connection);
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert Order Item Batch failed: Query %s", allExecutedQueries.toString()));
			Utility.rollBack(connection);
			e.printStackTrace();
		} finally {
			Utility.close(null, insertStm, null);
		}

		return false;
	}

	public boolean receiveOrder(Order order) {
		PreparedStatement updateStm = null;
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {
			updateStm = connection.prepareStatement(UPDATE_ORDER);
			updateStm.setString(1, order.getOrderStatus().toString());
			updateStm.setString(2, Utility.convertDMYToYMD(order.getPaymentDate()));
			updateStm.setString(3, order.getPaymentID());
			updateStm.setString(4, order.getId());
			logger.debug("Update Order query: " + updateStm.toString());

			if (updateStm.executeUpdate() == 0) {
				logger.debug(String.format("Update Order Detail failed %s", order.toString()));
				return false;
			} else {
				logger.debug("Update Order Detail succesful");
				return true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Update Order Detail failed %s", order.toString()));
			e.printStackTrace();
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Update Order Detail failed %s", order.toString()));
			e.printStackTrace();
		} finally {
			Utility.close(connection, updateStm, result);
		}
		return false;
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

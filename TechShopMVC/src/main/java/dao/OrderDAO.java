package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Order;
import mapper.OrderMapper;
import model.OrderItemDTO;

public class OrderDAO extends BaseDAO<OrderMapper, Order, OrderDAO> {

	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, product_name, product_description, product_img_src, price, quantity) "
			+ "VALUES (?,?,?,?,?,?,?);";

	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";	

	private static OrderDAO orderDAO;
	private static final Logger logger = LogManager.getLogger(OrderDAO.class);

	private OrderDAO() {
		super(new OrderMapper());
	}

	public static OrderDAO getOrderDAO() {
		if (orderDAO == null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}

	public List<String> getAllOrderNumbers() {
		List<Order> orders = getAll();

		if (orders == null)
			orders = new ArrayList<Order>();

		return orders.stream().map(x -> x.getOrderNumber()).collect(Collectors.toList());
	}

	private String generateOrderNumber(String uniqueID) {
		List<String> existingOrderNumbers = getAllOrderNumbers();

		String orderNumber;
		do {
			long seed = System.currentTimeMillis();
			Random random = new Random(seed);
			orderNumber = uniqueID.substring(uniqueID.length() - 6, uniqueID.length())
					+ String.format("%04d", random.nextInt(10000));

		} while (existingOrderNumbers.contains(orderNumber));

		return orderNumber;
	}

	public boolean insertOrder(Order order, List<OrderItemDTO> orderItems) {
		Connection connection = getConnection();
		boolean successful = false;

		try {
			connection.setAutoCommit(false);

			ZoneId z = ZoneId.of("Australia/Sydney");
			ZonedDateTime zdt = ZonedDateTime.now(z);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String orderDate = formatter.format(zdt);
			String orderNumber = generateOrderNumber(order.getPaymentID());

			order.setOrderNumber(orderNumber);
			order.setOrderDate(orderDate);

			int generatedKey = create(order, connection);

			if (generatedKey != -1) {
				successful = insertOrderItem(connection, generatedKey, orderItems);
				if (successful) {
					order.setId(generatedKey + "");
				}
			}

			logger.debug("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));

			return successful;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));
			return false;
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error("Insert Order " + (successful ? "successful" : "failed")
					+ String.format(": Order Detail %s", order));
			return false;
		} finally {
			close(connection, null, null);
		}
	}

	private void makeItemErrorForTestingRollback(OrderItemDTO item) {
		if (item.getProduct().getId().equals("-1")) {
			item.getProduct().setName(
					"This is a very long text exceeding 100 allowed characters so that this order item cannot be added to DB. All the updates should be rollbacked, transaction is voided and an error message should be displayed to the user. This is a very long text exceeding 100 allowed characters so that this order item cannot be added to DB. All the updates should be rollbacked, transaction is voided and an error message should be displayed to the user. This is a very long text exceeding 100 allowed characters so that this order item cannot be added to DB. All the updates should be rollbacked, transaction is voided and an error message should be displayed to the user");
		}
	}

	public boolean insertOrderItem(Connection connection, int orderID, List<OrderItemDTO> orderItems) {
		PreparedStatement insertStm = null;
		List<String> allExecutedQueries = new ArrayList<String>();

		try {
			insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL);
			int totalBatchCount = 0;

			for (OrderItemDTO item : orderItems) {
				makeItemErrorForTestingRollback(item);
				insertStm = mapper.mapOrderItem(insertStm, item, orderID);

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
			rollBack(connection);
			return false;
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Insert Order Item Batch failed: Query %s", allExecutedQueries.toString()));
			rollBack(connection);
			return false;
		} finally {
			close(null, insertStm, null);
		}
	}

	public List<OrderItemDTO> getOrderItemByOrderID(String orderID) {
		List<OrderItemDTO> items = new ArrayList<OrderItemDTO>();
		PreparedStatement selectStm = null;
		ResultSet result = null;
		Connection connection = getConnection();

		try {
			selectStm = connection.prepareStatement(SELECT_ORDERITEM_BY_ORDERID_SQL);
			selectStm.setString(1, orderID);
			result = selectStm.executeQuery();

			logger.debug("Get Order Item DTO query: " + selectStm.toString());

			while (result.next()) {
				OrderItemDTO orderItem = mapper.mapOrderItem(result);
				items.add(orderItem);

				logger.debug(String.format("Get Order Item DTO: Order Item Detail %s", orderItem.toString()));
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get Order Item DTO failed %s", orderID));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get Order Item DTO failed %s", orderID));
		} finally {
			close(connection, selectStm, result);
		}

		return items;
	}

	public List<Order> findOrder(String userID, String email, String orderNum) {
		List<Order> orders = null;

		try {
			if (!userID.isEmpty()) {
				orders = getBy(new String[] { "user_id" }, new Object[] { userID });
			} else {
				orders = getBy(new String[] { "order_number", "checkout_email" }, new Object[] { orderNum, email });
			}

			if (orders == null)
				orders = new ArrayList<Order>();

			logger.debug(String.format("Find Order %s %s %s %s", (!userID.isEmpty() ? "by User ID " + userID : ""),
					(!email.isEmpty() ? "by Email " + email : ""),
					(!orderNum.isEmpty() ? "by Order Number " + orderNum : ""), orders.toString()));

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(String.format("Find Order %s %s %s %s", (!userID.isEmpty() ? "by User ID " + userID : ""),
					(!email.isEmpty() ? "by Email " + email : ""),
					(!orderNum.isEmpty() ? "by Order Number " + orderNum : ""), orders.toString()));
		}

		return orders;
	}
}

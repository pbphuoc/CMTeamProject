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

import entity.Order;
import entity.Product;
import model.CartItemDTO;
import util.Utility;
import util.Utility.QueryResult;

public class OrderDAO{
	private static final String INSERT_ORDER_SQL = "INSERT INTO orders (order_number, order_date, checkout_email, checkout_fullname, checkout_phone, receiver_fullname, receiver_phone, receiver_address, receive_method_id, payment_type_id, payment_date, order_status_id, shipping, total) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, product_name, product_description, product_img_src, price, quantity) "
			+ "VALUES (?,?,?,?,?,?,?);";
	private static final String SELECT_ORDER_BY_USEREMAIL_SQL = "SELECT * FROM orders WHERE checkout_email = ?;";
	private static final String SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL = "SELECT * FROM orders WHERE checkout_email = ? AND order_number = ?;";	
	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";	
	private static final String SELECT_PAYMENTMETHOD_BY_ID_SQL = "SELECT * FROM payment_type where id = ?;";
	private static final String SELECT_RECEIVEMETHOD_BY_ID_SQL = "SELECT * FROM receive_type where id = ?;";
	private static final String SELECT_STATUS_BY_ID_SQL = "SELECT * FROM order_status where id = ?;";	
	
//	public OrderDAO() {
//		super();
//		connection = getConnection();
//	}	
//	
	
	private String generateOrderNumber(String date, int second) {	
		if (second < 0)
			second = second*-1;
		String first4DigitInSecond = (second*10000) + "";
		return date + first4DigitInSecond.substring(0, 8);
	}
	
	private long calculateTotal(String shipping, List<CartItemDTO> orderItems) {
		long totalCost = 0;
		try {
			totalCost = Long.parseLong(shipping);
			for(CartItemDTO item : orderItems) {
				totalCost += item.getProduct().getNewPrice() * item.getQuantity();
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return totalCost;
	}
	
	public QueryResult insertOrder(String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			String receiveMethodId, String paymentTypeId, String paymentDate, String shipping, List<CartItemDTO> orderItems) {
		PreparedStatement insertStm = null;		
		ResultSet generatedKeys = null;
		Connection connection = Utility.getConnection();
		long totalCost = calculateTotal(shipping, orderItems);
		ZoneId z = ZoneId.of("Australia/Sydney");
		ZonedDateTime zdt = ZonedDateTime.now(z);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		String orderNumber = generateOrderNumber("" + zdt.getDayOfMonth() + zdt.getMonthValue() + zdt.getYear(), zdt.getNano());
		try {			
			insertStm = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);			
			int currentParam = 0;
			insertStm.setString(++currentParam, orderNumber);
			insertStm.setString(++currentParam, formatter.format(zdt));			
			insertStm.setString(++currentParam, checkOutEmail);
			insertStm.setString(++currentParam, checkOutFullname);
			insertStm.setString(++currentParam, checkOutPhone);
			insertStm.setString(++currentParam, receiverFullname);
			insertStm.setString(++currentParam, receiverPhone);
			insertStm.setString(++currentParam, receiverAddress);
			insertStm.setInt(++currentParam, Integer.parseInt(receiveMethodId));
			insertStm.setInt(++currentParam, Integer.parseInt(paymentTypeId));
			insertStm.setString(++currentParam, paymentDate);
			insertStm.setInt(++currentParam, Integer.parseInt(Utility.ORDERSTATUS_RECEIVED));			
			insertStm.setDouble(++currentParam, Double.parseDouble(shipping));
			insertStm.setDouble(++currentParam, totalCost);		
//			System.out.println("query: " + insertStm.toString());
			if(Utility.getResultCode(insertStm.executeUpdate()) == QueryResult.UNSUCCESSFUL)
				return QueryResult.UNSUCCESSFUL;
			else {
				generatedKeys = insertStm.getGeneratedKeys();
				if(generatedKeys.next()) {
//					System.out.println("generated id: " + (int)generatedKeys.getLong(1));
					return insertOrderItem(generatedKeys.getInt(1), orderItems);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, insertStm, generatedKeys);
		}
		return QueryResult.UNSUCCESSFUL;
	}
	
	public QueryResult insertOrderItem(int orderID, List<CartItemDTO> orderItems) {
		PreparedStatement insertStm = null;		
		Connection connection = Utility.getConnection();
		try {			
			insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL, Statement.RETURN_GENERATED_KEYS);
			int batchCount = 0;
			int successCount = 0;
			for(CartItemDTO item: orderItems) {
				int currentParam = 0;
				insertStm.setInt(++currentParam, orderID);
				insertStm.setString(++currentParam, item.getProduct().getId());
				insertStm.setString(++currentParam, item.getProduct().getName());
				insertStm.setString(++currentParam, item.getProduct().getDescription());
				insertStm.setString(++currentParam, item.getProduct().getImgSrc());
				insertStm.setDouble(++currentParam, item.getProduct().getNewPrice());
				insertStm.setInt(++currentParam, item.getQuantity());
//				System.out.println("query: " + insertStm.toString());				
				insertStm.addBatch();
				++batchCount;
	            if (batchCount % 1000 == 0 || batchCount == orderItems.size()) {
	            	successCount = insertStm.executeBatch().length;
	            	if(successCount != batchCount)
	            		return QueryResult.UNSUCCESSFUL;
	            	else
	            		batchCount = 0;
	            }				
			}
			return QueryResult.SUCCESSFUL;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, insertStm, null);
		}
		return QueryResult.UNSUCCESSFUL;
	}	
	
	public List<CartItemDTO> getOrderItemByOrderID(String id){
		List<CartItemDTO> items = new ArrayList<CartItemDTO>();
		PreparedStatement selectStm = null;		
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {			
			selectStm = connection.prepareStatement(SELECT_ORDERITEM_BY_ORDERID_SQL);
			selectStm.setString(1, id);
			result = selectStm.executeQuery();
			while(result.next()) {
				String orderID = result.getInt("order_id") + "";
				String productID = result.getString("product_id");
				String productName = result.getString("product_name");
				String productDescription = result.getString("product_description");
				String productImgSrc = result.getString("product_img_src");
				double price = result.getDouble("price");
				int quantity = result.getInt("quantity");
				Product product = new Product(productID, productName, productDescription, price, productImgSrc);
//				items.add(new OrderItem(orderID, productID, productName, productDescription, productImgSrc, price, quantity));
				items.add(new CartItemDTO(orderID, product, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, selectStm, result);
		}
		return items;		
	}	
	
	public List<Order> getOrderByUserEmail(String email){
		PreparedStatement selectStm = null;
		List<Order> orders = new ArrayList<Order>();
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {			
			selectStm = connection.prepareStatement(SELECT_ORDER_BY_USEREMAIL_SQL);
			selectStm.setString(1, email);
			System.out.println("query: " + selectStm);
			result = selectStm.executeQuery();			
			while(result.next()) {
				String orderNumber = result.getString("order_number");
				String orderDate = result.getString("order_date");
				String checkoutEmail = result.getString("checkout_email");
				String checkoutFullname = result.getString("checkout_fullname");
				String checkoutPhone = result.getString("checkout_phone");
				String receiverFullname = result.getString("receiver_fullname");
				String receiverPhone = result.getString("receiver_phone");
				String receiverAddress = result.getString("receiver_address");
				String receiveMethod = Utility.RECEIVEMETHOD_MAP.get(result.getInt("receive_method_id") + "");
				String paymentType = Utility.PAYMENT_MAP.get(result.getInt("payment_type_id") + "");
				String paymentDate = result.getString("payment_date");
				String status = Utility.ORDERSTATUS_MAP.get(result.getInt("order_status_id") + "");				
				double shipping = result.getDouble("shipping");
				double total = result.getDouble("total");				
				orders.add(new Order(orderNumber, orderDate, checkoutEmail, checkoutFullname, checkoutPhone, receiverFullname, 
						receiverPhone, receiverAddress, receiveMethod, paymentType, paymentDate, status, shipping, total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, selectStm, result);
		}
		return orders;		
	}
	
	public Order getOrderByUserEmailAndOrderNumber(String email, String orderNum){
		PreparedStatement selectStm = null;
		Order order = null;
		ResultSet result = null;
		Connection connection = Utility.getConnection();
		try {			
			selectStm = connection.prepareStatement(SELECT_ORDER_BY_USEREMAIL_AND_ORDERNUMBER_SQL);
			selectStm.setString(1, email);
			selectStm.setString(2, orderNum);
			System.out.println("query: " + selectStm);
			result = selectStm.executeQuery();			
			if(result.next()) {
				String orderNumber = result.getString("order_number");
				String orderDate = result.getString("order_date");
				String checkoutEmail = result.getString("checkout_email");
				String checkoutFullname = result.getString("checkout_fullname");
				String checkoutPhone = result.getString("checkout_phone");
				String receiverFullname = result.getString("receiver_fullname");
				String receiverPhone = result.getString("receiver_phone");
				String receiverAddress = result.getString("receiver_address");
				String receiveMethod = Utility.RECEIVEMETHOD_MAP.get(result.getInt("receive_method_id") + "");
				String paymentType = Utility.PAYMENT_MAP.get(result.getInt("payment_type_id") + "");
				String paymentDate = result.getString("payment_date");
				String status = Utility.ORDERSTATUS_MAP.get(result.getInt("order_status_id") + "");	
				double shipping = result.getDouble("shipping");
				double total = result.getDouble("total");				
				order = new Order(orderNumber, orderDate, checkoutEmail, checkoutFullname, checkoutPhone, receiverFullname, 
						receiverPhone, receiverAddress, receiveMethod, paymentType, paymentDate, status, shipping, total);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			Utility.close(connection, selectStm, result);
		}
		return order;		
	}
}

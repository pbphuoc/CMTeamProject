package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.DAO.DAOType;
import entity.Order;
import entity.OrderItem;

public class OrderDAO extends DAO<Order> {
	private static final String INSERT_ORDER_SQL = "INSERT INTO orders (order_date, checkout_email, checkout_fullname, checkout_phone, receiver_fullname, receiver_phone, receiver_address, receive_method_id, payment_type_id, payment_date, shipping, total) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, price, quantity) "
			+ "VALUES (?,?,?,?);";
	private static final String SELECT_ORDER_BY_USEREMAIL_SQL = "SELECT * FROM orders where checkout_email = ?;";
	private static final String SELECT_ORDERITEM_BY_ORDERID_SQL = "SELECT * FROM order_item where order_id = ?;";	
	private static final String SELECT_PAYMENTMETHOD_BY_ID_SQL = "SELECT * FROM payment_type where id = ?;";
	private static final String SELECT_RECEIVEMETHOD_BY_ID_SQL = "SELECT * FROM receive_type where id = ?;";
	private static final String SELECT_STATUS_BY_ID_SQL = "SELECT * FROM order_status where id = ?;";	
	
//	public OrderDAO() {
//		super();
//		connection = getConnection();
//	}	
//	
	public QueryResult insertOrder(String date, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			String receiveMethodId, String paymentTypeId, String paymentDate, String shipping, String total, Map<String, Integer> orderItems) {
		PreparedStatement insertStm = null;		
		ResultSet generatedKeys = null;
		Connection connection = getConnection();
		try {			
			insertStm = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);			
			int currentParam = 0;
			insertStm.setString(++currentParam, date);			
			insertStm.setString(++currentParam, checkOutEmail);
			insertStm.setString(++currentParam, checkOutFullname);
			insertStm.setString(++currentParam, checkOutPhone);
			insertStm.setString(++currentParam, receiverFullname);
			insertStm.setString(++currentParam, receiverPhone);
			insertStm.setString(++currentParam, receiverAddress);
			insertStm.setInt(++currentParam, Integer.parseInt(receiveMethodId));
			insertStm.setInt(++currentParam, Integer.parseInt(paymentTypeId));
			insertStm.setString(++currentParam, paymentDate);
			insertStm.setDouble(++currentParam, Double.parseDouble(shipping));
			insertStm.setDouble(++currentParam, Double.parseDouble(total));		
//			System.out.println("query: " + insertStm.toString());
			if(getResultCode(insertStm.executeUpdate()) == QueryResult.UNSUCCESSFUL)
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
			close(connection, insertStm, generatedKeys);
		}
		return QueryResult.UNSUCCESSFUL;
	}
	
	public QueryResult insertOrderItem(int orderID, Map<String, Integer> orderItems) {
		PreparedStatement insertStm = null;		
		Connection connection = getConnection();
		try {			
			insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL, Statement.RETURN_GENERATED_KEYS);
			int batchCount = 0;
			int successCount = 0;
			for(Entry<String, Integer> entry: orderItems.entrySet()) {
				int currentParam = 0;
				insertStm.setInt(++currentParam, orderID);
				insertStm.setString(++currentParam, entry.getKey());
				insertStm.setDouble(++currentParam, ((ProductDAO) DAOService.getDAO(DAOType.PRODUCT)).getPriceByProductID(entry.getKey()));
				insertStm.setInt(++currentParam, entry.getValue());
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
			close(connection, insertStm, null);
		}
		return QueryResult.UNSUCCESSFUL;
	}	
	
	public List<OrderItem> getOrderItemByOrderID(String id){
		PreparedStatement selectStm = null;
		List<OrderItem> items = null;
		ResultSet result = null;
		Connection connection = getConnection();
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
				items.add(new OrderItem(orderID, productID, productName, productDescription, productImgSrc, price, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			close(connection, selectStm, result);
		}
		return items;		
	}	
	
	public List<Order> getOrderByUserEmail(String email){
		PreparedStatement selectStm = null;
		List<Order> orders = null;
		ResultSet result = null;
		Connection connection = getConnection();
		try {			
			selectStm = connection.prepareStatement(SELECT_ORDER_BY_USEREMAIL_SQL);
			selectStm.setString(1, email);
			result = selectStm.executeQuery();
			while(result.next()) {
				String id = result.getInt("id") + "";
				String orderDate = result.getString("order_date");
				String checkoutEmail = result.getString("checkout_email");
				String checkoutFullname = result.getString("checkout_fullname");
				String checkoutPhone = result.getString("checkout_phone");
				String receiverFullname = result.getString("receiver_fullname");
				String receiverPhone = result.getString("receiver_phone");
				String receiverAddress = result.getString("receiver_address");
				String receiveMethodID = result.getInt("receive_method_id") + "";
				String paymentTypeID = result.getInt("payment_type_id") + "";
				String paymentDate = result.getString("payment_date");
				String status = result.getInt("status") + "";
				double shipping = result.getDouble("shipping");
				double total = result.getDouble("total");				
				orders.add(new Order(id, orderDate, checkoutEmail, checkoutFullname, checkoutPhone, receiverFullname, 
						receiverPhone, receiverAddress, receiveMethodID, paymentTypeID, paymentDate, status, shipping, total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			close(connection, selectStm, result);
		}
		return orders;		
	}
	
	@Override
	public List<Order> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getRecordByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

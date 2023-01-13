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
import model.Order;
import model.OrderItem;

public class OrderDAO extends DAO<Order> {
	private static final String INSERT_ORDER_SQL = "INSERT INTO orders (order_date, checkout_email, checkout_fullname, checkout_phone, receiver_fullname, receiver_phone, receiver_address, receive_method_id, payment_type_id, payment_date, shipping, total) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_ORDERITEM_SQL = "INSERT INTO order_item (order_id, product_id, price, quantity) "
			+ "VALUES (?,?,?,?);";
	
	private Connection connection;
	
	public OrderDAO() {
		super();
		connection = getConnection();
	}	
	
	public QueryResult insertOrder(String date, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			String receiveMethodId, String paymentTypeId, String paymentDate, String shipping, String total, Map<String, Integer> orderItems) {
		try (PreparedStatement insertStm = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);) {
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
				ResultSet generatedKeys = insertStm.getGeneratedKeys();
				if(generatedKeys.next()) {
//					System.out.println("generated id: " + (int)generatedKeys.getLong(1));
					return insertOrderItem(generatedKeys.getInt(1), orderItems);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return QueryResult.UNSUCCESSFUL;
	}
	
	public QueryResult insertOrderItem(int orderID, Map<String, Integer> orderItems) {
		try (PreparedStatement insertStm = connection.prepareStatement(INSERT_ORDERITEM_SQL, Statement.RETURN_GENERATED_KEYS);) {			
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
		}
		return QueryResult.UNSUCCESSFUL;
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

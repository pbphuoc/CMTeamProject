package dao;

import dao.DAO.DAOType;

public class DAOService {
	public static DAO<?> getDAO(DAOType type) {
		switch (type) {
			case USER:
				return new UserDAO();
			case PRODUCT:
				return new ProductDAO();
			case ORDER:
				return new OrderDAO();				
			default:
				return null;
		}		
	}
}

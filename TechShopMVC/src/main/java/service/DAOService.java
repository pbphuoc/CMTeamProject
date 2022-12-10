package service;

import service.DAO.DAOType;

public class DAOService {
	public static DAO<?> getDAO(DAOType type) {
		switch (type) {
			case USER:
				return new UserDAO();
			case PRODUCT:
				return new ProductDAO();
			default:
				return null;
		}		
	}
}

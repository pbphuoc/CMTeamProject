package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entity.User;
import service.DAO;

public class DAOTest {
	private DAO dao = new DAO();

	
	@Test
	public void insertUserWithUnmatchedEmail() {
		User user = new User("ff.pbphuoc@gmail.com", "Phuoc Pham", "0450028815");
		user.setPassword("default");
		DAO.QueryResult result = dao.insertUser(user);
		Assert.assertEquals(DAO.QueryResult.SUCCESSFUL, result);
		dao.deleteUser(user);
	}
	
	@Test
	public void insertUserWithMatchedEmail() {
		User user = new User("ff.pbphuoc@gmail.com", "Phuoc Pham", "0450028815");
		user.setPassword("default");		
		dao.insertUser(user);
		DAO.QueryResult result = dao.insertUser(user);
		Assert.assertEquals(DAO.QueryResult.DUPLICATE, result);
		dao.deleteUser(user);
	}

}

package service;

import java.util.Iterator;
import java.util.List;

import model.Product;

public class main {
	public static void main(String[] args) {
		CartDAO cartDao = new CartDAO();
		List<Product> cartList = cartDao.getAllRecords();
		for (Product p : cartList) {
			System.out.println(p.getId() + " " + p.getName() +  " "  + p.getNewPrice() + " " + p.getImgSrc());
		
		}	
}
}


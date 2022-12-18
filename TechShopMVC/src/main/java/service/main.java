package service;

import java.util.ArrayList;
import java.util.List;

import model.CartItemDetail;
import model.Product;

public class main {
	public static void main(String[] args) {
		CartItemDetail[] cartTest = {new CartItemDetail(1,1),new CartItemDetail(2,1)};
		CartDAO cartDao = new CartDAO(); 
		List<Product> testList = cartDao.getAllProductInCartByID(cartTest);
		for (Product product : testList) {
			System.out.println(product.getImgSrc());
		}
	}
}

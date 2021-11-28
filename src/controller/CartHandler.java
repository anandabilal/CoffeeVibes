package controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Cart;

public class CartHandler {

	public static void createCart(int productId, int quantity) {
		Cart.createCart(productId, quantity);
	}
	
	public static ResultSet getAllCartWithProductDetail() {
		return Cart.getAllCartWithProductDetail();
	}
	
	public static void deleteCartByProductId(int productId) {
		Cart.deleteCartByProductId(productId);
	}
	
	public static String getCartTotalPrice() {
		return Cart.getCartTotalPrice();
	}
	
	public static int getCartRowCount() {
		return Cart.getCartRowCount();
	}
	
	public static ArrayList<Integer> getAllProductId() {
		return Cart.getAllProductId();
	}
	
	public static ArrayList<Integer> getAllProductQuantity() {
		return Cart.getAllProductQuantity();
	}
	
	public static void deleteAllCart() {
		Cart.deleteAllCart();
	}
	
}

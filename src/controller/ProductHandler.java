package controller;

import java.sql.ResultSet;

import model.Product;

public class ProductHandler {

	public static ResultSet getAllProduct() {
		return Product.getAllProduct();
	}
	
	public static Product getProductById(int productId) {
		return Product.getProductById(productId);
	}
	
	public static void decreaseProductStock(Product product, int quantity) {
		Product.decreaseProductStock(product, quantity);
	}
	
	public static void increaseProductStock(Product product, int quantity) {
		Product.increaseProductStock(product, quantity);
	}
	
	public static void deleteProductByProductId(int productId) {
		Product.deleteProductByProductId(productId);
	}
	
	public static void updateProductById(int id, String name, String description, int stock, int price) {
		Product.updateProductById(id, name, description, stock, price);
	}
	
	public static void createNewProduct(String name, String description, int price, int stock) {
		Product.createNewProduct(name, description, price, stock);
	}
	
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int productId;
	private String name;
	private String description;
	private int price;
	private int stock;
	
	public Product(int productId, String name, String description, int price, int stock) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public static ResultSet getAllProduct() {
		String query = "SELECT * "
				+ "FROM product";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Product getProductById(int productId) {
		String query = "SELECT * "
				+ "FROM product "
				+ "WHERE productId='" + productId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				Product product = new Product(
						rs.getInt("productId"), 
						rs.getString("name"), 
						rs.getString("description"),
						rs.getInt("price"),
						rs.getInt("stock"));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void decreaseProductStock(Product product, int quantity) {
		int currStock = product.getStock() - quantity;
		product.setStock(currStock);
		String query = "UPDATE product "
				+ "SET stock=? "
				+ "WHERE productId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, currStock);
			ps.setInt(2, product.getProductId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void increaseProductStock(Product product, int quantity) {
		int currStock = product.getStock() + quantity;
		product.setStock(currStock);
		String query = "UPDATE product "
				+ "SET stock=? "
				+ "WHERE productId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, currStock);
			ps.setInt(2, product.getProductId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteProductByProductId(int productId) {
		String query = "DELETE FROM product "
				+ "WHERE productId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateProductById(int productId, String name, String description, int stock, int price) {
		String query = "UPDATE product "
				+ "SET name=?, "
				+ "description=?, "
				+ "stock=?, "
				+ "price=? "
				+ "WHERE productId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, stock);
			ps.setInt(4, price);
			ps.setInt(5, productId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewProduct(String name, String description, int price, int stock) {
		String query = "INSERT INTO product (name, description, price, stock) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

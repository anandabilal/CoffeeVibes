package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int productId;
	private int quantity;
	
	public Cart(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static void createCart(int productId, int quantity) {
		String query;
		Cart cart = getCartByProductId(productId);
		if(cart == null) {
			try {
				query = "INSERT INTO cart (productId, quantity) "
						+ "VALUES (?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, productId);
				ps.setInt(2, quantity);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			query = "UPDATE cart "
					+ "SET quantity=? "
					+ "WHERE productId=?";
			int currQuantity = cart.getQuantity() + quantity;
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, currQuantity);
				ps.setInt(2, productId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Cart getCartByProductId(int productId) {
		String query = "SELECT * "
				+ "FROM cart "
				+ "WHERE productId='" + productId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				Cart cart = new Cart(
						rs.getInt("productId"), 
						rs.getInt("quantity"));
				return cart;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getAllCartWithProductDetail() {
		String query = "SELECT "
				+ "p.productId, "
				+ "p.name, "
				+ "c.quantity, "
				+ "p.price, "
				+ "c.quantity * p.price "
				+ "FROM product p "
				+ "INNER JOIN cart c "
				+ "ON p.productId = c.productId "
				+ "GROUP BY productId";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteCartByProductId(int productId) {
		String query = "DELETE FROM cart "
				+ "WHERE productId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCartTotalPrice() {
		String query = "SELECT "
				+ "SUM(c.quantity * p.price) total "
				+ "FROM product p "
				+ "INNER JOIN cart c "
				+ "ON p.productId = c.productId";
		String totalPrice = "";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				totalPrice = rs.getString("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPrice;
	}
	
	public static int getCartRowCount() {
		int rowCount = 0;
		String query = "SELECT COUNT(*)"
				+ "FROM cart";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				rowCount = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public static ArrayList<Integer> getAllProductId() {
		ArrayList<Integer> listProductId = new ArrayList<Integer>();
		String query = "SELECT productId "
				+ "FROM cart";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			while(rs.next()) {
				listProductId.add(rs.getInt("productId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProductId;
	}
	
	public static ArrayList<Integer> getAllProductQuantity() {
		ArrayList<Integer> listProductQty = new ArrayList<Integer>();
		String query = "SELECT quantity "
				+ "FROM cart";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			while(rs.next()) {
				listProductQty.add(rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProductQty;
	}
	
	public static void deleteAllCart() {
		String query = "DELETE FROM cart";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

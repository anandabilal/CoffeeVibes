package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDetail {

	private static Connection con = Database.getInstance().getCon();
	
	private int transactionId;
	private int productId;
	private int quantity;
	
	public TransactionDetail(int transactionId, int productId, int quantity) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
	
	public static ResultSet getTransactionDetailById(int transactionId) {
		String query = "SELECT "
				+ "td.transactionId, "
				+ "p.productId, "
				+ "p.name, "
				+ "td.quantity, "
				+ "p.price, "
				+ "td.quantity * p.price "
				+ "FROM transactiondetail td "
				+ "INNER JOIN product p ON p.productId = td.productId "
				+ "WHERE transactionId='" + transactionId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

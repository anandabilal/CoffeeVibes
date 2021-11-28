package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import controller.CartHandler;

public class Transaction {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int transactionId;
	private int employeeId;
	private int voucherId;
	private Date purchaseDate = new Date();
	private int totalPrice;
	
	public Transaction(int transactionId, int employeeId, int voucherId, Date purchaseDate, int totalPrice) {
		super();
		this.transactionId = transactionId;
		this.employeeId = employeeId;
		this.voucherId = voucherId;
		this.purchaseDate = purchaseDate;
		this.totalPrice = totalPrice;
	}

	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static int getLatestTransactionId() {
		int id = 0;
		String query = "SELECT transactionId "
				+ "FROM transaction "
				+ "ORDER BY transactionId "
				+ "DESC LIMIT 1;";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				id = rs.getInt("transactionId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static void createTransaction(int employeeId, int voucherId, Date purchaseDate, int totalPrice) {
		String query = "INSERT INTO transaction "
				+ "(employeeId, voucherId, purchaseDate, totalPrice) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			if(voucherId != -1) {
				ps.setInt(2, voucherId);
			} else {
				ps.setNull(2, Types.INTEGER);
			}
			ps.setDate(3, (java.sql.Date) purchaseDate);
			ps.setInt(4, totalPrice);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Insert into TransactionDetail
		int transactionId = getLatestTransactionId();
		ArrayList<Integer> listProductId = CartHandler.getAllProductId();
		ArrayList<Integer> listProductQuantity = CartHandler.getAllProductQuantity();
		for(int i = 0; i < CartHandler.getCartRowCount(); i++) {
			String query2 = "INSERT INTO transactiondetail "
					+ "(transactionId, productId, quantity) "
					+ "VALUES (?, ?, ?)";
			try {
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps2.setInt(1, transactionId);
				ps2.setInt(2, listProductId.get(i));
				ps2.setInt(3, listProductQuantity.get(i));
				ps2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ResultSet getAllTransaction() {
		String query = "SELECT "
				+ "t.transactionId, "
				+ "e.employeeId, "
				+ "e.name, "
				+ "IFNULL(t.voucherId, 'NULL'), "
				+ "IFNULL(v.discount, 'NULL'), "
				+ "t.purchaseDate, "
				+ "t.totalPrice "
				+ "FROM transaction t "
				+ "INNER JOIN employee e ON e.employeeId = t.employeeId "
				+ "LEFT JOIN voucher v ON v.voucherId = t.voucherId";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

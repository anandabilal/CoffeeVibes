package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Voucher {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int voucherId;
	private int discount;
	private String status;
	
	public Voucher(int voucherId, int discount, String status) {
		super();
		this.voucherId = voucherId;
		this.discount = discount;
		this.status = status;
	}

	public int getVoucherId() {
		return voucherId;
	}
	
	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static Voucher getVoucherById(String voucherId) {
		String query = "SELECT * "
				+ "FROM voucher "
				+ "WHERE voucherId ='" + voucherId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				Voucher voucher = new Voucher(
						rs.getInt("voucherId"),
						rs.getInt("discount"),
						rs.getString("status"));
				return voucher;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String applyVoucherDiscount(Voucher voucher, int totalPrice) {
		double discountSum = totalPrice * ((double) voucher.getDiscount() / 100);
		double currTotalPrice = totalPrice - discountSum;
		return "" + (int) currTotalPrice;
	}
	
	public static void changeVoucherStatusById(String voucherId) {
		String query = "UPDATE voucher "
				+ "SET status='expired'"
				+ "WHERE voucherId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, voucherId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getLatestVoucherId() {
		int id = 0;
		String query = "SELECT voucherId "
				+ "FROM voucher "
				+ "ORDER BY voucherId "
				+ "DESC LIMIT 1;";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				id = rs.getInt("voucherId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static void createNewVoucher(int discount) {
		String status = "available";
		String query = "INSERT INTO voucher (discount, status) "
				+ "VALUES (?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, discount);
			ps.setString(2, status);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getAllVoucher() {
		String query = "SELECT * "
				+ "FROM voucher";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteVoucherByVoucherId(String voucherId) {
		String query = "DELETE FROM voucher "
				+ "WHERE voucherId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, voucherId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

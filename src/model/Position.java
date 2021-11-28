package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Position {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int positionId;
	private String name;
	
	public Position(int positionId, String name) {
		super();
		this.positionId = positionId;
		this.name = name;
	}

	public int getPositionId() {
		return positionId;
	}
	
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getPositionNameByEmployeeId(int employeeId) {
		String query = "SELECT position.name "
				+ "FROM employee "
				+ "INNER JOIN position "
				+ "ON employee.positionId = position.positionId "
				+ "WHERE employeeId ='" + employeeId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				return rs.getString("position.name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}

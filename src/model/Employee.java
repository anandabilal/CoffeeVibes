package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	
	private static Connection con = Database.getInstance().getCon();
	
	private int employeeId;
	private int positionId;
	private String name;
	private String status;
	private int salary;
	private String username;
	private String password;
	
	public Employee(int employeeId, int positionId, String name, String status, int salary, String username, String password) {
		super();
		this.employeeId = employeeId;
		this.positionId = positionId;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static Employee getEmployeeByUsernameAndPassword(String username, String password) {
		String query = "SELECT * "
				+ "FROM employee "
				+ "WHERE username ='" + username 
				+ "' AND password ='" + password + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				Employee employee = new Employee(
						rs.getInt("employeeId"), 
						rs.getInt("positionId"), 
						rs.getString("name"),
						rs.getString("status"),
						rs.getInt("salary"),
						rs.getString("username"),
						rs.getString("password"));
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Employee getEmployeeById(int employeeId) {
		String query = "SELECT * "
				+ "FROM employee "
				+ "WHERE employeeId='" + employeeId + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				Employee employee = new Employee(
						rs.getInt("employeeId"), 
						rs.getInt("positionId"), 
						rs.getString("name"),
						rs.getString("status"),
						rs.getInt("salary"),
						rs.getString("username"),
						rs.getString("password"));
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean checkUsernameExist(String username) {
		String query = "SELECT * "
				+ "FROM employee "
				+ "WHERE username='" + username + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createNewEmployee(String positionId, String name, String salary, String username, String password) {
		String query = "INSERT INTO employee (positionId, name, status, salary, username, password) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, positionId);
			ps.setString(2, name);
			ps.setString(3, "Working");
			ps.setString(4, salary);
			ps.setString(5, username);
			ps.setString(6, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getAllEmployee() {
		String query = "SELECT * "
				+ "FROM employee";
		try {
			ResultSet rs = con.createStatement().executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteEmployeeByEmployeeId(String employeeId) {
		String query = "DELETE FROM employee "
				+ "WHERE employeeId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmployeeById(String employeeId, String name, String status, String salary, String username, String password) {
		String query = "UPDATE employee "
				+ "SET name=?, "
				+ "status=?, "
				+ "salary=?, "
				+ "username=?, "
				+ "password=? "
				+ "WHERE employeeId=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, status);
			ps.setString(3, salary);
			ps.setString(4, username);
			ps.setString(5, password);
			ps.setString(6, employeeId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

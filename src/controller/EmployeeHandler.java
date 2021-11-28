package controller;

import java.sql.ResultSet;

import model.Employee;

public class EmployeeHandler {
	
	public static Employee getEmployeeByUsernameAndPassword(String username, String password) {
		return Employee.getEmployeeByUsernameAndPassword(username, password);
	}
	
	public static Employee getEmployeeById(int employeeId) {
		return Employee.getEmployeeById(employeeId);
	}
	
	public static boolean checkUsernameExist(String username) {
		return Employee.checkUsernameExist(username);
	}
	
	public static void createNewEmployee(String positionId, String name, String salary, String username, String password) {
		Employee.createNewEmployee(positionId, name, salary, username, password);
	}
	
	public static ResultSet getAllEmployee() {
		return Employee.getAllEmployee();
	}
	
	public static void deleteEmployeeByEmployeeId(String employeeId) {
		Employee.deleteEmployeeByEmployeeId(employeeId);
	}
	
	public static void updateEmployeeById(String employeeId, String name, String status, String salary, String username, String password) {
		Employee.updateEmployeeById(employeeId, name, status, salary, username, password);
	}
	
}

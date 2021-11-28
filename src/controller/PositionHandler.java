package controller;

import model.Position;

public class PositionHandler {
	
	public static String getPositionNameByEmployeeId(int employeeId) {
		return Position.getPositionNameByEmployeeId(employeeId);
	}
	
}

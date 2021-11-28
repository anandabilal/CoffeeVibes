package controller;

import model.Voucher;

public class InputHandler {

	public static int toInt(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			i = Integer.MIN_VALUE;
		}
		return i;
	}
	
	public static String checkProductId(String productId) {
		String error = "";
		if(productId.equals("")) {
			error = "Please pick a product first";
		}
		return error;
	}
	
	public static String checkQuantity(String productId, String qty, String stock) {
		String error = "";
		if(productId.equals("")) {
			error = "Please pick a product first";
		} else if(toInt(stock) == 0) {
			error = "This product is out of stock";
		} else if(qty.equals("")) {
			error = "Quantity cannot be empty";
		} else if(toInt(qty) == Integer.MIN_VALUE) {
			error = "Quantity must be numerical";
		} else if(toInt(qty) < 1) {
			error = "Quantity cannot be less than 1";
		} else if(toInt(qty) > toInt(stock)) {
			error = "Quantity exceeded available stock";
		}
		return error;
	}
	
	public static String checkUpdateProductInput(String productId, String name, String description, String price, String stock) {
		String error = "";
		if(productId.equals("")) {
			error = "Please pick a product first";
		} else if(name.equals("")) {
			error = "Name cannot be empty";
		} else if(description.equals("")) {
			error = "Description cannot be empty";
		} else if(price.equals("")) {
			error = "Product price cannot be empty";
		} else if(toInt(price) == Integer.MIN_VALUE) {
			error = "Product price must be numerical";
		} else if(toInt(price) < 1) {
			error = "Product price cannot be less than 1";
		} else if(stock.equals("")) {
			error = "Product stock cannot be empty";
		} else if(toInt(stock) == Integer.MIN_VALUE) {
			error = "Product stock must be numerical";
		} else if(toInt(stock) < 0) {
			error = "Product stock cannot be less than 0";
		}
		return error;
	}
	
	public static String checkVoucher(Voucher voucher, int tableCartRowCount) {
		String error = "";
		if(tableCartRowCount == 0) {
			error = "Unable to apply voucher on an empty cart";
		} else if(voucher == null) {
			error = "Invalid voucher ID";
		} else if(voucher.getStatus().equalsIgnoreCase("expired")) {
			error = "Voucher ID '" + voucher.getVoucherId() + "' has expired";
		}
		return error;
	}
	
	public static String validateCheckout(int tableCartRowCount) {
		String error = "";
		if(tableCartRowCount == 0) {
			error = "Cannot checkout an empty cart";
		}
		return error;
	}
	
	public static String checkInsertProductInput(String name, String description, String price, String stock) {
		String error = "";
		if(name.equals("")) {
			error = "Product name cannot be empty";
		} else if(description.equals("")) {
			error = "Product description cannot be empty";
		} else if(price.equals("")) {
			error = "Product price cannot be empty";
		} else if(toInt(price) == Integer.MIN_VALUE) {
			error = "Product price must be numerical";
		} else if(toInt(price) < 1) {
			error = "Product price cannot be less than 1";
		} else if(stock.equals("")) {
			error = "Product stock cannot be empty";
		} else if(toInt(stock) == Integer.MIN_VALUE) {
			error = "Product stock must be numerical";
		} else if(toInt(stock) < 0) {
			error = "Product stock cannot be less than 0";
		}
		return error;
	}
	
	public static String checkDiscountInput(String discount) {
		String error = "";
		if(discount.equals("")) {
			error = "Discount rate cannot be empty";
		} else if(toInt(discount) == Integer.MIN_VALUE) {
			error = "Discount rate must be numerical";
		} else if(toInt(discount) < 0 || toInt(discount) > 100) {
			error = "Discount rate must be between 1-100";
		}
		return error;
	}
	
	public static String checkVoucherId(String voucherId) {
		String error = "";
		if(voucherId.equals("")) {
			error = "Please pick a voucher first";
		}
		return error;
	}
	
	public static String checkInsertEmployeeInput(String positionId, String name, String salary, String username, String password) {
		String error = "";
		if(positionId.equals("")) {
			error = "Employee position cannot be empty";
		} else if(name.equals("")) {
			error = "Employee name cannot be empty";
		} else if(salary.equals("")) {
			error = "Employee salary cannot be empty";
		} else if(toInt(salary) == Integer.MIN_VALUE) {
			error = "Employee salary must be numerical";
		} else if(toInt(salary) < 1) {
			error = "Employe salary cannot be less than 1";
		} else if(username.equals("")) {
			error = "Employee username cannot be empty";
		// Prevent one employee having the same username
		} else if(EmployeeHandler.checkUsernameExist(username) == true) {
			error = "Employee username already exists";
		} else if(password.equals("")) {
			error = "Employee password cannot be empty";
		}
		return error;
	}
	
	public static String checkEmployeeId(String employeeId) {
		String error = "";
		if(employeeId.equals("")) {
			error = "Please pick an employee first";
		}
		return error;
	}
	
	public static String checkUpdateEmployeeInput(String employeeId, String name, String status, String salary, String username, String password) {
		String error = "";
		if(employeeId.equals("")) {
			error = "Please pick an employee first";
		} else if(name.equals("")) {
			error = "Employee name cannot be empty";
		} else if(status.equals("")) {
			error = "Employee status cannot be empty";
		} else if(salary.equals("")) {
			error = "Employee salary cannot be empty";
		} else if(toInt(salary) == Integer.MIN_VALUE) {
			error = "Employee salary must be numerical";
		} else if(toInt(salary) < 1) {
			error = "Employee salary cannot be less than 1";
		} else if(username.equals("")) {
			error = "Employee username cannot be empty";
		} else if(password.equals("")) {
			error = "Employee password cannot be empty";
		}
		return error;
	}
	
}

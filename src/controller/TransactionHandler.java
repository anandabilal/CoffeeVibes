package controller;

import java.sql.ResultSet;
import java.util.Date;

import model.Transaction;
import model.TransactionDetail;

public class TransactionHandler {

	public static int getLatestTransactionId() {
		return Transaction.getLatestTransactionId();
	}
	
	public static void createTransaction(int employeeId, int voucherId, Date purchaseDate, int totalPrice) {
		Transaction.createTransaction(employeeId, voucherId, purchaseDate, totalPrice);
	}
	
	public static ResultSet getAllTransaction() {
		return Transaction.getAllTransaction();
	}
	
	public static ResultSet getTransactionDetailById(int transactionId) {
		return TransactionDetail.getTransactionDetailById(transactionId);
	}
	
}

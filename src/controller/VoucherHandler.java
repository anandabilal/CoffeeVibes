package controller;

import java.sql.ResultSet;

import model.Voucher;

public class VoucherHandler {

	public static Voucher getVoucherById(String voucherId) {
		return Voucher.getVoucherById(voucherId);
	}
	
	public static String applyVoucherDiscount(Voucher voucher, int totalPrice) {
		return Voucher.applyVoucherDiscount(voucher, totalPrice);
	}
	
	public static void changeVoucherStatusById(String voucherId) {
		Voucher.changeVoucherStatusById(voucherId);
	}
	
	public static int getLatestVoucherId() {
		return Voucher.getLatestVoucherId();
	}
	
	public static void createNewVoucher(int discount) {
		Voucher.createNewVoucher(discount);
	}
	
	public static ResultSet getAllVoucher() {
		return Voucher.getAllVoucher();
	}
	
	public static void deleteVoucherByVoucherId(String voucherId) {
		Voucher.deleteVoucherByVoucherId(voucherId);
	}
	
}

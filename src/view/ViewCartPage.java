package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Product;
import model.Voucher;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import controller.CartHandler;
import controller.InputHandler;
import controller.ProductHandler;
import controller.TransactionHandler;
import controller.VoucherHandler;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewCartPage extends JFrame {

	int selectedRowIndex = -1;
	String productId = "";
	String quantity = "";
	String totalPrice = "";
	String voucherId = "";
	
	private JPanel contentPane;
	private JTextField textFieldProductId;
	private JTable tableCart;
	private JLabel lblError1;
	private JLabel lblTitle;
	private JButton lblBackToHome;
	private JLabel lblClickTheRow;
	private JScrollPane scrollPane;
	private JPanel panelRemoveCart;
	private JButton btnRemoveFromCart;
	private JLabel lblProductId;
	private JPanel panelCheckout;
	private JLabel lblTotalPrice;
	private JTextField textFieldTotalPrice;
	private JButton btnCheckout;
	private JLabel lblError2;
	private JTextField textFieldVoucherId;
	private JLabel lblVoucherId;
	private JButton btnApplyVoucher;

	public static void openViewProductPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCartPage frame = new ViewCartPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Refresh table after remove product and checkout
	public void loadTableCart() {
		try {
			ResultSet rs = CartHandler.getAllCartWithProductDetail();
			tableCart.setModel(DbUtils.resultSetToTableModel(rs));
			tableCart.getColumnModel().getColumn(0).setHeaderValue("Product ID");
			tableCart.getColumnModel().getColumn(1).setHeaderValue("Name");
			tableCart.getColumnModel().getColumn(2).setHeaderValue("Quantity");
			tableCart.getColumnModel().getColumn(3).setHeaderValue("Price");
			tableCart.getColumnModel().getColumn(4).setHeaderValue("Sub Total");
			textFieldVoucherId.setText("");
			loadTotalPrice();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadTotalPrice() {
		textFieldTotalPrice.setText(CartHandler.getCartTotalPrice());
		totalPrice = textFieldTotalPrice.getText();
	}
	
	// Do this after every successful button click (mode = 1 for apply voucher, mode = 2 for remove cart/checkout)
	public void resetTextFieldAndValues(int mode) {
		textFieldProductId.setText("");
		textFieldVoucherId.setText("");
		productId = "";
		quantity = "";
		if(mode == 2) {
			voucherId = "";
		}
	}
	
	public void resetErrorLabel() {
		lblError1.setForeground(Color.RED);
		lblError1.setText("");
		lblError2.setForeground(Color.RED);
		lblError2.setText("");
	}

	public ViewCartPage(Employee employee) {
		setTitle("View Carts - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("View Carts");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 664, 43);
		contentPane.add(lblTitle);
		
		lblBackToHome = new JButton("<< Home");
		lblBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage.openHomePage(employee);
			}
		});
		lblBackToHome.setBounds(10, 65, 100, 23);
		contentPane.add(lblBackToHome);
		
		lblClickTheRow = new JLabel("Click the row of product that you would like to remove from this cart:");
		lblClickTheRow.setBounds(10, 99, 664, 14);
		contentPane.add(lblClickTheRow);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 664, 213);
		contentPane.add(scrollPane);
		
		tableCart = new JTable();
		tableCart.addMouseListener(new MouseAdapter() {
			// Click listener at row index (first one is 0)
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedRowIndex = tableCart.getSelectedRow();
				productId = tableCart.getValueAt(selectedRowIndex, 0).toString();
				quantity = tableCart.getValueAt(selectedRowIndex, 2).toString();
				textFieldProductId.setText(productId);
			}
		});
		// Make table content not editable by a mouse click
		tableCart.setDefaultEditor(Object.class, null);
		// Disable table header to be dragged to different position
		tableCart.getTableHeader().setReorderingAllowed(false);
		// .setViewportView so headers can appear as well, not just the table
		scrollPane.setViewportView(tableCart);
		
		panelRemoveCart = new JPanel();
		panelRemoveCart.setLayout(null);
		panelRemoveCart.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Remove from Cart", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRemoveCart.setBounds(10, 348, 249, 136);
		contentPane.add(panelRemoveCart);
		
		lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(10, 25, 110, 14);
		panelRemoveCart.add(lblProductId);
		
		textFieldProductId = new JTextField();
		textFieldProductId.setEditable(false);
		textFieldProductId.setColumns(10);
		textFieldProductId.setBounds(10, 43, 110, 20);
		panelRemoveCart.add(textFieldProductId);
		
		btnRemoveFromCart = new JButton("Remove from Cart");
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Remove Cart
				resetErrorLabel();
				lblError1.setText(InputHandler.checkProductId(productId));
				if(lblError1.getText().equals("")) {
					Product product = ProductHandler.getProductById(Integer.parseInt(productId));
					ProductHandler.increaseProductStock(product, Integer.parseInt(quantity));
					CartHandler.deleteCartByProductId(product.getProductId());
					lblError1.setForeground(Color.BLUE);
					lblError1.setText(product.getName() + " removed from cart");
					resetTextFieldAndValues(2);
					loadTableCart();
				}
			}
		});
		btnRemoveFromCart.setBounds(10, 92, 229, 33);
		panelRemoveCart.add(btnRemoveFromCart);
		
		lblError1 = new JLabel("");
		lblError1.setForeground(Color.RED);
		lblError1.setBounds(10, 74, 229, 14);
		panelRemoveCart.add(lblError1);
		
		panelCheckout = new JPanel();
		panelCheckout.setLayout(null);
		panelCheckout.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Checkout", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCheckout.setBounds(269, 348, 405, 136);
		contentPane.add(panelCheckout);
		
		lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setBounds(130, 25, 265, 14);
		panelCheckout.add(lblTotalPrice);
		
		textFieldTotalPrice = new JTextField();
		textFieldTotalPrice.setEditable(false);
		textFieldTotalPrice.setColumns(10);
		textFieldTotalPrice.setBounds(130, 43, 265, 20);
		panelCheckout.add(textFieldTotalPrice);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checkout
				resetErrorLabel();
				totalPrice = textFieldTotalPrice.getText();
				lblError2.setText(InputHandler.validateCheckout(tableCart.getRowCount()));
				if(lblError2.getText().equals("")) {
					if(voucherId.equals("")) {
						TransactionHandler.createTransaction(employee.getEmployeeId(), -1, new Date(System.currentTimeMillis()), Integer.parseInt(totalPrice));
					} else {
						TransactionHandler.createTransaction(employee.getEmployeeId(), Integer.parseInt(voucherId), new Date(System.currentTimeMillis()), Integer.parseInt(totalPrice));
						VoucherHandler.changeVoucherStatusById(voucherId);
					}
					CartHandler.deleteAllCart();
					lblError2.setForeground(Color.BLUE);
					lblError2.setText("Cart has been checked out");
					resetTextFieldAndValues(2);
					loadTableCart();
				}
			}
		});
		btnCheckout.setBounds(208, 92, 187, 33);
		panelCheckout.add(btnCheckout);
		
		lblError2 = new JLabel("");
		lblError2.setForeground(Color.RED);
		lblError2.setBounds(10, 74, 385, 14);
		panelCheckout.add(lblError2);
		
		textFieldVoucherId = new JTextField();
		textFieldVoucherId.setColumns(10);
		textFieldVoucherId.setBounds(10, 43, 110, 20);
		panelCheckout.add(textFieldVoucherId);
		
		lblVoucherId = new JLabel("Voucher ID");
		lblVoucherId.setBounds(10, 25, 110, 14);
		panelCheckout.add(lblVoucherId);
		
		btnApplyVoucher = new JButton("Apply Voucher");
		btnApplyVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Apply Voucher
				resetErrorLabel();
				voucherId = textFieldVoucherId.getText();
				Voucher voucher = VoucherHandler.getVoucherById(voucherId);
				lblError2.setText(InputHandler.checkVoucher(voucher, tableCart.getRowCount()));
				if(lblError2.getText().equals("")) {
					textFieldTotalPrice.setText(VoucherHandler.applyVoucherDiscount(voucher, Integer.parseInt(totalPrice)));
					lblError2.setForeground(Color.BLUE);
					lblError2.setText(voucher.getDiscount() + "%" + " discount applied from voucher ID '" + voucher.getVoucherId() + "'");
				} else {
					loadTotalPrice();
				}
				resetTextFieldAndValues(1);
			}
		});
		btnApplyVoucher.setBounds(10, 92, 187, 33);
		panelCheckout.add(btnApplyVoucher);
		
		loadTableCart();
	}
}

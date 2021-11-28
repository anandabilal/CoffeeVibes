package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PositionHandler;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblWelcome;
	private JButton btnViewProduct;
	private JButton btnViewCart;
	private JButton btnInsertNewProduct;
	private JButton btnGenerateVoucher;
	private JButton btnViewVoucher;
	private JButton btnViewAllTransaction;
	private JButton btnViewEmployee;
	private JButton btnInsertEmployee;
	private JButton btnLogout;
	
	public static void openHomePage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePage(Employee employee) {
		setTitle("Home - CoffeeVibes");
		String employeePositionName = PositionHandler.getPositionNameByEmployeeId(employee.getPositionId());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Home");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 514, 43);
		contentPane.add(lblTitle);
		
		lblWelcome = new JLabel("Welcome ");
		lblWelcome.setBounds(120, 69, 404, 14);
		contentPane.add(lblWelcome);
		lblWelcome.setText("Welcome " + employee.getName() + " (" + employeePositionName + ")");
		
		btnViewProduct = new JButton("View Products");
		btnViewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ViewProductPage.openViewProductPage(employee);
			}
		});
		btnViewProduct.setEnabled(false);
		btnViewProduct.setBounds(101, 94, 160, 33);
		contentPane.add(btnViewProduct);
		
		btnViewCart = new JButton("View Carts");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ViewCartPage.openViewProductPage(employee);
			}
		});
		btnViewCart.setEnabled(false);
		btnViewCart.setBounds(101, 147, 160, 33);
		contentPane.add(btnViewCart);
		
		btnInsertNewProduct = new JButton("Insert New Product");
		btnInsertNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				InsertNewProductPage.openInsertNewProductPage(employee);
			}
		});
		btnInsertNewProduct.setEnabled(false);
		btnInsertNewProduct.setBounds(101, 200, 160, 33);
		contentPane.add(btnInsertNewProduct);
		
		btnGenerateVoucher = new JButton("Generate Vouchers");
		btnGenerateVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GenerateVoucherPage.openGenerateVoucherPage(employee);
			}
		});
		btnGenerateVoucher.setEnabled(false);
		btnGenerateVoucher.setBounds(101, 253, 160, 33);
		contentPane.add(btnGenerateVoucher);
		
		btnViewVoucher = new JButton("View Vouchers");
		btnViewVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ViewVoucherPage.openViewVoucherPage(employee);
			}
		});
		btnViewVoucher.setEnabled(false);
		btnViewVoucher.setBounds(271, 94, 160, 33);
		contentPane.add(btnViewVoucher);
		
		btnViewAllTransaction = new JButton("View All Transactions");
		btnViewAllTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ViewAllTransactionPage.openViewAllTransactionPage(employee);
			}
		});
		btnViewAllTransaction.setEnabled(false);
		btnViewAllTransaction.setBounds(271, 147, 160, 33);
		contentPane.add(btnViewAllTransaction);
		
		btnViewEmployee = new JButton("View Employees");
		btnViewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ViewEmployeePage.openViewEmployeePage(employee);
			}
		});
		btnViewEmployee.setEnabled(false);
		btnViewEmployee.setBounds(271, 200, 160, 33);
		contentPane.add(btnViewEmployee);
		
		btnInsertEmployee = new JButton("Insert Employee");
		btnInsertEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				InsertEmployeePage.openInsertEmployeePage(employee);
			}
		});
		btnInsertEmployee.setEnabled(false);
		btnInsertEmployee.setBounds(271, 253, 160, 33);
		contentPane.add(btnInsertEmployee);
		
		btnLogout = new JButton("<< Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LoginPage.openLoginPage();
			}
		});
		btnLogout.setBounds(10, 65, 100, 23);
		contentPane.add(btnLogout);
		
		if(employee.getPositionId() == 1) {
			btnViewProduct.setEnabled(true);
			btnViewCart.setEnabled(true);
		} else if(employee.getPositionId() == 2) {
			btnViewProduct.setEnabled(true);
			btnInsertNewProduct.setEnabled(true);
			btnGenerateVoucher.setEnabled(true);
			btnViewVoucher.setEnabled(true);
		} else if(employee.getPositionId() == 3) {
			btnViewAllTransaction.setEnabled(true);
			btnViewEmployee.setEnabled(true);
		} else {
			btnInsertEmployee.setEnabled(true);
			btnViewEmployee.setEnabled(true);
		}
	}
}

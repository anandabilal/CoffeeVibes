package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputHandler;
import controller.VoucherHandler;
import model.Employee;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateVoucherPage extends JFrame {

	String discount = "";
	
	private JPanel contentPane;
	private JTextField textFieldDiscount;
	private JLabel lblTitle;
	private JButton btnGenerate;
	private JLabel lblVoucherDiscountRate;
	private JLabel lblError;
	private JButton lblBackToHome;
	
	public static void openGenerateVoucherPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateVoucherPage frame = new GenerateVoucherPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void resetTextFieldAndValues() {
		textFieldDiscount.setText("");
		discount = "";
	}
	
	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}

	public GenerateVoucherPage(Employee employee) {
		setTitle("Generate Voucher - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Generate Voucher");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 414, 43);
		contentPane.add(lblTitle);
		
		btnGenerate = new JButton("Generate Voucher");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Generate Voucher
				resetErrorLabel();
				discount = textFieldDiscount.getText();
				lblError.setText(InputHandler.checkDiscountInput(discount));
				if(lblError.getText().equals("")) {
					VoucherHandler.createNewVoucher(Integer.parseInt(discount));
					lblError.setForeground(Color.BLUE);
					lblError.setText("Voucher ID '" + VoucherHandler.getLatestVoucherId() + "' (" + discount + "%) has been generated");
				}
				resetTextFieldAndValues();
			}
		});
		btnGenerate.setBounds(137, 162, 160, 33);
		contentPane.add(btnGenerate);
		
		textFieldDiscount = new JTextField();
		textFieldDiscount.setColumns(10);
		textFieldDiscount.setBounds(201, 106, 172, 20);
		contentPane.add(textFieldDiscount);
		
		lblVoucherDiscountRate = new JLabel("Voucher Discount Rate");
		lblVoucherDiscountRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoucherDiscountRate.setBounds(61, 109, 130, 14);
		contentPane.add(lblVoucherDiscountRate);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(61, 137, 312, 14);
		contentPane.add(lblError);
		
		lblBackToHome = new JButton("<< Home");
		lblBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage.openHomePage(employee);
			}
		});
		lblBackToHome.setBounds(10, 65, 100, 23);
		contentPane.add(lblBackToHome);
	}

}

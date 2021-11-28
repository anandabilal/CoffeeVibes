package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputHandler;
import controller.ProductHandler;
import model.Employee;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertNewProductPage extends JFrame {

	String name = "";
	String description = "";
	String stock = "";
	String price = "";
	
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldStock;
	private JLabel lblTitle;
	private JButton btnInsertProduct;
	private JScrollPane scrollPane;
	private JEditorPane editorPaneDescription;
	private JLabel lblName;
	private JLabel lblDescription;
	private JLabel lblPrice;
	private JLabel lblStock;
	private JLabel lblError;
	private JButton lblBackToHome;
	
	public static void openInsertNewProductPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertNewProductPage frame = new InsertNewProductPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void resetTextFieldAndValues() {
		textFieldName.setText("");
		editorPaneDescription.setText("");
		textFieldPrice.setText("");
		textFieldStock.setText("");
		name = "";
		description = "";
		stock = "";
		price = "";
	}
	
	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}
	
	public InsertNewProductPage(Employee employee) {
		setTitle("Insert New Product - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Insert New Product");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 464, 43);
		contentPane.add(lblTitle);
		
		btnInsertProduct = new JButton("Insert Product");
		btnInsertProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Insert New Product
				resetErrorLabel();
				name = textFieldName.getText();
				description = editorPaneDescription.getText();
				price = textFieldPrice.getText();
				stock = textFieldStock.getText();
				lblError.setText(InputHandler.checkInsertProductInput(name, description, price, stock));
				if(lblError.getText().equals("")) {
					ProductHandler.createNewProduct(name, description, Integer.parseInt(price), Integer.parseInt(stock));
					lblError.setForeground(Color.BLUE);
					lblError.setText(name + " successfully inserted");
				}
				resetTextFieldAndValues();
			}
		});
		btnInsertProduct.setBounds(162, 317, 160, 33);
		contentPane.add(btnInsertProduct);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(196, 99, 211, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 130, 211, 79);
		contentPane.add(scrollPane);
		
		editorPaneDescription = new JEditorPane();
		scrollPane.setViewportView(editorPaneDescription);
		editorPaneDescription.setText("");
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(196, 220, 211, 20);
		contentPane.add(textFieldPrice);
		
		textFieldStock = new JTextField();
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(196, 251, 211, 20);
		contentPane.add(textFieldStock);
		
		lblName = new JLabel("Product Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(81, 102, 105, 14);
		contentPane.add(lblName);
		
		lblDescription = new JLabel("Product Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(66, 130, 120, 14);
		contentPane.add(lblDescription);
		
		lblPrice = new JLabel("Product Price");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setBounds(81, 223, 105, 14);
		contentPane.add(lblPrice);
		
		lblStock = new JLabel("Product Stock");
		lblStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStock.setBounds(81, 254, 105, 14);
		contentPane.add(lblStock);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(77, 292, 330, 14);
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

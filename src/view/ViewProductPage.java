package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CartHandler;
import controller.InputHandler;
import controller.ProductHandler;
import model.Employee;
import model.Product;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JEditorPane;

public class ViewProductPage extends JFrame {

	int selectedRowIndex = -1;
	String productId = "";
	String name = "";
	String description = "";
	String price = "";
	String stock = "";
	String quantity = "";
	
	private JPanel contentPane;
	private JTable tableProduct;
	private JScrollPane scrollPane;
	private JTextField textFieldProductId1;
	private JTextField textFieldQuantity;
	private JLabel lblError1;
	private JLabel lblInfo;
	private JButton lblBackToHome;
	private JLabel lblTitle;
	private JPanel panelAddToCart;
	private JLabel lblProductId1;
	private JLabel lblQuantitymustBe;
	private JButton btnAddToCart;
	private JTextField textFieldProductId2;
	private JTextField textFieldPrice;
	private JTextField textFieldStock;
	private JTextField textFieldName;
	
	private JPanel panelDeleteAndUpdate;
	private JButton btnUpdateProduct;
	private JLabel lblError2;
	private JLabel lblProductId2;
	private JButton btnDeleteProduct;
	private JLabel lblPrice;
	private JLabel lblStock;
	private JLabel lblName;
	private JLabel lblDescription;
	private JEditorPane editorPaneDescription;
	private JScrollPane scrollPane_1;
	
	public static void openViewProductPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProductPage frame = new ViewProductPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadTableProduct() {
		try {
			ResultSet rs = ProductHandler.getAllProduct();
			tableProduct.setModel(DbUtils.resultSetToTableModel(rs));
			tableProduct.getColumnModel().getColumn(0).setHeaderValue("Product ID");
			tableProduct.getColumnModel().getColumn(1).setHeaderValue("Name");
			tableProduct.getColumnModel().getColumn(2).setHeaderValue("Description");
			tableProduct.getColumnModel().getColumn(3).setHeaderValue("Price");
			tableProduct.getColumnModel().getColumn(4).setHeaderValue("Stock");
			resetTextFieldAndValues();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTextFieldAndValues() {
		textFieldProductId1.setText("");
		textFieldQuantity.setText("");
		textFieldProductId2.setText("");
		textFieldName.setText("");
		editorPaneDescription.setText("");
		textFieldStock.setText("");
		textFieldPrice.setText("");
		productId = "";
		name = "";
		description = "";
		price = "";
		stock = "";
		quantity = "";
	}
	
	public void resetErrorLabel() {
		lblError1.setForeground(Color.RED);
		lblError1.setText("");
		lblError2.setForeground(Color.RED);
		lblError2.setText("");
	}

	public ViewProductPage(Employee employee) {
		setTitle("View Products - CoffeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("View Products");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 664, 43);
		contentPane.add(lblTitle);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 664, 213);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.addMouseListener(new MouseAdapter() {
			// Click listener at row index (first one is 0)
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get values of clicked row
				selectedRowIndex = tableProduct.getSelectedRow();
				productId = tableProduct.getValueAt(selectedRowIndex, 0).toString();
				name = tableProduct.getValueAt(selectedRowIndex, 1).toString();
				description = tableProduct.getValueAt(selectedRowIndex, 2).toString();
				price = tableProduct.getValueAt(selectedRowIndex, 3).toString();
				stock = tableProduct.getValueAt(selectedRowIndex, 4).toString();
				// Fill textField with the values clicked
				textFieldProductId1.setText(productId);
				textFieldProductId2.setText(productId);
				textFieldName.setText(name);
				editorPaneDescription.setText(description);
				textFieldPrice.setText(price.toString());
				textFieldStock.setText(stock);
				// Reset quantity
				textFieldQuantity.setText("");
				quantity = "";
			}
		});
		tableProduct.setDefaultEditor(Object.class, null);
		tableProduct.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableProduct);
		
		panelAddToCart = new JPanel();
		panelAddToCart.setBorder(new TitledBorder(null, "Add to Cart", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAddToCart.setBounds(10, 348, 249, 136);
		contentPane.add(panelAddToCart);
		panelAddToCart.setLayout(null);
		
		lblProductId1 = new JLabel("Product ID");
		lblProductId1.setBounds(10, 25, 110, 14);
		panelAddToCart.add(lblProductId1);
		
		lblQuantitymustBe = new JLabel("Quantity");
		lblQuantitymustBe.setBounds(130, 25, 109, 14);
		panelAddToCart.add(lblQuantitymustBe);
		
		textFieldProductId1 = new JTextField();
		textFieldProductId1.setEditable(false);
		textFieldProductId1.setBounds(10, 43, 110, 20);
		panelAddToCart.add(textFieldProductId1);
		textFieldProductId1.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setEditable(false);
		textFieldQuantity.setBounds(129, 43, 110, 20);
		panelAddToCart.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setEnabled(false);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Add to Cart
				resetErrorLabel();
				quantity = textFieldQuantity.getText();
				lblError1.setText(InputHandler.checkQuantity(productId, quantity, stock));
				if(lblError1.getText().equals("")) {
					Product product = ProductHandler.getProductById(Integer.parseInt(productId));
					ProductHandler.decreaseProductStock(product, Integer.parseInt(quantity));
					CartHandler.createCart(Integer.parseInt(productId), Integer.parseInt(quantity));
					lblError1.setForeground(Color.BLUE);
					lblError1.setText(quantity + "x " + product.getName() + " added to cart");
					loadTableProduct();
				}
			}
		});
		btnAddToCart.setBounds(10, 92, 229, 33);
		panelAddToCart.add(btnAddToCart);
		
		lblError1 = new JLabel("");
		lblError1.setBounds(10, 74, 229, 14);
		panelAddToCart.add(lblError1);
		lblError1.setForeground(Color.RED);
		
		lblInfo = new JLabel("Click the row of product that you would like to select:");
		lblInfo.setBounds(10, 99, 664, 14);
		contentPane.add(lblInfo);
		
		lblBackToHome = new JButton("<< Home");
		lblBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomePage.openHomePage(employee);
			}
		});
		lblBackToHome.setBounds(10, 65, 100, 23);
		contentPane.add(lblBackToHome);
		
		panelDeleteAndUpdate = new JPanel();
		panelDeleteAndUpdate.setLayout(null);
		panelDeleteAndUpdate.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Delete & Update Product Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDeleteAndUpdate.setBounds(269, 348, 405, 202);
		contentPane.add(panelDeleteAndUpdate);
		
		btnUpdateProduct = new JButton("Update Product Details");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Update Product Details
				resetErrorLabel();
				productId = textFieldProductId2.getText();
				name = textFieldName.getText();
				description = editorPaneDescription.getText();
				stock = textFieldStock.getText();
				price = textFieldPrice.getText();
				lblError2.setText(InputHandler.checkUpdateProductInput(productId, name, description, price, stock));
				if(lblError2.getText().equals("")) {
					Product product = ProductHandler.getProductById(Integer.parseInt(productId));
					ProductHandler.updateProductById(Integer.parseInt(productId), name, description, Integer.parseInt(stock), Integer.parseInt(price));
					lblError2.setForeground(Color.BLUE);
					lblError2.setText("Product ID '" + product.getProductId() + "' has been updated");
					loadTableProduct();
				}
			}
		});
		
		btnUpdateProduct.setEnabled(false);
		btnUpdateProduct.setBounds(208, 158, 187, 33);
		panelDeleteAndUpdate.add(btnUpdateProduct);
		
		lblError2 = new JLabel("");
		lblError2.setForeground(Color.RED);
		lblError2.setBounds(10, 133, 385, 14);
		panelDeleteAndUpdate.add(lblError2);
		
		textFieldProductId2 = new JTextField();
		textFieldProductId2.setEditable(false);
		textFieldProductId2.setText("");
		textFieldProductId2.setColumns(10);
		textFieldProductId2.setBounds(10, 43, 70, 20);
		panelDeleteAndUpdate.add(textFieldProductId2);
		
		lblProductId2 = new JLabel("Product ID");
		lblProductId2.setBounds(10, 25, 70, 14);
		panelDeleteAndUpdate.add(lblProductId2);
		
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Delete Product
				resetErrorLabel();
				lblError2.setText(InputHandler.checkProductId(productId));
				if(lblError2.getText().equals("")) {
					Product product = ProductHandler.getProductById(Integer.parseInt(productId));
					ProductHandler.deleteProductByProductId(product.getProductId());
					lblError2.setForeground(Color.BLUE);
					lblError2.setText(product.getName() + " has been deleted");
					loadTableProduct();
				}
			}
		});
		btnDeleteProduct.setEnabled(false);
		btnDeleteProduct.setBounds(10, 158, 187, 33);
		panelDeleteAndUpdate.add(btnDeleteProduct);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 84, 70, 14);
		panelDeleteAndUpdate.add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setText("");
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(10, 102, 70, 20);
		panelDeleteAndUpdate.add(textFieldPrice);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(90, 84, 107, 14);
		panelDeleteAndUpdate.add(lblStock);
		
		textFieldStock = new JTextField();
		textFieldStock.setEditable(false);
		textFieldStock.setText("");
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(90, 102, 107, 20);
		panelDeleteAndUpdate.add(textFieldStock);
		
		lblName = new JLabel("Name");
		lblName.setBounds(90, 25, 107, 14);
		panelDeleteAndUpdate.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setText("");
		textFieldName.setColumns(10);
		textFieldName.setBounds(90, 43, 107, 20);
		panelDeleteAndUpdate.add(textFieldName);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(208, 25, 187, 14);
		panelDeleteAndUpdate.add(lblDescription);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(208, 43, 187, 79);
		panelDeleteAndUpdate.add(scrollPane_1);
		
		editorPaneDescription = new JEditorPane();
		scrollPane_1.setViewportView(editorPaneDescription);
		editorPaneDescription.setEditable(false);
		
		loadTableProduct();
		
		if(employee.getPositionId() == 1) {
			btnAddToCart.setEnabled(true);
			textFieldQuantity.setEditable(true);
		} else {
			btnDeleteProduct.setEnabled(true);
			btnUpdateProduct.setEnabled(true);
			textFieldPrice.setEditable(true);
			textFieldStock.setEditable(true);
			textFieldName.setEditable(true);
			editorPaneDescription.setEditable(true);
		}
	}
}

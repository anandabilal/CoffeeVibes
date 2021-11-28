package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TransactionHandler;
import model.Employee;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class ViewAllTransactionPage extends JFrame {

	int selectedRowIndex = -1;
	String transactionId = "";
	
	private JPanel contentPane;
	private JTable tableTransaction;
	private JScrollPane scrollPane1;
	private JLabel lblInfo;
	private JButton lblBackToHome;
	private JLabel lblTitle1;
	private JTable tableDetail;
	private JLabel lblError;
	private JScrollPane scrollPane2;
	
	public static void openViewAllTransactionPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllTransactionPage frame = new ViewAllTransactionPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadTableTransaction() {
		try {
			ResultSet rs = TransactionHandler.getAllTransaction();
			tableTransaction.setModel(DbUtils.resultSetToTableModel(rs));
			tableTransaction.getColumnModel().getColumn(0).setHeaderValue("Transaction ID");
			tableTransaction.getColumnModel().getColumn(1).setHeaderValue("Employee ID");
			tableTransaction.getColumnModel().getColumn(2).setHeaderValue("Name");
			tableTransaction.getColumnModel().getColumn(3).setHeaderValue("Voucher ID");
			tableTransaction.getColumnModel().getColumn(4).setHeaderValue("Discount (%)");
			tableTransaction.getColumnModel().getColumn(5).setHeaderValue("Purchase Date");
			tableTransaction.getColumnModel().getColumn(6).setHeaderValue("Total Price");
			resetTextFieldAndValues();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadTableDetail(int tId) {
		try {
			ResultSet rs = TransactionHandler.getTransactionDetailById(tId);
			tableDetail.setModel(DbUtils.resultSetToTableModel(rs));
			tableDetail.getColumnModel().getColumn(0).setHeaderValue("Transaction ID");
			tableDetail.getColumnModel().getColumn(1).setHeaderValue("Product ID");
			tableDetail.getColumnModel().getColumn(2).setHeaderValue("Name");
			tableDetail.getColumnModel().getColumn(3).setHeaderValue("Quantity");
			tableDetail.getColumnModel().getColumn(4).setHeaderValue("Price");
			tableDetail.getColumnModel().getColumn(5).setHeaderValue("Sub Total");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTextFieldAndValues() {
		transactionId = "";
	}

	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}
	
	public ViewAllTransactionPage(Employee employee) {
		setTitle("View All Transactions - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 124, 664, 205);
		contentPane.add(scrollPane1);
		
		tableTransaction = new JTable();
		tableTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetErrorLabel();
				// Get transactionId from row click
				selectedRowIndex = tableTransaction.getSelectedRow();
				transactionId = tableTransaction.getValueAt(selectedRowIndex, 0).toString();
				// Load tableDetail based on transactionId
				loadTableDetail(Integer.parseInt(transactionId));
				lblError.setForeground(Color.BLUE);
				lblError.setText("Showing details of transaction ID '" + transactionId + "':");
			}
		});
		tableTransaction.setDefaultEditor(Object.class, null);
		tableTransaction.getTableHeader().setReorderingAllowed(false);
		scrollPane1.setViewportView(tableTransaction);
		
		lblInfo = new JLabel("For more details, click on the row of the transaction that you would like to inspect:");
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
		
		lblTitle1 = new JLabel("View All Transactions");
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle1.setBounds(10, 11, 664, 43);
		contentPane.add(lblTitle1);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 365, 664, 185);
		contentPane.add(scrollPane2);
		
		tableDetail = new JTable();
		tableDetail.setDefaultEditor(Object.class, null);
		tableDetail.getTableHeader().setReorderingAllowed(false);
		scrollPane2.setViewportView(tableDetail);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 340, 664, 14);
		contentPane.add(lblError);
		
		loadTableTransaction();
		if(tableTransaction.getRowCount() == 0) {
			lblError.setText("Unable to look at any transaction details because there hasn't been any transaction");
		}
	}
}

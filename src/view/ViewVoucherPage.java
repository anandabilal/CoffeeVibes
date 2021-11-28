package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controller.InputHandler;
import controller.VoucherHandler;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewVoucherPage extends JFrame {

	int selectedRowIndex = -1;
	String voucherId = "";
	
	private JPanel contentPane;
	private JTable tableVoucher;
	private JTextField textFieldVoucherId;
	private JTextField textFieldDiscount;
	private JTextField textFieldStatus;
	private JLabel lblTitle;
	private JScrollPane scrollPane;
	private JLabel lblInfo;
	private JButton lblBackToHome;
	private JPanel panelDeleteVoucher;
	private JLabel lblVoucherId;
	private JLabel lblDiscount;
	private JButton btnDeleteVoucher;
	private JLabel lblError;
	private JLabel lblStatus;
	
	public static void openViewVoucherPage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVoucherPage frame = new ViewVoucherPage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loadTableVoucher() {
		try {
			ResultSet rs = VoucherHandler.getAllVoucher();
			tableVoucher.setModel(DbUtils.resultSetToTableModel(rs));
			tableVoucher.getColumnModel().getColumn(0).setHeaderValue("Voucher ID");
			tableVoucher.getColumnModel().getColumn(1).setHeaderValue("Discount (%)");
			tableVoucher.getColumnModel().getColumn(2).setHeaderValue("Status");
			resetTextFieldAndValues();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTextFieldAndValues() {
		textFieldVoucherId.setText("");
		textFieldDiscount.setText("");
		textFieldStatus.setText("");
		voucherId = "";
	}
	
	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}
	
	public ViewVoucherPage(Employee employee) {
		setTitle("View Vouchers - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("View Vouchers");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 664, 43);
		contentPane.add(lblTitle);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 405, 226);
		contentPane.add(scrollPane);
		
		tableVoucher = new JTable();
		tableVoucher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get values of clicked row
				selectedRowIndex = tableVoucher.getSelectedRow();
				voucherId = tableVoucher.getValueAt(selectedRowIndex, 0).toString();
				// Fill textField with the values clicked
				textFieldVoucherId.setText(voucherId);
				textFieldDiscount.setText(tableVoucher.getValueAt(selectedRowIndex, 1).toString());
				textFieldStatus.setText(tableVoucher.getValueAt(selectedRowIndex, 2).toString());
			}
		});
		tableVoucher.setDefaultEditor(Object.class, null);
		tableVoucher.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableVoucher);
		
		lblInfo = new JLabel("Click the row of voucher that you would like to delete:");
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
		
		panelDeleteVoucher = new JPanel();
		panelDeleteVoucher.setLayout(null);
		panelDeleteVoucher.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Delete Voucher", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDeleteVoucher.setBounds(425, 125, 249, 185);
		contentPane.add(panelDeleteVoucher);
		
		lblVoucherId = new JLabel("Voucher ID");
		lblVoucherId.setBounds(10, 25, 110, 14);
		panelDeleteVoucher.add(lblVoucherId);
		
		lblDiscount = new JLabel("Discount (%)");
		lblDiscount.setBounds(130, 25, 109, 14);
		panelDeleteVoucher.add(lblDiscount);
		
		textFieldVoucherId = new JTextField();
		textFieldVoucherId.setText("");
		textFieldVoucherId.setEditable(false);
		textFieldVoucherId.setColumns(10);
		textFieldVoucherId.setBounds(10, 43, 110, 20);
		panelDeleteVoucher.add(textFieldVoucherId);
		
		textFieldDiscount = new JTextField();
		textFieldDiscount.setText("");
		textFieldDiscount.setEditable(false);
		textFieldDiscount.setColumns(10);
		textFieldDiscount.setBounds(129, 43, 110, 20);
		panelDeleteVoucher.add(textFieldDiscount);
		
		btnDeleteVoucher = new JButton("Delete Voucher");
		btnDeleteVoucher.addActionListener(new ActionListener() {
			// Delete Voucher
			public void actionPerformed(ActionEvent arg0) {
				resetErrorLabel();
				lblError.setText(InputHandler.checkVoucherId(voucherId));
				if(lblError.getText().equals("")) {
					VoucherHandler.deleteVoucherByVoucherId(voucherId);
					lblError.setForeground(Color.BLUE);
					lblError.setText("Voucher ID '" + voucherId + "' has been deleted");
					loadTableVoucher();
				}
			}
		});
		btnDeleteVoucher.setBounds(10, 141, 229, 33);
		panelDeleteVoucher.add(btnDeleteVoucher);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 123, 229, 14);
		panelDeleteVoucher.add(lblError);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setText("");
		textFieldStatus.setEditable(false);
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(10, 92, 110, 20);
		panelDeleteVoucher.add(textFieldStatus);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 74, 110, 14);
		panelDeleteVoucher.add(lblStatus);
		
		loadTableVoucher();
	}
}

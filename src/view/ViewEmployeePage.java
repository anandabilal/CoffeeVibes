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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controller.EmployeeHandler;
import controller.InputHandler;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewEmployeePage extends JFrame {

	int selectedRowIndex = -1;
	String employeeId = "";
	String positionId = "";
	String name = "";
	String status = "";
	String salary = "";
	String username = "";
	String password = "";
	
	private JPanel contentPane;
	private JTable tableEmployee;
	private JTextField textFieldEmployeeId;
	private JTextField textFieldName;
	private JTextField textFieldStatus;
	private JTextField textFieldSalary;
	private JTextField textFieldUsername;
	private JLabel lblTitle;
	private JButton lblBackToHome;
	private JLabel lblInfo;
	private JScrollPane scrollPane;
	private JPanel panelFireAndUpdate;
	private JButton btnUpdate;
	private JLabel lblError;
	private JButton btnFire;
	private JLabel lblEmployeeId;
	private JLabel lblPositionId;
	private JLabel lblName;
	private JLabel lblStatus;
	private JLabel lblSalary;
	private JLabel lblUsername;
	private JComboBox comboBoxPositionId;
	private JLabel lblPassword;
	private JTextField textFieldPassword;

	public static void openViewEmployeePage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmployeePage frame = new ViewEmployeePage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadTableEmployee() {
		try {
			ResultSet rs = EmployeeHandler.getAllEmployee();
			tableEmployee.setModel(DbUtils.resultSetToTableModel(rs));
			tableEmployee.getColumnModel().getColumn(0).setHeaderValue("Employee ID");
			tableEmployee.getColumnModel().getColumn(1).setHeaderValue("Position ID");
			tableEmployee.getColumnModel().getColumn(2).setHeaderValue("Name");
			tableEmployee.getColumnModel().getColumn(3).setHeaderValue("Status");
			tableEmployee.getColumnModel().getColumn(4).setHeaderValue("Salary");
			tableEmployee.getColumnModel().getColumn(5).setHeaderValue("Username");
			tableEmployee.getColumnModel().getColumn(6).setHeaderValue("Password");
			resetTextFieldAndValues();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetTextFieldAndValues() {
		textFieldEmployeeId.setText("");
		comboBoxPositionId.setSelectedIndex(0);
		textFieldName.setText("");
		textFieldStatus.setText("");
		textFieldSalary.setText("");
		textFieldUsername.setText("");
		textFieldPassword.setText("");
		employeeId = "";
		positionId = "";
		name = "";
		status = "";
		salary = "";
		username = "";
		password = "";
	}
	
	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}

	public ViewEmployeePage(Employee employee) {
		setTitle("View Employees - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("View Employees");
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
		
		lblInfo = new JLabel("Click the row of employee that you would like to select:");
		lblInfo.setBounds(10, 99, 664, 14);
		contentPane.add(lblInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 664, 213);
		contentPane.add(scrollPane);
		
		tableEmployee = new JTable();
		tableEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Get values of clicked row
				selectedRowIndex = tableEmployee.getSelectedRow();
				employeeId = tableEmployee.getValueAt(selectedRowIndex, 0).toString();
				positionId = tableEmployee.getValueAt(selectedRowIndex, 1).toString();
				name = tableEmployee.getValueAt(selectedRowIndex, 2).toString();
				status = tableEmployee.getValueAt(selectedRowIndex, 3).toString();
				salary = tableEmployee.getValueAt(selectedRowIndex, 4).toString();
				username = tableEmployee.getValueAt(selectedRowIndex, 5).toString();
				password = tableEmployee.getValueAt(selectedRowIndex, 6).toString();
				// Fill textField with the values clicked
				textFieldEmployeeId.setText(employeeId);
				comboBoxPositionId.setSelectedIndex(Integer.parseInt(positionId) - 1);
				textFieldName.setText(name);
				textFieldStatus.setText(status);
				textFieldSalary.setText(salary);
				textFieldUsername.setText(username);
				textFieldPassword.setText(password);
			}
		});
		tableEmployee.setDefaultEditor(Object.class, null);
		tableEmployee.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableEmployee);
		
		panelFireAndUpdate = new JPanel();
		panelFireAndUpdate.setLayout(null);
		panelFireAndUpdate.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fire & Update Employee", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelFireAndUpdate.setBounds(10, 348, 664, 202);
		contentPane.add(panelFireAndUpdate);
		
		btnUpdate = new JButton("Update Employee");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update Employee
				resetErrorLabel();
				employeeId = textFieldEmployeeId.getText();
				name = textFieldName.getText();
				status = textFieldStatus.getText();
				salary = textFieldSalary.getText();
				username = textFieldUsername.getText();
				password = textFieldPassword.getText();
				lblError.setText(InputHandler.checkUpdateEmployeeInput(employeeId, name, status, salary, username, password));
				if(lblError.getText().equals("")) {
					EmployeeHandler.updateEmployeeById(employeeId, name, status, salary, username, password);
					lblError.setForeground(Color.BLUE);
					lblError.setText("Employee ID '" + employeeId + "' has been updated");
					loadTableEmployee();
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(337, 158, 317, 33);
		panelFireAndUpdate.add(btnUpdate);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 133, 644, 14);
		panelFireAndUpdate.add(lblError);
		
		btnFire = new JButton("Fire Employee");
		btnFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Fire Employee
				resetErrorLabel();
				lblError.setText(InputHandler.checkEmployeeId(employeeId));
				if(lblError.getText().equals("")) {
					EmployeeHandler.deleteEmployeeByEmployeeId(employeeId);
					lblError.setForeground(Color.BLUE);
					lblError.setText("Employee ID '" + employeeId + "' has been fired");
					loadTableEmployee();
				}
			}
		});
		btnFire.setEnabled(false);
		btnFire.setBounds(10, 158, 317, 33);
		panelFireAndUpdate.add(btnFire);
		
		lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(10, 25, 107, 14);
		panelFireAndUpdate.add(lblEmployeeId);
		
		textFieldEmployeeId = new JTextField();
		textFieldEmployeeId.setText("");
		textFieldEmployeeId.setEditable(false);
		textFieldEmployeeId.setColumns(10);
		textFieldEmployeeId.setBounds(10, 43, 107, 20);
		panelFireAndUpdate.add(textFieldEmployeeId);
		
		lblPositionId = new JLabel("Position ID");
		lblPositionId.setBounds(127, 25, 184, 14);
		panelFireAndUpdate.add(lblPositionId);
		
		lblName = new JLabel("Name");
		lblName.setBounds(10, 74, 107, 14);
		panelFireAndUpdate.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setText("");
		textFieldName.setEditable(false);
		textFieldName.setColumns(10);
		textFieldName.setBounds(10, 92, 107, 20);
		panelFireAndUpdate.add(textFieldName);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(127, 74, 107, 14);
		panelFireAndUpdate.add(lblStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setText("");
		textFieldStatus.setEditable(false);
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(127, 92, 107, 20);
		panelFireAndUpdate.add(textFieldStatus);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(244, 74, 107, 14);
		panelFireAndUpdate.add(lblSalary);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setText("");
		textFieldSalary.setEditable(false);
		textFieldSalary.setColumns(10);
		textFieldSalary.setBounds(244, 92, 107, 20);
		panelFireAndUpdate.add(textFieldSalary);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(361, 74, 107, 14);
		panelFireAndUpdate.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setText("");
		textFieldUsername.setEditable(false);
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(361, 92, 107, 20);
		panelFireAndUpdate.add(textFieldUsername);
		
		comboBoxPositionId = new JComboBox();
		comboBoxPositionId.setEnabled(false);
		comboBoxPositionId.setModel(new DefaultComboBoxModel(new String[] {"1 - Barista", "2 - Product Admin", "3 - Manager", "4 - Human Resource Department"}));
		comboBoxPositionId.setBounds(127, 43, 184, 20);
		panelFireAndUpdate.add(comboBoxPositionId);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(478, 74, 107, 14);
		panelFireAndUpdate.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setText("");
		textFieldPassword.setEditable(false);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(478, 92, 107, 20);
		panelFireAndUpdate.add(textFieldPassword);
		
		loadTableEmployee();
		
		if(employee.getPositionId() == 3) {
			btnFire.setEnabled(true);
		} else {
			btnFire.setEnabled(true);
			btnUpdate.setEnabled(true);
			textFieldName.setEditable(true);
			textFieldStatus.setEditable(true);
			textFieldSalary.setEditable(true);
			textFieldUsername.setEditable(true);
			textFieldPassword.setEditable(true);
		}
	}
}

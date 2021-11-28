package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeHandler;
import controller.InputHandler;
import model.Employee;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertEmployeePage extends JFrame {

	String positionId = "";
	String name = "";
	String salary = "";
	String username = "";
	String password = "";
	
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldSalary;
	private JTextField textFieldName;
	private JLabel lblTitle;
	private JLabel lblPosition;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblError;
	private JButton lblBackToHome;
	private JButton btnInsertEmployee;
	private JLabel lblSalary;
	private JLabel lblName;
	private JPasswordField passwordFieldPassword;
	private JComboBox comboBoxPosition;

	public static void openInsertEmployeePage(Employee employee) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEmployeePage frame = new InsertEmployeePage(employee);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void resetTextFieldAndValues() {
		// comboBox index starts at 0
		comboBoxPosition.setSelectedIndex(0);
		textFieldName.setText("");
		textFieldSalary.setText("");
		textFieldUsername.setText("");
		passwordFieldPassword.setText("");
		positionId = "";
		name = "";
		salary = "";
		username = "";
		password = "";
	}
	
	public void resetErrorLabel() {
		lblError.setForeground(Color.RED);
		lblError.setText("");
	}
	
	public InsertEmployeePage(Employee employee) {
		setTitle("Insert Employee - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Insert Employee");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 464, 43);
		contentPane.add(lblTitle);
		
		comboBoxPosition = new JComboBox();
		comboBoxPosition.setModel(new DefaultComboBoxModel(new String[] {"1 - Barista", "2 - Product Admin", "3 - Manager", "4 - Human Resource Department"}));
		comboBoxPosition.setBounds(217, 116, 184, 20);
		contentPane.add(comboBoxPosition);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(217, 209, 184, 20);
		contentPane.add(textFieldUsername);
		
		lblPosition = new JLabel("Employee Position");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setBounds(82, 119, 125, 14);
		contentPane.add(lblPosition);
		
		lblUsername = new JLabel("Employee Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(82, 212, 125, 14);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Employee Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(82, 243, 125, 14);
		contentPane.add(lblPassword);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(82, 292, 319, 14);
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
		
		btnInsertEmployee = new JButton("Insert Employee");
		btnInsertEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Insert Employee
				resetErrorLabel();
//				position = comboBoxPosition.getSelectedItem().toString();
				positionId = String.valueOf(comboBoxPosition.getSelectedIndex() + 1);
				name = textFieldName.getText();
				salary = textFieldSalary.getText();
				username = textFieldUsername.getText();
				password = String.valueOf(passwordFieldPassword.getPassword());
				lblError.setText(InputHandler.checkInsertEmployeeInput(positionId, name, salary, username, password));
				if(lblError.getText().equals("")) {
					EmployeeHandler.createNewEmployee(positionId, name, salary, username, password);
					lblError.setForeground(Color.BLUE);
					lblError.setText("Employee named '" + name + "' successfully inserted");
				}
				resetTextFieldAndValues();
			}
		});
		btnInsertEmployee.setBounds(162, 317, 160, 33);
		contentPane.add(btnInsertEmployee);
		
		lblSalary = new JLabel("Employee Salary");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalary.setBounds(82, 181, 125, 14);
		contentPane.add(lblSalary);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setColumns(10);
		textFieldSalary.setBounds(217, 178, 184, 20);
		contentPane.add(textFieldSalary);
		
		lblName = new JLabel("Employee Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(82, 150, 125, 14);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(217, 147, 184, 20);
		contentPane.add(textFieldName);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(217, 240, 184, 20);
		contentPane.add(passwordFieldPassword);
	}
}

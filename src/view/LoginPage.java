package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.EmployeeHandler;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

public class LoginPage extends JFrame {
	
	String username = "";
	String password = "";
	
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JLabel lblTitle;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblError;
	private JButton btnLogin;

	public static void openLoginPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginPage() {
		setTitle("Login - CoffeeVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Login");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(10, 11, 264, 28);
		contentPane.add(lblTitle);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 51, 210, 14);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 96, 210, 14);
		contentPane.add(lblPassword);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setBounds(37, 144, 210, 14);
		contentPane.add(lblError);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(37, 68, 210, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(37, 113, 210, 20);
		contentPane.add(passwordFieldPassword);
		
		btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblError.setText("");
				username = textFieldUsername.getText();
				password = String.valueOf(passwordFieldPassword.getPassword());
				Employee employee = EmployeeHandler.getEmployeeByUsernameAndPassword(username, password);
				if(employee == null) {
					lblError.setText("Invalid username or password");
				} else {
					dispose();
					HomePage.openHomePage(employee);
				}
			}
		});
		btnLogin.setBounds(62, 167, 160, 33);
		contentPane.add(btnLogin);
	}
}

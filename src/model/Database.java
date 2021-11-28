package model;

import java.sql.*;

public class Database {

	private static Database instance;
	private Connection con;
	
	public Database() {
		try {
			// Database name 'coffeevibesdb'
			// Default username & password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeevibesdb", "root", "");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public Connection getCon() {
		return con;
	}

}

package com.fmi.rmi.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBManager {
	
	private static DBManager instance;
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	public  static final String DB_NAME = "futbulicus";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/futbulicus";
	private static final String DB_USER = "postgres";

	private static final String DB_PASS = "postgres";
	private static volatile Connection con;

	private DBManager() {

		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("con: " + con);
			System.out.println("Connection created successfully");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error connection to DB " + e.getMessage());
		}
	}

	public static synchronized DBManager getDBManager() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

 	public Connection getConnection() {
		return con;
	}

	public  void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

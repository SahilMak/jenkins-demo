package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory cf;
	private static boolean build = true;

	private ConnectionFactory() {
		build = false;
	}

	public static synchronized ConnectionFactory getInstance() {
		return (build) ? cf = new ConnectionFactory() : cf;
	}

	public Connection getConnection() {

		Connection conn = null;

		Properties prop = new Properties();

		try {
			prop.load(new FileReader("src/main/resources/application.properties"));

			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pw"));

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return conn;

	}

	public Connection getTokenDBConnection() {
		
		Connection conn = null;

		Properties prop = new Properties();

		try {
			prop.load(new FileReader("src/main/resources/application.properties"));

			conn = DriverManager.getConnection(
					prop.getProperty("tokendb-url"), 
					prop.getProperty("tokendb-usr"),
					prop.getProperty("tokendb-pw"));

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return conn;
	}

}

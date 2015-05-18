package edu.tongji.database;

import java.sql.*;

import com.eva.me.cm.ConfigUtil;
import com.manager.failure.FailureManager;

public class DBConnection {
	private String url;  
	private String user;  
	private String password;
	private String driver;
	private Connection conn;
	
	public DBConnection(ConfigUtil config){
		this.url = config.getProperty("DB_URL");
		this.user = config.getProperty("DB_USER");
		this.password = config.getProperty("DB_PASSWORD");
		this.driver = config.getProperty("DB_DRIVER_CLASS");
	}
	
	public Connection getConnection(){
		
		try {
			Class.forName(driver);
			FailureManager.logInfo("DBConnection getConnection: success load driver");
		} catch (ClassNotFoundException e) {
			FailureManager.logError("DBConnection getConnection: load driver error");
			e.printStackTrace();
			return null;
		}
		
		try {
			this.conn = DriverManager.getConnection(url,user, password);
			FailureManager.logInfo("DBConnection getConnection: success connect server");
		} catch (SQLException e) {
			FailureManager.logError("DBConnection getConnection: connect server error");
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	protected void finalize(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			System.out.println("DBConnection finalize: error");
			e.printStackTrace();
		}
	}
}

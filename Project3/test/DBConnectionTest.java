package com.server2.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import t2.server2.db.DBConnection;

public class DBConnectionTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testGetConnection_CorrectProperty(){
		//correct connection property
		DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/student",
				"root", "18817870106", "com.mysql.jdbc.Driver");
		
		Connection connection = dbConnection.getConnection();
		
		Assert.assertNotNull("Cann't connection to database", connection);
	}
	
	@Test
	public void testGetConnection_ErrorProperty(){
		//error connection property
		DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/student",
				"error_user", "18817870106", "com.mysql.jdbc.Driver");
		
		Connection connection = dbConnection.getConnection();
		
		Assert.assertNull("Error database porperty but connect success!", connection);
	}
}

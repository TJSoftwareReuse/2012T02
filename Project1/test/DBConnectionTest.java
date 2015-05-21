package database;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eva.me.cm.ConfigUtil;

import server.Server;
import database.DBConnection;

public class DBConnectionTest {

	@Test
	public void testDBConnection() {
		
		try{
			ConfigUtil config = null;
			DBConnection instance = new DBConnection(config);
		}
		catch(Exception e){
			System.out.println("DBConnection() failed\n");
		}
		
		System.out.println("DBConnection() done\n");
	}

	@Test
	public void testGetConnection() {
		ConfigUtil config = null;
		DBConnection instance = new DBConnection(config);
		
		try{
			instance.getConnection();
		}
		catch(Exception e){
			System.out.println("getConnection() failed\n");
		}
		
		System.out.println("getConnection() done\n");
	}

}

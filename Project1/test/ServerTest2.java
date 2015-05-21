package server;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.eva.me.cm.ConfigUtil;

import database.DBConnection;
import server.Server;

public class ServerTest {
	
	
	public static Connection connect = null;

	@Test
	public void testMain() throws SQLException {
		
		try{
		Server instance = new Server();
		instance.init();
		ConfigUtil config = null;
		DBConnection db = new DBConnection(config);
		connect = db.getConnection();
		String selectSQL = "SELECT * FROM student_info WHERE name = ";
		PreparedStatement preparedStatement = connect.prepareStatement(selectSQL);
		
		String query="·½³Ì";
		
		preparedStatement.setString(1, query);
        ResultSet rs = preparedStatement.executeQuery();
        boolean is_empty = true;
        while(rs.next()){
			System.out.println("Student "+query+" is in Team "+rs.getInt("team_num"));
			is_empty = false;
		}
        if(is_empty) System.out.println("Student "+query+ " dosen't exist");
		rs.close();
		
		String expString = "7";
		assertEquals(expString==rs.getString(1),1);
		}
		catch(Exception e)
		{ System.out.println("main() function failed\n");}
		System.out.println("main() function finished \n");
		
	}

	@Test
	  public void testReadConfig() {
	    Server instance = new Server();
	    try{
	      instance.readConfig();
	    }
	    catch(Exception e){
	      System.out.println("ReadConfig() failed\n");
	    }
	    
	    System.out.println("ReadConfig() done\n");
	  }

	@Test
	  public void testInit() {
	    Server instance = new Server();
	    try{
	      instance.init();
	    }
	    catch(Exception e){
	      System.out.println("Initialization init() failed\n");
	    }
	    
	    System.out.println("Initialization init() done\n");
	  }
	
	
	@Test
	  public void testRun() {
	    Server instance = new Server();
	    
	    try{
	      instance.run();
	    }
	    catch(Exception e){
	      System.out.println("Run Function failed\n");
	    }
	    
	    System.out.println("Run Function done\n");
	  }

}

package db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eva.me.cm.ConfigUtil;

public class DBConnectionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDBConnection() {
		try{
			ConfigUtil config = null;
			DBConnection instance = new DBConnection();
		}
		catch(Exception e){
			System.out.println("DBConnection() failed\n");
		}
		
		System.out.println("DBConnection() done\n");
	}

	@Test
	public void testGetConnection() {
		ConfigUtil config = null;
		DBConnection instance = new DBConnection();
		
		try{
			instance.getConnection();
		}
		catch(Exception e){
			System.out.println("getConnection() failed\n");
		}
		
		System.out.println("getConnection() done\n");
	}

}



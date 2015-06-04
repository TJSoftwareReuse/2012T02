package main;

import static org.junit.Assert.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class MainTest {

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
	public void testMain() {
		System.out.println("main");
		String hostname = ConfigUtil.getInstance().getProperty("SERVER_NAME");
		int port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
		try{
			Registry registry = LocateRegistry.getRegistry(hostname, port);
            TeamQueryInterface teamQueryInterface = (TeamQueryInterface) registry.lookup("teamQuery");
            if(true){
            	String team = "2";
            	ArrayList<String> members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
            	
            	assertTrue(members.contains("Õı–¶”Ø"));
            	assertTrue(members.contains("ÀÔ¡’"));
            	assertTrue(members.contains("–ÌΩ¢"));
            	assertTrue(members.contains("¿ÓŒ∞"));
            	assertTrue(members.contains("πÿ≥ø"));
            	
            	members.clear();
            	
            	
            	team = "-1";
            	members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
            	assertTrue(members.isEmpty());
            	
            	System.out.println("test of main() done");
            	
            }
		}catch(RemoteException e){
            	e.printStackTrace();
            	fail("test of main failed");
            	
        }catch (NotBoundException e) {
            	e.printStackTrace();
            	fail("test of main failed");
				// TODO: handle exception
	    }
		
		
	}

}

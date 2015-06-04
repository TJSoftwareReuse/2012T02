package server5;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.com.team8.License.License;

public class TeamQueryImplTest {

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
	public void testGetTeamMember() {
		 System.out.println("getTeamMember");
	        TeamQueryImpl instance = new TeamQueryImpl();
	        ArrayList<String> nameList = new ArrayList<String>();
	        nameList=instance.getTeamMember(2);
	        assertTrue(nameList.contains("Õı–¶”Ø"));
	        assertTrue(nameList.contains("ÀÔ¡’"));
	        assertTrue(nameList.contains("–ÌΩ¢"));
	        assertTrue(nameList.contains("¿ÓŒ∞"));
	        assertTrue(nameList.contains("πÿ≥ø"));
	        nameList.clear();
	        nameList=instance.getTeamMember(-1);
	        assertTrue(nameList.isEmpty());
	        while(License.inLicense().getRestLicenseCapacity()>0){
	            nameList=instance.getTeamMember(2);
	        }
	        nameList.clear();
	        nameList=instance.getTeamMember(2);
	        assertTrue(nameList==null);
	        System.out.println("test of getTeamMember() done");
	        
	    
	}

	@Test
	public void testGetUserTeam() throws RemoteException {
		TeamQueryImpl instance = new TeamQueryImpl();
		String expTeam = "2";
		String actualOutput = instance.getUserTeam("Õı–¶”Ø");
		assertEquals(actualOutput==expTeam, 1);
	}

}

package server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Server;

public class ServerTest {
	
	@Before
	public void setUp() throws Exception {
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
			instance.run();;
		}
		catch(Exception e){
			System.out.println("Run Function failed\n");
		}
		
		System.out.println("Run Function done\n");
	}

}

package t2.server2.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import t2.server2.impl.TeamQueryImpl;
import t2.server2.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		TeamQueryImpl teamQuery = new TeamQueryImpl();
		TeamQueryInterface teamQueryInterface = (TeamQueryInterface) UnicastRemoteObject.exportObject(teamQuery, 0);
		Registry registry = LocateRegistry.createRegistry(Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT")));
		registry.rebind("teamQuery", teamQueryInterface);
		
		System.out.println("in service...");

	}

}

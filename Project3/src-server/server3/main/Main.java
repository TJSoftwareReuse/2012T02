package server3.main;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server3.impl.TeamQueryImpl;
import server3.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class Main {

	public static void main(String[] args) throws AlreadyBoundException, RemoteException {
		TeamQueryImpl teamQuery = new TeamQueryImpl();
		TeamQueryInterface teamQueryInterface = (TeamQueryInterface) UnicastRemoteObject.exportObject(teamQuery, 0);
		Registry registry = LocateRegistry.createRegistry(Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT")));
		registry.rebind("teamQuery", teamQueryInterface);
		System.out.println("in service...");

	}

}

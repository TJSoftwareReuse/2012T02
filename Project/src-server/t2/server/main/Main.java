package t2.server.main;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import t2.server.impl.UserQueryImpl;
import t2.server.stub.UserQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class Main {

	public static void main(String[] args) throws AlreadyBoundException, RemoteException{
		UserQueryImpl userQuery = new UserQueryImpl();
		UserQueryInterface userQueryInterface = (UserQueryInterface) UnicastRemoteObject.exportObject(userQuery, 0);
		Registry registry = LocateRegistry.createRegistry(Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT")));
		registry.rebind("userQuery", userQueryInterface);
		
		System.out.println("in service...");
	}

}

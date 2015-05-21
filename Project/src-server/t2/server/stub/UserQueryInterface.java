package t2.server.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserQueryInterface extends Remote {
	public String getUserTeam(String name) throws RemoteException;
}

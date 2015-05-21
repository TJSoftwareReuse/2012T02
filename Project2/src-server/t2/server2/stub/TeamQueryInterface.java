package t2.server2.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TeamQueryInterface extends Remote {
	ArrayList<String> getTeamMember(int team) throws RemoteException; 
}

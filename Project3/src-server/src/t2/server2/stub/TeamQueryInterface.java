package t2.server2.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import t2.server2.queryresultmessage.QueryResultMessage;

public interface TeamQueryInterface extends Remote {
	public QueryResultMessage getTeamMember(int team) throws RemoteException;
	public QueryResultMessage getUserTeam(String name) throws RemoteException;
}
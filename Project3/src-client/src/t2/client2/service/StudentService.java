package t2.client2.service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import t2.server2.queryresultmessage.QueryResultMessage;
import t2.server2.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class StudentService {
	private String hostname;
	private int port;
	private TeamQueryInterface teamQueryInterface;
	
	public StudentService(){
		hostname = ConfigUtil.getInstance().getProperty("SERVER_HOSTNAME");
		port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(hostname, port);
			teamQueryInterface = (TeamQueryInterface) registry.lookup("teamQuery");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e1){
			e1.printStackTrace();
		}
	}
	
	public void getTeamMemberService() throws RemoteException{
		System.out.println("Team: (Please input team number)");
		@SuppressWarnings("resource")
		String team = new Scanner(System.in).next().toString();
		
		int teamNo = 0; 
		try{
			teamNo = Integer.valueOf(team);
		}catch(NumberFormatException e){
			System.out.println(team + " isn't a team number, please input a team number!");
			return;
		}

		QueryResultMessage qrm = teamQueryInterface.getTeamMember(teamNo);
		
		if(!qrm.isSuccess()){
			System.out.println(qrm.getMessage());
		}else{
			@SuppressWarnings("unchecked")
			ArrayList<String> members = (ArrayList<String>) qrm.getResult();
			if(members.isEmpty()){
				System.out.println("Team "+team+" don't has any members");
			}else{
				System.out.println("Team "+team+" has "+members.size()+" member(s):");
				for(int i = 0; i < members.size(); ++i){
					System.out.println(members.get(i));
				}
			}
		}
	}
	
	public void getUserTeamService() throws RemoteException{
		System.out.println("Name: (Please input student name)");
		@SuppressWarnings("resource")
		String userName = new Scanner(System.in).next().toString();
		QueryResultMessage qrm = teamQueryInterface.getUserTeam(userName);
		if(!qrm.isSuccess()){
			System.out.println(qrm.getMessage());
		}else{
			String team = (String) qrm.getResult();
			System.out.println("Student "+userName+" is in Team "+team);
		}
	}
}

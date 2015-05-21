package t2.client2.main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import t2.server2.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;

public class Main {

	public static void main(String[] args) {
		String hostname = ConfigUtil.getInstance().getProperty("SERVER_HOSTNAME");
		int port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
		try {
			Registry registry = LocateRegistry.getRegistry(hostname, port);
			TeamQueryInterface teamQueryInterface = (TeamQueryInterface) registry.lookup("teamQuery");
			
			while(true){
				System.out.print("Team('quit' for exit): ");
				String team = new Scanner(System.in).next().toString();
				
				if(team.toLowerCase().equals("quit")) break;
				ArrayList<String> members = teamQueryInterface.getTeamMember(Integer.valueOf(team));
				
				if(null == members){
					System.out.println("Reject: License out of limit!");
				}else if(members.isEmpty()){
					System.out.println("Team "+team+" don't has any members");
				}else{
					System.out.println("Team "+team+" has "+members.size()+" member(s):");
					for(int i = 0; i < members.size(); ++i){
						System.out.println(members.get(i));
					}
				}
				
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}

package t2.client.main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import com.eva.me.cm.ConfigUtil;

import t2.server.stub.UserQueryInterface;

public class Main {

	public static void main(String[] args) {
		String hostname = ConfigUtil.getInstance().getProperty("SERVER_HOSTNAME");
		int port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
		try {
			Registry registry = LocateRegistry.getRegistry(hostname, port);
			UserQueryInterface userQueryInterface = (UserQueryInterface) registry.lookup("userQuery");
			
			while(true){
				System.out.print("Name('quit' for exit): ");
				String name = new Scanner(System.in).next().toString();
				
				if(name.toLowerCase().equals("quit")) break;
				String team = userQueryInterface.getUserTeam(name);
				if(team.equals("REJECT")){
					System.out.println("Reject: License out of limit!");
				}else if(team.equals("NOEXIST")){
					System.out.println("Student "+name+ " dosen't exist");
				}else{
					System.out.println("Student "+name+" is in Team "+team);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		

	}

}

package client3.main;

import com.eva.me.cm.ConfigUtil;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import server3.stub.TeamQueryInterface;

public class Main {

    public static void main(String[] args) {
        String hostname = ConfigUtil.getInstance().getProperty("SERVER_HOSTNAME");
        int port = Integer.valueOf(ConfigUtil.getInstance().getProperty("SERVER_PORT"));
        try {
            Registry registry = LocateRegistry.getRegistry(hostname, port);
            TeamQueryInterface teamQueryInterface = (TeamQueryInterface) registry.lookup("teamQuery");
            boolean isNum = false;
            while (true) {
                Pattern pattern = Pattern.compile("[1-8]");
                System.out.print("input('quit' for exit): ");
                String input_info = new Scanner(System.in).next();

                if (input_info.toLowerCase().equals("quit")) {
                    break;
                }
                isNum = pattern.matcher(input_info).matches();
                if (!isNum) {
                    System.out.println("Search for team number!");
                    String team = teamQueryInterface.getUserTeam(input_info);
                    if (team.equals("REJECT")) {
                        System.out.println("Reject: License out of limit!");
                    } else if (team.equals("NOEXIST")) {
                        System.out.println("Student " + input_info + " dosen't exist");
                    } else {
                        System.out.println("Student " + input_info + " is in Team " + team);
                    }
                } else {
                    System.out.println("Search for team members!");
                    ArrayList<String> members = teamQueryInterface.getTeamMember(Integer.valueOf(input_info));

                    if (null == members) {
                        System.out.println("Reject: License out of limit!");
                    } else if (members.isEmpty()) {
                        System.out.println("Team " + input_info + " don't has any members");
                    } else {
                        System.out.println("Team " + input_info + " has " + members.size() + " member(s):");
                        for (int i = 0; i < members.size(); ++i) {
                            System.out.println(members.get(i));
                        }
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

package t2.client2.main;

import java.rmi.RemoteException;
import java.util.Scanner;

import t2.client2.service.StudentService;

public class Main {
	public static void main(String[] args) throws NumberFormatException, RemoteException {
		StudentService service = new StudentService();
		while(true){
			System.out.println("--------------------------------------------------------");
			System.out.println("Please choose service: \n 1: get team member\n 2: get student team\n 0: quit");
			System.out.println("--------------------------------------------------------");
			System.out.println("Command Number: ");
			@SuppressWarnings("resource")
			String serviceType = new Scanner(System.in).next().toString();
			switch(serviceType){
			case "0":
			{
				System.out.println("End Service");
			}
				return;
			case "1":
			{
				service.getTeamMemberService();
			}
				break;
			case "2":
			{
				service.getUserTeamService();
			}
				break;
			default:
			{
				System.out.println("Error Command!");
			}
				break;
			}
			
		}
	}

}

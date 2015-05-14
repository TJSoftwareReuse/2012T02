package edu.tongji.server;

import java.sql.*;
import java.util.Scanner;

import com.eva.me.cm.ConfigUtil;
import com.manager.failure.FailureManager;
import com.team8.PerformanceManagement.PM;
import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;

import edu.tongji.database.DBConnection;

public class Server {
	
	private static ConfigUtil config;
	private static Connection connect;
	private static LicenseManager licenseManager;

	public static void main(String[] args) {
		readConfig();
		init();
		if(null == connect){
			return;
		}
		run();
	}
	
	public static void readConfig(){
		config = ConfigUtil.getInstance();
	}
	
	public static void init(){
		FailureManager.resetOutputFile(config.getProperty("FM_PATH"));
		PM.setPathName(config.getProperty("PM_PATH"));
		System.out.println(PM.getPathName());
		PM.sendPMMessage("message", 1);
		licenseManager = LicenseManager.getInstance();
		licenseManager.setLicenseCapacity(Integer.parseInt(config.getProperty("LICENSE_NUM")));
		
		DBConnection db = new DBConnection(config);
		connect = db.getConnection();
	}
	
	public static void run(){
		FailureManager.logInfo("System start run");
		try {
			String selectSQL = "SELECT * FROM student_info WHERE name = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(selectSQL);
			String query;
			
			while(true){
				System.out.print("Name('quit' for exit): ");
				query = new Scanner(System.in).next().toString();
				
				if(query.toLowerCase().equals("quit")) break;
				PM.sendPMMessage("NEW_REQUEST", 1);
				
				
				CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
		        RequestResultMessage rrm = licenseManager.requestLicense(callerMessage);
		         
		        if(rrm.isSuccess()){
		        	FailureManager.logInfo("Provide service, query: "+query);
		            PM.sendPMMessage("PROVIED_REQUEST", 1);
		            preparedStatement.setString(1, query);
		            ResultSet rs = preparedStatement.executeQuery();
		            boolean is_empty = true;
		            while(rs.next()){
						System.out.println("Student "+query+" is in Team "+rs.getInt("team_num"));
						is_empty = false;
					}
		            if(is_empty) System.out.println("Student "+query+ " dosen't exist");
					rs.close();
		        }else{
		            FailureManager.logError("Reject service, query: "+query);
		            PM.sendPMMessage("REJECT_REQUEST", 1);
		            System.out.println("Reject: License out of limit!");
		        }
		        
				PM.sendPMMessage("END_REQUEST", 1);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			FailureManager.logFatal("Server run: sql execute error");
			e.printStackTrace();
		}
		
		System.out.println("See you~");
		FailureManager.logInfo("System shut down");
	}
	
}

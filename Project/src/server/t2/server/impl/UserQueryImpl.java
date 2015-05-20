package t2.server.impl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eva.me.cm.ConfigUtil;
import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.manager.failure.FailureManager;
import com.team8.PerformanceManagement.PM;

import t2.server.db.DBConnection;
import t2.server.stub.UserQueryInterface;

public class UserQueryImpl implements UserQueryInterface {

	private String getUserTeamSQL = "SELECT * FROM student_info WHERE name = ?";
	private LicenseManager licenseManager;
	private ConfigUtil config;

	public UserQueryImpl(ConfigUtil config) throws RemoteException{

		FailureManager.resetOutputFile(config.getProperty("FM_PATH"));
		PM.setPathName(config.getProperty("PM_PATH"));
		licenseManager = LicenseManager.getInstance();
		licenseManager.setLicenseCapacity(Integer.parseInt(config.getProperty("LICENSE_NUM")));
		
		this.config = config;
	}

	@Override
	public String getUserTeam(String name) throws RemoteException {
		String ans = "NOEXIST";
		try{
			PM.sendPMMessage("NEW_REQUEST", 1);

			CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
			RequestResultMessage rrm = licenseManager.requestLicense(callerMessage);
			
			if(rrm.isSuccess()){
				FailureManager.logInfo("Provide service, query: "+name);
				PM.sendPMMessage("PROVIED_REQUEST", 1);
				
				DBConnection db = new DBConnection(config);
				Connection connect = db.getConnection();
				
				PreparedStatement getUserTeamStat = connect.prepareStatement(getUserTeamSQL);
				getUserTeamStat.setString(1, name);
				ResultSet rs = getUserTeamStat.executeQuery();

				while(rs.next()){
					ans = String.valueOf(rs.getInt("team_num"));
				}
				rs.close();
				getUserTeamStat.close();
				connect.close();

			}else{
				FailureManager.logError("Reject service, query: "+name);
				PM.sendPMMessage("REJECT_REQUEST", 1);
				ans = "REJECT";
			}

			PM.sendPMMessage("END_REQUEST", 1);

		}catch(Exception e){
			System.out.println("getUserTeam error");
			e.printStackTrace();
		}

		return ans;
	}

}

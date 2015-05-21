package t2.server2.impl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import t2.server2.db.DBConnection;
import t2.server2.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;
import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.manager.failure.FailureManager;
import com.team8.PerformanceManagement.PM;

public class TeamQueryImpl implements TeamQueryInterface{
	
	private String getTeamMembSQL = "SELECT * FROM student_info WHERE team_num = ?";
	
	public TeamQueryImpl() throws RemoteException {
		ConfigUtil config = ConfigUtil.getInstance();
		FailureManager.resetOutputFile(config.getProperty("FM_PATH"));
		PM.setPathName(config.getProperty("PM_PATH"));
		LicenseManager.getInstance().setLicenseCapacity(Integer.parseInt(config.getProperty("LICENSE_NUM")));
	}

	@Override
	public ArrayList<String> getTeamMember(int team) throws RemoteException {
		ArrayList<String> members = new ArrayList<String>();
		try{
			PM.sendPMMessage("NEW_REQUEST", 1);

			CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
			RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(callerMessage);
			
			if(rrm.isSuccess()){
				FailureManager.logInfo("Provide service, query: "+team);
				PM.sendPMMessage("PROVIED_REQUEST", 1);
				
				DBConnection db = new DBConnection();
				Connection connect = db.getConnection();
				
				PreparedStatement getUserTeamStat = connect.prepareStatement(getTeamMembSQL);
				getUserTeamStat.setInt(1, team);
				ResultSet rs = getUserTeamStat.executeQuery();

				while(rs.next()){
					members.add(rs.getString("name"));
				}
				rs.close();
				getUserTeamStat.close();
				connect.close();

			}else{
				FailureManager.logError("Reject service, query: "+team);
				PM.sendPMMessage("REJECT_REQUEST", 1);
				members = null;
			}

			PM.sendPMMessage("END_REQUEST", 1);

		}catch(Exception e){
			System.out.println("getUserTeam error");
			e.printStackTrace();
		}

		return members;
	}

}

package t2.server2.impl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import t2.server2.db.DBConnection;
import t2.server2.queryresultmessage.QueryResultMessage;
import t2.server2.stub.TeamQueryInterface;

import com.eva.me.cm.ConfigUtil;
import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.manager.failure.FailureManager;
import com.team8.PerformanceManagement.PM;

public class TeamQueryImpl implements TeamQueryInterface{
	
	public TeamQueryImpl() throws Exception {
		ConfigUtil config = ConfigUtil.getInstance();
		FailureManager.resetOutputFile(config.getProperty("FM_PATH"));
		PM.setPathName(config.getProperty("PM_PATH"));
		LicenseManager.getInstance().setLicenseCapacity(Integer.parseInt(config.getProperty("LICENSE_NUM")));
	}

	public boolean isTeamNumExist(int team) throws SQLException{
		DBConnection db = new DBConnection();
		Connection conn = db.getConnection();
		
		String getTeamCountSQL = "SELECT team_num FROM student_info";
		Statement stmt = conn.createStatement();
		ResultSet rsCount = stmt.executeQuery(getTeamCountSQL);

		boolean bExist = false;
		while(rsCount.next()){
			if(team == rsCount.getInt(1)){
				bExist =  true;
			}
		}
		rsCount.close();
		stmt.close();
		conn.close();
		return bExist;
	}
	
	@Override
	public QueryResultMessage getTeamMember(int team) throws RemoteException {
		QueryResultMessage qrm = new QueryResultMessage();
		ArrayList<String> members = new ArrayList<String>();
		try{
			PM.sendPMMessage("NEW_REQUEST (Get_Team_Member)", 1);

			CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
			RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(callerMessage);
			
			if(rrm.isSuccess()){
				FailureManager.logInfo("Provide service (Get_Team_Member), query: "+team);
				PM.sendPMMessage("PROVIED_REQUEST (Get_Team_Member)", 1);
				
				if(!isTeamNumExist(team)){
					qrm.setSuccess(false);
					qrm.setMessage("team " + team +" doesn't exist!");
					return qrm;
				}
				
				DBConnection db = new DBConnection();
				Connection connect = db.getConnection();

				String getTeamMembSQL = "SELECT * FROM student_info WHERE team_num = ?";
				PreparedStatement getUserTeamStat = connect.prepareStatement(getTeamMembSQL);
				getUserTeamStat.setInt(1, team);
				ResultSet rs = getUserTeamStat.executeQuery();

				while(rs.next()){
					members.add(rs.getString("name"));
				}
				
				qrm.setSuccess(true);
				qrm.setMessage("Success!");
				qrm.setResult(members);
				
				rs.close();
				getUserTeamStat.close();
				connect.close();

			}else{
				FailureManager.logError("Reject service (Get_Team_Member), query: "+team);
				PM.sendPMMessage("REJECT_REQUEST (Get_Team_Member)", 1);
				qrm.setSuccess(false);
				qrm.setMessage("Reject: License out of limit!");
			}

			PM.sendPMMessage("END_REQUEST (Get_Team_Member)", 1);

		}catch(Exception e){
			qrm.setSuccess(false);
			qrm.setMessage("Error: There are some errors in Get_Team_Member request. Info: " + e.getMessage());
			System.out.println("There are some errors in Get_Team_Member request.");
			e.printStackTrace();
		}

		return qrm;
	}

	@Override
	public QueryResultMessage getUserTeam(String name) throws RemoteException {
		// TODO Auto-generated method stub
		QueryResultMessage qrm = new QueryResultMessage();
		try{
			PM.sendPMMessage("NEW_REQUEST (Get_User_Team)", 1);
			CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
			RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(callerMessage);
			if(rrm.isSuccess()){
				FailureManager.logInfo("Provide service (Get_User_Team), query: " + name);
				PM.sendPMMessage("PROVIED_REQUEST (Get_User_Team)", 1);
				
				DBConnection db = new DBConnection();
				Connection connect = db.getConnection();
				
				String getUserTeamSQL = "SELECT * FROM student_info WHERE name = ?";
				PreparedStatement getUserTeamStat = connect.prepareStatement(getUserTeamSQL);
				getUserTeamStat.setString(1, name);
				ResultSet rs = getUserTeamStat.executeQuery();
				
				String userTeam = null;
				while(rs.next()){
					userTeam = String.valueOf(rs.getInt("team_num"));
				}
				if(userTeam == null){
					qrm.setSuccess(false);
					qrm.setMessage("Error! the student " + name +" , doesn't exist");
					return qrm;
				}else{
					qrm.setSuccess(true);
					qrm.setMessage("Success!");
					qrm.setResult(userTeam);
				}
				rs.close();
				getUserTeamStat.close();
				connect.close();
			}else{
				FailureManager.logError("Reject service (Get_User_Team), query: "+name);
				PM.sendPMMessage("REJECT_REQUEST (Get_User_Team)", 1);
				qrm.setSuccess(false);
				qrm.setMessage("Reject: License out of limit!");
			}
			PM.sendPMMessage("END_REQUEST (Get_User_Team)", 1);
		}catch(Exception e){
			System.out.println("get user team error");
			e.printStackTrace();
			qrm.setSuccess(false);
			qrm.setMessage("Error! There are some errors in Get_Team_Member request. INFO: " + e.getMessage());
		}
		return qrm;
	}

}

package server3.impl;

import com.PerformanceManagement.PM;
import com.eva.me.cm.ConfigUtil;
import com.lisence.CallerMessage;
import com.lisence.LicenseManager;
import com.lisence.RequestResultMessage;
import com.manager.failure.FailureManager;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import server3.db.DBConnection;
import server3.stub.TeamQueryInterface;

public class TeamQueryImpl implements TeamQueryInterface {

    private String getTeamMembSQL = "SELECT * FROM student_info WHERE team_num = ?";
    private String getUserTeamSQL = "SELECT * FROM student_info WHERE name = ?";

    public TeamQueryImpl() throws RemoteException {
        ConfigUtil config = ConfigUtil.getInstance();
        FailureManager.resetOutputFile(config.getProperty("FM_PATH"));
        PM.setPathName(config.getProperty("PM_PATH"));
        LicenseManager.getInstance().setLicenseCapacity(Integer.parseInt(config.getProperty("LICENSE_NUM")));
    }

    @Override
    public ArrayList<String> getTeamMember(int team) throws RemoteException {
        ArrayList<String> members = new ArrayList<String>();
        try {
            PM.sendPMMessage("NEW_REQUEST", 1);

            CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
            RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(callerMessage);

            if (rrm.isSuccess()) {
                FailureManager.logInfo("Provide service, query: " + team);
                PM.sendPMMessage("PROVIED_REQUEST", 1);

                DBConnection db = new DBConnection();
                Connection connect = db.getConnection();

                PreparedStatement getUserTeamStat = connect.prepareStatement(getTeamMembSQL);
                getUserTeamStat.setInt(1, team);
                ResultSet rs = getUserTeamStat.executeQuery();

                while (rs.next()) {
                    members.add(rs.getString("name"));
                }
                rs.close();
                getUserTeamStat.close();
                connect.close();

            } else {
                FailureManager.logError("Reject service, query: " + team);
                PM.sendPMMessage("REJECT_REQUEST", 1);
                members = null;
            }

            PM.sendPMMessage("END_REQUEST", 1);

        } catch (SQLException e) {
            System.out.println("getUserTeam error");
        }
        return members;
    }

    @Override
    public String getUserTeam(String name) throws RemoteException {
        String ans = "NOEXIST";
        try {
            PM.sendPMMessage("NEW_REQUEST", 1);

            CallerMessage callerMessage = new CallerMessage("SOFTWARE-REUSE-TEAM2");
            RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(callerMessage);

            if (rrm.isSuccess()) {
                FailureManager.logInfo("Provide service, query: " + name);
                PM.sendPMMessage("PROVIED_REQUEST", 1);

                DBConnection db = new DBConnection();
                Connection connect = db.getConnection();

                PreparedStatement getUserTeamStat = connect.prepareStatement(getUserTeamSQL);
                getUserTeamStat.setString(1, name);
                ResultSet rs = getUserTeamStat.executeQuery();

                while (rs.next()) {
                    ans = String.valueOf(rs.getInt("team_num"));
                }
                rs.close();
                getUserTeamStat.close();
                connect.close();

            } else {
                FailureManager.logError("Reject service, query: " + name);
                PM.sendPMMessage("REJECT_REQUEST", 1);
                ans = "REJECT";
            }

            PM.sendPMMessage("END_REQUEST", 1);

        } catch (SQLException e) {
            System.out.println("getUserTeam error");
        }

        return ans;
    }

}

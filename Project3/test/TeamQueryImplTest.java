package com.server2.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.license.manager.LicenseManager;

import t2.server2.impl.TeamQueryImpl;
import t2.server2.queryresultmessage.QueryResultMessage;


public class TeamQueryImplTest {
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test
	public void testGetTeamMember_TeamExist() throws Exception{
		//team 2 exist
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		QueryResultMessage qrm = tqImpl.getTeamMember(2);
		@SuppressWarnings("unchecked")
		ArrayList<String> members = (ArrayList<String>) qrm.getResult();
		
		Assert.assertTrue("Cann't get the team members in a team.", qrm.isSuccess());
		Assert.assertEquals("Cann't get the complete team members.", 5, members.size());
	}
	
	@Test
	public void testGetTeamMember_TeamNotExist() throws Exception{
		// team -1 doesn't exist
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		QueryResultMessage qrm = tqImpl.getTeamMember(-1);
		
		Assert.assertFalse("Get members from the not exist team_no", qrm.isSuccess());
	}
	
	@Test
	public void testGetTeamMember_License_Out_Of_Limit() throws Exception{
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		
		// let the server has no license.
		LicenseManager.getInstance().setLicenseCapacity(1);
		tqImpl.getTeamMember(2);
		
		//send the query request
		QueryResultMessage qrm = tqImpl.getTeamMember(2);
		
		Assert.assertFalse("Through there no license in server, the server can provide query service",
				qrm.isSuccess());
		Assert.assertEquals("Reject: License out of limit!", qrm.getMessage());
	}
	
	@Test
	public void testGetUserTeam_UserExist() throws Exception{
		//User 许舰 exist, belongs to team 2
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		
		QueryResultMessage qrm = tqImpl.getUserTeam("许舰");
		int team_no = Integer.parseInt(String.valueOf(qrm.getResult()));
		
		Assert.assertTrue("Cann't get user's team_no information!", qrm.isSuccess());
		Assert.assertEquals(2, team_no);
	}
	
	@Test
	public void testGetUserTeam_UserNotExist() throws Exception{
		//User HHH doesn't exist
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		QueryResultMessage qrm = tqImpl.getUserTeam("HHH");
		
		Assert.assertFalse("Through the user doesn't exist, server can return team_no", qrm.isSuccess());
	}
	
	@Test
	public void testGetUserTeam_License_Out_Of_Limit() throws Exception{
		TeamQueryImpl tqImpl = new TeamQueryImpl();
		
		// let the server has no license.
		LicenseManager.getInstance().setLicenseCapacity(1);
		tqImpl.getUserTeam("许舰");
		
		QueryResultMessage qrm = tqImpl.getUserTeam("许舰");
		
		Assert.assertFalse("Through there no license in server, the server can provide query service",
				qrm.isSuccess());
		Assert.assertEquals("Reject: License out of limit!", qrm.getMessage());
	}
}

# 项目测试文档
本次主要针对服务器端进行测试，测试分为两部分，一个是对```DBConnection```类功能的测试；另外是对```TeamQueryImpl```类功能的测试。

1. ```DBConnectionTest```类中包含两个测试方法，如下所示：
	* public void testGetConnection_CorrectProperty(); 设置正确的数据库连接属性，测试DBConnection能否连接上数据库。
	* public void testGetConnection_ErrorProperty(); 设置不正确的数据库连接属性，测试DBConnection能否连接上数据库。

2. ```TeamQueryImplTest```类中包含六个测试方法，他们分别如下：
	* public void testGetTeamMember_TeamExist() throws Exception; 在所查询的组号存在的情况下，看能否获得正确的组员信息。
	* public void testGetTeamMember_TeamNotExist() throws Exception; 在所查询组号不存在的情况下，看能否获得组员的信息。
	* public void testGetTeamMember_License_Out_Of_Limit() throws Exception;测试在serve的license资源耗尽的情况下，还能否进行组员的查询。
	* public void testGetUserTeam_UserExist() throws Exception;测试在组员名字存在的情况下，能否获得组员的组号信息。
	* public void testGetUserTeam_UserNotExist() throws Exception;测试在组员名字不存在的情况下，方法能否对错误的组员名字做出正确的响应。
	* public void testGetUserTeam_License_Out_Of_Limit() throws Exception; 测试在系统license资源耗尽的情况下，还能否进行组员组号的查询。
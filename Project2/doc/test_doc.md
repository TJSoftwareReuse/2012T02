#项目测试文档
该项目的测试代码在```DBConnectionTest.java```, ```ServerTest2.java ```这三个java源文件中。


* ```DBConnectionTest.java ``` 中主要测试与数据库的链接功能，其中```public void testDBConnection()```方法测试能否成功获得```DBConnection```这个数据库连接实例；而```public void testGetConnection() ```方法则是测试能否成功与数据库建立连接。
* ```ServerTest2.java ```  主要测试服务类的功能，```public void testMain() throws SQLException ```方法测试输入一个学生的名字后能否获得学生正确的组号；``` public void testReadConfig()```方法则是测试```Server```类能否正确地读取程序相关的配置信息，例如数据库配置信息，许可证配置信息等；```public void testRun()```方法测试```Server```类能否正确地给用户提供连续的查询服务。
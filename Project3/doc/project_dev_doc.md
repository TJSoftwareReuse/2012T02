# 项目开发文档

### 项目更新部分
这次我们将project1和project2的功能融合到了一起，并且使用了更新后的四个组件：
1. FM文件路径： 支持下一条告警切换到新文件
2. PM文件路径： 输出文件路径可更改
3. PM输出时间间隔： 性能文件日志名称包含起始时间和结束时间，支持以当前配置完成性能文件输出，并从下一个文件开始使用新配置。
4. License数量： 当License数量修改后，从0开始重新计数。

### 项目模块说明
1. 项目采用RMI的Server-Client方式
2. Client 端负责与用户进行交互，通过client端，用户可以：
	1. 通过输入组号查询该组所有的成员。
	2. 通过输入一个成员名来查询该成员所在的组号。
3. Sercer 端向客户端提供两种查询服务：
	1. 接收客户端传递来的组号信息，然后将该组的成员信息发送给客户端。
	2. 接收客户端传递过来的成员名称信息，然后将该成员所在的组号信息发送给客户端。
4. Server端还具有系统日志输出功能，这些系统日志包括：
	1. PM日志信息
		* NEW_REQUEST (Service_Name): num   表示服务器接收到新请求
		* END_REQUEST (Service Name): num	表示服务器成功处理一个请求
		* PROVIED_REQUEST (Service Name): num	表示服务器开始为一个请求服务
		* REJECT_REQUEST (Service Name): num	表示服务器拒绝为一个请求服务
	其中Service Name表示服务器提供的服务类型，num表示请求的次数。
	2. FM日志信息
		* YYYY-MM-dd HH: mm :ss INFO INFO_TYPE, query: Query_Parameter
	其中 INFO_TYPE 表示信息类型， Query_Parameter 表示服务器在处理该请求时 客户端传递过来的参数。

### 开发环境

* jdk 1.8
* FM - Team2
* License - Team2
* PM - Team8
* CM - Team1
* Mysql + JDBC
* JUNIT测试工具

##### 配置文件

* 配置文件名为config.properties，位于项目的根目录下。
* 文件示例（示例在```/data```目录下）

###### server端配置（server-config.properties）

```
# server 端口号
SERVER_PORT = 8899
# FM构件输出文件的路径+文件名
FM_PATH=/Users/momo/program/eclipse-workspace/server/fm.log
# PM构件输出文件的路径
PM_PATH=/Users/momo/program/eclipse-workspace/server/pm
# LICENSE构件的容量
LICENSE_NUM=5
# 数据库相关配置，给出的为本地mysql数据库的配置，数据库名为student，端口号为3306，用户名和密码均为momo（当然也可以采用远程访问数据库的形式）
DB_NAME=mysql
DB_DRIVER_CLASS=com.mysql.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/student
DB_USER=momo
DB_PASSWORD=momo
```

###### client端配置(client-config.properties)

```
# server主机名称，此例中server与client运行在同一台机器上，因此为localhost
SERVER_HOSTNAME = localhost
# server 端口号，此端口号需与server端保持一致
SERVER_PORT = 8899
```

### 系统说明

##### 准备步骤
* 导入数据
* 新建项目Server和Client，将相应代码导入
* 将```/jar/server.jar```包和CM组件导入Client项目中
* 将```/jar/mysql-connector-java-5.1.35-bin.jar```和四个组件导入Server项目中
* 在Server项目中如之前所说完成配置文件
* 在Client项目中如之前所说完成配置文件


##### Client端查询说明
client端在启动时，输出提示信息如下：

```
--------------------------------------------------------
Please choose service: 
 1: get team member
 2: get student team
 0: quit
--------------------------------------------------------
Command Number: 
```

其中1代表查询一个组的所有成员，输入1后，信息如下：
```
Command Number: 
1
Team: (Please input team number)
2
Team 2 has 5 member(s):
李伟
许舰
王笑盈
关晨
孙琳
```

2代表查询组员所在组的组号，输入2后，信息如下：
```
Command Number: 
2
Name: (Please input student name)
李伟
Student 李伟 is in Team 2
```
0代表退出查询，示例如下：
```
Command Number: 
0
End Service
```
##### 系统运行流程

1. 启动数据库
2. 启动Server
3. 启动Client
4. 开始查询


### Server端结构设计

文件名称  | 执行功能
------------- | -------------
UserQueryInterface.java | 服务接口
UserQueryImpl.java | 服务接口实现
DBConnection.java | 数据库连接类
RequestMessage.java | 请求服务的返回消息
Main.java | 注册RMI接口，指定服务端口

### Client端结构设计
文件名称 | 执行功能
------------- | -------------
StudentService.java | 提供查询学生信息的各种方式

### server端代码实现

1. ```UserQueryInterface``` 类为rmi的注册接口，里面提供了两个服务方法的申明：

	* ```public QueryResultMessage getTeamMember(int team) throws RemoteException;``` 提供查询组成员服务的接口方法。 传入参数：组号；返回值：```QueryResultMessage``` 包含查询结果信息的类实例。 

	* ```public QueryResultMessage getUserTeam(String name) throws RemoteException;``` 提供查询学生所在组号信息的接口方法。传入参数：学生姓名；返回值：```QueryResultMessage``` 包含查询结果信息的类实例。 

2. ```UserQueryImpl``` 主要是对```UserQueryInterface```两个方法的实现。
	* 另外其包含一个私有方法：public boolean isTeamNumExist(int team) throws SQLException; 该方法辅助检测所查询的组号是否存在。输入参数：team，组号；返回值：boolean，若组号存在，那么返回true；否则返回false。

3. ```QueryResultMessage```类， 其包含查询结果的相关信息， 客户端通过接收到的该实例获得查询结果。该类包含的字段如下：
	* isSuccess： 此次查询是否成功
	* message： 查询成功或者失败的提示信息
	* result： 查询所得到的结果，若成功则为符合查询条件的实例；如失败则为null。
4. ```DBConnection```类主要负责连接数据库的操作，其中包含的方法如下：
	* public DBConnection(); 构造函数，负责读入配置文件中的配置属性（数据库连接属性，四个构件的连接属性等）
	* public Connection getConnection(); 负责从数据库中获得一个数据库连接。
5. ```MyFailureManager``` 类主要是根据项目的需要对原来fm构件的功能进行封装，从而实现同类型的连续的警告信息只输出一次。其中包含如下方法。
	* ```private static boolean isSameLog(String currentLog, String lastLog)``` 判断两条日志信息是否相同。
	* ```public static void logInfo(String log)``` 输出info类型的日志信息，连续相同的日志信息不再输出。
	* ```public static void logDebug(String log)``` 输出debug类型的日志信息，连续相同的日志信息不再输出。
	* ```public static void logWarn(String log) ``` 输出Warn类型的日志信息，连续相同的日志信息不再输出。
	* ```public static void logError(String log)``` 输出Error类型的日志信息，连续相同的日志信息不再输出。
	* ```public static void logFatal(String log) ``` 输出Fatal类型的日志信息，连续相同的日志信息不再输出。
	* ```public synchronized static boolean resetOutputFile(String filepath)``` 重新设置fm日志文件输出路径。

### client端代码实现
1. ```StudentService```类，该类主要负责将从服务器接受来的回应数据显示给用户， 其包含的方法如下：
	* public StudentService();  构造函数，负责与远端的服务器连接。
	* public void getTeamMemberService() throws NumberFormatException, RemoteException; 负责将某一组的组员信息显示给用户。
	* public void getUserTeamService() throws RemoteException; 负责将某一组员所在的组号信息显示给用户。

# 项目复用文档

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
QueryResultMessage.java | 请求服务的返回消息
Main.java | 注册RMI接口，指定服务端口

### Client端结构设计
文件名称 | 执行功能
------------- | -------------
StudentService.java | 提供查询学生信息的各种方式

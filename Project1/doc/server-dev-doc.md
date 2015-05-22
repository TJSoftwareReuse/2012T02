# Server 开发文档

### 功能说明
1. 项目采用RMI的Server-Client方式
2. Client端与用户交互，用户输入学生姓名（字符串），Client通过访问Server端将返回结果展现给用户
1. Server端从Client接收一个学生姓名（字符串），通过查询数据库，将学生所在组别返回给Client端
2. Server端记录以下数据：
	* 收到请求次数
	* 提供服务次数
	* 拒绝服务次数
	* 返回消息次数
3. Server端输出系统运行日志

### 开发环境

* jdk 1.8
* FM - Team2
* License - Team2
* PM - Team8
* CM - Team1
* Mysql + JDBC
* JUNIT测试工具
   
### 数据导入与配置

由于我没有服务器，所以暂不提供远程数据库支持，用户需自己将数据导入本地或其他服务器的数据库中

##### 数据导入

* 请新建数据库（样例名称为student）
* 请在目标数据库中执行 ```source /data/student.sql```命令，将数据导入数据库中
* 需注意更改配置数据库的配置选项
	
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
* 用户输入：
	* 查询： 学生姓名（String）
	* 退出： “quit”（大小写不限）
* server输出：
	* License分配失败，拒接提供查询： Reject: License out of limit!
	* 学生存在： Student __NAME__ is in Team __X__
	* 学生不存在： Student haha dosen't exist

	
##### Server端日志输出说明
* PM构件日志输出: __PM_PATH/YYYY-MM-DD HH-mm-ss.log__
	* NEW_REQUEST: xxx
	* PROVIED_REQUEST : xxx
	* REJECT_REQUEST: xxx
	* END_REQUEST: xxx

* FM构件日志输出： __FM_PATH__

事件 | 输出日志
------ | ------
系统接收查询请求 | _YYYY-MM-DD HH:mm:ss_ INFO Provide service, query: __name__
系统拒接查询请求 | _YYYY-MM-DD HH:mm:ss_ ERROR Reject service, query: __name__


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
Main.java | 注册RMI接口，指定服务端口


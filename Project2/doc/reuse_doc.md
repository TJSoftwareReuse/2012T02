# 项目复用文档
### 功能说明
1. 项目采用RMI的Server-Client方式
2. Client端与用户交互，用户输入组号，Client通过访问Server端将返回结果展现给用户
3. Server端从Client接收一个组号，通过查询数据库，将该组全部成员姓名返回给Client端
4. Server端记录以下数据：
	* 收到请求次数
	* 提供服务次数
	* 拒绝服务次数
	* 返回消息次数
5. Server端输出系统运行日志

### 开发环境
* jdk 1.8
* FM - Team2
* License - Team2
* PM - Team8
* CM - Team1
* Mysql + JDBC
* JUNIT测试工具

### 关于配置文件
__使用配置文件之前需要将配置文件名修改成为config.properties，并将配置文件置于项目的根目录下。__

1. server-config.properties：服务器端配置文件，包含如下配置：
	*  ```SERVER_PORT``` 服务器端的端口配置
	*  ```PM_PATH``` PM构件日志文件的保存目录，例如```PM_PATH=/Users/momo/program/eclipse-workspace/server/pm```.
	*  ```FM_PATH``` FM（告警管理）构件错误日志的输出目录+文件名称，例如：```FM_PATH=/Users/momo/program/eclipse-workspace/server/fm.log```
	*  ```LICENSE_NUM``` 系统License资源的上限值
	*  ```DB_NAME``` 服务端所使用的数据库类型
	*  ```DB_DRIVER_CLASS``` 数据库驱动类的具体包位置信息，例如mysql数据库的驱动类为```com.mysql.jdbc.Driver```
	*  ```DB_URL``` 数据库连接字符串，例如mysql数据库的连接字符串 示例为：```jdbc:mysql://localhost:3306/student```
	*  ```DB_USER``` 服务器端数据库用户名配置
	*  ```DB_PASSWORD``` 服务器端数据库访问密码配置
2. client-config.properties:客户端配置文件，包含如下配置：
	*  ```SERVER_HOSTNAME``` 客户端所访问服务器的地址配置信息
	*  ```SERVER_PORT``` 客户端所访问服务器的端口配置信息
3. student.sql：SQL数据文件，包含学生以及其所属团队的信息。__由于目前我们没有服务器，没有提供远程数据库访问支持，因此用户必须将数据导入本机或者其他服务器的数据库中。__ 在导入数据之前首先要确保```server-config.properties```中的数据库配置正确。

### Server端示例配置
```
SERVER_PORT=8899
FM_PATH=/Users/momo/program/eclipse-workspace/server/fm.log
PM_PATH=/Users/momo/program/eclipse-workspace/server/pm
LICENSE_NUM=5
DB_NAME=mysql
DB_DRIVER_CLASS=com.mysql.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/student
DB_USER=momo
DB_PASSWORD=momo
```

### Client端示例配置
```
SERVER_HOSTNAME=localhost
SERVER_PORT=8899
```

### 系统说明

##### 准备步骤
* 导入数据
* 新建项目Server和Client，将相应代码导入
* 将```/jar/server2.jar```包和CM组件导入Client项目中
* 将```/jar/mysql-connector-java-5.1.35-bin.jar```和四个组件导入Server项目中
* 在Server项目中如之前所说完成配置文件(将配置文件名修改成为config.properties，并将配置文件置于项目的根目录下)
* 在Client项目中如之前所说完成配置文件(将配置文件名修改成为config.properties，并将配置文件置于项目的根目录下)

##### Client端查询说明
* 用户输入：
	* 查询： 组号（Integer）
	* 退出： “quit”（String， 大小写不限）
* server输出：
	* License分配失败，拒接提供查询： Reject: License out of limit!
	* 学生存在： Team __NUMBER__ has __MEMBER_COUNT__ members:\n __XXX__ \n __XXX__...
	* 学生不存在： Team __NUMBER__ does not has any members

##### Server端输出日志说明
* PM构件日志输出: __PM_PATH/YYYY-MM-DD HH-mm-ss.log__
	* NEW_REQUEST: xxx  此时服务端接收到客户端的请求
	* PROVIED_REQUEST : xxx  表示此时服务端的license资源足够，服务端可以为客户端的请求服务
	* REJECT_REQUEST: xxx  表示此时服务端的License资源不足，服务端拒绝为客户端提供服务
	* END_REQUEST: xxx 表示服务器端已处理完客户端的请求


* FM构件日志输出： __FM_PATH__

事件 | 输出日志
------ | ------
系统接收查询请求 | _YYYY-MM-DD HH:mm:ss_ INFO Provide service, query: __team__
系统拒接查询请求 | _YYYY-MM-DD HH:mm:ss_ ERROR Reject service, query: __team__

##### 系统运行流程

1. 启动数据库
2. 启动Server
3. 启动Client
4. 开始查询


### Server端结构设计

文件名称  | 执行功能
------------- | -------------
TeamQueryInterface.java | 服务接口
TeamQueryImpl.java | 服务接口实现
DBConnection.java | 数据库连接类
Main.java | 注册RMI接口，指定服务端口

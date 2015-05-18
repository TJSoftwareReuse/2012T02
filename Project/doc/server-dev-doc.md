# Server 开发文档

### 功能说明
1. 从标准输入接收一个学生姓名（字符串），通过查询数据库，得到并输出该学生所在组别
2. 记录以下数据：
	* 收到请求次数
	* 提供服务次数
	* 拒绝服务次数
	* 返回消息次数
3. 输出系统运行日志

### 环境说明

1. 运行环境： java运行环境
2. 开发环境： 
	* jdk 1.8
	* FM - Team2
	* License - Team2
	* PM - Team8
	* CM - Team1
3. 测试环境：JUNIT测试工具
   
### 数据导入与配置
1. 数据导入
	* 请在mysql中新建数据库
	* 请在目标数据库中执行 ```source PROJECT_PATH/data/student.sql```命令，将数据导入数据库中
	* 需注意更改配置数据库的配置选项
	
2. 配置文件
	* 配置文件名为config.properties，位于项目的根目录下。
	* 文件示例

```
# FM构件输出文件的路径+文件名
FM_PATH=/Users/momo/program/eclipse-workspace/server/fm.log
# PM构件输出文件的路径
PM_PATH=/Users/momo/program/eclipse-workspace/server/pm
# LICENSE构件的容量
LICENSE_NUM=5
# 数据库相关配置，给出的为本地mysql数据库的配置，数据库名为student，端口号为3306，用户名和密码均为momo
DB_NAME=mysql
DB_DRIVER_CLASS=com.mysql.jdbc.Driver
DB_URL=jdbc:mysql://localhost:3306/student
DB_USER=momo
DB_PASSWORD=momo
```

### 系统说明

1. 启动步骤
	* 导入数据
	* 修改配置文件
	* 启动数据库
	* 运行项目

2. 查询说明
	* 用户输入：
		* 查询： 学生姓名（String）
		* 退出： “quit”（大小写不限）
	* server输出：
		* License分配失败，拒接提供查询： Reject: License out of limit!
		* 学生存在： Student __NAME__ is in Team __X__
		* 学生不存在： Student haha dosen't exist

	
3. 日志输出
	* PM构件日志输出: __PM_PATH/YYYY-MM-DD HH-mm-ss.log__
		* NEW_REQUEST: xxx
		* PROVIED_REQUEST : xxx
		* REJECT_REQUEST: xxx
		* END_REQUEST: xxx
	
	* FM构件日志输出： __FM_PATH__

事件 | 输出日志
------ | ------
数据库驱动加载成功 | _YYYY-MM-DD HH:mm:ss_ INFO DBConnection getConnection: success load driver
数据库驱动加载失败 | _YYYY-MM-DD HH:mm:ss_ ERROR DBConnection getConnection: load driver error
数据库连接成功 | _YYYY-MM-DD HH:mm:ss_ INFO DBConnection getConnection: success connect server
数据库连接失败 | _YYYY-MM-DD HH:mm:ss_ ERROR DBConnection getConnection: connect server error
系统运行 | _YYYY-MM-DD HH:mm:ss_ INFO System start run
系统关闭 | _YYYY-MM-DD HH:mm:ss_ INFO System shut down
系统接收查询请求 | _YYYY-MM-DD HH:mm:ss_ INFO Provide service, query: __name__
系统拒接查询请求 | _YYYY-MM-DD HH:mm:ss_ ERROR Reject service, query: __name__
查询失败 | _YYYY-MM-DD HH:mm:ss_ FATAL Server run: sql execute error
	
   
### 系统设计

##### 系统运行流程

1. 启动
2. 读入配置文件
3. 初始化配置，连接数据库
4. 运行，开始查询

##### 函数说明

函数名称  | 函数功能
------------- | -------------
Server.java |
readConfig() | 读入配置文件
init() | 根据配置文件进行数据库连接以及参数初始化
run() | 初始化成功，运行系统
DBConnection.java |
DBConnection(ConfigUtil config) | 构造函数
getConnection() | 数据库连接
finalize() | 程序中断前断开数据库连接



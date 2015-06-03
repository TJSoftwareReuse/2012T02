# Failure Manager

1. 接收应用程序的告警信息
2. 输出告警信息到一个单独的告警文件

##FM2.0更新要求
下一条警告切换到新文件

###实现
<code>
log4j.rootLogger = DEBUG, FILE
</code>

<code>
log4j.appender.FILE=org.apache.log4j.DailyRollingFileAppender
</code>

<code>
log4j.appender.FILE.File=${WORKDIR}/logs/app.log
</code>

<code>
log4j.appender.file.datePattern=’.'yyyy-MM-dd-HH-mm-SS
</code>

<code>
log4j.appender.FILE.layout=org.apache.log4j.PatternLayout
</code>

<code>
log4j.appender.FILE.layout.conversionPattern=%d{YYYY-MM-DD HH:mm:ss} %p %m%n
</code>

用log4j.appender.FILE=org.apache.log4j.DailyRollingFileAppender，以及log4j.appender.file.datePattern=’.'yyyy-MM-dd-HH-mm-SS实现每秒检查一次

从而实现下一条警告切换到新文件，这个方法很无赖。。。

##About FM2.1

FM2.0里有些乱七八糟的代码，修复了一下
然后FM2.0似乎是用不了的，

FM2.1可用


改了log文件的输出地址，然后把propertie文件放在了根目录下

## FM3.0 更新日志

###### 注：FM3.0代码部分有较大的改动

### 支持

* 所有接口的参数和返回值没有变动，对旧版本兼容
* 依旧支持5中警告类型

### 变动

* 利用Log4j进行日志输出（而非告警输出），文件名为fm.log
* 接口：```resetOutputFile```的意义有所变动，详细信息见下条
* 警告输出到 ```FILE_PATH/yyyy-MM-dd_HH-mm-ss-x.log``` 文件中
	* ```FILE_PATH``` 为输出路径，由 ```resetOutputFile(String filename)``` 接口进行设置
	* 文件名为```时间戳-ID.log```
	* 每条警告信息输出到一个新文件中
	* 连续相同的警告信息不输出
	* 信息输出格式依旧为```警告类型 警告信息``` -- 文件名中已有时间戳


### 依赖

* FM3.0支持```jdk-1.6```及以上版本
* FM3.0支持```log4j-1.2.17```及以上版本


## FM3.1 更新日志

### 支持

* 所有接口的参数和返回值没有变动，对旧版本兼容
* 依旧支持5中警告类型
* 依赖： ```jdk-1.6```及以上版本 & ```log4j-1.2.17```及以上版本

### 变动

* 警告信息在文件路径变动之前不变 -- 之前理解有误，so sorry~
* 接口：```resetOutputFile```意义回滚为原先： 设置警告信息（路径）+名称
	* 若输入参数不为文件名， 输出日志至```fm.log```
	```时间戳 ERROR Failed: resetOutputFile recieved a directory```
	* 路径可为相对路径也可为绝对路径
* 新增接口：```setRepetation(Boolean b)``` --- 可设置相同信息是否重复输出
	* 输入参数：b
		* true: 设置输出为重复输出（初始设置默认为重复输出）
		* false: 设置输出为不重复输出
	* 注： 警告等级不同的相同信息依旧会重复输出
* 信息输出格式依旧为```时间戳 警告类型 警告信息``` -- 文件名中已有时间戳
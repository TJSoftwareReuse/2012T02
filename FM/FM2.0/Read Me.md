#FM2.0 说明文档

##FM2.0更新要求
下一条警告切换到新文件

##实现
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

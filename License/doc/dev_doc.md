# License Manager 开发文档
### 总体描述
1. 构件名称：LicenseManager
2. 构件信息：
	* 开发语言：java
	* 包结构：
		1. ```com.license.caller``` 该包内的类主要与证书申请者的信息有关。
		2. ```com.license.manager``` 该包内的类主要负责系统证书资源的调度。
		3. ```com.license.manager.message``` 该包内的类与系统回馈给证书请求者的信息有关。
		4. ```com.license.resource``` 该包内的类主要与系统的证书资源有关。
3. 源码信息：
	* ```com.license.caller```： 在com.license.caller包下，有一个CallerMessage类，该类主要封装一些请求证书资源的客户端的一些信息。该类中包含的字段如下：
		1. ```private String callerID;``` 申请证书资源客户端的ID信息。

		该类中包含的方法如下：
		
		1. ```public CallerMessage(String callerID);``` 构造方法，传入参数：callerID，申请证书资源客户端的ID信息。
		2. ```public String getCallerID();``` 获取申请证书资源客户端的ID信息。


	* 在```com.license.resuorce```包下，有一个LicenseResource类，该类存储与系统证书资源相关的一些信息。该类中包含以下字段：
		1. ```private static int MAX_REQUEST_NUM;``` 保存系统证书资源的最大数量，默认值为10。
		2. ```private static int REST_REQUEST_NUM;``` 记录系统当前剩余的证书资源数量，初始值与MAX_REQUEST_NUM相等。
		
		LicenseResource类中还包含以下方法：

		1. ```public static int getMaxRequestNum();``` 获取系统证书资源的最大数量，无传入参数。
		2. ```public static void setMaxRequestNum(int mAX_REQUEST_NUM);``` 设置系统证书资源的上限。传入参数： ```mAX_REQUEST_NUM```，系统证书的最大数量。
		3. ```public static int getRestRequestNum();``` 获得当前剩余系统证书的数量。
		4. ```public static void setRestRequestNum(int rEST_REQUEST_NUM);```设置当前系统剩余的证书数量。传入参数：```rEST_REQUEST_NUM```，系统剩余证书的数量。


	* 在```com.license.manager.message```包下，有RequestResultMessage类，该类是请求证书资源的客户端在向系统发送证书请求后，得到的来自于系统的回馈信息。该类中主要包含如下字段：
		1. ```private boolean isSuccess;``` 标记此次证书的申请是否成功，True表明证书申请成功，False表明证书申请失败。
		2. ```private String info;``` 记录证书申请的相关信息。若此次证书申请成功，则该字段为“Request License Successful”；若失败，那么该字段为具体的出错信息，“Request License Failed”代表当前系统证书资源已不足，若证书申请过程中出现异常，那么该字段为 “License Request Exception，Error Message： error detail”。
		3. ```private Date responseTime;``` 系统对证书申请进行相应处理的时间。

		该类包含的方法如下：
		
		1. ```public boolean isSuccess();``` 查看此次证书申请是否成功，Ture表示此次证书申请成功，False表示此次证书申请失败。
		2. ```public void setSuccess(boolean isSuccess);``` 设置此次证书申请成功与否，Ture表示此次证书申请成功，False表示此次证书申请失败。
		3. ```public String getInfo();```获得此次证书申请的附加信息。
		4. ```public void setInfo(String info);```设置此次证书申请的附加信息。传入参数：info， 证书申请过程的相关信息。
		5. ```public Date getResponseTime();```获得系统对证书申请作出响应的时间。
		6. ```public void setResponseTime(Date responseTime);``` 设置系统对证书申请作出响应的时间。


	* ```Com.license.manager```包下的LicenseManager类是该组件最核心的类，它负责合理分配系统的证书资源，并且处理来自客户端的证书申请请求。该类中主要包含以下字段：
	
		1. ```private static LicenseManager licenseManager;```一个私有静态的LicenseManager实例，不同的客户端都将通过该实例获取系统的证书资源（单例模式实现，保证线程安全）。
		
		该类中的方法如下：

		1. ```private LicenseManager();``` 该类私有的无参构造函数。
		2. ```public static LicenseManager getInstance();```获得LicenseManager的单例对象，该方法保证线程安全。
		3. ```public RequestResultMessage requestLicense(CallerMessage callerMessage);```
客户端将通过调用该方法申请系统的证书资源。传入参数：CallerMessage，调用者的基本信息；返回值：RequestResultMessage 系统返回给证书申请者的回馈信息。

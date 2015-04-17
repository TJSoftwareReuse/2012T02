//系统的证书资源。
package com.license.resource;

public class LicenseResource {
	//系统接收请求数的上限。
	private static int MAX_REQUEST_NUM = 10;
	//系统当前剩余的请求数量。
	private static int REST_REQUEST_NUM = MAX_REQUEST_NUM;
	
	public static int getMaxRequestNum() {
		return MAX_REQUEST_NUM;
	}

	public static void setMaxRequestNum(int mAX_REQUEST_NUM) {
		LicenseResource.MAX_REQUEST_NUM = mAX_REQUEST_NUM;
	}
	
	public static int getRestRequestNum() {
		return REST_REQUEST_NUM;
	}

	public static void setRestRequestNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_REQUEST_NUM = rEST_REQUEST_NUM;
	}
}

//系统的证书资源。
package com.license.resource;

public class LicenseResource {
	//系统接收请求数的上限。
	private static volatile int MAX_LICENSE_NUM = 10;
	//系统当前剩余的请求数量。
	private static volatile int REST_LICENSE_NUM = MAX_LICENSE_NUM;
	
	//获得系统可以处理的最大证书请求数量。
	public static synchronized int getMaxLicenseNum() {
		return MAX_LICENSE_NUM;
	}
	
	//设置系统可以处理的最大证书请求数量。
	public static synchronized void setMaxLicenseNum(int mAX_REQUEST_NUM) {
		LicenseResource.MAX_LICENSE_NUM = mAX_REQUEST_NUM;
	}
	
	//获得当前剩余的证书数量。
	public static synchronized int getRestLicenseNum() {
		return REST_LICENSE_NUM;
	}
	
	//设置当前剩余的证书数量。
	public static synchronized void setRestLicenseNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_LICENSE_NUM = rEST_REQUEST_NUM;
	}
}

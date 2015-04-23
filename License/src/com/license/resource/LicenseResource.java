package com.license.resource;

public class LicenseResource {
	private static volatile int MAX_LICENSE_NUM = 10;
	private static volatile int REST_LICENSE_NUM = MAX_LICENSE_NUM;
	
	public static synchronized int getMaxLicenseNum() {
		return MAX_LICENSE_NUM;
	}
	
	public static synchronized void setMaxLicenseNum(int mAX_REQUEST_NUM) {
		LicenseResource.MAX_LICENSE_NUM = mAX_REQUEST_NUM;
	}
	
	public static synchronized int getRestLicenseNum() {
		return REST_LICENSE_NUM;
	}
	
	public static synchronized void setRestLicenseNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_LICENSE_NUM = rEST_REQUEST_NUM;
	}
}

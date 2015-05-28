package com.lisence;

public class LicenseResource {
	private static volatile int MAX_LICENSE_NUM = 5;
	private static volatile int REST_LICENSE_NUM = MAX_LICENSE_NUM;
	
	public static synchronized int getMaxLicenseNum() {
		return MAX_LICENSE_NUM;
	}
	
	public static synchronized boolean setMaxLicenseNum(int mAX_REQUEST_NUM) {
		if(mAX_REQUEST_NUM <= 0)
			return false;
		if(MAX_LICENSE_NUM != mAX_REQUEST_NUM){
			REST_LICENSE_NUM = mAX_REQUEST_NUM;
		}
		LicenseResource.MAX_LICENSE_NUM = mAX_REQUEST_NUM;
		return true;
	}
	
	public static synchronized int getRestLicenseNum() {
		return REST_LICENSE_NUM;
	}
	
	public static synchronized void setRestLicenseNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_LICENSE_NUM = rEST_REQUEST_NUM;
	}
}

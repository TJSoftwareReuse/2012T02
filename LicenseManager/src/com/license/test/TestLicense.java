package com.license.test;

import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.license.resource.LicenseResource;

public class TestLicense {
	public static void main(String[] args){
		System.out.println("开始申请License资源：");
		LicenseManager licenseManager = LicenseManager.getInstance();
		for(int index = 0;index<LicenseResource.getMaxLicenseNum() + 5;index++){
			RequestResultMessage rrm = licenseManager.requestLicense(new CallerMessage("1252878"));
			System.out.println("第"+ (index + 1) +"次申请License,返回信息："+rrm.isSuccess()+","+rrm.getInfo()
					+",申请时间："+rrm.getResponseTime());
		}
	}
}

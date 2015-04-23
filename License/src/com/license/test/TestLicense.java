package com.license.test;

import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.license.resource.LicenseResource;

public class TestLicense {
	public static void main(String[] args){
		System.out.println("Start Testing");
		LicenseManager licenseManager = LicenseManager.getInstance();
		for(int index = 0;index<LicenseResource.getMaxLicenseNum() + 5;index++){
			RequestResultMessage rrm = licenseManager.requestLicense(new CallerMessage("1252878"));
			System.out.println((index + 1) +" Request License, Feedback Message"+rrm.isSuccess()+","+rrm.getInfo()
					+",Time :"+rrm.getResponseTime());
		}
	}
}

package com.license.test;

import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;
import com.license.resource.LicenseResource;

public class TestLicense {
	public static void main(String[] args){
		System.out.println("��ʼ����License��Դ��");
		LicenseManager licenseManager = LicenseManager.getInstance();
		for(int index = 0;index<LicenseResource.getMaxLicenseNum() + 5;index++){
			RequestResultMessage rrm = licenseManager.requestLicense(new CallerMessage("1252878"));
			System.out.println("��"+ (index + 1) +"������License,������Ϣ��"+rrm.isSuccess()+","+rrm.getInfo()
					+",����ʱ�䣺"+rrm.getResponseTime());
		}
	}
}

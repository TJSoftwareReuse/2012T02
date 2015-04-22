//�����֤��Դ���в�����
package com.license.manager;

import java.util.Date;

import com.license.caller.CallerMessage;
import com.license.manager.message.RequestResultMessage;
import com.license.resource.LicenseResource;

public class LicenseManager {

	private static LicenseManager licenseManager;
	
	private LicenseManager(){};
	
	public static LicenseManager getInstance(){
		 if(licenseManager == null){
			 synchronized (LicenseManager.class) {
				if(licenseManager == null){
					licenseManager = new LicenseManager();
				}
			}
		 }
		 return licenseManager;
	}
	
	//�����������ߵ����֤���������
	public RequestResultMessage requestLicense(CallerMessage callerMessage){
		RequestResultMessage rrm = new RequestResultMessage();
		try{
			int restRequestNum = LicenseResource.getRestLicenseNum();
			if(restRequestNum <= 0){
				rrm.setSuccess(false);
				rrm.setInfo("ϵͳLicense��Դ����");
			}else{
				LicenseResource.setRestLicenseNum(--restRequestNum);
				rrm.setSuccess(true);
				rrm.setInfo("����License�ɹ�");
			}
		}catch(Exception e){
			rrm.setSuccess(false);
			rrm.setInfo("License��������쳣��������Ϣ��"+e.toString());
		}
		rrm.setResponseTime(new Date());
		return rrm;
	}
}

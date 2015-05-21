//对许可证资源进行操作。
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
	
	//组件处理调用者的许可证请求操作。
	public RequestResultMessage requestLicense(CallerMessage callerMessage){
		RequestResultMessage rrm = new RequestResultMessage();
		try{
			int restRequestNum = LicenseResource.getRestLicenseNum();
			if(restRequestNum <= 0){
				rrm.setSuccess(false);
				rrm.setInfo("系统License资源不足");
			}else{
				LicenseResource.setRestLicenseNum(--restRequestNum);
				rrm.setSuccess(true);
				rrm.setInfo("申请License成功");
			}
		}catch(Exception e){
			rrm.setSuccess(false);
			rrm.setInfo("License申请出现异常，出错信息："+e.toString());
		}
		rrm.setResponseTime(new Date());
		return rrm;
	}
}

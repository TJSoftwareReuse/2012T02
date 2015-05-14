package com.license.test;

import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;

public class TestLicense {
	public static void main(String[] args){
		//1. 获得LicenseManager实例，用于接下来的证书申请操作。
		LicenseManager licenseManager = LicenseManager.getInstance();

		//默认系统证书资源上限为10.
		System.out.println("默认系统证书资源上限为:" + licenseManager.getLicenseCapacity());
		//设置系统证书资源的上限值为100（默认为10）
		boolean bResult1 = licenseManager.setLicenseCapacity(100);
		if(bResult1 == true){
			System.out.println("证书资源上限设置成功, 当前系统证书上限为：" + licenseManager.getLicenseCapacity());
		}else{
			System.out.println("证书资源上限设置失败");
		}
		System.out.println("当前系统剩余的证书资源为(请求操作前)：" + licenseManager.getRestLicenseCapacity());
		
		
		//2. 生成CallerMessage实例，填充做出证书申请客户端的一些基本信息。
		CallerMessage callerMessage = new CallerMessage("THFERDXEWWS-98754456");
		//3. 向系统发起证书申请操作。
		RequestResultMessage rrm = licenseManager.requestLicense(callerMessage);
		//4. 检查此次证书申请是否成功。
		if(rrm.isSuccess()){//证书申请成功
			System.out.println("证书申请成功，申请时间："+rrm.getResponseTime());
		}else{//证书申请失败
			System.out.println("证书申请失败，出错信息："+rrm.getInfo()+", 申请时间："+rrm.getResponseTime());
		}

		System.out.println("当前系统剩余的证书资源为(请求操作后)：" + licenseManager.getRestLicenseCapacity());
	}
}

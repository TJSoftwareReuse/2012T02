//http://tool.oschina.net/apidocs/apidoc?api=junit-4.10
package com.license.manager;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import com.license.manager.message.RequestResultMessage;
import com.license.resource.LicenseResource;

public class LicenseManagerTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("开始测试LicenseManager：");
	}

	@Test
	public void testGetInstance() {
		//测试获得的两个LicenseManager对象是否为同一个对象。
		LicenseManager licenseManager1 = LicenseManager.getInstance();
		LicenseManager licenseManager2 = LicenseManager.getInstance();
		assertThat("两次请求产生的不是同一个LicenseManager实例",
				licenseManager1.hashCode(),IsEqual.equalTo(licenseManager2.hashCode()));
	}

	@Test
	public void testCanGetResponse() {
		RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(null);
		assertNotNull("无法获得License请求回馈数据", rrm);
	}
	
	@Test
	public void testRequestLicense(){
		int restLicense = LicenseResource.getRestLicenseNum();
		RequestResultMessage rrm = null;
		//正常范围内的License请求。
		for(int index = 0;index < restLicense;index ++)
		{
			rrm = LicenseManager.getInstance().requestLicense(null);
			assertTrue("LicenseManager处理正常范围内的请求有问题", rrm.isSuccess());
		}
		//过载的License请求。
		rrm = LicenseManager.getInstance().requestLicense(null);
		assertFalse("LicenseManager处理请求过载的情况有问题", rrm.isSuccess());
	}

}

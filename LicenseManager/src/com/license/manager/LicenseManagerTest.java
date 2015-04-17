//http://tool.oschina.net/apidocs/apidoc?api=junit-4.10
package com.license.manager;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import com.license.manager.message.RequestResultMessage;

public class LicenseManagerTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("��ʼ����LicenseManager��");
	}

	@Test
	public void testGetInstance() {
		LicenseManager licenseManager1 = LicenseManager.getInstance();
		LicenseManager licenseManager2 = LicenseManager.getInstance();
//		LicenseManager licenseManager2 = null;
//		assertSame("���ص�LicenseManager����ͬһ��ʵ��", licenseManager1, licenseManager2);
//		assertThat("Zero is one", licenseManager1, IsEqual.equalTo(licenseManager2));
		assertThat("Zero is one", licenseManager1, Is.is(licenseManager2));
	}

	@Test
	public void testRequestLicense() {
		RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(null);
		assertNotNull("�޷����License�����������", rrm);
	}

}

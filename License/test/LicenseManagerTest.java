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
	}

	@Test
	public void testGetInstance() {
		LicenseManager licenseManager1 = LicenseManager.getInstance();
		LicenseManager licenseManager2 = LicenseManager.getInstance();
		assertThat(" the two LicenseManager are not the same instance",
				licenseManager1.hashCode(),IsEqual.equalTo(licenseManager2.hashCode()));
	}

	@Test
	public void testCanGetResponse() {
		RequestResultMessage rrm = LicenseManager.getInstance().requestLicense(null);
		assertNotNull("Can't get License feedback data", rrm);
	}
	
	@Test
	public void testRequestLicense(){
		int restLicense = LicenseResource.getRestLicenseNum();
		RequestResultMessage rrm = null;
		for(int index = 0;index < restLicense;index ++)
		{
			rrm = LicenseManager.getInstance().requestLicense(null);
			assertTrue("LicenseManager Error", rrm.isSuccess());
		}
		//¹ýÔØµÄLicenseÇëÇó¡£
		rrm = LicenseManager.getInstance().requestLicense(null);
		assertFalse("LicenseManager Error", rrm.isSuccess());
	}

}

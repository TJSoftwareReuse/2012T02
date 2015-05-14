package com.license.test;

import com.license.caller.CallerMessage;
import com.license.manager.LicenseManager;
import com.license.manager.message.RequestResultMessage;

public class TestLicense {
	public static void main(String[] args){
		//1. ���LicenseManagerʵ�������ڽ�������֤�����������
		LicenseManager licenseManager = LicenseManager.getInstance();

		//Ĭ��ϵͳ֤����Դ����Ϊ10.
		System.out.println("Ĭ��ϵͳ֤����Դ����Ϊ:" + licenseManager.getLicenseCapacity());
		//����ϵͳ֤����Դ������ֵΪ100��Ĭ��Ϊ10��
		boolean bResult1 = licenseManager.setLicenseCapacity(100);
		if(bResult1 == true){
			System.out.println("֤����Դ�������óɹ�, ��ǰϵͳ֤������Ϊ��" + licenseManager.getLicenseCapacity());
		}else{
			System.out.println("֤����Դ��������ʧ��");
		}
		System.out.println("��ǰϵͳʣ���֤����ԴΪ(�������ǰ)��" + licenseManager.getRestLicenseCapacity());
		
		
		//2. ����CallerMessageʵ�����������֤������ͻ��˵�һЩ������Ϣ��
		CallerMessage callerMessage = new CallerMessage("THFERDXEWWS-98754456");
		//3. ��ϵͳ����֤�����������
		RequestResultMessage rrm = licenseManager.requestLicense(callerMessage);
		//4. ���˴�֤�������Ƿ�ɹ���
		if(rrm.isSuccess()){//֤������ɹ�
			System.out.println("֤������ɹ�������ʱ�䣺"+rrm.getResponseTime());
		}else{//֤������ʧ��
			System.out.println("֤������ʧ�ܣ�������Ϣ��"+rrm.getInfo()+", ����ʱ�䣺"+rrm.getResponseTime());
		}

		System.out.println("��ǰϵͳʣ���֤����ԴΪ(���������)��" + licenseManager.getRestLicenseCapacity());
	}
}

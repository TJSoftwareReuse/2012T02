//ϵͳ��֤����Դ��
package com.license.resource;

public class LicenseResource {
	//ϵͳ���������������ޡ�
	private static volatile int MAX_LICENSE_NUM = 10;
	//ϵͳ��ǰʣ�������������
	private static volatile int REST_LICENSE_NUM = MAX_LICENSE_NUM;
	
	//���ϵͳ���Դ�������֤������������
	public static synchronized int getMaxLicenseNum() {
		return MAX_LICENSE_NUM;
	}
	
	//����ϵͳ���Դ�������֤������������
	public static synchronized void setMaxLicenseNum(int mAX_REQUEST_NUM) {
		LicenseResource.MAX_LICENSE_NUM = mAX_REQUEST_NUM;
	}
	
	//��õ�ǰʣ���֤��������
	public static synchronized int getRestLicenseNum() {
		return REST_LICENSE_NUM;
	}
	
	//���õ�ǰʣ���֤��������
	public static synchronized void setRestLicenseNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_LICENSE_NUM = rEST_REQUEST_NUM;
	}
}

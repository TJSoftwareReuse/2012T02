//ϵͳ��֤����Դ��
package com.license.resource;

public class LicenseResource {
	//ϵͳ���������������ޡ�
	private static int MAX_REQUEST_NUM = 10;
	//ϵͳ��ǰʣ�������������
	private static int REST_REQUEST_NUM = MAX_REQUEST_NUM;
	
	public static int getMaxRequestNum() {
		return MAX_REQUEST_NUM;
	}

	public static void setMaxRequestNum(int mAX_REQUEST_NUM) {
		LicenseResource.MAX_REQUEST_NUM = mAX_REQUEST_NUM;
	}
	
	public static int getRestRequestNum() {
		return REST_REQUEST_NUM;
	}

	public static void setRestRequestNum(int rEST_REQUEST_NUM) {
		LicenseResource.REST_REQUEST_NUM = rEST_REQUEST_NUM;
	}
}

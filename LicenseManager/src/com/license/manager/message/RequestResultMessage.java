//����������License��ķ�����Ϣ��
package com.license.manager.message;

import java.util.Date;

public class RequestResultMessage {
	private boolean isSuccess;//����License�Ƿ�ɹ���
	private String info;//����Licenseʱ�����ص������Ϣ��
	private Date responseTime;//��Ӧ�����ߵ�ʱ�䡣
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
}

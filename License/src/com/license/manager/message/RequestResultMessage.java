package com.license.manager.message;

import java.util.Date;

public class RequestResultMessage {
	private boolean isSuccess;
	private String info;
	private Date responseTime;
	
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
		return this.responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
}

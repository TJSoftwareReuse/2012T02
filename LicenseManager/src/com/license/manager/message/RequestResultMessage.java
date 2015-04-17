//调用者请求License后的返回消息。
package com.license.manager.message;

import java.util.Date;

public class RequestResultMessage {
	private boolean isSuccess;//请求License是否成功。
	private String info;//请求License时，返回的相关信息。
	private Date responseTime;//响应调用者的时间。
	
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

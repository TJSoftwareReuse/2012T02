//��ŵ����ߵ�һЩ��Ϣ
package com.license.caller;

public class CallerMessage {
	private String callerID;//�����ߵ�ID��Ϣ�����д�ġ�
	
	public CallerMessage(){
		this.callerID = null;
	}
	
	public CallerMessage(String callerID){
		this.callerID = callerID;
	}
	
	public String getCallerID() {
		return callerID;
	}

	public void setCallerID(String callerID) {
		this.callerID = callerID;
	}
	
	
}

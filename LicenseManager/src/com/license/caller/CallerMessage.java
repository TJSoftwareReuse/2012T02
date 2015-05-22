//存放调用者的一些信息
package com.license.caller;

public class CallerMessage {
	private String callerID;//调用者的ID信息，随便写的。
	
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

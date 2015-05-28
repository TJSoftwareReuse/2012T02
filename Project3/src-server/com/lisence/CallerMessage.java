package com.lisence;

public class CallerMessage {
	private String callerID;
	
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

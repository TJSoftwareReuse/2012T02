package t2.server2.queryresultmessage;

import java.io.Serializable;

public class QueryResultMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSuccess;
	private String message;
	private Object result;
	
	public QueryResultMessage(){
		this.isSuccess = false;
		this.message = null;
		this.result = null;
	}
	
	public QueryResultMessage(boolean isSuccess, String message, Object result){
		this.isSuccess = isSuccess;
		this.message = message;
		this.result = result;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}

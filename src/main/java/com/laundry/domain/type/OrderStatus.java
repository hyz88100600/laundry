package com.laundry.domain.type;

public enum OrderStatus {

	init("1","新建");
	
	OrderStatus(String code,String message){
		this.code  = code;
		this.message = message;
	}
	
	private String code;
	private String message;
	
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

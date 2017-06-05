package com.laundry.pojo;

import com.laundry.domain.type.StatusCode;

//返回基础类
public class BaseResult {

	protected String code;
	protected String message;
	
	public BaseResult(){
		
	}
	public BaseResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

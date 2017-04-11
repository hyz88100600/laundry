package com.laundry.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.type.StatusCode;


//返回基础类
public class BaseResult {

	private String code;
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private DataResult data;
	
	public BaseResult(){
		
	}
	
	public BaseResult(String code,String message){
		this.code = code;
		this.message = message;
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

	public DataResult getData() {
		return data;
	}

	public void setData(DataResult data) {
		this.data = data;
	}

}

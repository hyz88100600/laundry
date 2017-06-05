package com.laundry.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.type.StatusCode;

public class LoginResult extends BaseResult{
	
	//用户返回
	@JsonInclude(Include.NON_NULL)
	private String nickName;//昵称

	public LoginResult(){
		
	}
	public LoginResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}

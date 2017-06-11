package com.laundry.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.Address;
import com.laundry.domain.type.StatusCode;

public class LoginResult extends BaseResult{
	
	//用户返回
	@JsonInclude(Include.NON_NULL)
	private String nickName;//昵称
	private Address address;//地址
	private String token;//令牌

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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}

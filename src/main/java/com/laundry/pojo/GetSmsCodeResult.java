package com.laundry.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.type.StatusCode;

public class GetSmsCodeResult extends BaseResult{
	
	@JsonInclude(Include.NON_NULL)
	private String smsCode;//短信验证码

	public GetSmsCodeResult(){
		
	}
	public GetSmsCodeResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
	
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
}

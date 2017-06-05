package com.laundry.pojo;

import com.laundry.domain.type.StatusCode;

public class RegisterResult extends BaseResult{
	public RegisterResult(){
	}
	public RegisterResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
}

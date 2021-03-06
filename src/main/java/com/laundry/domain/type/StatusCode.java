package com.laundry.domain.type;

public enum StatusCode {

	success("0000","成功"),
	phone_error("0001","手机号格式不正确"),
	smsCode_error("0002","验证码不正确"),
	
	password_error_blank("0003","密码不能为空"),
	password_error_length("0004","密码的长度是6-12位"),
	
	user_already_exist("0005","用户已经存在"),
	user_not_exist("0006","用户不存在"),
	
	user_password_error("0007","用户名或密码错误"),
	
	param_error_blank("0008","必填参数不能为空"),
	
	laundry_params_blank("0009","店铺必填参数不能为空"),
	laundry_already_exist("0010","店铺已经存在"),
	
	order_status_error("0011","订单状态不正确"),
	
	token_error_blank("0012","token不能为空"),
	
	token_error_Invalid("0013","token失效"),
	
	address_not_exist("0014","用户地址没有配置"),
	
	sys_error("9000","系统异常");
	
	StatusCode(String code,String message){
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

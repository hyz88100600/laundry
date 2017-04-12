package com.laundry.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.Laundry;

public class DataResult {

	@JsonInclude(Include.NON_NULL)
	private String smsCode;//短信验证码
	//用户返回
	@JsonInclude(Include.NON_NULL)
	private String nickName;//昵称
	//店铺返回
	@JsonInclude(Include.NON_NULL)
	private List<Laundry> laundry ;
	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public List<Laundry> getLaundry() {
		return laundry;
	}

	public void setLaundry(List<Laundry> laundry) {
		this.laundry = laundry;
	}
	
}

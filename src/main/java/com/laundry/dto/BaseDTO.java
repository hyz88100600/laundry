package com.laundry.dto;

public class BaseDTO {
	
	private String token;
	private String version;
	
	public BaseDTO(){
		
	}
	
	public BaseDTO(String token,String version){
		this.token = token;
		this.version= version;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}

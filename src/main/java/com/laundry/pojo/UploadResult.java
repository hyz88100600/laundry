package com.laundry.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.type.StatusCode;

public class UploadResult extends BaseResult{
	
	@JsonInclude(Include.NON_NULL)
	private String url;//图片路径

	public UploadResult(){
	}
	public UploadResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

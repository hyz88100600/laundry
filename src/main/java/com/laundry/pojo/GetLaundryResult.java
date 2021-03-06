package com.laundry.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.type.StatusCode;

public class GetLaundryResult extends BaseResult {
	
	public GetLaundryResult(){
		
	}
	public GetLaundryResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}
	// 店铺返回
	@JsonInclude(Include.NON_NULL)
	private List<LaundryPOJO> laundry;

	public List<LaundryPOJO> getLaundry() {
		return laundry;
	}
	public void setLaundry(List<LaundryPOJO> laundry) {
		this.laundry = laundry;
	}

}

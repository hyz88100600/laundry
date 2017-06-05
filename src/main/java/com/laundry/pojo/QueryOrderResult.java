package com.laundry.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.Order;
import com.laundry.domain.type.StatusCode;

public class QueryOrderResult extends BaseResult{
	
	public QueryOrderResult(){
		
	}
	public QueryOrderResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}

	@JsonInclude(Include.NON_NULL)
	private List<Order> order;
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}

package com.laundry.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.laundry.domain.OrderItem;
import com.laundry.domain.type.StatusCode;

public class QueryOrderItemResult extends BaseResult{
	
	public QueryOrderItemResult(){
		
	}
	public QueryOrderItemResult(StatusCode statusCode){
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
	}

	@JsonInclude(Include.NON_NULL)
	private List<OrderItem> orderItem;

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
}

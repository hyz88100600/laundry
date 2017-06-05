package com.laundry.dto;

import com.laundry.domain.type.OrderStatus;

public class ModifyOrderStatusDTO {

	private Long orderId;
	private OrderStatus orderStatus;
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}

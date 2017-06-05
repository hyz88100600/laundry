package com.laundry.dto;

public class OrderOfferDTO {
	
	private Long orderId;
	
	private OrderItemOfferDTO[] items ;

	public OrderItemOfferDTO[] getItems() {
		return items;
	}

	public void setItems(OrderItemOfferDTO[] items) {
		this.items = items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
}

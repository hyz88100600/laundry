package com.laundry.dto;



public class OrderDTO {

	private Long laundryId;//店铺id
	
	private OrderItemDTO[] items ;

	public Long getLaundryId() {
		return laundryId;
	}

	public void setLaundryId(Long laundryId) {
		this.laundryId = laundryId;
	}

	public OrderItemDTO[] getItems() {
		return items;
	}

	public void setItems(OrderItemDTO[] items) {
		this.items = items;
	}

}

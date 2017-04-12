package com.laundry.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.laundry.domain.BaseModel;
import com.laundry.domain.type.OrderStatus;

//订单
@Entity
@Table(name="t_order")
public class Order extends BaseModel{

	@Id
	@GeneratedValue
	private Long id;
	
	private Double sumPrice;//总价
	
	private Long laundryId;//店铺id
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLaundryId() {
		return laundryId;
	}

	public void setLaundryId(Long laundryId) {
		this.laundryId = laundryId;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}

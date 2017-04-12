package com.laundry.domain.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.laundry.domain.BaseModel;

//订单
@Entity
@Table(name="order")
public class Order extends BaseModel{

	@Id
	@GeneratedValue
	private Long id;
	
	private Double sumPrice;//总价
	
	private Long laundryId;//店铺id

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
	
}

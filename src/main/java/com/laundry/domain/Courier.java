package com.laundry.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * 快递员
 */
@Entity
@Table(name="t_courier")
public class Courier {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;//快递员姓名
	
	@Column(nullable=false)
	private String contact;//联系方式
	
	private Long laundryId;//店铺id
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createOn = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifyOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getLaundryId() {
		return laundryId;
	}

	public void setLaundryId(Long laundryId) {
		this.laundryId = laundryId;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}
	
}

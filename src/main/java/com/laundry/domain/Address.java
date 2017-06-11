package com.laundry.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="t_address")
public class Address {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long userId;//用户id
	
	private String desc;//描述
	private Double longitude;//经度
	private Double dimension;//维度
	
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getDimension() {
		return dimension;
	}
	public void setDimension(Double dimension) {
		this.dimension = dimension;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

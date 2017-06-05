package com.laundry.dto;


public class CreateLaundryDTO {

	private String name;//洗衣店名称
	private String hoster;//店主
	private String contact;//联系方式
	
	private Double longitude;//经度
	private Double dimension;//维度
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHoster() {
		return hoster;
	}
	public void setHoster(String hoster) {
		this.hoster = hoster;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	
}

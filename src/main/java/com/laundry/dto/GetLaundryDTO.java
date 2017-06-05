package com.laundry.dto;

public class GetLaundryDTO {

	private Double longitude1;//经度
	private Double longitude2;//经度
	
	private Double dimension1;//维度
	private Double dimension2;//维度
	
	public Double getLongitude1() {
		return longitude1;
	}
	public void setLongitude1(Double longitude1) {
		this.longitude1 = longitude1;
	}
	public Double getLongitude2() {
		return longitude2;
	}
	public void setLongitude2(Double longitude2) {
		this.longitude2 = longitude2;
	}
	public Double getDimension1() {
		return dimension1;
	}
	public void setDimension1(Double dimension1) {
		this.dimension1 = dimension1;
	}
	public Double getDimension2() {
		return dimension2;
	}
	public void setDimension2(Double dimension2) {
		this.dimension2 = dimension2;
	}
	
}

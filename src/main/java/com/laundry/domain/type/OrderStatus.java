package com.laundry.domain.type;

public enum OrderStatus {

	init("新建"),
	offer("报价"),
	customer_reject("客户拒绝"),
	business_reject("商家拒绝"),
	pay("付款"),
	washed("已洗完"),
	finish("完成");
	
	OrderStatus(String message){
		this.message = message;
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

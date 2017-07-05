package com.laundry.pojo.utils;

import java.util.ArrayList;
import java.util.List;

import com.laundry.domain.Address;
import com.laundry.domain.Laundry;
import com.laundry.pojo.AddressPOJO;
import com.laundry.pojo.LaundryPOJO;

public class TransferUtils {

	//address转化
	public static AddressPOJO toAddressPOJO(Address address){
		if(address==null){
			return null;
		}
		AddressPOJO pojo = new AddressPOJO();
		pojo.setLatitude(String.valueOf(address.getLatitude()));
		pojo.setLongitude(String.valueOf(address.getLongitude()));
		pojo.setRemarks(address.getRemarks());
		return pojo;
	}
	
	//laundry转化
	public static LaundryPOJO toLaundryPOJO(Laundry laundry){
		if(laundry==null){
			return null;
		}
		LaundryPOJO pojo = new LaundryPOJO();
		pojo.setId(laundry.getId());
		pojo.setHoster(laundry.getHoster());
		pojo.setContact(laundry.getContact());
		pojo.setLatitude(String.valueOf(laundry.getLatitude()));
		pojo.setLongitude(String.valueOf(laundry.getLongitude()));
		pojo.setName(laundry.getName());
		pojo.setScore(String.valueOf(laundry.getScore()));
		pojo.setRemarks(laundry.getRemarks());
		return pojo;
	}
	
	//List<Laundry>转化
	public static List<LaundryPOJO> toLaundryPOJOList(List<Laundry> list){
		List<LaundryPOJO> l = new ArrayList<LaundryPOJO>();
		for (Laundry laundry : list) {
			l.add(toLaundryPOJO(laundry));
		}
		return l;
	}
}

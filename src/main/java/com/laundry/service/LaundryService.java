package com.laundry.service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.laundry.dao.LaundryRepository;
import com.laundry.domain.Laundry;
import com.laundry.dto.CreateLaundryDTO;
import com.laundry.dto.GetLaundryDTO;


@Service
@Transactional
public class LaundryService {

	@Autowired
	private LaundryRepository laundryRepository;
	
	public List<Laundry> findAll(){
		return laundryRepository.findAll();
	}
	
	//创建
	public Laundry create(CreateLaundryDTO dto){
		Laundry laundry = new Laundry();
		
		laundry.setName(dto.getName());
		laundry.setHoster(dto.getHoster());
		laundry.setContact(dto.getContact());
		laundry.setLatitude(dto.getLatitude());
		laundry.setLongitude(dto.getLongitude());
		laundry.setCreateOn(new Date());
		
		return laundryRepository.save(laundry);
	}
	
	//根据坐标精确查询店铺
	public Laundry getLaundry(Double latitude,Double longitude){
		LaundryParams params = new LaundryParams();
		params.setLatitude(latitude);
		params.setLongitude(longitude);
		return laundryRepository.findOne(buildSpecification(params));
	}
	
	//根据坐标范围查询店铺
	public List<Laundry> getLaundryByCoordinate(GetLaundryDTO dto){
		
		Double longitude = dto.getLongitude();
		Double latitude = dto.getLatitude();
		LaundryParams params = new LaundryParams();
		
		params.setLatitude1(latitude-0.03d);
		params.setLatitude2(latitude+0.03d);
		params.setLongitude1(longitude-0.03d);
		params.setLongitude2(longitude+0.03d);
		
		Specification<Laundry> buildSpecification = buildSpecification(params);
		
		return laundryRepository.findAll(buildSpecification);
	}
	
	// 获取Specification
	private Specification<Laundry> buildSpecification(LaundryParams params) {

		final Double latitude1 = params.getLatitude1();
		final Double latitude2 = params.getLatitude2();
		final Double longitude1 = params.getLongitude1();
		final Double longitude2 = params.getLongitude2();
		
		return new Specification<Laundry>() {
			public Predicate toPredicate(Root<Laundry> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				if(latitude1!=null){
					predicates.add(cb.ge(root.get("latitude").as(Double.class), latitude1));
				}
				
				if(latitude2!=null){
					predicates.add(cb.le(root.get("latitude").as(Double.class), latitude2));
				}
				
				if(longitude1!=null){
					predicates.add(cb.ge(root.get("longitude").as(Double.class), longitude1));
				}
				
				if(longitude2!=null){
					predicates.add(cb.le(root.get("longitude").as(Double.class), longitude2));
				}
				
				if (!predicates.isEmpty()) {
					return cb.and(predicates.toArray(new Predicate[predicates
							.size()]));
				} else {
					return null;
				}
			}
		};
	}
}


class LaundryParams{
	
	private Double longitude1;//经度
	private Double longitude2;//经度
	
	private Double latitude1;//维度
	private Double latitude2;//维度
	
	private Double longitude;//经度
	private Double latitude;//维度
	
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
	public Double getLatitude1() {
		return latitude1;
	}
	public void setLatitude1(Double latitude1) {
		this.latitude1 = latitude1;
	}
	public Double getLatitude2() {
		return latitude2;
	}
	public void setLatitude2(Double latitude2) {
		this.latitude2 = latitude2;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}

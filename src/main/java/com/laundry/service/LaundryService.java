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
	public Laundry getLaundry(Double dimension,Double longitude){
		LaundryParams params = new LaundryParams();
		params.setDimension(dimension);
		params.setLongitude(longitude);
		return laundryRepository.findOne(buildSpecification(params));
	}
	
	//根据坐标范围查询店铺
	public List<Laundry> getLaundryByCoordinate(GetLaundryDTO dto){
		LaundryParams params = new LaundryParams();
		params.setDimension1(dto.getDimension1());
		params.setDimension2(dto.getDimension2());
		params.setLongitude1(dto.getLongitude1());
		params.setLongitude2(dto.getLongitude2());
		
		Specification<Laundry> buildSpecification = buildSpecification(params);
		
		return laundryRepository.findAll(buildSpecification);
	}
	
	// 获取Specification
	private Specification<Laundry> buildSpecification(LaundryParams params) {

		final Double dimension1 = params.getDimension1();
		final Double dimension2 = params.getDimension2();
		final Double longitude1 = params.getLongitude1();
		final Double longitude2 = params.getLongitude2();
		
		return new Specification<Laundry>() {
			public Predicate toPredicate(Root<Laundry> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				if(dimension1!=null){
					predicates.add(cb.ge(root.get("dimension").as(Double.class), dimension1));
				}
				
				if(dimension2!=null){
					predicates.add(cb.le(root.get("dimension").as(Double.class), dimension2));
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
	
	private Double dimension1;//维度
	private Double dimension2;//维度
	
	private Double longitude;//经度
	private Double dimension;//维度
	
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

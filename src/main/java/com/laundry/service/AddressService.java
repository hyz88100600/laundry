package com.laundry.service;

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
import com.laundry.dao.AddressRepository;
import com.laundry.domain.Address;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Address findByUserId(Long userId){
		AddressParams params = new AddressParams();
		params.setUserId(userId);
		return addressRepository.findOne(buildSpecification(params));
	}

	// 获取Specification
	private Specification<Address> buildSpecification(AddressParams params) {

		final Long userId = params.getUserId();
		
		return new Specification<Address>() {
			public Predicate toPredicate(Root<Address> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				if (userId!=null) {
					predicates.add(cb.equal(root.get("userId").as(Long.class),
							userId));
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

class AddressParams {
	private Long userId;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

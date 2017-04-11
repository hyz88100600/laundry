package com.laundry.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.laundry.dao.UserRepository;
import com.laundry.domain.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByPhone(String phone) {
		UserParams params = new UserParams();
		params.setPhone(phone);

		Specification<User> buildSpecification = buildSpecification(params);

		return userRepository.findOne(buildSpecification);
	}

	// 获取Specification
	private Specification<User> buildSpecification(UserParams params) {

		final String phone = params.getPhone();

		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				if (StringUtils.isNotBlank(phone)) {
					predicates.add(cb.equal(root.get("phone").as(String.class),
							phone));
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

class UserParams {
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

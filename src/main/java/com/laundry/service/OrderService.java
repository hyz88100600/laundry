package com.laundry.service;

import java.util.Date;
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
import com.laundry.dao.OrderItemRepository;
import com.laundry.dao.OrderRepository;
import com.laundry.domain.Order;
import com.laundry.domain.OrderItem;
import com.laundry.domain.type.OrderStatus;
import com.laundry.dto.OrderDTO;
import com.laundry.dto.OrderItemDTO;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//创建
	public void create(OrderDTO orderDTO){
		Order order  = new Order();
		order.setCreateOn(new Date());
		order.setLaundryId(orderDTO.getLaundryId());
		order.setPhone(orderDTO.getPhone());
		order.setOrderStatus(OrderStatus.init);
		order = orderRepository.save(order);
		//保存明细
		for (OrderItemDTO item : orderDTO.getItems()) {
			OrderItem orderItem =new OrderItem();
			orderItem.setRemarks(item.getDesc());
			orderItem.setOrderId(order.getId());
			orderItem.setPicture(item.getPicture());
			orderItemRepository.save(orderItem);
		}
	}
	
	public List<Order> findByPhoneAndOrderStatus(String phone,String orderStatus) {
		OrderParams params = new OrderParams();
		params.setOrderStatus(orderStatus);
		params.setPhone(phone);
		
		Specification<Order> buildSpecification = buildSpecification(params);
		return orderRepository.findAll(buildSpecification);
	}
	
	public List<OrderItem> findByItemsById(Long orderId){
		OrderItemParams params = new OrderItemParams();
		params.setOrderId(orderId);
		
		Specification<OrderItem> buildSpecificationItem = buildSpecificationItem(params);
		
		return orderItemRepository.findAll(buildSpecificationItem);
	}
	
	//查询
	private Specification<Order> buildSpecification(OrderParams params) {

		final String phone = params.getPhone();
		final String orderStatus = params.getOrderStatus();

		return new Specification<Order>() {
			public Predicate toPredicate(Root<Order> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				if (StringUtils.isNotBlank(phone)) {
					predicates.add(cb.equal(root.get("phone").as(String.class),
							phone));
				}
				
				if(StringUtils.isNotBlank(orderStatus)){
					predicates.add(cb.equal(root.get("orderStatus").as(String.class),
							orderStatus));
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
	
	
	private Specification<OrderItem> buildSpecificationItem(OrderItemParams params) {

		final Long orderId = params.getOrderId();
		
		return new Specification<OrderItem>() {
			public Predicate toPredicate(Root<OrderItem> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = Lists.newArrayList();
				
				
				if(orderId!=null){
					predicates.add(cb.equal(root.get("orderId").as(Long.class),
							orderId));
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

class OrderParams{
	private String orderStatus;
	private String phone;
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

class OrderItemParams{
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}

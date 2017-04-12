package com.laundry.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

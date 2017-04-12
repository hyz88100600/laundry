package com.laundry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.laundry.domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>,JpaSpecificationExecutor<OrderItem>{

}

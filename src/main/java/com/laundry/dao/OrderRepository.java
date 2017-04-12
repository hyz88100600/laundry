package com.laundry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.laundry.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order>{

}

package com.laundry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.laundry.domain.Laundry;

public interface LaundryRepository extends JpaRepository<Laundry, Long>,JpaSpecificationExecutor<Laundry>{

}

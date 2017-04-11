package com.laundry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laundry.dao.LaundryRepository;
import com.laundry.domain.Laundry;


@Service
@Transactional
public class LaundryService {

	@Autowired
	private LaundryRepository laundryRepository;
	
	public List<Laundry> findAll(){
		return laundryRepository.findAll();
	}
}

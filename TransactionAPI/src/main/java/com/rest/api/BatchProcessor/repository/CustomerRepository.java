package com.rest.api.BatchProcessor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.BatchProcessor.model.CustomerDO;

public interface CustomerRepository extends JpaRepository<CustomerDO, Long> {

	CustomerDO findById(long id);
	
    CustomerDO findByAccountNumber(String keyword);

}

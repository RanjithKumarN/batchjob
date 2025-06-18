package com.rest.api.BatchProcessor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.BatchProcessor.model.TransactionDO;

public interface TransactionRepository extends JpaRepository<TransactionDO, Long> {
	
	
    List<TransactionDO> findByCustomerId(long customerId);
    
	TransactionDO findById(long id);

    List<TransactionDO> findByDescriptionContainingIgnoreCase(String keyword);
    List<TransactionDO> findByDescription(String keyword);
    Page<TransactionDO> findAll(Pageable pageable);


}

package com.rest.api.BatchProcessor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.BatchProcessor.model.TransactionDO;
import com.rest.api.BatchProcessor.services.TransactionServiceImpl;

@RestController
@RequestMapping("/api/batch")
public class TransactionController {

	@Autowired
	private TransactionServiceImpl service;
	
	@GetMapping("/upload")
	public List<TransactionDO> upload() throws IOException {
		return service.saveToDB();
	}
	
	@GetMapping("/getAll")
	public Page<TransactionDO> getAll(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) throws IOException {

		return service.getAllTransactions(pageNo, pageSize, true);
	}

	@GetMapping("/search")
	public List<TransactionDO> search(@RequestParam(required = false) String customerId,
			@RequestParam(required = false) String accountNo, @RequestParam(required = false) String description)
			throws IOException {
		return service.search(customerId, accountNo, description);
	}

	@PutMapping("/{id}")
	public TransactionDO update(@RequestBody TransactionDO trn) throws IOException {
		return service.updateTransaction(trn);
	}

}

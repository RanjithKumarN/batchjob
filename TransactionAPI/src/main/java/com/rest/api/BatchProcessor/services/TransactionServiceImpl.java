package com.rest.api.BatchProcessor.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.api.BatchProcessor.model.CustomerDO;
import com.rest.api.BatchProcessor.model.TransactionDO;
import com.rest.api.BatchProcessor.repository.CustomerRepository;
import com.rest.api.BatchProcessor.repository.TransactionRepository;

@Service
public class TransactionServiceImpl {

	
	@Value("${data.file.path}")
    private Resource dataFile;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Page<TransactionDO> getAllTransactions(int pageNo, int pageSize, boolean paginationInd) throws IOException {
		Page<TransactionDO> list = null;
			Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("transactionId").descending());
			list = transactionRepository.findAll(pageable);
		return list;
	}

	public TransactionDO updateTransaction(TransactionDO updated) throws IOException {
		boolean found = false;
		TransactionDO existing = transactionRepository.getById(updated.getTransactionId());
		if (existing != null) {
			found = true;
			existing.setTrxAmount(updated.getTrxAmount());
			existing.setDescription(updated.getDescription());
			existing.setTransactionDate(updated.getTransactionDate());
			existing.setTransactionTime(updated.getTransactionTime());

			CustomerDO customer = customerRepository.getById(updated.getCustomer().getId());
			CustomerDO cust = customer;
			cust.setAccountNumber(updated.getCustomer().getAccountNumber());
			existing.setCustomer(cust);
			transactionRepository.save(existing);

		}
		if (!found)
			throw new NoSuchElementException("details not found for " + updated.getDescription());
		return updated;
	}

	public List<TransactionDO> search(String customerId, String accountNo, String description) throws IOException {
		List<TransactionDO> resultList = new ArrayList<TransactionDO>();
		if (customerId != null && !customerId.isEmpty()) {
			resultList = transactionRepository.findByCustomerId(Long.valueOf(customerId));
		} else if (description != null && !description.isEmpty()) {
			resultList = transactionRepository.findByDescription(description);
		} else {
			CustomerDO custDO = customerRepository.findByAccountNumber(accountNo);
			resultList = transactionRepository.findByCustomerId(custDO.getId());
		}

		return resultList;
	}

	public void saveToFile(List<TransactionDO> transactionDOs) throws IOException {
		try {
			for (TransactionDO transactionDO : transactionDOs) {
				CustomerDO customerDO = customerRepository.findById(transactionDO.getCustomer().getId());
				if (customerDO != null) {
					transactionDO.setCustomer(customerDO);
				}

				transactionRepository.save(transactionDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TransactionDO> saveToDB() throws IOException {
		List<TransactionDO> list = new ArrayList<>();
		List<TransactionDO> filtered = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
                new InputStreamReader(dataFile.getInputStream(), StandardCharsets.UTF_8))) {
			String headers = br.readLine();
			String line;

			while ((line = br.readLine()) != null) {

				if (!line.trim().isEmpty()) {
					String[] parts = line.trim().toString().split("\\|");

					if (parts.length == 6) {
						TransactionDO trnDO = new TransactionDO();

						trnDO.setTrxAmount(Double.valueOf(parts[1]));
						trnDO.setDescription(parts[2].toString());
						trnDO.setTransactionDate(parts[3].toString());
						trnDO.setTransactionTime(parts[4].toString());

						CustomerDO custDO = new CustomerDO();
						custDO.setId(Long.valueOf(parts[5]));
						custDO.setAccountNumber(parts[0].toString());
						trnDO.setCustomer(custDO);

						list.add(trnDO);
					}
				}
			}
			filtered = new ArrayList<>(list);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return filtered;
	}

}

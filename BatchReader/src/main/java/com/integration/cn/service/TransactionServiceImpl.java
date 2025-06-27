package com.integration.cn.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.integration.cn.dao.TransactionDAO;
import com.integration.cn.dao.TransactionDAOImpl;
import com.integration.domain.TransactionDO;

/**
 * 
 * @author Ranjith Kumar
 * @since 1.8
 *
 */
public class TransactionServiceImpl implements TransactionService {

	private static final TransactionDAO transDAO = new TransactionDAOImpl();
	
	public List<TransactionDO> saveToDB(String filename,Properties props) {
			List<TransactionDO> list = new ArrayList<>();
			List<TransactionDO> filtered = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
				String headers = reader.readLine();
				String line;

				while ((line = reader.readLine()) != null) {

					if (!line.trim().isEmpty()) {
						String[] parts = line.trim().toString().split("\\|");

						if (parts.length == 6) {
							TransactionDO trnDO = new TransactionDO();

							trnDO.setTrxAmount(Double.valueOf(parts[1]));
							trnDO.setDescription(parts[2].toString());
							trnDO.setTransactionDate(parts[3].toString());
							trnDO.setTransactionTime(parts[4].toString());
							trnDO.setCubstomerId(parts[5]);
							trnDO.setAccountNo(parts[0].toString());
							list.add(trnDO);
						}
					}
				}
				transDAO.insertTransactions(list,props);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

package com.integration.cn.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import com.integration.domain.TransactionDO;

public interface TransactionService {

	public List<TransactionDO> saveToDB(String fileName,Properties prop) ;
	

}
	

package com.integration.cn.dao;

import java.util.List;
import java.util.Properties;

import com.integration.domain.TransactionDO;

public interface TransactionDAO {
	public  void insertTransactions( List<TransactionDO> list,Properties prop);
}

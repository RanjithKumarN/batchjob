package com.integration.cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import com.integration.domain.TransactionDO;


/**
 *
 * @author Ranjith Kumar
 * @since 1.8
 *
 */
public class TransactionDAOImpl implements TransactionDAO {
	
	@Override
	 public void insertTransactions( List<TransactionDO> list,Properties props) {
		String dbUrl = props.getProperty("db.url");
        String dbUser = props.getProperty("db.username");
        String dbPass = props.getProperty("db.password");
	Connection conn=null;

	        try ( conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
	        	String sql = "INSERT INTO transactions (account_number, trx_amount, description, transaction_date, transaction_time, cust_id) VALUES (?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement ps = conn.prepareStatement(sql)) {
		            for (TransactionDO t : list) {
		                ps.setString(1, t.getAccountNo());
		                ps.setDouble(2, t.getTrxAmount());
		                ps.setString(3, t.getDescription());
		                ps.setString(4, t.getTransactionDate());
		                ps.setString(5, t.getTransactionTime());
		                ps.setString(6, t.getCubstomerId());
		                ps.addBatch();
		            }
		            ps.executeBatch();
		        }
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }finally{
			conn.close();
		}
	        
	    }			
}


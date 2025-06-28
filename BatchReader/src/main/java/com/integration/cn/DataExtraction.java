package com.integration.cn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.integration.cn.service.TransactionService;
import com.integration.cn.service.TransactionServiceImpl;
import com.integration.common.utils.LogUtils;

/**
 * 
 * @author Ranjith Kumar
 * @since 1.8
 *
 */
public final class DataExtraction {

	private static final TransactionService service = new TransactionServiceImpl();
	
	public static void main(String[] args) {
		boolean status = true;
		try {
			
			Properties props = loadProperties("config.properties");

	        String filePath = props.getProperty("file.path");
			
		LogUtils.log("***************************************************");
		service.saveToDB(filePath,props);		
			
		} catch (Exception e) {
			status = false;
		} finally {
			LogUtils.log("\n");
			LogUtils.log("***** Executing Finally Block *****");
		}

	}
	
	  public static Properties loadProperties(String filename) {
	        Properties props = new Properties();
	        try (InputStream input = new FileInputStream(filename)) {
	            props.load(input);
	        } catch (IOException ex) {
	            System.err.println("Failed to load config file: " + filename);
	            ex.printStackTrace();
	        }
	        return props;
	    }	


}

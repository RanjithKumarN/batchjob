package com.integration.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;



/**
 *
 * @author Ranjith Kumar
 * @since 1.8
 */
public class LogUtils {

	private static Logger logger;
	private static Date startDateTime, endDateTime;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	private static final DecimalFormat df = new DecimalFormat("#00");

	public static void init() throws IOException {

		Properties log4j = new Properties();
		try {
			log4j.load(new FileInputStream("/lib/log4j.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	public static void log(String message, Object... args) {
		if (args != null && args.length > 0) {
			logger.info(String.format(message, args));
		} else {
			logger.info(message);
		}
	}

}

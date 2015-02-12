package com.carelink.logger;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description logger service
 * 
 * @version 1.0
 * 
 * @author fanjian
 * 
 * @update 2014-6-28
 * 
 * @company www.petalways.com
 */
public class LoggerService {

	private static Logger logger = LoggerFactory.getLogger(LoggerService.class);

	/**
	 * 
	 * @description Open the logging
	 * 
	 */
	public static void startLog() {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Open the Logging Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Open the logger failed,Cause:");
			e.printStackTrace();
			System.exit(0);
		}
	}
}

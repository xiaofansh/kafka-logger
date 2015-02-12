package com.carelink.logger;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carelink.logger.appender.LogkafkaAppender;
import com.carelink.logger.console.Control;
import com.carelink.logger.hbase.HbaseConfig;
import com.carelink.logger.hbase.HbaseService;
import com.carelink.logger.kafka.KafkaConfig;
import com.carelink.logger.kafka.KafkaService;
import com.carelink.logger.monitor.MonitorService;

/**
 * 
 * @description the main function
 * 
 * @version 1.0
 * 
 * @author fanjian
 * 
 * @update 2014-6-28
 * 
 * @company www.petalways.com
 */
public class App {
	private static Logger logger = null;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoggerService.startLog();

		logger = LoggerFactory.getLogger(App.class);

		ServerConfig.readXml();

		LogkafkaAppender.setServerAddress(ServerConfig.getServerAddress());
		LogkafkaAppender.setProgrameName(ServerConfig.getProgrameName());
		LogkafkaAppender.setLogLevel(Level.WARN);

		HbaseConfig.readXml();
		HbaseService.startHbase();
		
		KafkaConfig.readXml();

		KafkaService.startLogAgent();
		
		KafkaService.startLogCollecter();
		
		MonitorService.startMonitor();

		Control.startControl();

		logger.info(ServerConfig.getProgrameName() + " Start Successfully");
	}

}

package com.carelink.logger.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.producer.ProducerConfig;

/**
 * 
 * @description 日志收集器
 * 
 * @version 1.0
 * 
 * @author yangfei
 * 
 * @update 2014-10-15
 * 
 * @company www.petalways.com
 */
public class LogAgent implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(LogAgent.class);

	private static final ConcurrentLinkedQueue<String> LogCacheQueue = new ConcurrentLinkedQueue<String>();

	public static void collector(String message) {
		if (null != message) {
			LogCacheQueue.add(message);
		}
	}

	private static final AtomicLong Key = new AtomicLong();

	private ProducerConfig config;

	private String topic = "Command_CareLink"; // 生产者队列主题

	private List<String> sendList = new ArrayList<String>();

	private Producer<String, String> producer;

	private ProducerData<String, String> data;

	private String partitionKey = "key"; // 生产者所需要的KEY

	public LogAgent(String kafkaAddress) {

		Properties props = new Properties();
		props.put("zk.connect", kafkaAddress);
		props.put("zk.sessiontimeout.ms", "60000");
		props.put("zk.sessiontimeout.ms", "60000");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("producer.type", "async");
		props.put("compression.codec", "1");
		props.put("batch.size", 1 + "");
		props.put("queue.size", 100000 + "");
		props.put("queue.time", 500000 + "");
		props.put("buffer.size", "604800");
		props.put("max.message.size", "20000000");
		props.put("reconnect.interval", String.valueOf(Integer.MAX_VALUE));
		ProducerConfig config = new ProducerConfig(props);

		this.config = config;
		this.topic = "SystemLog_CareLink";
		producer = new Producer<String, String>(this.config);

		logger.info("Start LogAgent, Kafka Topic:" + this.topic
				+ ",Properties:" + props.toString());
	}

	private void sleepWhile(int s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("Sleep Error:", e);
		}
	}

	public void run() {
		String logMessage = null;
		while (true) {
			while (!LogCacheQueue.isEmpty()) {
				logMessage = LogCacheQueue.poll();
				this.transfer(logMessage);
				logMessage = null;
			}
			this.sleepWhile(10);
		}
	}

	private void transfer(String message) {
		try {
			sendList.add(message.toString());
			String kafkaKey = partitionKey + Key.getAndIncrement();
			data = new ProducerData<String, String>(topic, kafkaKey, sendList);
			producer.send(data);
			sendList.clear();
			// logger.debug("Send LogMessage {}", message);
		} catch (Exception e) {
			logger.error("Send LogMessage Error", e);
		}
	}
}

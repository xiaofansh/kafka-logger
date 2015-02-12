package com.carelink.logger.appender;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.carelink.logger.agent.LogAgent;

public class LogkafkaAppender extends DailyRollingFileAppender {

	private static String serverAddress = "";

	private static String programeName = "";

	private static Level logLevel = Level.WARN;

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void append(LoggingEvent event) {
		// TODO Auto-generated method stub
		super.append(event);
		// String message = "["
		// + event.getLevel().toString()
		// + "] ["
		// + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(event
		// .getTimeStamp()) + "] "
		// + event.getLocationInformation().fullInfo + "["
		// + event.getThreadName() + "] " + event.getMessage();
		if (event.getLevel().toInt() >= logLevel.toInt()) {
			String content = event.getMessage().toString();
			if (content != null) {
				content = content.replaceAll("\"", "").replaceAll("\n", "");
			}
			String location = event.getLocationInformation().fullInfo;
			if (location != null) {
				location = location.replaceAll("\"", "").replaceAll("\n", "");
			}
			SystemLogItem item = new SystemLogItem();
			item.setFromDevice(serverAddress);
			item.setProgrameName(programeName);
			item.setLogLevel(event.getLevel().toString());
			item.setLogTime(event.getTimeStamp());
			item.setThreadName(event.getThreadName());
			item.setLocationInformation(location);
			item.setContent(content);
			String jsonItem = item.toJson();
			LogAgent.collector(jsonItem);
		}

	}

	/**
	 * @return serverAddress : return the property serverAddress.
	 */
	public static String getServerAddress() {
		return serverAddress;
	}

	/**
	 * @param serverAddress
	 *            : set the property serverAddress.
	 */
	public static void setServerAddress(String serverAddress) {
		LogkafkaAppender.serverAddress = serverAddress.replaceAll("\"", "");
	}

	/**
	 * @return programeName : return the property programeName.
	 */
	public static String getProgrameName() {
		return programeName;
	}

	/**
	 * @param programeName
	 *            : set the property programeName.
	 */
	public static void setProgrameName(String programeName) {
		LogkafkaAppender.programeName = programeName.replaceAll("\"", "");
	}

	/**
	 * @return logLevel : return the property logLevel.
	 */
	public static Level getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel
	 *            : set the property logLevel.
	 */
	public static void setLogLevel(Level logLevel) {
		LogkafkaAppender.logLevel = logLevel;
	}
}

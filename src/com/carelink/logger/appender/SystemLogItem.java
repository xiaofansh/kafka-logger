package com.carelink.logger.appender;

public class SystemLogItem {
	/**
	 * 关注对象
	 */
	private String observed = "";

	/**
	 * 日志时间
	 */
	private long logTime = System.currentTimeMillis();

	/**
	 * 日志级别
	 */
	private String logLevel = null;

	/**
	 * 设备源
	 */
	private String fromDevice = null;

	/**
	 * 程序名称
	 */
	private String programeName = null;

	/**
	 * 日志内容
	 */
	private String content = "";

	/**
	 * 日志位置信息
	 */
	private String locationInformation = "";

	/**
	 * 日志线程名称
	 */
	private String threadName = "";

	/**
	 * @return observed : return the property observed.
	 */
	public String getObserved() {
		return observed;
	}

	/**
	 * @param observed
	 *            : set the property observed.
	 */
	public void setObserved(String observed) {
		this.observed = observed;
	}

	/**
	 * @return logTime : return the property logTime.
	 */
	public long getLogTime() {
		return logTime;
	}

	/**
	 * @param logTime
	 *            : set the property logTime.
	 */
	public void setLogTime(long logTime) {
		this.logTime = logTime;
	}

	/**
	 * @return logLevel : return the property logLevel.
	 */
	public String getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel
	 *            : set the property logLevel.
	 */
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return fromDevice : return the property fromDevice.
	 */
	public String getFromDevice() {
		return fromDevice;
	}

	/**
	 * @param fromDevice
	 *            : set the property fromDevice.
	 */
	public void setFromDevice(String fromDevice) {
		this.fromDevice = fromDevice;
	}

	/**
	 * @return programeName : return the property programeName.
	 */
	public String getProgrameName() {
		return programeName;
	}

	/**
	 * @param programeName
	 *            : set the property programeName.
	 */
	public void setProgrameName(String programeName) {
		this.programeName = programeName;
	}

	/**
	 * @return content : return the property content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            : set the property content.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return locationInformation : return the property locationInformation.
	 */
	public String getLocationInformation() {
		return locationInformation;
	}

	/**
	 * @param locationInformation
	 *            : set the property locationInformation.
	 */
	public void setLocationInformation(String locationInformation) {
		this.locationInformation = locationInformation;
	}

	/**
	 * @return threadName : return the property threadName.
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName
	 *            : set the property threadName.
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"observed\"").append(":").append("\"")
				.append(this.observed).append("\"").append(",");
		sb.append("\"logTime\"").append(":").append(this.logTime).append(",");
		sb.append("\"logLevel\"").append(":").append("\"")
				.append(this.logLevel).append("\"").append(",");
		sb.append("\"fromDevice\"").append(":").append("\"")
				.append(this.fromDevice).append("\"").append(",");
		sb.append("\"programeName\"").append(":").append("\"")
				.append(this.programeName).append("\"").append(",");
		sb.append("\"content\"").append(":").append("\"").append(this.content)
				.append("\"").append(",");
		sb.append("\"locationInformation\"").append(":").append("\"")
				.append(this.locationInformation).append("\"").append(",");
		sb.append("\"threadName\"").append(":").append("\"")
				.append(this.threadName).append("\"");
		sb.append("}");
		return sb.toString();
	}
}

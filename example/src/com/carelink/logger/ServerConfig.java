package com.carelink.logger;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carelink.logger.common.Common;

/**
 * 
 * @description configuration information
 * 
 * @version 1.0
 * 
 * @author fanjian
 * 
 * @update 2014-6-28
 * 
 * @company www.petalways.com
 */
public class ServerConfig {

	private static final Logger logger = LoggerFactory
			.getLogger(ServerConfig.class);
	/**
	 * control source id
	 */
	private static int ControlSourceID = 13;

	private static String ProgrameName = "logger";

	private static String ServerAddress = "127.0.0.1";

	/**
	 * 
	 * @description config information
	 * 
	 * @return
	 */
	public static String forView() {
		StringBuilder sb = new StringBuilder();
		sb.append(Common.LineSep)
				.append("--------------------[ServerConfig]--------------------")
				.append(Common.LineSep);
		sb.append("ControlSourceID : ").append(ControlSourceID)
				.append(Common.LineSep);
		sb.append("ProgrameName : ").append(ProgrameName)
				.append(Common.LineSep);
		sb.append("ServerAddress : ").append(ServerAddress)
				.append(Common.LineSep);

		return sb.toString();
	}

	/**
	 * 
	 * @description print config inforamtion
	 * 
	 */
	public static void printConfig() {
		logger.info(ServerConfig.forView());
	}

	/**
	 * 
	 * @description Reads the configuration file
	 * 
	 */
	public static void readXml() {
		SAXReader reader = new SAXReader();
		Document root = null;
		try {

			root = reader.read(new File("config.xml"));

			ServerConfig.ControlSourceID = Integer.parseInt(root
					.selectSingleNode("root/Server/Config")
					.valueOf("@ControlSourceID").trim());

			ServerConfig.ProgrameName = root
					.selectSingleNode("root/Server/Config")
					.valueOf("@ProgrameName").trim();

			ServerConfig.ServerAddress = root
					.selectSingleNode("root/Server/Config")
					.valueOf("@ServerAddress").trim();

			logger.info("Reads the configuration file successfully");
		} catch (Exception e) {
			logger.error("Reads the configuration file error");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * @return controlSourceID : return the property controlSourceID.
	 */
	public static int getControlSourceID() {
		return ControlSourceID;
	}

	/**
	 * @return programeName : return the property programeName.
	 */
	public static String getProgrameName() {
		return ProgrameName;
	}

	/**
	 * @return serverAddress : return the property serverAddress.
	 */
	public static String getServerAddress() {
		return ServerAddress;
	}
}

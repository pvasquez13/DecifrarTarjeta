package com.globokas.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyManager {

	private static final Logger logger = Logger.getLogger(PropertyManager.class);

	public static String databaseURL;
	public static String databaseDriver;
	public static String databaseUser;
	public static String databasePassword;
	public static String databaseSchema;
	
	public static String racalDatabaseURL;
	public static String racalDatabaseDriver;
	public static String racalDatabaseUser;
	public static String racalDatabasePassword;
	public static String racalDatabaseSchema;
	
	public static String racalSixHostname;
	public static String racalSixUsername;
	public static String racalSixPassword;
	public static String racalSixHostdest;
	public static String racalSixPortdest;
	public static String racalSixProcdest;

	static {
		logger.debug("Inicializando PropertyManager ...");

		try {
			Properties prop = new Properties();
                        FileInputStream file = new FileInputStream("./app.properties");
			prop.load(file);
			
			racalDatabaseURL = prop.getProperty("racal.database.url");
			racalDatabaseDriver = prop.getProperty("racal.database.driver");
			racalDatabaseUser = prop.getProperty("racal.database.user");
			racalDatabasePassword = "Lima54321***";
//			racalDatabasePassword = prop.getProperty("racal.database.password");
			
			racalSixHostname = prop.getProperty("racal.six.hostname");
			racalSixUsername = prop.getProperty("racal.six.username");
			racalSixPassword = "srvibkbc";
//			racalSixPassword = prop.getProperty("racal.six.password");
			racalSixHostdest = prop.getProperty("racal.six.hostdest");
			racalSixPortdest = prop.getProperty("racal.six.portdest");
			racalSixProcdest = prop.getProperty("racal.six.procdest");

		} catch (IOException e) {
			logger.error("Error Inicializando PropertyManager [...]");
			logger.error(e.getMessage(), e);
		}
	}

}

package com.globokas.connection;

import com.globokas.property.PropertyManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;


public class FactoryDBConnection {
	
	private static final Logger logger = Logger.getLogger(FactoryDBConnection.class);
	
	private Connection trxConnection;
	private Connection racalConnection;
	private static FactoryDBConnection factoryDBConnection;
	
	public FactoryDBConnection() {
		this.racalConnection = this.getRacalConnectionInstance();
	}
	
	public static FactoryDBConnection getInstance(){
		if(factoryDBConnection == null) {
			factoryDBConnection = new FactoryDBConnection();
		}
		
		return factoryDBConnection;
	}
	
	private Connection getRacalConnectionInstance() {
		try {
			Properties prop = new Properties();
			prop.setProperty("user", PropertyManager.racalDatabaseUser);
			prop.setProperty("password", PropertyManager.racalDatabasePassword);

			Class.forName(PropertyManager.racalDatabaseDriver);
			Connection connection = DriverManager.getConnection(PropertyManager.racalDatabaseURL, prop);
			connection.setAutoCommit(false);
			return connection;

		} catch (SQLException e) {
			logger.error("Error inicializando conexion con base de datos para datos RACAL");
			logger.error(e.getMessage(), e);

		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException :: " + PropertyManager.databaseDriver);
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	public void closeConnection() throws SQLException {
		if(trxConnection != null) {
			trxConnection.close();
		}
		
		if(racalConnection != null) {
			racalConnection.close();
		}
	}
	
	
	public Connection getTrxConnection() {
		return trxConnection;
	}
	
	public Connection getRacalConnection() {
		return racalConnection;
	}
	
	public Connection SQLServerConnection() {
		try {
			Properties prop = new Properties();
			prop.setProperty("user", PropertyManager.databaseUser);
			prop.setProperty("password", PropertyManager.databasePassword);

			Class.forName(PropertyManager.databaseDriver);
			Connection connection = DriverManager.getConnection(PropertyManager.databaseURL, prop);
			connection.setAutoCommit(false);
			
			return connection;

		} catch (SQLException e) {
			logger.error("Error inicializando conexion con repositorio transaccional");
			logger.error(e.getMessage(), e);

		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException :: " + PropertyManager.databaseDriver);
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}
        
        public Connection RacalConnection() {
		try {
			Properties prop = new Properties();
			prop.setProperty("user", PropertyManager.racalDatabaseUser);
			prop.setProperty("password", PropertyManager.racalDatabasePassword);

			Class.forName(PropertyManager.racalDatabaseDriver);
			Connection connection = DriverManager.getConnection(PropertyManager.racalDatabaseURL, prop);
			connection.setAutoCommit(false);
			return connection;

		} catch (SQLException e) {
			logger.error("Error inicializando conexion con base de datos para datos RACAL");
			logger.error(e.getMessage(), e);

		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException :: " + PropertyManager.databaseDriver);
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}

}

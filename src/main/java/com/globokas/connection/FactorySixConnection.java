package com.globokas.connection;
import com.globokas.property.PropertyManager;
import java.util.Properties;

import com.novatronic.components.sixclient.tcp.ClienteSIXConnectionFactory;
import com.novatronic.components.sixclient.tcp.exception.ClienteSIXConnectionException;

public class FactorySixConnection {

    private static final Properties POOL;
    private static final Properties CONFIG;
    static private ClienteSIXConnectionFactory factory;
    
    static{
        POOL = new Properties();
        POOL.setProperty("name", "SIX"); // default configuration
        POOL.setProperty("min","1"); // default configuration-1
        POOL.setProperty("max","20"); // default configuration-1
        POOL.setProperty("interval","30"); // default configuration-30
        POOL.setProperty("validate","true"); // default configuration
        POOL.setProperty("containment","true"); // default configuration
        POOL.setProperty("containment_timeout","15"); // default configuration-15
        POOL.setProperty("timeDestroyConnect", "10"); // default configuration-10
        
        CONFIG = new Properties();
        CONFIG.setProperty("hostname", PropertyManager.racalSixHostname); //configurations in the server SIX
        CONFIG.setProperty("username", PropertyManager.racalSixUsername);//configurations in the server SIX
        CONFIG.setProperty("password", PropertyManager.racalSixPassword);//configurations in the server SIX
        CONFIG.setProperty("hostdest", PropertyManager.racalSixHostdest); // IP of server SIX        
        CONFIG.setProperty("procdest", PropertyManager.racalSixProcdest); // procedure of SIX
        CONFIG.setProperty("cifrado", "true"); // default configuration
        CONFIG.setProperty("masterkey", "01020304050607080910111213141516"); // default configuration
        CONFIG.setProperty("portdest", "8004"); // port of server SIX
        CONFIG.setProperty("timeout", "20"); // default configuration-20
        CONFIG.setProperty("typesixdrv", "web"); // default configuration
        CONFIG.setProperty("logger", "log4j.properties"); // register the actions of warn, error,trace, etc.
    }
    
    public static ClienteSIXConnectionFactory getInstance()throws ClienteSIXConnectionException {
    	if(factory == null) {
    		factory = ClienteSIXConnectionFactory.createConnectionFactory(CONFIG, POOL);
    	}
        return factory;
    }
}

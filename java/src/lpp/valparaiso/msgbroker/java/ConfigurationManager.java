package lpp.valparaiso.msgbroker.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

	public static final String RMQHOST_PROP = "rmqhost";
	
	private Properties props;
	private static ConfigurationManager configManager;
	
	private ConfigurationManager() {
		try {
			props = new Properties();
			FileInputStream in = new FileInputStream("config.properties");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigurationManager getInstance() {
		if (configManager == null)
			configManager = new ConfigurationManager();
		return configManager;
	}
	
	public Properties getProperties() {
		return props;
	}
	
}

package de.massisoft.blockchain.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	public static  Properties loadProperties(String configPath) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(configPath);
		prop.load(ip);
		
		return prop;
	}
}

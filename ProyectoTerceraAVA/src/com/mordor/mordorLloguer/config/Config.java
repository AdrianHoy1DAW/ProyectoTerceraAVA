package com.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private static Config instance = new Config();
	
	private String defaultFile  = "db.properties";
	private String appFile = "app.properties";
	private Properties properties;
	
	
	private Config() {
		
		Properties defaultProperties = new Properties();
		
		try(FileInputStream fis = new FileInputStream(new File(defaultFile))) {
			
			defaultProperties.load(fis);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		properties = new Properties(defaultProperties);
		
		try(FileInputStream fis = new FileInputStream(new File(appFile))) {
			
			properties.load(fis);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static Config getInstance() {
		return instance;
	}
	
	public void setProperties(String driver, String url, String user, String password) {
		
		properties.setProperty("ORACLE_DB_DRIVER_CLASS", driver);
		properties.setProperty("ORACLE_DB_URL", url);
		properties.setProperty("ORACLE_DB_USERNAME", user);
		properties.setProperty("ORACLE_DB_PASSWORD", password);
		save();
		
	}
	
	public void save() {
		
		try(FileOutputStream fos = new FileOutputStream(new File(appFile))) {
			
			properties.store(fos, "Store new Color");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

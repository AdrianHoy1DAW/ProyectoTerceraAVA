package com.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Config {

	private static Config instance = new Config();
	
	private String defaultFile  = "db.properties";
	private String appFile = "app.properties";
	private Properties properties;
	
	private String key = "asdkfasdfhsagfhsdfgfhmkijug";
	private Map<String,String> propiedadesSeguras;
	
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
		
		try {
			checkEncriptedProperties();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void checkEncriptedProperties() {
		
		propiedadesSeguras = new HashMap<String, String>();
		propiedadesSeguras.put("ORACLE_DB_USERNAME", "IS_ORACLE_DB_USERNAME_ENCRYPTED");
		propiedadesSeguras.put("ORACLE_DB_PASSWORD", "IS_ORACLE_DB_PASSWORD_ENCRYPTED");
		
		for(String isEncripted : propiedadesSeguras.values()) {
			if(!properties.containsKey(isEncripted))
				properties.put(isEncripted, "false");
		}
		
		for(String property:propiedadesSeguras.keySet()) {
			encryptPropertyValue(property, propiedadesSeguras.get(property));
		}
		
		save();
			
		
	}
	
	private String encrypt(String tmpPwd) {

		// Encrypt

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String encryptedPassword = encryptor.encrypt(tmpPwd);

		return encryptedPassword;

		}
	
	private String decryptPropertyValue(String propertyKey) {

		String encryptedPropertyValue = properties.getProperty(propertyKey);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

		return decryptedPropertyValue;

		}
	
	private void encryptPropertyValue(String propertyKey, String isPropertyKeyEncrypted) {

		// Retrieve boolean properties value to see if password is already

		// encrypted or not

		String isEncrypted = properties.getProperty(isPropertyKeyEncrypted);

		// Check if password is encrypted?

		if (isEncrypted.equals("false")) {

		String tmpPwd = properties.getProperty(propertyKey);

		String encryptedPassword = encrypt(tmpPwd);

		// Overwrite password with encrypted password in the properties file

		// using Apache Commons Cinfiguration library

		properties.setProperty(propertyKey, encryptedPassword);

		// Set the boolean flag to true to indicate future encryption

		// operation that password is already encrypted

		properties.setProperty(isPropertyKeyEncrypted, "true");

		// Save the properties file

		save();

		}

		}


	public static Config getInstance() {
		return instance;
	}
	

	
	public void setDriver(String driver) {
		properties.setProperty("ORACLE_DB_DRIVER_CLASS", driver);
		save();
	}
	
	public void setUrl(String url) {
		properties.setProperty("ORACLE_DB_URL", url);
		save();
	}
	
	public void setUser(String user) {
		properties.setProperty("ORACLE_DB_USERNAME", encrypt(user));
		properties.put("IS_ORACLE_DB_USERNAME_ENCRYPTED","true");
		save();
	}
	
	public void setPassword(String password) {
		properties.setProperty("ORACLE_DB_PASSWORD", encrypt(password));
		properties.put("IS_ORACLE_DB_PASSWORD_ENCRYPTED","true");
		save();
	}
	
	public String getDriver() {
		
		return properties.getProperty("ORACLE_DB_DRIVER_CLASS");
		
	}
	
	public String getUrl() {
		
		return properties.getProperty("ORACLE_DB_URL");
		
	}
	
	public String getUsername() {
		
		
		return decryptPropertyValue("ORACLE_DB_USERNAME");
		
		
	}
	
	public String getPassword() {
		
		return decryptPropertyValue("ORACLE_DB_PASSWORD");
		
	}
	
	public void save() {
		
		try(FileOutputStream fos = new FileOutputStream(new File(appFile))) {
			
			properties.store(fos, "UTF-8");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

package com.mordor.mordorLloguer.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mordor.mordorLloguer.config.Config;

import oracle.jdbc.datasource.impl.OracleDataSource;



public class MyDataSource {

	private static String defaultProperties = "app.properties";

	
	public static DataSource getOracleDataSource() {

		// Propiedades donde tenemos los datos de acceso a la BD

		Properties props = new Properties();

		// Objeto DataSource que devolveremos

		OracleDataSource oracleDS = null;

		try(FileInputStream fis = new FileInputStream("app.properties");) {

		// Cargamos las propiedades

		props.load(fis);

		// Generamos el DataSource con los datos URL, user y passwd necesarios

		oracleDS = new OracleDataSource();

		oracleDS.setURL(Config.getInstance().getUrl());

		oracleDS.setUser(Config.getInstance().getUsername());

		oracleDS.setPassword(Config.getInstance().getPassword());

		} catch (IOException e) {

		e.printStackTrace();

		} catch (SQLException e) {

		e.printStackTrace();

		}

		return oracleDS;

		}
	

}

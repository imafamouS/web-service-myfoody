package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DatabaseHelper {
	private String classname;

	private String url;

	private String database;

	private String user;

	private String password;

	private static DatabaseHelper databaseHelper;

	protected Connection connection;

	private DatabaseHelper() throws SQLException {
		getInfo();

		connection = getConnection();
	}

	public static synchronized DatabaseHelper getInstanse() {
		try {
			if (databaseHelper == null) {
				return new DatabaseHelper();
			} else {
				return databaseHelper;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	private void getInfo() {

		try {
			Properties properties = new Properties();

			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

			if (input == null) {
				System.out.println("Cannot find config");
				return;
			}

			properties.load(input);

			classname = properties.getProperty("classname");

			url = properties.getProperty("url");

			database = properties.getProperty("database");

			user = properties.getProperty("user");

			password = properties.getProperty("password");

			Class.forName(classname);

		} catch (IOException | ClassNotFoundException e) {

		}
	}

	public Connection getConnection() throws SQLException {

		return (Connection) DriverManager
				.getConnection(url + database + "?useUnicode=true&characterEncoding=utf-8&useSSL=true", user, password);
	}
}
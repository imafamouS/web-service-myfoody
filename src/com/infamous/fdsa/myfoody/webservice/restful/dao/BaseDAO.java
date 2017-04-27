package com.infamous.fdsa.myfoody.webservice.restful.dao;

public abstract class BaseDAO {
	public DatabaseHelper databaseHelper;
	
	public BaseDAO(){
		databaseHelper=DatabaseHelper.getInstanse();
	}
}

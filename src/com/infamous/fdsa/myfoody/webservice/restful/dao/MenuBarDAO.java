package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.mysql.jdbc.PreparedStatement;

public class MenuBarDAO  extends BaseDAO {

	public MenuBarDAO(){
		super();
	}
	
	public ResultSet getListCategory(String code) throws SQLException{
		String sql="";
		if(code.equals(AppConfig.REQUEST_CODE_CATEGORY_WHAT2DO)){
			sql="select * from view_restype";
		}else if(code.equals(AppConfig.REQUEST_CODE_CATEGORY_WHERE2GO)){
			sql="select * from view_wheretype";
		}
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
	}
}

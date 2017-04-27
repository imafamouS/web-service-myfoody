package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.myfoody.webservice.restful.bean.LoginCredential;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO extends BaseDAO {
	public UserDAO() {
		super();
	}
	
	public ResultSet checkLogin(LoginCredential loginCredential) throws SQLException{
		String sql="select * from tbl_user where userid=? and password=?";
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, loginCredential.getUserID());
			pre.setString(2, MyFunction.encryptMD5(loginCredential.getPassword()));
			
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
		
	}
	public ResultSet getUserByID(String userid) throws SQLException{
		String sql="select * from tbl_user where userid=?";
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1,userid);
			
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
		
	}
}

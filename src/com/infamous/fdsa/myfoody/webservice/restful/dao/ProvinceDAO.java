package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ProvinceDAO extends BaseDAO {
	public ProvinceDAO(){
		super();
	}
	
	public ResultSet getListProvince(String countryID) throws SQLException{
		String sql="";
		sql="SELECT * FROM view_province where id_country=?";
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, countryID);
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
	}
}

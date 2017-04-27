package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class MoreImageRestaurantDAO extends BaseDAO {
	public MoreImageRestaurantDAO(){
		super();
	}
	
	public ResultSet getMoreImageRestaurantDAO(String resid,int numofPhoto) throws SQLException{
		String sql="SELECT * FROM tbl_morephoto_res where res_id=? "+" LIMIT ?";
		
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, resid);
			pre.setInt(2, numofPhoto);
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
	}
	
}
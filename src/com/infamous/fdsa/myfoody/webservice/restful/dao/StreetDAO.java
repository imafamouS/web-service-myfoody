package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class StreetDAO extends BaseDAO{
	public StreetDAO(){
		super();
	}
	public ResultSet getListStreet(String districtID) throws SQLException{
		String sql="";
		sql="SELECT * FROM tbl_street where id_district=?";
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, districtID);
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}
	public ResultSet getListStreetByProvinceID(String provinceID) throws SQLException{
		String sql="";
		sql=	"SELECT * FROM dbomyfoody.tbl_street ";
		sql=sql+" where id_district in ";
		sql=sql+" (select id_district from tbl_province where id_province=?)";
		
		PreparedStatement pre=null;
		try{
			pre=(PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);
		}catch(SQLException e){
			System.out.print("FAIL to get");
		}
		
		return pre.executeQuery();
	}

}

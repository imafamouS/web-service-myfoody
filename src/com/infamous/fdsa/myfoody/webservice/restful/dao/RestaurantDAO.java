package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class RestaurantDAO extends BaseDAO {
	private RestaurantDAO() {
		super();
	}

protected static RestaurantDAO instance;
	
	public synchronized static RestaurantDAO getInstance(){
		if(instance==null){
			instance=new RestaurantDAO();
		}
		return instance;
	}

	public ResultSet getListRestaurant(String provinceID, String districtID, String streetID, String whereType)
			throws SQLException {
		String sql = "call sp_getRestaurant(?,?,?,?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);
			pre.setString(2, districtID);
			pre.setString(3, streetID);
			pre.setString(4, whereType);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	public ResultSet getRestaurantById(String id)
			throws SQLException {
		String sql = "call sp_getRestaurantDetail(?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, id);
			
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

}

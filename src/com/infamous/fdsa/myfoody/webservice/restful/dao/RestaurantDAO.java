package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class RestaurantDAO extends BaseDAO {
	private RestaurantDAO() {
		super();
	}

	protected static RestaurantDAO instance;

	public synchronized static RestaurantDAO getInstance() {
		if (instance == null) {
			instance = new RestaurantDAO();
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

	public ResultSet getRestaurantById(String id) throws SQLException {
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
	public ResultSet getNewRestaurantID() throws SQLException{
		String sql = "select count(*) from tbl_restaurant";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	
	public ResultSet getListRestaurantByOffset(String provinceID, String districtID, String streetID, String whereType,int page,int num_record)
			throws SQLException {
		String sql = "call sp_getRestaurantByOffset(?,?,?,?,?,?)";
		System.out.println(page+"DSDSADS");
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);
			pre.setString(2, districtID);
			pre.setString(3, streetID);
			pre.setString(4, whereType);
			pre.setInt(5, page);
			pre.setInt(6, num_record);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	

	public int insertRestaurant(String res_id, String rest_name, String address,String phone, String id_province, String id_district,
			String where_type, String res_type, String photo, String open_time, String close_time, double lat,
			double lng, double min_cash, double max_cash) throws SQLException {
		String sql = "insert into tbl_restaurant(res_id,rest_name,address,phone,id_province,id_district,"
				+ "where_type,res_type,photo,open_time,close_time,lat,tbl_restaurant.long,min_cash,max_cash)";
		sql += " value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, res_id);
			pre.setString(2, rest_name);
			pre.setString(3, address);
			pre.setString(4, phone);
			pre.setString(5, id_province);
			pre.setString(6, id_district);
			pre.setString(7, where_type);
			pre.setString(8, res_type);
			pre.setString(9, photo);
			pre.setString(10, open_time);
			pre.setString(11, close_time);
			pre.setDouble(12, lat);
			pre.setDouble(13, lng);
			pre.setDouble(14, min_cash);
			pre.setDouble(15, max_cash);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		System.out.println(pre.getPreparedSql());
		return pre.executeUpdate();
	}
}

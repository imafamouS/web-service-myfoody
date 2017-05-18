package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class FoodDAO extends BaseDAO {

	private FoodDAO() {
		super();
	}

	protected static FoodDAO instance;

	public synchronized static FoodDAO getInstance() {
		if (instance == null) {
			instance = new FoodDAO();
		}
		return instance;
	}

	public ResultSet getListFood(String provinceID, String districtID, String streetID, String resType)
			throws SQLException {
		String sql = "call sp_getFood(?,?,?,?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);
			pre.setString(2, districtID);
			pre.setString(3, streetID);
			pre.setString(4, resType);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	public ResultSet getListFoodByOffset(String provinceID, String districtID, String streetID, String resType,int page,int num_record)
			throws SQLException {
		String sql = "call sp_getFoodByOffset(?,?,?,?,?,?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);
			pre.setString(2, districtID);
			pre.setString(3, streetID);
			pre.setString(4, resType);
			pre.setInt(5, page);
			pre.setInt(6, num_record);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	

}

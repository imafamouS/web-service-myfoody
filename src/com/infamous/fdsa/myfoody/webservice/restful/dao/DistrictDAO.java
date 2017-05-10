package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class DistrictDAO extends BaseDAO {
	private DistrictDAO() {
		super();
	}

	protected static DistrictDAO instance;

	public synchronized static DistrictDAO getInstance() {
		if (instance == null) {
			instance = new DistrictDAO();
		}
		return instance;
	}

	public ResultSet getListDistrictByProvinceID(String provinceID) throws SQLException {
		String sql = "";
		sql = "SELECT * FROM tbl_district WHERE id_province=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, provinceID);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

}

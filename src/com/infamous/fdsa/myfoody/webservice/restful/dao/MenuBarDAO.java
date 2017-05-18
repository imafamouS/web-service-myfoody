package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.mysql.jdbc.PreparedStatement;

public class MenuBarDAO extends BaseDAO {

	private MenuBarDAO() {
		super();
	}

	protected static MenuBarDAO instance;

	public synchronized static MenuBarDAO getInstance() {
		if (instance == null) {
			instance = new MenuBarDAO();
		}
		return instance;
	}

	public ResultSet getListCategory(String code) throws SQLException {
		String sql = "";
		if (code.equals(AppConfig.REQUEST_CODE_CATEGORY_WHAT2DO)) {
			sql = "select * from view_restype";
		} else if (code.equals(AppConfig.REQUEST_CODE_CATEGORY_WHERE2GO)) {
			sql = "select * from view_wheretype";
		}

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	public ResultSet getWhereTypeByResType(String res_type) throws SQLException{
		String sql = "select id_type from tbl_wheretype where tbl_wheretype.name_type = (select name_type from tbl_restype where tbl_restype.id_type=?)";
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, res_type);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}
	public ResultSet getResTypeByWhereType(String where_type) throws SQLException{
		String sql = "select id_type from tbl_restype where tbl_restype.name_type = (select name_type from tbl_wheretype where tbl_wheretype.id_type=?)";
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, where_type);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}
}

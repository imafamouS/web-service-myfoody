package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class MoreImageRestaurantDAO extends BaseDAO {
	private MoreImageRestaurantDAO() {
		super();
	}

	protected static MoreImageRestaurantDAO instance;

	public synchronized static MoreImageRestaurantDAO getInstance() {
		if (instance == null) {
			instance = new MoreImageRestaurantDAO();
		}
		return instance;
	}

	public ResultSet getMoreImageRestaurantDAO(String resid, int numofPhoto) throws SQLException {
		String sql = "SELECT * FROM tbl_morephoto_res where res_id=? " + " LIMIT ?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, resid);
			pre.setInt(2, numofPhoto);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}
	public int insertMoreImageResturant(String id,String resid,String photo) throws SQLException{
		String sql="INSERT INTO tbl_morephoto_res(id,res_id,photo) value(?,?,?)";
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, id);
			pre.setString(2, resid);
			pre.setString(3, photo);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		
		return pre.executeUpdate();

	}
	public ResultSet getNewID() throws SQLException{
		String sql = "select count(*) from tbl_morephoto_res";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

}

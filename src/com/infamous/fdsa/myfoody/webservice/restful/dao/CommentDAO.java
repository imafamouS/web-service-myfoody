package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class CommentDAO extends BaseDAO {
	private CommentDAO() {
		super();
	}

	protected static CommentDAO instance;

	public synchronized static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	public ResultSet getCommentRes(String resid, int numofComment) throws SQLException {
		String sql = "";
		sql = "SELECT * FROM tbl_comment_res WHERE res_id=? " + "LIMIT ?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, resid);
			pre.setInt(2, numofComment);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

	public ResultSet getCommentFood(String foodid, int numofComment) throws SQLException {
		String sql = "";
		sql = "SELECT * FROM tbl_comment_food WHERE food_id=? " + "LIMIT ?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, foodid);
			pre.setInt(2, numofComment);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();
	}

}

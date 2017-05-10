package com.infamous.fdsa.myfoody.webservice.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.infamous.fdsa.myfoody.webservice.restful.bean.LoginCredential;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO extends BaseDAO {
	private UserDAO() {
		super();
	}
	
	protected static UserDAO instance;
	
	public synchronized static UserDAO getInstance(){
		if(instance==null){
			instance=new UserDAO();
		}
		return instance;
	}

	public ResultSet checkLogin(LoginCredential loginCredential) throws SQLException {
		String sql = "select * from tbl_user where userid=? and password=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, loginCredential.getUserID());
			pre.setString(2, MyFunction.encryptMD5(loginCredential.getPassword()));

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}

		return pre.executeQuery();

	}

	public ResultSet getUserByID(String userid) throws SQLException {
		String sql = "select * from tbl_user where userid=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, userid);

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}

	public ResultSet getUserByID(String userid, String token) throws SQLException {
		String sql = "select * from tbl_user where userid=? and secretcode=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, userid);
			pre.setString(2, token);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}

	public int register(UserBean user, String password) throws SQLException {
		String sql = "Insert into tbl_user(userid,password,name,date_join,secretcode) values (?,?,?,?,?)";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, user.getUserid());
			pre.setString(2, MyFunction.encryptMD5(password));
			pre.setString(3, user.getName());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			pre.setString(4, dateFormat.format(date));

			pre.setString(5, MyFunction.generateSecretCode());
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeUpdate();

	}

	public int update(UserBean user) throws SQLException {
		String sql = "UPDATE tbl_user set name=?,firstname=?,sex=?,marry=?,age=? where userid=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, user.getName());
			pre.setString(2, user.getFirstname());
			pre.setString(3, user.getSex());
			pre.setString(4, user.getMarry());
			pre.setString(5, user.getAge());
			pre.setString(6, user.getUserid());

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeUpdate();
	}

	public int changePass(String userid, String currentpass, String newpass) throws SQLException {
		String sql = "UPDATE tbl_user set password=? where userid=? and password=?";

		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, MyFunction.encryptMD5(newpass));
			pre.setString(2, userid);
			pre.setString(3, MyFunction.encryptMD5(currentpass));

		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeUpdate();

	}

	public ResultSet getimage(String userid,String image,String token) throws SQLException {
		String sql="";
		if(image.equalsIgnoreCase("avatar")){
			sql = "select avatar from tbl_user where userid=? and secretcode=?";
		}else if(image.equalsIgnoreCase("cover")){
			sql = "select cover from tbl_user where userid=? and secretcode=?";
		}
	
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, userid);
			pre.setString(2, token);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeQuery();
	}

	public int changeavatar(String userid,String image,String token) throws SQLException {
	
		String sql = "UPDATE tbl_user set avatar=? where userid=? and secretcode=?";
	
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, image);
			pre.setString(2, userid);
			pre.setString(3, token);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeUpdate();
	}
	public int changecover(String userid,String image,String token) throws SQLException {
		
		String sql = "UPDATE tbl_user set cover=? where userid=? and secretcode=?";
	
		PreparedStatement pre = null;
		try {
			pre = (PreparedStatement) this.databaseHelper.connection.prepareStatement(sql);
			pre.setString(1, image);
			pre.setString(2, userid);
			pre.setString(3, token);
		} catch (SQLException e) {
			System.out.print("FAIL to get");
		}
		return pre.executeUpdate();
	}
}

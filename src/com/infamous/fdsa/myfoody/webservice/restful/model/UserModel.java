package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.LoginCredential;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.UserDAO;

public class UserModel {
	private UserDAO userDAO;
	private String avatarPath=AppConfig.IMAGE_PATH_USER_AVATAR_LOCAL;
	private String coverPath=AppConfig.IMAGE_PATH_USER_COVER_LOCAL;
	public UserModel(){
		userDAO=UserDAO.getInstance();
	}
	
	public UserBean checkLogin(LoginCredential loginCredential){
		UserBean user=null;
		
		try {
			ResultSet rs=userDAO.checkLogin(loginCredential);
			
			if(rs!=null){
				if(rs.next()){
					String user_id=rs.getString(1);
					String name=rs.getString(3);
					String phone=rs.getString(4);
					String address=rs.getString(5);
					String age=rs.getString(6);
					
					String sex=rs.getString(8);
					String marry=rs.getString(9);
					String avatar=rs.getString(7)!=null?avatarPath+rs.getString(7):"";
					String cover=rs.getString(10)!=null?coverPath+rs.getString(10):"";
					String datejoin=rs.getString(11);
					String firstname=rs.getString(12);
					String secretcode=rs.getString(13);
					
					user=new UserBean(user_id,name,phone,address,age,avatar,sex,marry,cover,firstname);
					user.setDatejoin(datejoin);
					user.setSecretcode(secretcode);
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public UserBean getUserByID(String userid){
		UserBean user=null;
		
		try {
			ResultSet rs=userDAO.getUserByID(userid);
			
			if(rs!=null){
				if(rs.next()){
					String user_id=rs.getString(1);
					String name=rs.getString(3);
					String phone=rs.getString(4);
					String address=rs.getString(5);
					String age=rs.getString(6);
					String sex=rs.getString(8);
					String marry=rs.getString(9);
					String avatar=rs.getString(7)!=null?avatarPath+rs.getString(7):"";
					String cover=rs.getString(10)!=null?coverPath+rs.getString(10):"";
					String datejoin=rs.getString(11);
					String firstname=rs.getString(12);
					String secretcode=rs.getString(13);
					
					user=new UserBean(user_id,name,phone,address,age,avatar,sex,marry,cover,firstname);
					user.setDatejoin(datejoin);
					user.setSecretcode(secretcode);
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public UserBean getUserByID(String userid,String token){
		UserBean user=null;
		
		try {
			ResultSet rs=userDAO.getUserByID(userid,token);
			
			if(rs!=null){
				if(rs.next()){
					String user_id=rs.getString(1);
					String name=rs.getString(3);
					String phone=rs.getString(4);
					String address=rs.getString(5);
					String age=rs.getString(6);
					String sex=rs.getString(8);
					String marry=rs.getString(9);
					
					String avatar=rs.getString(7)!=null?avatarPath+rs.getString(7):"";
					String cover=rs.getString(10)!=null?coverPath+rs.getString(10):"";
					String datejoin=rs.getString(11);
					String firstname=rs.getString(12);
					String secretcode=rs.getString(13);
					
					user=new UserBean(user_id,name,phone,address,age,avatar,sex,marry,cover,firstname);
					user.setDatejoin(datejoin);
					user.setSecretcode(secretcode);
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public String getimage(String userid,String image,String token){
		String out="";
		try{
			ResultSet rs=userDAO.getimage(userid, image, token);
			if(rs!=null){
				if(rs.next()){
					out=rs.getString(1);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	public int register(UserBean user,String password) throws SQLException{
		return userDAO.register(user, password);
	}
	public int update(UserBean user) throws SQLException{
		return userDAO.update(user);
	}
	public int changePass(String userid,String currentpass,String newpass) throws SQLException{
		return userDAO.changePass(userid,currentpass,newpass);
	}
	public int changeavatar(String userid,String avatar,String token) throws SQLException{
		return userDAO.changeavatar(userid, avatar, token);
	}
	public int changecover(String userid,String cover,String token) throws SQLException{
		return userDAO.changecover(userid, cover, token);
	}
}

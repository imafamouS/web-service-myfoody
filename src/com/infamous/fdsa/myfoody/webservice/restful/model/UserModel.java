package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.LoginCredential;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.UserDAO;

public class UserModel {
	private UserDAO userDAO;
	private String imgPath=AppConfig.IMAGE_PATH_USER_AVATAR_LOCAL;
	public UserModel(){
		userDAO=new UserDAO();
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
					String avatar=imgPath+rs.getString(7);
					
					user=new UserBean(user_id,name,phone,address,age,avatar);
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
					String avatar=imgPath+rs.getString(7);
					
					user=new UserBean(user_id,name,phone,address,age,avatar);
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}

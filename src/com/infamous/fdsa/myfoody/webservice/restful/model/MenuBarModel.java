package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.MenuBarBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.MenuBarDAO;

public class MenuBarModel {
	
	private MenuBarDAO menuBarDAO;
	String imgPath=AppConfig.IMAGE_PATH_CATEGORY_LOCAL;
	
	public MenuBarModel(){
		menuBarDAO=MenuBarDAO.getInstance();
	}
	
	public List<MenuBarBean> getListCategory(String code){
		List<MenuBarBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=menuBarDAO.getListCategory(code);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					String image=imgPath+rs.getString(3);
					
					MenuBarBean img=new MenuBarBean(id,title,image,false);
					
					list.add(img);	
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public String getWhereTypeByResType(String res_type){
		String result="";
		try{
			ResultSet rs=menuBarDAO.getWhereTypeByResType(res_type);
			if(rs!=null){
				if(rs.next()){
					result=rs.getString(1);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String getResTypeByWhereType(String where_type){
		String result="";
		try{
			ResultSet rs=menuBarDAO.getResTypeByWhereType(where_type);
			if(rs!=null){
				if(rs.next()){
					result=rs.getString(1);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

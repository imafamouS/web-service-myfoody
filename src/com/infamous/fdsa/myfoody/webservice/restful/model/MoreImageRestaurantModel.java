package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.MoreImageRestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.MoreImageRestaurantDAO;

public class MoreImageRestaurantModel {
	private MoreImageRestaurantDAO moreImageRestaurantDAO;
	private String imagePath=AppConfig.IMAGE_PATH_MAIN_PHOTO_RES_LOCAL;
	public MoreImageRestaurantModel(){
		this.moreImageRestaurantDAO=new MoreImageRestaurantDAO();
	}
	
	public List<MoreImageRestaurantBean> getMoreImageRestaurantDAO(String resid,int numofPhoto){
		List<MoreImageRestaurantBean> list = new ArrayList<>();

		try {
			
			ResultSet rs = moreImageRestaurantDAO.getMoreImageRestaurantDAO(resid, numofPhoto);
			if (rs != null) {
				while (rs.next()) {
					String id = rs.getString(1);
					String res_id = rs.getString(2);
					String photo=imagePath+rs.getString(3);
					
					
					MoreImageRestaurantBean moreImage=new MoreImageRestaurantBean(id, res_id,photo);

					list.add(moreImage);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

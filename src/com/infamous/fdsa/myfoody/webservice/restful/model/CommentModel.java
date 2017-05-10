package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.bean.CommentFoodBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.CommentResBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.CommentDAO;

public class CommentModel {
	private CommentDAO commentDao;
	private UserModel userModel;
	public CommentModel(){
		commentDao=CommentDAO.getInstance();
		userModel=new UserModel();
	}
	
	public List<CommentResBean> getCommentRes(String resid,int numofComment){
			List<CommentResBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=commentDao.getCommentRes(resid, numofComment);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String userid=rs.getString(2);
					String res_id=rs.getString(3);
					String comment=rs.getString(4);
					double rating=rs.getDouble(5);
					
					UserBean user=userModel.getUserByID(userid);
					
					CommentResBean item=new CommentResBean(id, user, res_id, comment, rating);
					
					list.add(item);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<CommentFoodBean> getCommentFood(String foodid,int numofComment){
		List<CommentFoodBean> list=new ArrayList<>();
	
	try {
		ResultSet rs=commentDao.getCommentFood(foodid, numofComment);
		
		if(rs!=null){
			while(rs.next()){
				String id=rs.getString(1);
				String userid=rs.getString(2);
				String food_id=rs.getString(3);
				String comment=rs.getString(4)==null?"Thêm bởi admin":rs.getString(4);

				UserBean user=userModel.getUserByID(userid)==null?userModel.getUserByID("admin"):userModel.getUserByID(userid);
				
				CommentFoodBean item=new CommentFoodBean(id, user, food_id, comment);
				
				list.add(item);	
			}
		}
			
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
}
}

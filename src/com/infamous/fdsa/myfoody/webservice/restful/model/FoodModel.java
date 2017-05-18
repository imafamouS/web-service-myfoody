package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.CommentFoodBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.FoodBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.FoodDAO;

public class FoodModel {

	public static final String SORT_MOSTVIEW = "xemnhieu";
	public static final String SORT_POPULAR = "phobien";
	
	public static final int DEFAULT_NUM_COMMENT=1;
	
	String imagePath=AppConfig.IMAGE_PATH_FOOD_LOCAL;
	
	private FoodDAO foodDao;
	private CommentModel commentModel;
	private UserModel userModel;
	public FoodModel() {
		foodDao = FoodDAO.getInstance();
		commentModel=new CommentModel();
		userModel=new UserModel();
		
	}

	public List<FoodBean> getListFood(String provinceID,String districtID,
			String streetID, String resType,
			String sortType) {

		List<FoodBean> list = new ArrayList<>();

		try {
			
			ResultSet rs = foodDao.getListFood(provinceID, districtID, streetID, resType);
			System.out.println(districtID+"   "+streetID+"    "+resType);
			if (rs != null) {
				while (rs.next()) {
					String id = rs.getString(1);
					String res_id = rs.getString(2);
					String title=rs.getString(3);
					String photo=imagePath+rs.getString(4);
					String name_res=rs.getString(10);
					String address_res=rs.getString(9);
					int total_review=rs.getInt(11);
					
					FoodBean food=new FoodBean(id, title, res_id, name_res, address_res, photo);
					food.setTotal_review(total_review);
					
					List<CommentFoodBean> comments=commentModel.getCommentFood(id, DEFAULT_NUM_COMMENT);
					
					if(comments==null ||comments.size()<=0){
						comments=new ArrayList<>();
						UserBean user=userModel.getUserByID("admin@gmail.com");
						CommentFoodBean comment=new CommentFoodBean(res_id, user, id, "Thêm bởi admin");
						comments.add(comment);
					}
					food.setListComment(comments);

					list.add(food);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sortType!=null && !sortType.equals("") && sortType.length()>0){
			if (sortType.equals(SORT_MOSTVIEW) || sortType.equals(SORT_POPULAR)) {
				Collections.sort(list, Collections.reverseOrder(new Comparator<FoodBean>() {

					@Override
					public int compare(FoodBean o1, FoodBean o2) {
						Integer io1 = (Integer) o1.getTotal_review();
						Integer io2 = (Integer) o2.getTotal_review();
						return io1.compareTo(io2);
					}

				}));
			}
		}
		
		System.out.println("SIZE LIST:"+list.size());
		return list;
	}
	public List<FoodBean> getListFoodByOffset(String provinceID,String districtID,
			String streetID, String resType,
			String sortType,int page,int num_record) {

		List<FoodBean> list = new ArrayList<>();

		try {
			if(page==0){
				page=1;
			}
			page=(page-1)*num_record;
			ResultSet rs = foodDao.getListFoodByOffset(provinceID, districtID, streetID, resType, page, num_record);
			System.out.println(districtID+"   "+streetID+"    "+resType);
			if (rs != null) {
				while (rs.next()) {
					String id = rs.getString(1);
					String res_id = rs.getString(2);
					String title=rs.getString(3);
					String photo=imagePath+rs.getString(4);
					String name_res=rs.getString(10);
					String address_res=rs.getString(9);
					int total_review=rs.getInt(11);
					
					FoodBean food=new FoodBean(id, title, res_id, name_res, address_res, photo);
					food.setTotal_review(total_review);
					
					List<CommentFoodBean> comments=commentModel.getCommentFood(id, DEFAULT_NUM_COMMENT);
					
					if(comments==null ||comments.size()<=0){
						comments=new ArrayList<>();
						UserBean user=userModel.getUserByID("admin@gmail.com");
						CommentFoodBean comment=new CommentFoodBean(res_id, user, id, "Thêm bởi admin");
						comments.add(comment);
					}
					food.setListComment(comments);

					list.add(food);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sortType!=null && !sortType.equals("") && sortType.length()>0){
			if (sortType.equals(SORT_MOSTVIEW) || sortType.equals(SORT_POPULAR)) {
				Collections.sort(list, Collections.reverseOrder(new Comparator<FoodBean>() {

					@Override
					public int compare(FoodBean o1, FoodBean o2) {
						Integer io1 = (Integer) o1.getTotal_review();
						Integer io2 = (Integer) o2.getTotal_review();
						return io1.compareTo(io2);
					}

				}));
			}
		}
		
		System.out.println("SIZE LIST:"+list.size());
		return list;
	}
	
	
}

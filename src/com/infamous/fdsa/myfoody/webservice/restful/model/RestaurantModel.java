package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.CommentResBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.MoreImageRestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.PositionBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.RestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.RestaurantDAO;
import com.infamous.fdsa.myfoody.webservice.restful.util.LocatorUtil;

public class RestaurantModel {

	private RestaurantDAO restaurantDAO;
	private CommentModel commentModel;
	private MoreImageRestaurantModel moreImageRestaurantModel;
	private MenuBarModel menuBarModel;

	 static final String SORT_MOSTVIEW = "xemnhieu";
	 static final String SORT_POPULAR = "phobien";
	 static final String SORT_NEARBY ="ganday";

	public static final int DEFAULT_NUM_COMMENT = 2;
	public static final int DEFAULT_NUM_PHOTO = 3;

	String imagePath = AppConfig.IMAGE_PATH_MAIN_PHOTO_RES_LOCAL;

	public RestaurantModel() {
		restaurantDAO = RestaurantDAO.getInstance();
		commentModel = new CommentModel();
		moreImageRestaurantModel = new MoreImageRestaurantModel();
		menuBarModel=new MenuBarModel();
	}

	public List<RestaurantBean> getListRestaurant(String provinceID, String districtID, String streetID,
			String whereType, String sortType) {

		List<RestaurantBean> list = new ArrayList<>();

		try {

			ResultSet rs = restaurantDAO.getListRestaurant(provinceID, districtID, streetID, whereType);
			if (rs != null) {
				while (rs.next()) {
					String id = rs.getString(1);
					String title = rs.getString(2);
					String address = rs.getString(3);
					double avg_rating = rs.getDouble(4);
					String phone = rs.getString(5);
					int total_review = rs.getInt(6);
					String photo = imagePath + rs.getString(12) != null ? imagePath + rs.getString(12) : "";

					String opentime=rs.getString(13);
					if(opentime==null||opentime.equals("")||opentime.equals("null")){
						opentime="09:00";
					}else{
						opentime=opentime.trim();
					}
					String closetime=rs.getString(14);
					if(closetime==null||closetime.equals("")||closetime.equals("null")){
						closetime="21:00";
					}else{
						closetime=closetime.trim();
					}
					PositionBean position=new PositionBean(rs.getDouble(15), rs.getDouble(16));
					double mincash=rs.getDouble(17);
					if(mincash==0){
						mincash=50000;
					}
					double maxcash=rs.getDouble(18);
					if(maxcash==0){
						maxcash=500000;
					}
					
					RestaurantBean restaurant = new RestaurantBean(id, title, address, avg_rating, phone, total_review,
							photo,position,opentime,closetime,mincash,maxcash);
					
					List<CommentResBean> comments = commentModel.getCommentRes(id, DEFAULT_NUM_COMMENT);
					List<MoreImageRestaurantBean> images = moreImageRestaurantModel.getMoreImageRestaurantDAO(id,
							DEFAULT_NUM_PHOTO);

					restaurant.setListComment(comments);
					restaurant.setListImage(images);

					list.add(restaurant);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sortType != null && !sortType.equals("") && sortType.length() > 0) {
			if (sortType.equals(SORT_MOSTVIEW) || sortType.equals(SORT_POPULAR)) {
				Collections.sort(list, Collections.reverseOrder(new Comparator<RestaurantBean>() {

					@Override
					public int compare(RestaurantBean o1, RestaurantBean o2) {

						Integer io1 = (Integer) o1.getTotal_review();
						Integer io2 = (Integer) o2.getTotal_review();
						return io1.compareTo(io2);
					}

				}));
			}else if(sortType.equals(SORT_NEARBY)){
				List<RestaurantBean> newList=new ArrayList<>();
				for (RestaurantBean restaurantBean : list) {
					double distance=LocatorUtil.calculateDistance(restaurantBean.getPosition(),AppConfig.getMYLOCATION());
					if(distance>=0 && distance<20){
						newList.add(restaurantBean);
					}
				}
				list=newList;
			}
		}

		return list;
	}
	public int getCountRestaurant(String provinceID, String districtID, String streetID,
			String whereType, String sortType){
		return this.getListRestaurant(provinceID, districtID, streetID, whereType, sortType).size();
	}
	public List<RestaurantBean> getListRestaurantByOffset(String provinceID, String districtID, String streetID,
			String whereType, String sortType,int page,int num_record) {

		List<RestaurantBean> list = new ArrayList<>();

		try {
			if(page==0){
				page=1;
			}
			ResultSet rs=null;
			page=(page-1)*num_record;
			if(sortType.equals(SORT_NEARBY)){
				rs= restaurantDAO.getListRestaurant(provinceID, districtID, streetID, whereType);
			}else{
				rs = restaurantDAO.getListRestaurantByOffset(provinceID, districtID, streetID, whereType, page, num_record);
			}
			if (rs != null) {
				while (rs.next()) {
					String id = rs.getString(1);
					String title = rs.getString(2);
					String address = rs.getString(3);
					double avg_rating = rs.getDouble(4);
					String phone = rs.getString(5);
					int total_review = rs.getInt(6);
					String photo = imagePath + rs.getString(12) != null ? imagePath + rs.getString(12) : "";

					String opentime=rs.getString(13);
					if(opentime==null||opentime.equals("")||opentime.equals("null")){
						opentime="09:00";
					}else{
						opentime=opentime.trim();
					}
					String closetime=rs.getString(14);
					if(closetime==null||closetime.equals("")||closetime.equals("null")){
						closetime="21:00";
					}else{
						closetime=closetime.trim();
					}
					PositionBean position=new PositionBean(rs.getDouble(15), rs.getDouble(16));
					double mincash=rs.getDouble(17);
					if(mincash==0){
						mincash=50000;
					}
					double maxcash=rs.getDouble(18);
					if(maxcash==0){
						maxcash=500000;
					}
					
					RestaurantBean restaurant = new RestaurantBean(id, title, address, avg_rating, phone, total_review,
							photo,position,opentime,closetime,mincash,maxcash);
					
					List<CommentResBean> comments = commentModel.getCommentRes(id, DEFAULT_NUM_COMMENT);
					List<MoreImageRestaurantBean> images = moreImageRestaurantModel.getMoreImageRestaurantDAO(id,
							DEFAULT_NUM_PHOTO);

					restaurant.setListComment(comments);
					restaurant.setListImage(images);

					list.add(restaurant);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sortType != null && !sortType.equals("") && sortType.length() > 0) {
			if (sortType.equals(SORT_MOSTVIEW) || sortType.equals(SORT_POPULAR)) {
				Collections.sort(list, Collections.reverseOrder(new Comparator<RestaurantBean>() {

					@Override
					public int compare(RestaurantBean o1, RestaurantBean o2) {

						Integer io1 = (Integer) o1.getTotal_review();
						Integer io2 = (Integer) o2.getTotal_review();
						return io1.compareTo(io2);
					}

				}));
			}else if(sortType.equals(SORT_NEARBY)){
				List<RestaurantBean> newList=new ArrayList<>();
				for (RestaurantBean restaurantBean : list) {
					double distance=LocatorUtil.calculateDistance(restaurantBean.getPosition(),AppConfig.getMYLOCATION());
					if(distance>=0 && distance<200){
						newList.add(restaurantBean);
					}
				}
				list=newList;
			}
		}
		
		System.out.println(list.size()+"fsdafsdafsd"+AppConfig.getMYLOCATION().toString());

		return list;
	}

	public RestaurantBean getRestaurantById(String resid) {
		RestaurantBean restaurant = null;
		try {
			ResultSet rs = restaurantDAO.getRestaurantById(resid);
			if (rs != null) {
				if (rs.next()) {
					String id = rs.getString(1);
					String title = rs.getString(2);
					String address = rs.getString(3);
					double avg_rating = rs.getDouble(4);
					String phone = rs.getString(5);
					int total_review = rs.getInt(6);

					String id_province = rs.getString(7);
					String id_district = rs.getString(8);
					String id_street = rs.getString(9) != null ? rs.getString(9) : "";
					String where_type = rs.getString(10);
					String res_type = rs.getString(11);

					String photo = imagePath + rs.getString(12) != null ? imagePath + rs.getString(12) : "";
					
					String opentime=rs.getString(13);
					if(opentime==null||opentime.equals("")||opentime.equals("null")){
						opentime="09:00";
					}else{
						opentime=opentime.trim();
					}
					String closetime=rs.getString(14);
					if(closetime==null||closetime.equals("")||closetime.equals("null")){
						closetime="21:00";
					}else{
						closetime=closetime.trim();
					}
					PositionBean position=new PositionBean(rs.getDouble(15), rs.getDouble(16));

					double mincash=rs.getDouble(17);
					if(mincash==0){
						mincash=50000;
					}
					double maxcash=rs.getDouble(18);
					if(maxcash==0){
						maxcash=500000;
					}
					
					RestaurantBean ress = new RestaurantBean(id,title,address,avg_rating,
							phone,total_review,id_province,id_district,id_street,
							where_type,res_type,photo,position,opentime,closetime,mincash,maxcash);
							
					List<CommentResBean> comments = commentModel.getCommentRes(id, DEFAULT_NUM_COMMENT);
					List<MoreImageRestaurantBean> images = moreImageRestaurantModel.getMoreImageRestaurantDAO(id,
							DEFAULT_NUM_PHOTO);

					ress.setListComment(comments);
					ress.setListImage(images);
					
					restaurant=ress;
				}
			}
		} catch (SQLException e) {

		}
		return restaurant;
	}
	public int insertRestaurant(String res_id, String rest_name, String address,String phone, String id_province, String id_district,
			 String where_type, String photo, String open_time, String close_time, double lat,
			double lng, double min_cash, double max_cash) throws SQLException{
		String res_type=menuBarModel.getResTypeByWhereType(where_type);
		return restaurantDAO.insertRestaurant(res_id, rest_name, address, phone,
				id_province, id_district, where_type, res_type, photo, open_time, close_time, lat, lng, min_cash, max_cash);
	}
	public String getNewRestaurantID(){
		String result="";
		try{
			ResultSet rs=restaurantDAO.getNewRestaurantID();
			if(rs!=null){
				if(rs.next()){
					result=rs.getString(1);
				}
			}
			result=(Integer.parseInt(result)+1)+"";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

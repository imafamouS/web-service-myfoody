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
import com.infamous.fdsa.myfoody.webservice.restful.bean.RestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.RestaurantDAO;

public class RestaurantModel {

	private RestaurantDAO restaurantDAO;
	private CommentModel commentModel;
	private MoreImageRestaurantModel moreImageRestaurantModel;

	public static final String SORT_MOSTVIEW = "xemnhieu";
	public static final String SORT_POPULAR = "phobien";

	public static final int DEFAULT_NUM_COMMENT = 2;
	public static final int DEFAULT_NUM_PHOTO = 3;

	String imagePath = AppConfig.IMAGE_PATH_MAIN_PHOTO_RES_LOCAL;

	public RestaurantModel() {
		restaurantDAO = new RestaurantDAO();
		commentModel = new CommentModel();
		moreImageRestaurantModel = new MoreImageRestaurantModel();
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
					/*
					 * String id_province = rs.getString(7); String id_district
					 * = rs.getString(8); String id_street =
					 * rs.getString(9)!=null?rs.getString(9):""; String
					 * where_type = rs.getString(10); String res_type =
					 * rs.getString(11);
					 */
					String photo = imagePath + rs.getString(12) != null ? imagePath + rs.getString(12) : "";

					RestaurantBean restaurant = new RestaurantBean(id, title, address, avg_rating, phone, total_review,
							photo);

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
				Collections.sort(list,Collections.reverseOrder(new Comparator<RestaurantBean>() {

					@Override
					public int compare(RestaurantBean o1, RestaurantBean o2) {
						
						Integer io1 = (Integer) o1.getTotal_review();
						Integer io2 = (Integer) o2.getTotal_review();
						return io1.compareTo(io2);
					}

				}));
			}
		}

		
		return list;
	}
}

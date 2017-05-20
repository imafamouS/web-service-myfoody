package com.infamous.fdsa.myfoody.webservice.restful;

import com.infamous.fdsa.myfoody.webservice.restful.bean.PositionBean;

public class AppConfig {
	/**
	 * Giá trị của requestcode khi muốn lấy danh mục loại của Fragment Ăn gì
	 */
	public static final String REQUEST_CODE_CATEGORY_WHAT2DO = "category_what2do";

	/**
	 * Giá trị của requestcode khi muốn lấy danh mục loại của Fragment ở đâu
	 */
	public static final String REQUEST_CODE_CATEGORY_WHERE2GO = "category_where2go";

	public static final String IMAGE_PATH_CATEGORY = "http://res.cloudinary.com/hcmute/image/upload/category/";

	public static final String IMAGE_PATH_USER_AVATAR = "http://res.cloudinary.com/hcmute/image/upload/avatar/";

	public static final String IMAGE_PATH_MAIN_PHOTO_RES = "http://res.cloudinary.com/hcmute/image/upload/restaurant/";

	/** FOLDER IMAGE LOCAL **/
	public static final String IMAGE_PATH_CATEGORY_LOCAL = "category/";

	public static final String IMAGE_PATH_USER_AVATAR_LOCAL = "avatar/";
	public static final String IMAGE_PATH_USER_COVER_LOCAL = "cover/";

	public static final String IMAGE_PATH_MAIN_PHOTO_RES_LOCAL = "restaurant/";

	public static final String IMAGE_PATH_FOOD_LOCAL = "food/";

	public static final String LOCATION_DATA_LOCAL = "/Users/apple/res/"; // Change

	public static final int LIMIT_RECORD = 5;
	
	private static PositionBean MYLOCATION=null;

	public static PositionBean getMYLOCATION() {
		if(MYLOCATION==null){
			return new PositionBean(10.851035, 106.772001);
		}
		return MYLOCATION;
	}

	public static void setMYLOCATION(PositionBean mYLOCATION) {
		MYLOCATION = mYLOCATION;
	}
	
}

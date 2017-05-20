package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.MoreImageRestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.PositionBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.RestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.MoreImageRestaurantModel;
import com.infamous.fdsa.myfoody.webservice.restful.model.RestaurantModel;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;
import com.infamous.fdsa.myfoody.webservice.restful.util.UploadImageController;

@Path("/api")
public class RestaurantController {

	@GET
	@Path("/restaurant/get")
	@Produces("application/json")
	public String getmenubar(@QueryParam("provinceid") String provinceid, @QueryParam("districtid") String districtid,
			@QueryParam("streetid") String streetid, @DefaultValue("l0") @QueryParam("wheretype") String wheretype,
			@DefaultValue("xemnhieu") @QueryParam("sort") String sorttype, @QueryParam("page") String page,
			@DefaultValue("-1") @QueryParam("lat") String lat,
			@DefaultValue("-1") @QueryParam("long") String lng) {

		JsonObject object = new JsonObject();
		try {
			RestaurantModel model = new RestaurantModel();
			List<RestaurantBean> list=new ArrayList<>();
			if(sorttype.equals("ganday") && !lat.equals("-1") && !lng.equals("-1")){
				AppConfig.setMYLOCATION(new PositionBean(Double.parseDouble(lat), Double.parseDouble(lng)));
			}
			if(page==null ||page.length()<=0){
				list = model.getListRestaurant(provinceid, districtid, streetid, wheretype, sorttype);
			}else{
				list =model.getListRestaurantByOffset(provinceid, districtid, streetid, wheretype, 
						sorttype,Integer.parseInt(page),AppConfig.LIMIT_RECORD);
			}
			
			Gson gson = new Gson();

			JsonElement data;

			if (list != null && list.size() > 0) {
				data = gson.toJsonTree(list);
				object.addProperty("success", true);
				object.add("data", data);
			} else {
				object.addProperty("success", false);
				object.addProperty("data", "Not found");
			}

		}

		catch (Exception e) {
			e.getMessage();
			System.out.println("Exception Error4444"); // Console
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		return object.toString();
	}
	@GET
	@Path("/restaurant/count")
	@Produces("application/json")
	public String getCountRestaurant(@QueryParam("provinceid") String provinceid, @QueryParam("districtid") String districtid,
			@QueryParam("streetid") String streetid, @DefaultValue("l0") @QueryParam("wheretype") String wheretype,
			@DefaultValue("xemnhieu") @QueryParam("sort") String sorttype,
			@DefaultValue("-1") @QueryParam("lat") String lat,
			@DefaultValue("-1") @QueryParam("long") String lng) {

		JsonObject object = new JsonObject();
		try {
			if(sorttype.equals("ganday") && !lat.equals("-1") && !lng.equals("-1")){
				AppConfig.setMYLOCATION(new PositionBean(Double.parseDouble(lat), Double.parseDouble(lng)));
			}
			RestaurantModel model = new RestaurantModel();
			int list=model.getCountRestaurant(provinceid, districtid, streetid, wheretype, sorttype);
			
			
			object.addProperty("success", true);
			object.addProperty("data", list);
			

		}

		catch (Exception e) {
			e.getMessage();
			System.out.println("Exception Error4444"); // Console
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		return object.toString();
	}

	@GET
	@Path("/restaurant/getbyid/{id}")
	@Produces("application/json")
	public String getmenubar(@PathParam("id") String id) {

		JsonObject object = new JsonObject();
		try {
			RestaurantModel model = new RestaurantModel();

			RestaurantBean list = model.getRestaurantById(id);

			Gson gson = new Gson();

			JsonElement data;

			if (list != null) {
				data = gson.toJsonTree(list);
				object.addProperty("success", true);
				object.add("data", data);
			} else {
				object.addProperty("success", false);
				object.addProperty("data", "Not found");
			}

		}

		catch (Exception e) {
			e.getStackTrace();
			System.out.println("Exception Error12312"); // Console
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		return object.toString();
	}

	@POST
	@Path("/restaurant/post")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello(InputStream incomingData) throws Exception {

		UploadImageController uploadImageController = UploadImageController.getInstanse();
		RestaurantModel restaurantModel = new RestaurantModel();
		MoreImageRestaurantModel moreImageModel = new MoreImageRestaurantModel();

		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);

		JsonObject output = new JsonObject();
		Gson gson = new Gson();
		RestaurantBean restaurant = gson.fromJson(jsonObject, RestaurantBean.class);
		
		boolean flagUploadRes = true;
		boolean flagUploadMoreItem = true;
		
		try {
			String mainPhotoID ="";
			String resID = restaurantModel.getNewRestaurantID();
			if(restaurant.getPhoto()==null ||restaurant.getPhoto().length()==0){
				restaurant.setPhoto("");
			}else{
				mainPhotoID=UUID.randomUUID().toString();
			}
			
			if ((restaurantModel.insertRestaurant(resID, restaurant.getTitle(), restaurant.getAddress(),restaurant.getPhone(),
					restaurant.getId_province(), restaurant.getId_district(), restaurant.getWhere_type(), mainPhotoID,
					restaurant.getOpenTime(), restaurant.getCloseTime(), restaurant.getPosition().get_lat(),
					restaurant.getPosition().get_long(), restaurant.getMinCash(), restaurant.getMaxCash()) != 0)) {
				if (restaurant.getPhoto().trim().length() > 0
						&& uploadImageController.uploadImage_Local(mainPhotoID, restaurant.getPhoto(), "restaurant")) {
					
				} else {
					if (restaurant.getPhoto().trim().length() > 0) {
						flagUploadRes = false;
						uploadImageController.deleteExistImage("restaurant", mainPhotoID);
					}
				}
			} else {
				flagUploadRes = false;
			}

			if (flagUploadRes) {
				
				List<MoreImageRestaurantBean> moreImage = restaurant.getListImage();
				if (moreImage!=null&&moreImage.size() > 0) {
					for (int i = 0; i < moreImage.size(); i++) {
						String moreImageID = moreImageModel.getNewID();
						MoreImageRestaurantBean item = moreImage.get(i);
						String photoID = UUID.randomUUID().toString();
						if (uploadImageController.uploadImage_Local(photoID, item.getPhoto(), "restaurant")) {
							if ((moreImageModel.insertMoreImage(moreImageID, resID, photoID) != 0)) {
								
							} else {
								uploadImageController.deleteExistImage("restaurant", photoID);
								flagUploadMoreItem = false;
								break;
							}
						} else {
							flagUploadMoreItem = false;
							break;
						}
					}
				} else {
					flagUploadMoreItem = false;
				}

			}

		} catch (Exception e) {
			flagUploadRes = false;
			flagUploadMoreItem = false;
			System.out.println(e.getMessage());
		}
		System.out.println("TEST"+flagUploadRes+ "  "+flagUploadMoreItem);
		if (flagUploadRes == true && flagUploadMoreItem == true) {
			output.addProperty("success", true);
			output.addProperty("message", "OK");
		} else if (flagUploadRes == true && flagUploadMoreItem == false) {
			output.addProperty("success", true);
			output.addProperty("message", "Cannot upload MoreImage");
		} else if (flagUploadRes == false && flagUploadMoreItem == false) {
			output.addProperty("success", false);
			output.addProperty("message", "Cannot upload");
		}

		System.out.println(output.toString());
		return output.toString();

	}
}

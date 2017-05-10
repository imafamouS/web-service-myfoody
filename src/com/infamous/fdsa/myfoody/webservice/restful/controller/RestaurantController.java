package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.io.InputStream;
import java.util.List;

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
import com.infamous.fdsa.myfoody.webservice.restful.bean.RestaurantBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.RestaurantModel;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;
import com.infamous.fdsa.myfoody.webservice.restful.util.UploadImageController;

@Path("/api")
public class RestaurantController {

	@GET
	@Path("/restaurant/get")
	@Produces("application/json")
	public String getmenubar(
			@QueryParam("provinceid") String provinceid,
			@QueryParam("districtid") String districtid, 
			@QueryParam("streetid") String streetid,
			@DefaultValue("l0") @QueryParam("wheretype") String wheretype, 
			@DefaultValue("xemnhieu") @QueryParam("sort") String sorttype) {

		JsonObject object = new JsonObject();
		try {
			RestaurantModel model = new RestaurantModel();
			System.out.println("Controller:"+districtid+ " "+streetid+" "+wheretype+"  "+sorttype);
			List<RestaurantBean> list = model.getListRestaurant(provinceid,districtid, streetid, wheretype, sorttype);

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
	@Path("/restaurant/getbyid/{id}")
	@Produces("application/json")
	public String getmenubar(
			@PathParam("id") String id) {

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
		
		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);

		String id = jsonObject.get("id").toString().replaceAll("\"", "");
		String imgBase64 = jsonObject.get("image").toString();
		String folder="restaurant";
		//Save to database
		//RestaurantController
		System.out.println("ID: "+id);
		UploadImageController uploadImageController=UploadImageController.getInstanse();
		
		JsonObject output = new JsonObject();

		if (uploadImageController.uploadImage_Local(id, imgBase64,folder)) {
			output.addProperty("success", true);
			output.addProperty("message", "OK");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Cannot upload");
		}
		
		return output.toString();
	}
}

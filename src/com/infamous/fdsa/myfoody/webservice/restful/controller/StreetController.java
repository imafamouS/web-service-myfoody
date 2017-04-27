package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.StreetBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.StreetModel;

@Path("/api")
public class StreetController {
	
	@GET
	@Path("/street/getbydistrict/{district}")
	@Produces("application/json")
	public String getStreet(
		@PathParam("district") String districtID) {

		JsonObject object = new JsonObject();
		try {
			StreetModel model = new StreetModel();

			List<StreetBean> list = model.getListStreet(districtID);
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
			System.out.println("Exception Error"); // Console
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		return object.toString();
	}
	@GET
	@Path("/street/getbyprovince/{province}")
	@Produces("application/json")
	public String getStreett(
		@PathParam("province") String provinceID) {

		JsonObject object = new JsonObject();
		try {
			StreetModel model = new StreetModel();

			List<StreetBean> list = model.getListStreetByProvinceID(provinceID);
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
			System.out.println("Exception Error"); // Console
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		return object.toString();
	}
}

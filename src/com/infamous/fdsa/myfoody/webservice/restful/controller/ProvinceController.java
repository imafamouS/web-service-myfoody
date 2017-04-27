package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.ProvinceBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.ProvinceModel;

@Path("/api")
public class ProvinceController {
	
	@GET
	@Path("/province/get/{country}")
	@Produces("application/json")
	public String getmenubar(
		@PathParam("country") String countryID,
		@DefaultValue("false")	@QueryParam("getdistrict") boolean isGetDistrict) {

		JsonObject object = new JsonObject();
		try {
			ProvinceModel model = new ProvinceModel();

			List<ProvinceBean> list = model.getListProvince(countryID, isGetDistrict);
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

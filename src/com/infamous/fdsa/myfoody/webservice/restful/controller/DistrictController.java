package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.DistrictBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.DistrictModel;

@Path("/api")
public class DistrictController {
	@GET
	@Path("/district/get/{provinceid}")
	@Produces("application/json")
	public String getListDistrictByProvinceID(@PathParam("provinceid") String provinceID) {

		JsonObject object = new JsonObject();
		try {
			DistrictModel model = new DistrictModel();

			List<DistrictBean> list = model.getListDistrictByProvinceID(provinceID);

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

package com.infamous.fdsa.myfoody.webservice.restful.controller;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.MenuBarBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.MenuBarModel;

@Path("/api")
public class MenuBarController {

	@GET
	@Path("/menubar/get/{code}")
	@Produces("application/json")
	public String getmenubar(@PathParam("code") String code) {

		JsonObject object = new JsonObject();
		try {
			MenuBarModel model = new MenuBarModel();

			List<MenuBarBean> list = model.getListCategory(code);

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

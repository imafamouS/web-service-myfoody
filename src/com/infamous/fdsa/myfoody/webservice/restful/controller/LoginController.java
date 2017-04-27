package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.LoginCredential;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.UserModel;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;

@Path("/login")
public class LoginController {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(InputStream incomingData){
		JsonObject jsonObject=MyFunction.parseStream2Json(incomingData);
		
		String userID=jsonObject.get("userid").toString().replaceAll("\"", "");
		String password=jsonObject.get("password").toString().replaceAll("\"", "");
		
		System.out.println(userID+ "   "+password);
		
		LoginCredential loginCredential=new LoginCredential(userID, password);
		UserModel userModel=new UserModel();
		UserBean user=userModel.checkLogin(loginCredential);
		Gson gson = new Gson();

		JsonElement data;
		
		JsonObject output=new JsonObject();
		
		if(user!=null){
			data=gson.toJsonTree(user);
			output.addProperty("success", true);
			output.addProperty("message", "Login successful");
			output.add("data",data);
		}else{
			output.addProperty("success", false);
			output.addProperty("message", "Login fails");
			output.addProperty("data", "Not found");
		}
		return output.toString();
	}
}

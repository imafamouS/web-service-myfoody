package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.io.InputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.bean.UserBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.UserModel;
import com.infamous.fdsa.myfoody.webservice.restful.util.MyFunction;
import com.infamous.fdsa.myfoody.webservice.restful.util.UploadImageController;

@Path("/user")
public class RegisterController {

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(InputStream incomingData) throws SQLException {
		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);

		String userID = jsonObject.get("userid").toString().replaceAll("\"", "");
		String password = jsonObject.get("password").toString().replaceAll("\"", "");
		String name = jsonObject.get("name").toString().replaceAll("\"", "");

		System.out.println(userID + "   " + password);

		UserModel userModel = new UserModel();
		UserBean user = new UserBean(userID, name);

		JsonObject output = new JsonObject();

		if (userModel.register(user, password) != 0) {
			output.addProperty("success", true);
			output.addProperty("message", "Register Success");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Register fails");
		}
		return output.toString();
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(InputStream incomingData) throws SQLException {
		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);

		String userID = jsonObject.get("userid").toString().replaceAll("\"", "");
		String firstname = jsonObject.get("firstname").toString().replaceAll("\"", "");
		String lastname = jsonObject.get("lastname").toString().replaceAll("\"", "");
		String marry = jsonObject.get("marry").toString().replaceAll("\"", "");
		String gender = jsonObject.get("gender").toString().replaceAll("\"", "");
		String age = jsonObject.get("age").toString().replaceAll("\"", "");

		UserModel userModel = new UserModel();
		UserBean user = new UserBean();
		user.setUserid(userID);
		user.setFirstname(firstname);
		user.setName(lastname);
		user.setMarry(marry);
		user.setSex(gender);
		user.setAge(age);

		JsonObject output = new JsonObject();

		if (userModel.update(user) != 0) {
			output.addProperty("success", true);
			output.addProperty("message", "Register Success");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Register fails");
		}
		return output.toString();
	}

	@POST
	@Path("/changepass")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePassword(InputStream incomingData) throws SQLException {
		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);

		String userID = jsonObject.get("userid").toString().replaceAll("\"", "");
		String currentpass = jsonObject.get("currentpass").toString().replaceAll("\"", "");
		String newpass = jsonObject.get("newpass").toString().replaceAll("\"", "");

		UserModel userModel = new UserModel();

		JsonObject output = new JsonObject();

		if (userModel.changePass(userID, currentpass, newpass) != 0) {
			output.addProperty("success", true);
			output.addProperty("message", "Register Success");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Register fails");
		}
		return output.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getinfo(@QueryParam("userid") String userid, @QueryParam("token") String token) throws SQLException {

		UserModel userModel = new UserModel();

		UserBean user = userModel.getUserByID(userid, token);
		Gson gson = new Gson();

		JsonElement data;

		JsonObject output = new JsonObject();

		if (user != null) {
			data = gson.toJsonTree(user);
			output.addProperty("success", true);
			output.addProperty("message", "Login successful");
			output.add("data", data);
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Login fails");
			output.addProperty("data", "Not found");
		}
		return output.toString();
	}

	@POST
	@Path("/changeavatar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello(InputStream incomingData,@QueryParam("token") String token) throws Exception {

		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);
		UploadImageController uploadImageController = UploadImageController.getInstanse();

		String userid = jsonObject.get("userid").toString().replaceAll("\"", "");
		String id = jsonObject.get("id").toString().replaceAll("\"", "");
		String imgBase64 = jsonObject.get("image").toString();
		
		String folder="avatar";
		
		// Save to database
		UserModel userModel = new UserModel();
		String nameAvatar=userModel.getimage(userid, "avatar", token);
		uploadImageController.deleteExistImage("avatar",nameAvatar);
	
		JsonObject output = new JsonObject();

		if (uploadImageController.uploadImage_Local(id, imgBase64, folder)
				&& userModel.changeavatar(userid, id,token) != 0) {
			output.addProperty("success", true);
			output.addProperty("message", "OK");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Cannot upload");
		}

		return output.toString();
	}
	
	@POST
	@Path("/changecover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String changecover(InputStream incomingData,@QueryParam("token") String token) throws Exception {

		JsonObject jsonObject = MyFunction.parseStream2Json(incomingData);
		UploadImageController uploadImageController = UploadImageController.getInstanse();

		String userid = jsonObject.get("userid").toString().replaceAll("\"", "");
		String id = jsonObject.get("id").toString().replaceAll("\"", "");
		String imgBase64 = jsonObject.get("image").toString();
		
		String folder="cover";
		
		// Save to database
		UserModel userModel = new UserModel();
		String namecover=userModel.getimage(userid, "cover", token);
		uploadImageController.deleteExistImage("cover",namecover);


		JsonObject output = new JsonObject();

		if (uploadImageController.uploadImage_Local(id, imgBase64, folder)
				&& userModel.changecover(userid, id,token) != 0) {
			output.addProperty("success", true);
			output.addProperty("message", "OK");
		} else {
			output.addProperty("success", false);
			output.addProperty("message", "Cannot upload");
		}

		return output.toString();
	}
	

}

package com.infamous.fdsa.myfoody.webservice.restful;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.google.gson.JsonObject;

public class main {

	public static void main(String[] args) throws IOException {
	
		/*Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
		  "cloud_name", "hcmute", "api_key", "614476562467614", "api_secret",
		  "56PomzVs_YwW7fWaH9pVDV_PBcE"));
		  
		 File toUpload = new File("D:/img/1.png"); 
				 
		 System.out.println(cloudinary.url().imageTag("1jKm5zS_sefw0z.jpg"));*/
			
		
			
		/**
		 * UP ảnh
		 * **/
		
		/*InputStream inputStream = new FileInputStream(new File("D:/img/1.jpg"));
		byte[] byteArray = IOUtils.toByteArray(inputStream);

		String str =Base64.getEncoder().encodeToString(byteArray);
		JsonObject object = new JsonObject();*/
		
		while(true){
			String id= UUID.randomUUID().toString();
			System.out.println(id);
		}
		//object.addProperty("id", id);
		//object.addProperty("image", str);
		
		
		//FileUtils.writeByteArrayToFile(new File("D:/myjson_1.txt"), 
		//		object.toString().getBytes("UTF-8"));
		
		/*URL url = new URL("http://localhost:9080/MyFoodyWebService/rest/api/menubar/post");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(object.toString());
		out.close();

		// read the response
		InputStream in = new BufferedInputStream(connection.getInputStream());
		String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
		JSONObject jsonObject = new JSONObject(result);

		in.close();
		System.out.println(jsonObject);*/
		
		/***
		 * LOGIN
		 *//*
		JsonObject object = new JsonObject();
		
		object.addProperty("userid", "admin");
		object.addProperty("password", "123");
		
		
		FileUtils.writeByteArrayToFile(new File("D:/login.txt"), 
				object.toString().getBytes("UTF-8"));
		
		System.out.println(MyFunction.encryptMD5("123"));

		URL url = new URL("http://localhost:9080/MyFoodyWebService/rest/login");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(object.toString());
		out.close();

		// read the response
		InputStream in = new BufferedInputStream(connection.getInputStream());
		String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
		JSONObject jsonObject = new JSONObject(result);

		in.close();
		System.out.println(jsonObject);*/
		
	/*	URL url = new URL("http://localhost:9080/MyFoodyWebService/rest/api/menubar/get/category_what2do");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		InputStream in = new BufferedInputStream(connection.getInputStream());
		String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
		JSONObject jsonObject = new JSONObject(result);
		System.out.println(jsonObject.toString());*/

	}

}

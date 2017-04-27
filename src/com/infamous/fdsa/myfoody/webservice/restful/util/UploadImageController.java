package com.infamous.fdsa.myfoody.webservice.restful.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;
import com.infamous.fdsa.myfoody.webservice.restful.bean.StreetBean;
import com.infamous.fdsa.myfoody.webservice.restful.model.StreetModel;

public class UploadImageController {

	private String CLOUD_NAME;
	private String API_KEY;
	private String API_SECRET;

	private static UploadImageController instance;
	
	public Cloudinary cloudinary;
	
	private UploadImageController() {
		cloudinary=getcloudinary();
	}
	public Cloudinary getcloudinary(){
		return new Cloudinary(getInfo());
	}
	
	@SuppressWarnings("rawtypes")
	private Map getInfo() {
		Map config = null;
		try {
			Properties properties = new Properties();

			InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

			if (input == null) {
				System.out.println("Cannot find config");
				return null;
			}

			properties.load(input);

			CLOUD_NAME = properties.getProperty("cloud_name");

			API_KEY = properties.getProperty("api_key");

			API_SECRET = properties.getProperty("api_secret");
			
			config=ObjectUtils.asMap(
					"cloud_name", CLOUD_NAME, 
					"api_key", API_KEY,
					"api_secret", API_SECRET);

		} catch (IOException e) {

		}
		return config;
	}

	public static UploadImageController getInstanse() {
		if (instance == null) {
			synchronized (UploadImageController.class) {
				if (instance == null) {
					instance = new UploadImageController();
				}
			}

		}
		return instance;
	}
	public boolean uploadImage_Cloudianary(String id,String imgBase64,String folder) {
		boolean flag = false;
		try {
			
			byte[] bytearr = Base64.decodeBase64(imgBase64.getBytes());
			File file = new File("file.png");
			
			FileOutputStream stream = new FileOutputStream(file);
			try {
				stream.write(bytearr);
			
				this.cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id",folder+"/"+id));
			} finally {
				stream.close();
				file.delete();
			}
			
			flag = true;
		} catch (IOException e) {
			System.out.println("Something went wrong");
			flag = false;
		}
		return flag;
	}
	public boolean uploadImage_Local(String id,String imgBase64,String folder) {
		boolean flag = false;
		try {
			
			byte[] bytearr = Base64.decodeBase64(imgBase64.getBytes());
			File file = new File(AppConfig.LOCATION_DATA_LOCAL+"img/"+folder+"/"+id+".png");
			
			FileOutputStream stream = new FileOutputStream(file);
			try {
				stream.write(bytearr);
			
				//this.cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id",folder+"/"+id));
			} finally {
				stream.close();
				//file.delete();
			}
			
			flag = true;
		} catch (IOException e) {
			System.out.println("Something went wrong");
			flag = false;
		}
		return flag;
	}
	
	

}

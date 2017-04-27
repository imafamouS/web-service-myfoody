package com.infamous.fdsa.myfoody.webservice.restful.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.infamous.fdsa.myfoody.webservice.restful.AppConfig;

@Path("/image")
public class ImageController {
	@GET
	@Produces({ "image/png", "image/jpg" })
	public Response  getStreet(
		@QueryParam("id") String id) {
		BufferedImage image=null;
		
		String path=AppConfig.LOCATION_DATA_LOCAL+"img/"+id;
		System.out.println(path);
		try {
			File file=new File(path+".png");
			if(!file.exists()){
				file=new File(path+".jpg");
			}
			image = ImageIO.read(file);
		} catch (IOException e) {
		}
		
		return Response.ok(image).build();
	}
}

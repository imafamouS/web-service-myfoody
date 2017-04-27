package com.infamous.fdsa.myfoody.webservice.restful.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class MyFunction {
	public static JsonObject parseStream2Json(InputStream incomingData ){
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject=null;
		try {
			jsonObject = (JsonObject) jsonParser.parse(new InputStreamReader(incomingData, "UTF-8"));
		} catch (JsonIOException | JsonSyntaxException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static String encryptMD5(String plaintext) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		plaintext = "myf00dY_" + plaintext + "104395301";

		messageDigest.update(plaintext.getBytes());

		byte byteData[] = messageDigest.digest();

		StringBuffer ciphertext = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
			ciphertext.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

		return ciphertext.toString();

	}
}

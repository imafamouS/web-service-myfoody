package com.infamous.fdsa.myfoody.webservice.restful.bean;

import java.util.List;

public class ProvinceBean{
	
	private String id;
	private String title;
	private List<DistrictBean> listDistrict;
	
	private String countryID;
	
	public ProvinceBean(String id, String title, List<DistrictBean> listDistrict,String countryID) {
		this.id = id;
		this.title = title;
		this.listDistrict = listDistrict;
		this.countryID=countryID;
	}
	public ProvinceBean(String id, String title,String countryID) {
		this.id = id;
		this.title = title;
		this.countryID=countryID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public List<DistrictBean> getListDistrict() {
		return listDistrict;
	}
	public void setListDistrict(List<DistrictBean> listDistrict) {
		this.listDistrict = listDistrict;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	
	
	
}

package com.infamous.fdsa.myfoody.webservice.restful.bean;

public class MenuBarBean {
	private String title;
	private String id;
	private String image;
	private boolean isSelected;
	
	
	public MenuBarBean(String id,String title, String image, boolean isSelected) {
		this.title = title;
		this.id = id;
		this.image = image;
		this.isSelected = isSelected;
	}
	
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
}

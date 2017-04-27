package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.bean.StreetBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.StreetDAO;

public class StreetModel {
	private StreetDAO streetDAO;
	
	public StreetModel(){
		streetDAO=new StreetDAO();
	}
	
	public List<StreetBean> getListStreet(String districtID){
		List<StreetBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=streetDAO.getListStreet(districtID);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					String districtid=rs.getString(3);
					
					StreetBean street=new StreetBean(id, title,districtid,false);
					
					list.add(street);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<StreetBean> getListStreetByProvinceID(String provinceID){
		List<StreetBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=streetDAO.getListStreetByProvinceID(provinceID);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					String districtid1=rs.getString(3);
					
					StreetBean street=new StreetBean(id, title,districtid1,false);
					
					list.add(street);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}

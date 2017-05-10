package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.bean.DistrictBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.StreetBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.DistrictDAO;

public class DistrictModel {

	private DistrictDAO districtDAO;
	private StreetModel streetModel;
	public DistrictModel(){
		districtDAO=DistrictDAO.getInstance();
		streetModel=new StreetModel();
	}
	public List<DistrictBean> getListDistrictByProvinceID(String provinceID){
		List<DistrictBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=districtDAO.getListDistrictByProvinceID(provinceID);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					List<StreetBean> listStreet=streetModel.getListStreet(id);
					if(listStreet==null ||listStreet.size()<=0){
						listStreet=new ArrayList<>();
					}
					DistrictBean district=new DistrictBean(id, title,listStreet,listStreet.size(),false);
					
					list.add(district);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<DistrictBean> getListDistrictByProvinceID_NoStreet(String provinceID){
		List<DistrictBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=districtDAO.getListDistrictByProvinceID(provinceID);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					
					DistrictBean district=new DistrictBean(id, title,false);
					
					list.add(district);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}

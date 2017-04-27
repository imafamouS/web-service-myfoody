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
		districtDAO=new DistrictDAO();
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
					int numofStreet=rs.getInt(3);
					List<StreetBean> listStreet=streetModel.getListStreet(id);
					DistrictBean district=new DistrictBean(id, title,listStreet,numofStreet,false);
					
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

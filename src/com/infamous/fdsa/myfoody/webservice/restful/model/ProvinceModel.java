package com.infamous.fdsa.myfoody.webservice.restful.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infamous.fdsa.myfoody.webservice.restful.bean.DistrictBean;
import com.infamous.fdsa.myfoody.webservice.restful.bean.ProvinceBean;
import com.infamous.fdsa.myfoody.webservice.restful.dao.ProvinceDAO;

public class ProvinceModel {
	public static final boolean GET_DISTRICT=true;
	public static final boolean NO_GET_DISTRICT=false;
	
	private ProvinceDAO provinceDAO;
	
	private DistrictModel districtModel;

	public ProvinceModel(){
		provinceDAO=new ProvinceDAO();
		districtModel=new DistrictModel();
	}
	
	public List<ProvinceBean> getListProvince(String countryID,boolean isGetDistrict){
		List<ProvinceBean> list=new ArrayList<>();
		
		try {
			ResultSet rs=provinceDAO.getListProvince(countryID);
			
			if(rs!=null){
				while(rs.next()){
					String id=rs.getString(1);
					String title=rs.getString(2);
					List<DistrictBean> listDistrict=null;
					ProvinceBean province;
					if(isGetDistrict==GET_DISTRICT){
						listDistrict=new ArrayList<>();
						listDistrict=districtModel.getListDistrictByProvinceID(id);
						province=new ProvinceBean(id,title,listDistrict,countryID);
					}else{
						province=new ProvinceBean(id,title,countryID);
					}

					list.add(province);	
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}

package com.infamous.fdsa.myfoody.webservice.restful.util;

import com.infamous.fdsa.myfoody.webservice.restful.bean.PositionBean;

public class LocatorUtil {
	private static Double EARTH_RADIUS = 6371.00;

	public static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
		Double Radius = LocatorUtil.EARTH_RADIUS; // 6371.00;
		Double dLat = LocatorUtil.toRadians(lat2 - lat1);
		Double dLon = LocatorUtil.toRadians(lon2 - lon1);
		Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(LocatorUtil.toRadians(lat1))
				* Math.cos(LocatorUtil.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		Double c = 2 * Math.asin(Math.sqrt(a));
		return Radius * c;
	}
	public static Double calculateDistance(PositionBean pos1, PositionBean pos2) {
		return calculateDistance(pos1.get_lat(), pos1.get_long(),pos2.get_lat(),pos2.get_long());
	}

	public static Double toRadians(Double degree) {
		// Value degree * Pi/180
		Double res = degree * 3.1415926 / 180;
		return res;
	}

}

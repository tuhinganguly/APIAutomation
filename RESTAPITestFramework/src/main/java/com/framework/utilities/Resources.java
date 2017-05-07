package com.framework.utilities;

public class Resources {
	public static String getPlaceResource(){
		String findPlaceResource = "maps/api/place/nearbysearch/json";
		return findPlaceResource;
	}
	
	public static String addPlaceResource(){
		String addPlaceResource = "/maps/api/place/add/json";
		return addPlaceResource;
	}
	
	
	public static String deletePlaceResource(){
		String deletePlaceResource = "/maps/api/place/delete/json";
		return deletePlaceResource;
	}
}

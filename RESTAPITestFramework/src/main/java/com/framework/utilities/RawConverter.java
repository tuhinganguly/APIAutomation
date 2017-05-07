package com.framework.utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RawConverter {
	
	public static JsonPath rawDatatoJSON(Response r){
		String resp = r.asString();
		JsonPath jsonp = new JsonPath(resp);
		return jsonp;
	}
	
	public static XmlPath rawDataToXML(Response r){
		String resp = r.asString();
		XmlPath xmlp = new XmlPath(resp);
		return xmlp;
	}
}

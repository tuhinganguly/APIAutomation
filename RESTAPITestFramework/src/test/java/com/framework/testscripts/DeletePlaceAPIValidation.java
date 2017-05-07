package com.framework.testscripts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.utilities.GetPath;
import com.framework.utilities.PayLoad;
import com.framework.utilities.Resources;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeletePlaceAPIValidation {
	
	Properties prop = new Properties();
	@BeforeTest
	public void loadPropFile() throws IOException{
		FileInputStream file = new FileInputStream(GetPath.getPropFilePath());
		prop.load(file);
	}
	
	@Test
	public void addAPlace(){
		//Base URI declaration
		RestAssured.baseURI=prop.getProperty("HOST");
		String payload = PayLoad.AddPlacePayload();
		
		//define query param for POST call
		Response res =given().
			queryParam("key",prop.getProperty("KEY")).
			body(payload).
		when().
			post(Resources.addPlaceResource()).
		then().extract().response();
		//Converting the raw response to String 
		String responseString = res.asString();
				
		//convert response String to JSON object
		JsonPath js = new JsonPath(responseString);
		String placeId = js.get("place_id");
		System.out.println("Added a place with Place ID: "+ placeId);
		deleteAddedPlace(placeId);
	}
	
	public void deleteAddedPlace(String placeId){
		String payload = "{"+
							"\"place_id\": \""+
							placeId+"\""+
							"}";
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().
			queryParam("key",prop.getProperty("KEY")).
			body(payload).
		when().
			post(Resources.deletePlaceResource()).
		then().
			assertThat().statusCode(200).and().
			body("status", equalTo ("OK")).extract().response();
		
		System.out.println("Deleted the place successfully. Status: "+res.statusCode());
			
	}


}

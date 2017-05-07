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
import com.framework.utilities.RawConverter;
import com.framework.utilities.Resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddPlaceAPIValidation {
	Properties prop = new Properties();
	@BeforeTest
	public void loadPropFile() throws IOException{
		FileInputStream file = new FileInputStream(GetPath.getPropFilePath());
		prop.load(file);
	}
	
	@Test
	public void addPlace(){
		System.out.println("Validating the ADD Place API...");
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res = given().
			queryParam("key", prop.getProperty("KEY")).
			body(PayLoad.AddPlacePayload()).
			when().
			post(Resources.addPlaceResource()).
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("status", equalTo("OK")).extract().response();
		
		//Working on the Rew response
		 String responseString = res.asString();
			JsonPath js = new JsonPath(responseString);
			String placeId = js.get("place_id");
		if (res.statusCode()==200){
			System.out.println("Place successfully Added.");
			System.out.println("The Place ID is: "+placeId);
		}
			
	}

}

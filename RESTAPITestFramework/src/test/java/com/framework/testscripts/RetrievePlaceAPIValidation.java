package com.framework.testscripts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.utilities.GetPath;
import com.framework.utilities.Resources;

public class RetrievePlaceAPIValidation {
Properties prop = new Properties();	


@Test
	public void getPlaceAPI() {
	System.out.println("Validating Retrieve Place API...");
		// Base URL
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res = given().
				param("location","22.225028,84.845839").
				param("radius","500").
				param("key",prop.getProperty("KEY")).
		when().
				get(Resources.getPlaceResource()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name",equalTo("Rourkela")).and().
		body("results[0].place_id", equalTo("ChIJpcKUTJsZIDoRJJaHdy7mpLQ")).and().
		header("Server","pablo").extract().response();
		System.out.println("The API Retrieved the place successfully with status code: "+res.getStatusCode());
		
		

	}

@BeforeTest
public void loadPropFile() throws IOException{
	FileInputStream file = new FileInputStream(GetPath.getPropFilePath());
	prop.load(file);
}


}

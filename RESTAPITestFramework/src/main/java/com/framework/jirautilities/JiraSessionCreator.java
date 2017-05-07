package com.framework.jirautilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;



import com.framework.utilities.GetPath;
import com.framework.utilities.RawConverter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class JiraSessionCreator {
	Properties prop = new Properties();
	public void loadPropFile() throws IOException{
		FileInputStream filePath = new FileInputStream(GetPath.getPropFilePath());
		prop.load(filePath);
	}
	
	
	public void createJiraSession(){
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		Response res = given().header("Content-Type", "application/json").
				body(JiraAuthorization.JiraAuthParams()).
		when().
		post("/rest/auth/1/session").		
		then().assertThat().statusCode(200).extract().response();
		JsonPath js = RawConverter.rawDatatoJSON(res);
		String sessionId = js.get("session.value");
		System.out.println("Jira session created");
		
	}

}

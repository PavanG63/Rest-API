package test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NewTest {

	Logger logger;

	@BeforeMethod
	public void setUp() {

		try {

			FileInputStream fis = new FileInputStream("log4j.properties");
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();

			FileOutputStream fos = new FileOutputStream("log4j2.properties");			
			properties.setProperty("logname", "testlogin.out");
			properties.store(fos, null);
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		PropertyConfigurator.configure("log4j1.properties");
		logger = Logger.getLogger(Logs.class.getName());
		 
		logger.info("Browser started");

	}

	@Test
	public void TestLogin() {

		GetRequest lib = new GetRequest();

		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification given = RestAssured.given();

		Response response = given.request(Method.GET, "/api/users?page=1");
		System.out.println(response.getBody().asString());
		logger.info("Getting response Body");

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		logger.info("Getting data object");

//		int j = 0;
//		int k = 0;

		for (int i = 0; i < 6; i++) {
			Object object = jsonArray.get(i);
			JsonPath jsonPath = new JsonPath(object.toString());
			System.out.println(jsonPath.get("id"));

			String firstName = lib.getFirstName((Integer) jsonPath.get("id"));
			String lastname = lib.getLastName((Integer) jsonPath.get("id"));
			String emailName = lib.getemail((Integer) jsonPath.get("id"));
			

			if (firstName.equals(jsonPath.get("first_name"))) {
				System.out.println("First Name Matches");

			} else {
				System.out.println("Not MAtch");

			}

			if (lastname.equals(jsonPath.get("last_name"))) {
				System.out.println("last Name Matches");

			} else {
				System.out.println("Not MAtch");

			}

			if (emailName.equals(jsonPath.get("email"))) {
				System.out.println("Email Matches");

			} else {
				System.out.println("Not MAtch");

			}
		
		}

		logger.info("Test Login Passed");

	}

	@AfterMethod
	public void TearDown() {
		logger.info("Browser Closed");

	}

}

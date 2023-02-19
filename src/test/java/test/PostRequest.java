package test;

import org.json.JSONObject;

import com.google.gson.Gson;
//import test.User;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://reqres.in";
		
		JSONObject reqPar = new JSONObject();
		reqPar.put("name", "Pavan");
		reqPar.put("job", "Tester");
		System.out.println(reqPar.toString());

		
		RequestSpecification given = RestAssured.given();
		given.body(reqPar.toString());
		given.headers("Content-type", "application/json");
		Response response = given.request(Method.POST, "/api/users");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		
		Gson gson = new Gson();
		User user = gson.fromJson(response.getBody().asString(), User.class);
		System.out.println(user.getName());
		System.out.println(user.getJob());
		System.out.println(user.getId());
		System.out.println(user.getCreatedAt());
		

	}

}

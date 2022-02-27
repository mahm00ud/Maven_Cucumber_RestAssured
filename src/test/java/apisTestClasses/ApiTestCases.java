package apisTestClasses;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.ApiTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class ApiTestCases extends ApiTestBase {


	@Test
	public void request_ValidatePostsOfUser() {
		int userID = 1;
		System.out.println(getValueFromUsersDataUsingSpecificKey(userID,"email"));
		Assert.assertTrue(validatePostIDUsingUserID(userID));
	}
	
	@Test
	public void ValidateResponseBodyOfPostRequest() {
		Response response = given().
		baseUri(baseURI).
		and().header("ContentType", "application/json").and().
		body(readingJsonFIles("Body")).
		when().
		post("posts").then().
		log().all().
		assertThat().statusCode(201).and().		
		extract().
		response();	
		JsonPath jsonPath = response.jsonPath();
		
		
		String responseTitle = jsonPath.get("title");
		String responseBody = jsonPath.get("body");
		
		
		assertEquals(responseTitle, "A Title text is written here for the task");
		assertEquals(responseBody, "A Body text is written here for the task");
	}
}

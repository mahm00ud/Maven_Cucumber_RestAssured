package base;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterClass;

import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTestBase {
	
	//Declaration
	private static FileInputStream fileStream;
	private static String streamedBody;
	protected static String baseURI="https://jsonplaceholder.typicode.com/";
	private static int sizeOfPostIdList;
	protected static JsonPath jsonPath;
	protected static Response response;
	
	
	
	@AfterClass
	public void reporting () {
			try {
				String allureCommand = "allure serve " + System.getProperty("user.dir") + "\\allure-results";
				Process process = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", allureCommand });
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	//Reading a json file to get data from it
	public static String readingJsonFIles(String fileName) {
		try {
			fileStream = new FileInputStream("src\\test\\resources\\" + fileName+ ".txt");
			streamedBody = new String(IOUtils.toByteArray(fileStream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return streamedBody;
	}
	

	//	
	public static JsonPath getJsonResponseWithPath(String EndpointPath){
		response = given().baseUri(baseURI).
				when().
				get(EndpointPath).
			//	get("users/{userID}", "2").
				then().
				extract().
				response();	
		return jsonPath = response.jsonPath();
	}
	
	public static JsonPath getUserDataUsingUserID(int userID){
		response = given().baseUri(baseURI).
				and().
				pathParam("userID", userID).
				when().
				get("users/{userID}").
				then().
				extract().
				response();
		
		return jsonPath = response.jsonPath();
	}
	

	
	public static String getValueFromUsersDataUsingSpecificKey(int userID, String Key) {
		return getUserDataUsingUserID(userID).get(Key);
	}
	
	

	
	
	public static boolean validatePostIDUsingUserID(int userID) {
		
		boolean idChecker = false;
		ArrayList<Integer> postIDList = new ArrayList<Integer>(); 

		jsonPath = getJsonResponseWithPath("posts?userId=" + userID);
		postIDList = jsonPath.get("id");
		sizeOfPostIdList = postIDList.size();
		int postID;
		
		for (int i = 0; i < sizeOfPostIdList ; i++) {
			
			postID = postIDList.get(i);
			if (postID <= 100 && postID == (int)postID) {
				idChecker =  true;
			}else {
				idChecker = false;
			}
		}
		return idChecker;
	}
  
	  
}

//package Tests;
//
//import org.json.simple.JSONObject;
//import org.testng.annotations.Test;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;
//
//public class TestPostMethod 
//{
//	
//  @Test
//  public void postMethod()
//  {
//	  RestAssured.baseURI = "http://restapi.demoqa.com/customer";
//	  RequestSpecification request = RestAssured.given();
//	  JSONObject requestObj = new JSONObject();
//	  requestObj.put("FirstName", "A977444");
//	  requestObj.put("LastName", "S874644");
//	  requestObj.put("UserName", "D43u777");
//	  requestObj.put("Password", "FDuy6uey77678");
//	  requestObj.put("Email",  "67D4g577@gmail.com");
//	  request.header("Content-Type", "application/json");
//	  request.body(requestObj.toJSONString());
//	  Response response = request.post("/register");
//	  
//	  int statusCode = response.getStatusCode();
//	  Assert.assertEquals(201, statusCode);
//	  
//	  JsonPath jsonEvaluator = response.jsonPath();
//	  String message = jsonEvaluator.getString("Message");
//	  System.out.println("Message is: " + message);
//  }
//  
//  
//}

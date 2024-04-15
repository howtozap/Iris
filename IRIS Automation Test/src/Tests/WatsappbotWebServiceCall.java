//package Tests;
//
//import org.json.simple.JSONObject;
//import org.testng.annotations.Test;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;
//
//public class WatsappbotWebServiceCall 
//{
//	@Test
//	public void WatsappBot_WebServiceCall()
//	{
//		  String tokenURL = "http://10.100.100.253/CSCChatAssistWorkFlow/api/WorkFlow";
//		  RestAssured.baseURI = tokenURL;
//		  RequestSpecification requesttoken = RestAssured.given();
//		  JSONObject requestObj = new JSONObject();
//		  requestObj.put("integrationId", "sample string 1");
//		  requestObj.put("integrationName", "sample string 2");
//		  requestObj.put("content", "Hi");
//		  requestObj.put("messageId", "sample string 4");
//		  requestObj.put("relatedMessageId", "sample string 5");
//		  requestObj.put("from", "919545286516");
//		  requestObj.put("timestamp", "sample string 7");
//		  requestObj.put("clientMessageId", "sample string 8");
//		  requestObj.put("contentType", "sample string 9");
//		  requestObj.put("encryptionKey", "sample string 10");
//		  
//		  requesttoken.header("Content-Type", "application/json");
//		  requesttoken.body(requestObj.toJSONString());
//		  Response response = requesttoken.post("/GetResponse");
//		  
//		  int statusCode = response.getStatusCode();
//		  int expectedStatusCode = 200;
//		  Assert.assertEquals(expectedStatusCode, statusCode);
//		  
//		  response.getBody().prettyPrint();
//	}
//}

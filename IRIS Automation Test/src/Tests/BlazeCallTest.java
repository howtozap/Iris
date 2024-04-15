//package Tests;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import org.json.simple.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//
//import SourceCode.BlazeCalculations;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//public class BlazeCallTest {
//	
//	BlazeCalculations blazeCalculations = new BlazeCalculations(); 
//	
//  @Test(priority=1)
//  public void BlazeCallForCreditDecissionAndVerifyBlazeCalculations() throws Exception
//  {
//	  String tokenURL = "https://iam.dms.euwt1.ficoanalyticcloud.com/registration/rest/client";
//	  RestAssured.baseURI = tokenURL;
//	  RequestSpecification requesttoken = RestAssured.given();
//	  JSONObject requestObj = new JSONObject();
//	  requestObj.put("clientId", "t5d02swde2");
//	  requestObj.put("secret", "ShwU11nYPJ*1bNbF6eM7olAMkPT8RGbvRAAF");
//	  requesttoken.header("Content-Type", "application/json");
//	  requesttoken.body(requestObj.toJSONString());
//	  Response response = requesttoken.post("/token");
//	  
//	  int statusCode = response.getStatusCode();
//	  int expectedStatusCode = 200;
//	  Assert.assertEquals(expectedStatusCode, statusCode);
//	  
//	  String token = response.getBody().asString();
//	  //System.out.println(data);
//	  
//	  File blazeRequestXML = new File("src/TestData/BlazeRequestTestData.xml");
//	  
//	  String creditDecissionURL = "https://t6iwoxpp3f-t6iwoxpp3f.dms.euwt1.ficoanalyticcloud.com/DecisionExecutor/RmaDynamicWebService/solutionid";
//	  RestAssured.baseURI = creditDecissionURL;
//	  RequestSpecification creditDecissionRequest = RestAssured.given();
//	  creditDecissionRequest.header("Authorization", "Bearer "+token);
//	  creditDecissionRequest.header("Content-Type", "text/xml");
//	  creditDecissionRequest.body(blazeRequestXML);
//	  Response creditDecissionResponse = creditDecissionRequest.post("/t5d02swde2?wsdl");
//	  int creditDecissionStatusCode = creditDecissionResponse.getStatusCode();
//	  int expectedCreditDecissionStatusCode = 200;
//	  Assert.assertEquals(expectedCreditDecissionStatusCode, creditDecissionStatusCode);
//	  
//	  String creditDecissionBody = creditDecissionResponse.getBody().asString();
//	  
//	  BufferedWriter writer = new BufferedWriter(new FileWriter("src/TestData/BlazeResponseFile.xml"));
//	  writer.flush();
//	  writer.write(creditDecissionBody);
//	  writer.close();
//	  
//	  
//	  try 
//	  {
//		  blazeCalculations.CalculateInstallment("src/TestData/BlazeResponseFile.xml");
//	  } 
//	  catch (Exception e) 
//	  {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//  }
//  
//  /*@Test(priority=2)
//  public void BlazeCallForCreditDecissionAndVerifyBlazeCalculations2() throws IOException 
//  {
//	  String tokenURL = "https://iam.dms.euwt1.ficoanalyticcloud.com/registration/rest/client";
//	  RestAssured.baseURI = tokenURL;
//	  RequestSpecification requesttoken = RestAssured.given();
//	  JSONObject requestObj = new JSONObject();
//	  requestObj.put("clientId", "t5d02swde2");
//	  requestObj.put("secret", "ShwU11nYPJ*1bNbF6eM7olAMkPT8RGbvRAAF");
//	  requesttoken.header("Content-Type", "application/json");
//	  requesttoken.body(requestObj.toJSONString());
//	  Response response = requesttoken.post("/token");
//	  
//	  int statusCode = response.getStatusCode();
//	  int expectedStatusCode = 200;
//	  Assert.assertEquals(expectedStatusCode, statusCode);
//	  
//	  String token = response.getBody().asString();
//	  //System.out.println(data);
//	  
//	  File blazeRequestXML = new File("src/TestData/BlazeRequestTestData2.xml");
//	  
//	  String creditDecissionURL = "https://t6iwoxpp3f-t6iwoxpp3f.dms.euwt1.ficoanalyticcloud.com/DecisionExecutor/RmaDynamicWebService/solutionid";
//	  RestAssured.baseURI = creditDecissionURL;
//	  RequestSpecification creditDecissionRequest = RestAssured.given();
//	  creditDecissionRequest.header("Authorization", "Bearer "+token);
//	  creditDecissionRequest.header("Content-Type", "text/xml");
//	  creditDecissionRequest.body(blazeRequestXML);
//	  Response creditDecissionResponse = creditDecissionRequest.post("/t5d02swde2?wsdl");
//	  int creditDecissionStatusCode = creditDecissionResponse.getStatusCode();
//	  int expectedCreditDecissionStatusCode = 200;
//	  Assert.assertEquals(expectedCreditDecissionStatusCode, creditDecissionStatusCode);
//	  
//	  String creditDecissionBody = creditDecissionResponse.getBody().asString();
//	  
//	  BufferedWriter writer = new BufferedWriter(new FileWriter("src/TestData/BlazeResponseFile2.xml"));
//	  writer.flush();
//	  writer.write(creditDecissionBody);
//	  writer.close();
//	  
//	  
//	  try 
//	  {
//		  blazeCalculations.CalculateInstallment("src/TestData/BlazeResponseFile2.xml");
//	  } 
//	  catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) 
//	  {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//	  
//	  
//  }*/
//    
//}

//package Tests;
//
//import org.testng.annotations.Test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.Method;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//public class TestGetMethod 
//{
//	
//  @Test(priority=1 , description="Assert GET method status code" )
//  public void GetMethod_statusCode() 
//  {
//	  get("https://postman-echo.com/GET").then().statusCode(200);
//  }
//  
//  @Test(priority=2 , description="Assert GET method response body")
//  public void GetMethod_assertBody()
//  {
//	  get("https://postman-echo.com/GET").then().assertThat().body("headers.x-forwarded-port",equalTo("443"));
//  }
//  
//  @Test(priority=3 , description="Assert GET method response header")
//  public void GetMethod_testHeader()
//  {
//	  get("https://postman-echo.com/GET").then().header("Server", "nginx");
//  }
//  
//  @Test(priority=4)
//  public void RestAssured_Get()
//  {
//	  RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
//	  RequestSpecification httpsRequest = RestAssured.given();
//	  Response response = httpsRequest.request(Method.GET, "/Mumbai");
//	  String responseBody = response.getBody().asString();
//	  System.out.println(responseBody);
//  }
//}

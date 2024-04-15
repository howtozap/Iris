package GetRequest;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.impl.client.HttpClientBuilder;
import org.dom4j.DocumentException;
import org.jdom2.JDOMException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.HttpResponse;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetDataall {

	public String blazetoken;
	public String token;
	static String line = "", str = "";
	String wbctoken;
	String cat;
	String retailval;
	Object mmcode;

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/IntegrationData/data.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}
//
//	@Test(priority = 1)
//	public void testResponseCompuscan() throws IOException {
//
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//compuscan.json");
//		RestAssured.baseURI = "http://10.100.100.52/SATLayerUAT";
//
//		String compuscancontenttype = readInputData("compuscancontenttype");
//		String compuscanusername = readInputData("Compuscanusername");
//		String compuscanpassword = readInputData("compuscanpassword");
//		Response response = given().header("Content-Type", compuscancontenttype).header("UserName", compuscanusername)
//				.header("Password", compuscanpassword).and().body(request).when()
//				.post("/api/Compuscan/GetCompuscanData").then().extract().response();
//		int code = response.getStatusCode();
//
//		System.out.println("\nResponse from Compuscan");
//		System.out.println("Status Code:" + code);
//		response.prettyPrint();
//		Assert.assertEquals(code, 200);
//
//	}
//
//	@Test(priority = 2)
//	public void testResponseAcquire() throws IOException {
//
//		String Paramkey = readInputData("AcquireParmkey");
//		String Paramvalue = readInputData("AcquireParmvalue");
//		Response r = given().queryParam(Paramkey, Paramvalue).when()
//				.get("http://10.100.100.233:8085/CalidadRWS/AcquireAPIRequest/AcquireSystemDate/");
//
//		int code = r.getStatusCode();
//		System.out.println("\nResponse from Acquire:");
//		System.out.println("The response status is " + code);
//		r.prettyPrint();
//		Assert.assertEquals(code, 200);
//
//	}
//
//	@Test(priority = 3)
//	public void gettokensafps() throws IOException {
//
//		String SAFPStokencontenttype = readInputData("SAFPStokencontenttype");
//		String bodycontent = readInputData("SAFPStokenbody");
//		RestAssured.baseURI = "https://auth.safps.org.za";
//		Response responsefromtoken = given().header("Content-Type", SAFPStokencontenttype).and().body(bodycontent)
//				.when().post("/connect/token").then().extract().response();
//		int code = responsefromtoken.getStatusCode();
//		System.out.println("\nSAFPS Generated Token:");
//		System.out.println("Status Code:" + code);
//		responsefromtoken.prettyPrint();
//
//		Assert.assertEquals(code, 200);
//
//		String jsonresp = responsefromtoken.getBody().asString();
//		String tokengenerated = JsonPath.from(jsonresp).get("access_token");
//
//		RestAssured.baseURI = "https://external.safps.org.za";
//
//		String SAFPScontenttype = readInputData("SAFPScontenttype");
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//safps.json");
//		Response response = given().header("Content-Type", SAFPScontenttype)
//				.header("Authorization", "Bearer " + tokengenerated).and().body(request).when()
//				.post("/Api/Search/DetailedSearch").then().extract().response();
//		int code1 = response.getStatusCode();
//		int main = 200;
//		int main2 = 204;
//
//		System.out.println("\nResponse from SAFPS:");
//		System.out.println("Status Code:" + code1);
//		System.out.println(response.body().asString());
//		response.prettyPrint();
//
//		if (code1 == main || code1 == main2) {
//
//			System.out.println("\nSAPFS is passed");
//
//		}
//
//	}
//
	@Test(priority = 4)
	public void tokenresponsexds() throws ClientProtocolException, IOException {

		String endpoint = "https://www.uat.xds.co.za/xdsconnect/xdsconnectws.asmx";
		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//XDS.xml");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(request)));
		post.setHeader("Content-Type", "text/xml");
		HttpResponse response = client.execute(post);
		System.out.println("\nXDS Response code: " + response.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {

			sb.append(line);

		}

		PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "//src//IntegrationData//XDSResponse.xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();

	}

	@Test(priority = 5)
	public void gettoken() throws DocumentException, InterruptedException, IOException, JDOMException,
			ParserConfigurationException, SAXException, XPathExpressionException {

		BufferedReader br = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "//src//IntegrationData//XDSResponse.xml"));
		while ((line = br.readLine()) != null) {
			str += line;
		}
		JSONObject jsondata = XML.toJSONObject(str);
		System.out.println(jsondata);

		String jsonresp = (String) jsondata.toString();
		System.out.println("String" + jsonresp);
		token = jsonresp.substring(jsonresp.indexOf("LoginResult") + 14, jsonresp.length() - 5);

		System.out.println(token);
		br.close();
	}

	@Test(priority = 6)
	public void responsexds() throws DocumentException, InterruptedException, IOException, JDOMException,
			ParserConfigurationException, SAXException, XPathExpressionException {
		try {

			ArrayList<String> bookNames = new ArrayList<String>();

			// Parse XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(new FileInputStream(
					new File(System.getProperty("user.dir") + "\\" + "src\\IntegrationData\\XDSRequest1.xml")));

			// Get XPath expression
			XPathFactory xpathfactory = XPathFactory.newInstance();
			XPath xpath = xpathfactory.newXPath();
			xpath.setNamespaceContext(new NamespaceResolver(doc));
			XPathExpression expr = xpath
					.compile("//soapenv:Envelope/soapenv:Body/xds:ConnectConsumerMatch/xds:ConnectTicket");

			// Search XPath expression
			Object result = expr.evaluate(doc, XPathConstants.NODESET);

			// Iterate over results and fetch book names
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {

				nodes.item(i).setTextContent(token);
				bookNames.add((nodes.item(i)).getTextContent());
			}

			// Verify book names
			System.out.println(bookNames);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);

			StreamResult streamResult = new StreamResult(new File("D:\\xml\\updated.xml"));
			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

		String xdscontenttype = readInputData("xdscontenttype");
		String xdsreqURL = readInputData("xdsreqURL");

		String endpoint = xdsreqURL;
		File request = new File("D:\\xml\\updated.xml");
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(request)));
		post.setHeader("Content-Type", xdscontenttype);
		HttpResponse response = client.execute(post);
		System.out.println("\nXDS Actual Response code: " + response.getStatusLine().getStatusCode());
		BufferedReader br1 = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = br1.readLine()) != null) {

			sb.append(line);

		}

		PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "//src//IntegrationData//XDSResponse1.xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();

	}
//
////	@Test(priority=5)
////	public void responsetial() throws IOException {
////		
////		File TIALreq = new File(System.getProperty("user.dir")+"//src//IntegrationData//TIALrequest.xml");
////
////		String tialcontenttype  = readInputData("tialcontenttype");
////		String tialusername  = readInputData("tialusername");
////		String tialpassword  = readInputData("tialpassword");
////		 RestAssured.baseURI = "http://appsystemauat01:101";
////		 Response response = given()
////	               .header("Content-Type", tialcontenttype).header("Authenticate-UserName", tialusername).header("Authenticate-Password", tialpassword)
////	                .and()
////	                .body(TIALreq)
////	                .when()
////	                .post("/API/Quote")
////	                .then()
////	                .extract().response();
////		 int code=response.getStatusCode();
////		 System.out.println("\nTIAL Response");
////		 System.out.println("Status Code:"+code);
////		 response.prettyPrint();
////	}

//	@Test(priority = 7)
//	public void wbcretail() throws IOException {
//
//		String wbccontenttype = readInputData("wbccontenttype");
//		String wbcusername = readInputData("wbcusername");
//		String wbcpassword = readInputData("wbcpassword");
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//wbc.json");
//		RestAssured.baseURI = "http://10.100.100.52/SATLayerUAT";
//		Response response = given().header("Username", wbcusername).header("Password", wbcpassword)
//				.header("Content-Type", wbccontenttype).and().body(request).when().post("/api/WBC/GetRetailAmount")
//				.then().extract().response();
//		int code = response.getStatusCode();
//		System.out.println("\nWBC Response");
//		System.out.println("Status Code:" + code);
//		Assert.assertEquals(code, 200);
//		System.out.println("\nWBC Retail: ");
//		response.prettyPrint();
//
//	}
//
//	@Test(priority = 8)
//	public void wbcres() throws IOException {
//
//		String wbccontenttype = readInputData("wbccontenttype");
//		String wbcusername = readInputData("wbcusername");
//		String wbcpassword = readInputData("wbcpassword");
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//wbcres.json");
//		RestAssured.baseURI = "http://10.100.100.52/SATLayerUAT";
//		Response response = given().header("Username", wbcusername).header("Password", wbcpassword)
//				.header("Content-Type", wbccontenttype).and().body(request).when().post("/api/WBC/GetResidualAmount")
//				.then().extract().response();
//		int code = response.getStatusCode();
//		System.out.println("\nWBC Response");
//		System.out.println("Status Code:" + code);
//		Assert.assertEquals(code, 200);
//		System.out.println("\nWBC Res: ");
//		response.prettyPrint();
//
//	}
//
//	@Test(priority = 9)
//	public void wbctoken() throws IOException {
//
//		String tokenboday = readInputData("tokenboday");
//		String wbctokencontenttype = readInputData("wbctokencontenttype");
//		RestAssured.baseURI = "https://identity-uat.webuycars.co.za/connect/token";
//		Response responsefromtoken = given().header("Content-Type", wbctokencontenttype).and().body(tokenboday).when()
//				.post("/connect/token").then().extract().response();
//		int code = responsefromtoken.getStatusCode();
//		System.out.println("\nWBC Generated Token:");
//		System.out.println("Status Code:" + code);
//		responsefromtoken.prettyPrint();
//
//		Assert.assertEquals(code, 200);
//
//		String jsonresp = responsefromtoken.getBody().asString();
//		wbctoken = JsonPath.from(jsonresp).get("access_token");
//
//	}
//
//	@Test(priority = 10)
//	public void wbccatalogue() throws IOException {
//
//		mmcode = readInputData("wbccatloguemmcode");
//		String year = readInputData("wbccatlogueregyear");
//		Response response = given().header("Authorization", "Bearer " + wbctoken).when().get(
//				"https://api-uat.webuycars.co.za/vehicle-catalogue/api/v1/lookup/variant?page=1&pageSize=20&filter=(mmCode~eq~'"
//						+ mmcode + "')&regYear="+year)
//				.thenReturn();
//		int code = response.getStatusCode();
//
//		System.out.println("\nResponse from WBC Catlogue:");
//		System.out.println("Status Code:" + code);
//		response.prettyPrint();
//
//		Assert.assertEquals(code, 200);
//
//		String jsonresp = response.getBody().asString();
//		JSONObject jsonObject = new JSONObject(jsonresp);
//		List<String> list = new ArrayList<String>();
//		org.json.JSONArray jsonArray = jsonObject.getJSONArray("data");
//		for (int i = 0; i < jsonArray.length(); i++) {
//			list.add(jsonArray.getJSONObject(i).getString("catalogueId"));
//			cat = jsonArray.getJSONObject(i).getString("catalogueId");
//			System.out.println("Catlogue id is:" + cat); // display catlogue name
//		}
//
//	}
//
//	@Test(priority = 11)
//	public void wbcvaluation() throws IOException {
//		
//		String year = readInputData("wbccatlogueregyear");
//		Response response = given().header("Authorization", "Bearer " + wbctoken).when()
//				.get("https://api-uat.webuycars.co.za/vehicle-catalogue/api/v1/valuation/" + cat + "/"+year).thenReturn();
//		int code = response.getStatusCode();
//
//		System.out.println("\nResponse from WBC Valuation:");
//		System.out.println("Status Code:" + code);
//		response.prettyPrint();
//
//		Assert.assertEquals(code, 200);
//
//	}
//
//	@Test(priority = 12)
//	public void tokenblaze() throws IOException {
//
//		String blazecontenttype = readInputData("blazecontenttype");
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//blaze.json");
//		RestAssured.baseURI = "https://iam-svc.dms.euwt1.ficoanalyticcloud.com";
//		Response response = given().header("Content-Type", blazecontenttype).and().body(request).when()
//				.post("/registration/rest/client/token").then().extract().response();
//		int code = response.getStatusCode();
//		System.out.println("\nBlaze Response");
//		System.out.println("Status Code:" + code);
//		Assert.assertEquals(code, 200);
//		System.out.println("\nBlaze Token: ");
//		blazetoken = response.body().asString();
//		response.prettyPrint();
//	}
//
//	@Test(priority = 13)
//	public void responseblaze() throws IOException {
//
//		String endpoint = "https://dm-46mizxe8zj.dms.euwt1.ficoanalyticcloud.com/SOWNiZJCgwYu3HpkPtEyZAJQjdz/RmaDynamicWebService/solutionid/10vje3xrgux";
//		File request = new File(System.getProperty("user.dir") + "//src//IntegrationData//blazereq.xml");
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(endpoint);
//		post.setEntity(new InputStreamEntity(new FileInputStream(request)));
//		post.setHeader("Content-Type", "text/xml");
//		post.setHeader("Authorization", "Bearer " + blazetoken);
//		HttpResponse response = client.execute(post);
//		System.out.println("\nBlaze Response code: " + response.getStatusLine().getStatusCode());
//		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		String line = "";
//		StringBuffer sb = new StringBuffer();
//		while ((line = br.readLine()) != null) {
//
//			sb.append(line);
//
//		}
//
//		PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "//src//IntegrationData//BlazeResponse.xml");
//		pw.write(sb.toString());
//		pw.close();
//		pw.flush();
//
//	}

}

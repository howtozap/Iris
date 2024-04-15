package SourceCode;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.formula.functions.Finance;
import org.apache.poi.util.SystemOutLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class BlazeCal {

	double monthlyFinanceInstalment;
	double vehiclePricePresentValuePMT;
	double depositAmount;
	double principalDebt;
	double totalFinancedAmount;
	double monthlyVehicleInstalment;
	double totalInstalmentAmount;
	double monthlyCreditLifeProrata;
	double firstMonthCostOfInsuranceAndCreditLifeProrata;
	double monthlyPrincipleDebtInstalment;
	double monthlyProrataCarTrackSoftwareFee;
	double MonthlyInsuranceProrata;
	double requestedTerm;
	double vehiclePricePresentValue;
	double instalmentAmount;
	double kilometersPerTrip;
	double maxFarePerKmLimit;
	double maxTripsPerDayLimit;
	double maxDaysPerMonthLimit;
	double maxSeatingCapacity;
	double interestRate;
	double maxExposure;
	double totalPurchasePrice;
	double vatableExtras;
	double nonVatableExtras;
	double discount; 
	String isFinancetrackUnit;
	double totalTradeIn;
	double totalExtras;
	double monthlyCarTrackInstalment;
	double initiationFeesInstalment;
	double initiationFees;
	double monthlyInsuranceInstalment;
	double requestedRate;
	double monthlyAdminFee;
	double monthlyNetIncomeAfterExpenses;
	double monthlyTotalInstalmentAmount;
	double monthlyNetIncomeAfterInstalment;
	double totalTrackingDeviceInstalmentPerMonth;
	double carTrackHardwareCost;
	double monthlyCarTrackSoftwareFee;
	double prorataCarTrackFee;
	double vehiclePrice;
	double driverWages;
	double farePerPassenger;
	double daysWorked;
	double noOfpassenger;
	double insuranceProRata;
	String isCreditLife;
	double insurancePremium;
	double cl_MonthlyPremium;
	double overridenInsurancePremium;
	double overridenInsuranceProRata;
	double windScreen;
	double saSRIA;
	double totalInsuranceAddonAmount;
	double baseRate;
	double requestedDepositAmount;
	double insurancepremium=0.0;
	double proRataInsurance=0.0;
	boolean iscreditlife=false;
	double monthlypremium=0.0;
	double cl_ProRata=0.0;
	String dateOfAssessment;
	String daysFirstInstalment;
	double gbbPrice;
	
	public String isEqual(double value1, double value2)
	{
		if(Math.abs(value1 - value2) <= (0.0001))
		{
			return "YES";
		}
		else
		{
			return "NO";
		}
		
	}
	
	public double calculateTotalTrackingDeviceInstalmentPerMonth(String isFinancetrackUnit, double monthlyCarTrackInstalment, double carTrackSoftwareMonthlyCost, double monthlyProrataCarTrackSoftwareFee)
	{
		if (isFinancetrackUnit.equalsIgnoreCase("true"))
		{
			double calculatedTotalTrackingDeviceInstalmentPerMonth = monthlyCarTrackInstalment + carTrackSoftwareMonthlyCost + monthlyProrataCarTrackSoftwareFee;
			return calculatedTotalTrackingDeviceInstalmentPerMonth;
		}
		else
		{
			double calculatedTotalTrackingDeviceInstalmentPerMonth = carTrackSoftwareMonthlyCost + monthlyProrataCarTrackSoftwareFee;
			return calculatedTotalTrackingDeviceInstalmentPerMonth;
		}
	}
	
	public double isCreditLifeTrue(String isCreditLife, double CL_MonthlyPremium, double MonthlyCreditLifeProrata)
	{
		if(isCreditLife.equalsIgnoreCase("true"))
		{
			double creditLifeValue = CL_MonthlyPremium + MonthlyCreditLifeProrata;
			return creditLifeValue;
		}
		else
		{
			return 0;
		}
	}
	
	@Test(priority=1)
	public void readresponse() throws IOException, SAXException, ParserConfigurationException {
		
		File file = new File(System.getProperty("user.dir")+"//src//SourceCode//BlazeOutput.txt");  
        FileOutputStream fis = new FileOutputStream(file);  
        PrintStream out = new PrintStream(fis);  
        System.setOut(out); 
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	 
	//Build Document
	Document document = builder.parse(new File(System.getProperty("user.dir")+"//src//TestData//BlazeRequestTestData2.xml"));
	 
	//Normalize the XML Structure; It's just too important !!
	document.getDocumentElement().normalize();
	 
	NodeList n1=document.getElementsByTagName("Message");
	int a=0;
	for(int j = 0; j < n1.getLength(); j++) {
        //Traverse down the product offer decision tag
        NodeList request = ((Element)n1.item(j)).getElementsByTagName("request");
        for(int i = 0; i < request.getLength(); i++) {
        	NodeList t1=request.item(i).getChildNodes();
        	for(a=0;a<t1.getLength();a++) {
        		if(t1.item(a).getNodeName().equals("requestTimestamp")) {
    				
        			dateOfAssessment=t1.item(a).getTextContent();
    				System.out.println("Date of Assessment: "+dateOfAssessment);
    			
    			}
        	}
        NodeList inputdata = ((Element)request.item(i)).getElementsByTagName("inputData");
        for(int k = 0; k < inputdata.getLength(); k++) {
        NodeList applicant = ((Element)inputdata.item(k)).getElementsByTagName("applicant");
        for(int l = 0; l < applicant.getLength(); l++) {
        NodeList weBuyCars = ((Element)applicant.item(l)).getElementsByTagName("weBuyCars");
    	for(int m = 0; m < weBuyCars.getLength(); m++) {
    	NodeList n2=weBuyCars.item(m).getChildNodes();
    	for(a=0;a<n2.getLength();a++) {
    		
    		if(n2.item(a).getNodeName().equals("gbbPrice")) {
				
    			gbbPrice=Double.parseDouble(n2.item(a).getTextContent());
				System.out.println("GBB Price: "+gbbPrice);
			
			}
    		
    	}
        NodeList consumerVehicle = ((Element)applicant.item(l)).getElementsByTagName("consumerVehicle");
	    for(int m1 = 0; m1 < consumerVehicle.getLength(); m1++) {
		NodeList n3=consumerVehicle.item(m1).getChildNodes();
			for(a=0;a<n3.getLength();a++) {
						
					if(n3.item(a).getNodeName().equals("vehiclePrice")) {
						
						vehiclePrice=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Vehicle Price: "+vehiclePrice);
					
					}
					if(n3.item(a).getNodeName().equals("vatableExtras")) {
						
						vatableExtras=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Vatable Extras:"+vatableExtras);
					
					}
					if(n3.item(a).getNodeName().equals("nonVatableExtras")) {
						
						nonVatableExtras=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Non Vatable Extras: "+nonVatableExtras);
					
					}
					if(n3.item(a).getNodeName().equals("initiationFees")) {
						
						initiationFees=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Initiation Fee: "+initiationFees);
					
					}
					if(n3.item(a).getNodeName().equals("monthlyAdminFee")) {
						
						monthlyAdminFee=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Monthly AdminFee: "+monthlyAdminFee);
					
					}
					if(n3.item(a).getNodeName().equals("requestedDepositAmount")) {
						
						requestedDepositAmount=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Requested Deposit Amount: "+requestedDepositAmount);
					
					}
					if(n3.item(a).getNodeName().equals("discount")) {
						
						discount=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Discount: "+discount);
					
					}
					if(n3.item(a).getNodeName().equals("totalTradeIn")) {
						
						totalTradeIn=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Total Trade In: "+totalTradeIn);
					
					}
					if(n3.item(a).getNodeName().equals("requestedTerm")) {
						
						requestedTerm=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Requested Term: "+requestedTerm);
					
					}
					if(n3.item(a).getNodeName().equals("requestedRate")) {
						
						requestedRate=Double.parseDouble(n3.item(a).getTextContent());
						System.out.println("Requested Rate: "+requestedRate);
					
					}
					if(n3.item(a).getNodeName().equals("firstInstalmentDate")) {
						
						daysFirstInstalment=n3.item(a).getTextContent();
						System.out.println("First Instalment Date: "+daysFirstInstalment);
					
					}
					if(n3.item(a).getNodeName().equals("firstInstalmentDate")) {
						
						daysFirstInstalment=n3.item(a).getTextContent();
						System.out.println("First Instalment Date: "+daysFirstInstalment);
					
					}
					
					}
					}
	    
				}
				
			}
        }

		}
	    }
	//Build Document
		Document responsedocument = builder.parse(new File(System.getProperty("user.dir")+"//src//TestData//BlazeResponseFile2.xml"));
		 
		//Normalize the XML Structure; It's just too important !!
		responsedocument.getDocumentElement().normalize();
		 
		NodeList r1=responsedocument.getElementsByTagName("Message");
		int a1=0;
		for(int j = 0; j < r1.getLength(); j++) {
	        //Traverse down the product offer decision tag
	        NodeList response = ((Element)r1.item(j)).getElementsByTagName("response");
	        for(int i = 0; i < response.getLength(); i++) {
	        NodeList decision = ((Element)response.item(i)).getElementsByTagName("decision");
	        for(int k = 0; k < decision.getLength(); k++) {
	        NodeList offerDecision = ((Element)decision.item(k)).getElementsByTagName("offerDecision");
	        for(int l = 0; l < offerDecision.getLength(); l++) {
	    	NodeList n2=offerDecision.item(l).getChildNodes();
	    	for(a1=0;a1<n2.getLength();a1++) {
	    		
			    	if(n2.item(a1).getNodeName().equals("principalDebt")) {
							
			    		principalDebt=Double.parseDouble(n2.item(a1).getTextContent());
						System.out.println("Principal Debt: "+principalDebt);
						
			    	}	
			    	if(n2.item(a1).getNodeName().equals("totalFinancedAmount")) {
						
			    		totalFinancedAmount=Double.parseDouble(n2.item(a1).getTextContent());
						System.out.println("Total Financed Amount: "+totalFinancedAmount);
					
		    		}	
			    	if(n2.item(a1).getNodeName().equals("monthlyVehicleInstalment")) {
						
			    		monthlyVehicleInstalment=Double.parseDouble(n2.item(a1).getTextContent());
						System.out.println("Monthly Vehicle Instalment: "+monthlyVehicleInstalment);
					
		    		}
			    	if(n2.item(a1).getNodeName().equals("totalInstalmentAmount")) {
						
			    		totalInstalmentAmount=Double.parseDouble(n2.item(a1).getTextContent());
						System.out.println("Monthly Vehicle Instalment: "+totalInstalmentAmount);
					
		    		}
						
						}
						}
		    
					}
					
				}
	        }

	}

	@Test(priority=2)
	public void calculation() throws IOException, SAXException, ParserConfigurationException, ParseException { 
		
		Finance finance = new Finance();
		int tterm=(int)requestedTerm;
		//double approverate=requestedTerm+baseRate;
		double purchasePrice = (vehiclePrice + vatableExtras + nonVatableExtras) ;
		
		double calculatedPrincipleDebt = (vehiclePrice + vatableExtras + nonVatableExtras + initiationFees) - (discount + totalTradeIn + requestedDepositAmount);
		String PrincipleDebtResult = isEqual(calculatedPrincipleDebt, principalDebt);
		
		SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");  
		Date date1 = obj.parse(daysFirstInstalment);   
        Date date2 = obj.parse(dateOfAssessment);   
        // Calucalte time difference in milliseconds   
        long time_difference = date1.getTime() - date2.getTime();  
        
        // Calucalte time difference in days  
        double days_difference = (time_difference / (1000*60*60*24)) % 365;  
        
        double proRataInterestassessment = calculatedPrincipleDebt * requestedRate * ((days_difference - 30) / 365);
        
        double proRataInteresttoday = calculatedPrincipleDebt * requestedRate * ((days_difference - 30) / 365);
       
        double calculatedtotalFinancedAmount = calculatedPrincipleDebt + insurancepremium +  proRataInterestassessment + monthlypremium + cl_ProRata;
        String totalFinancedAmountResult = isEqual(calculatedtotalFinancedAmount, totalFinancedAmount);
        
        double calculatedMonthlyVehicleInstalment = (finance.pmt((requestedRate/12), tterm, totalFinancedAmount, -gbbPrice)) *(-1);
        String MonthlyPrincipleDebtInstalmentResult = isEqual(calculatedMonthlyVehicleInstalment, monthlyVehicleInstalment);
        
        double calculatedTotalInstalmentAmount =  (calculatedMonthlyVehicleInstalment + monthlyAdminFee); 
        String TotalInstalmentAmountResult = isEqual(calculatedTotalInstalmentAmount, totalInstalmentAmount);
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s %60s %26s %25s", "Field Name", "Actual Value", "Calculated Value", "Are Values Matching");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s %60s %25s %20s", "PrincipleDebt", principalDebt, calculatedPrincipleDebt, PrincipleDebtResult);
        System.out.println();
        System.out.printf("%5s %43s %25s %20s", "MonthlyPrincipleDebtInstalment", monthlyVehicleInstalment, calculatedMonthlyVehicleInstalment, MonthlyPrincipleDebtInstalmentResult);
        System.out.println();
        System.out.printf("%5s %43s %25s %20s", "TotalFinancedAmount", totalFinancedAmount, calculatedtotalFinancedAmount, totalFinancedAmountResult);
        System.out.println();
        System.out.printf("%5s %43s %25s %20s", "TotalInstalmentAmount", totalInstalmentAmount, calculatedTotalInstalmentAmount, TotalInstalmentAmountResult);
        System.out.println();
        
	}

}

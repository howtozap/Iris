package Pages;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.formula.functions.Finance;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Podium {

	public File inputFile;
	public SAXReader saxReader;
	public Document document;
	public URL TestURL;
	public BufferedInputStream TestFile;
	public PDDocument TestPDF;
	public String TestText;

	public float vehiclePrice;
	public float vatableextras;
	public float nonvatableextras;
	public float initiationFees;
	public float discount;
	public float totalTradeIn;
	public float requestedDepositAmount;
	public float requestedRate;
	public float requestedTerm;
	public float gbbPrice;
	//public String principalDebt;
	public String insurancePremium;
	public String monthlyCarTrackSoftwareFee;
	public String CL_MonthlyPremium;
	public float monthlyAdminFee;
	public String term;
	public float totalFinancedAmount;
	public float principalDebt;
	
	String zero;
	String zeroExcess;
	String bp;
	String businessProtct;
	String pa;
	String personalAccident;
	String ds;
	String depositSure;

	Finance finance = new Finance();

	String vehicleInstallment;
	String comprehensiveMotorInsurrance;
	String trackingDevice;
	String creditLife;
	String monthlyServiceFee;
	String TrackingDeviceProrata;
	String CreditLifeProrata;
	String totalMonthlyInstalmentValueFromPodium;
	String totalMonthlyInstallmentvalueFromCalculations;
	double covers ;
	double insurancepremium=0.0;
	double proRataInsurance=0.0;
	boolean iscreditlife=false;
	double monthlypremium=0.0;
	double cl_ProRata=0.0;
	String dateOfAssessment;
	String daysFirstInstalment;

	String regexVehiclePrice = "(Vehicle Price)( )?(:)( )?(R)( )?(-)?(\\d+)(,)?(\\d+)(.)?(\\d+)";
	String regexTotalFinanceAmount = "(Total Finance Amount)( )?(:)( )?(R)( )?(-)?(\\d+)(,)?(\\d+)(.)?(\\d+)";
	//String regexVehiclePrice = "R\\s(.+):Vehicle Price";
	String regexTerm = "(Term)( )?(:)( )?(-)?(\\d+)(\\d+)";
	String regexDeposit = "(Deposit)( )?(:)( )?(R)( )?(-)?(\\d+)(,)?(\\d+)(.)?(\\d+)";
	String regexExtras = "(Extras)( )?(:)( )?(R)( )?(-)?(\\d+)(,)?(\\d+)(.)?(\\d+)";
	String regexRequestedDeposit ="(?!(.+):Approved Int Rate)R\\s(.+):Requested Deposit";
	String regexApprovedIntRate ="(.+):Approved Int Rate";
	String regexPaymentFrequency="(.+):Payment Frequency";
	String regexRequestedIntRate="(?!(.+):Payment Frequency)\\d(.+):Requested Int Rate";
	String regexPrincipalDebt="R\\s(.+):Principal Debt";
	String regexVehicleInstalment="R\\s(.+):Vehicle Instalment";
	String regexComprehensiveInsurance="R\\s(.+) Comprehensive Motor Insurance";
	String regexTrackingDevice="(?!R\\s(.+)Tracking Device pro-rata)R\\s(.+)Tracking Device";
	String regexCreditLife="R\\s(.+)Credit Life";
	String regexMonthlyServiceFee="R\\s(.+)Monthly service fee";
   // String regexPeriod="(?!(.+):Period at address)(.+):Period";
    String regexPeriod="\\d+(?=\\smonths:Period)";
    String regexZeroExcess="(.+):Zero Excess";
    String regexPersonalAccident="(.+):Personal Accident";
    String regexDepositSure="(.+):Deposit Sure";
    String regexBusinessProtct="(.+):Business Protect";
    String regexTrackingDeviceProrata="R\\s(.+)Tracking Device pro-rata";
    String regexTotalMonthlyInstalment="R(.+)Total Monthly Instalment";
    String regexCreditLifeProrata="R\\s(.*):\\s";
   

	public void readBlazeRequest() throws DocumentException, IOException, InterruptedException {

		inputFile = new File(System.getProperty("user.dir") + "\\" + "src\\TestData\\BlazeRequestTestData.xml");
		saxReader = new SAXReader();
		document = saxReader.read(inputFile);

		Thread.sleep(2000);
		vehiclePrice = Float.parseFloat(document.selectSingleNode("//request/inputData/applicant/consumerVehicle/vehiclePrice")
				.getText());
		vatableextras = Float.parseFloat(document.selectSingleNode("//request/inputData/applicant/consumerVehicle/vatableExtras")
				.getText());
		nonvatableextras = Float.parseFloat(document.selectSingleNode("//request/inputData/applicant/consumerVehicle/nonVatableExtras")
				.getText());
		initiationFees = Float.parseFloat(document.selectSingleNode("//request/inputData/applicant/consumerVehicle/initiationFees")
				.getText());
		discount = Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/discount").getText());
		
		totalTradeIn=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/totalTradeIn").getText());
		
		requestedDepositAmount=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/requestedDepositAmount").getText());
		
		requestedRate=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/requestedRate").getText());
		
		requestedTerm=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/requestedTerm").getText());
		
		gbbPrice=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/weBuyCars/gbbPrice").getText());
		dateOfAssessment=document
				.selectSingleNode("//request/requestTimestamp").getText();
		daysFirstInstalment=document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/firstInstalmentDate").getText();
		monthlyAdminFee=Float.parseFloat(document
				.selectSingleNode("//request/inputData/applicant/consumerVehicle/monthlyAdminFee").getText());
		

//		principalDebt=document
//				.selectSingleNode("//CreditDecisionRequest/outputData/productOffer/principalDebt").getText();
//		
//		insurancePremium=document
//				.selectSingleNode("//CreditDecisionRequest/inputData/product/insurance/overridenInsurancePremium").getText();
//		
//		monthlyCarTrackSoftwareFee=document
//				.selectSingleNode("//CreditDecisionRequest/outputData/productOffer/monthlyCarTrackSoftwareFee").getText();
//		
//		CL_MonthlyPremium=document
//				.selectSingleNode("//CreditDecisionRequest/inputData/product/insurance/CL_MonthlyPremium").getText();
//		
//		monthlyAdminFee=document
//				.selectSingleNode("//CreditDecisionRequest/outputData/productOffer/monthlyAdminFee").getText();
		
		System.out.println(
				"*************************************************************************************************************");

		System.out.println("Field Name" + "         |         " + "Value fetched from Blaze Response");

		System.out.println(
				"*************************************************************************************************************");

		System.out.println("Vehicle Price" + "                           " + vehiclePrice);

		System.out.println("Vatable Extras"   + "                          " + vatableextras);

		System.out.println("Non Vatable Extras" + "                      " + nonvatableextras);

		System.out.println("Initiation Fees "  + "                        " + initiationFees);

		System.out.println("Discount " + "                               " + discount);
		
		System.out.println("Total Trade In " + "                         " + totalTradeIn);
		
		System.out.println("Requested Deposit Amount" + "                " + requestedDepositAmount);
		
		System.out.println("Requested Rate" + "                          " + requestedRate);
		
		System.out.println("Requested Term" + "                          " + requestedTerm);
		
		System.out.println("GBB Price" + "                               " + gbbPrice);
		
		System.out.println("Insurance Premium" + "                       " + insurancePremium);
		
		System.out.println("Car Track Software Fee" + "                  " + monthlyCarTrackSoftwareFee);

		System.out.println("Credit Life Premium" + "                     " + CL_MonthlyPremium);
		
		System.out.println("Admin Fee" + "                               " + monthlyAdminFee);
		
		System.out.println(
				"**************************************************************************************************************");
		System.out.println(
				"                                                                                                               ");

		System.out.println(
				"***********************************Podium Values and Result***************************************************");
		System.out.println(
				"                                                                                                               ");

	}
	
	public void readBlazeResponse() throws DocumentException, IOException, InterruptedException {

		inputFile = new File(System.getProperty("user.dir") + "\\" + "src\\TestData\\BlazeResponseFile2.xml");
		saxReader = new SAXReader();
		document = saxReader.read(inputFile);

		Thread.sleep(2000);
		totalFinancedAmount = Float.parseFloat(document.selectSingleNode("//response/decision/offerDecision/totalFinancedAmount")
				.getText());
		principalDebt = Float.parseFloat(document.selectSingleNode("//response/decision/offerDecision/principalDebt")
				.getText());
	}

	public void convertPDFToText() throws IOException {
		TestURL = new URL("file:///C:/Users/RiddhiT/Downloads/30224_APPOD_06_06_2022_02h30m27s.pdf");

		TestFile = new BufferedInputStream(TestURL.openStream());
		TestPDF = PDDocument.load(TestFile);
		TestText = new PDFTextStripper().getText(TestPDF);
		System.out.println(TestText);

	}
	
	public String checkDecimal(String str ) {
		
		if( str.matches("^\\d+\\.\\d+") ) {
			//System.out.println(str +" is a decimal number");
		}
		
		else {
		  // System.out.println(str+"  is a not decimal number");
		   str =str+".00";
		   //System.out.println(str);
		   }
		return str;
		
	}
//	 public  String checkCoverAcceptOrDecline(String str) {
//			
//			if(str.equals("Cover not selected")) {
//					System.out.println("Cover is Decline");
//					str=str.replace(str, "00");
//				}
//			else if(!(str.equals("Cover not selected"))) {
//					System.out.println("Cover is accepted");
//	                str=str.replaceAll("^(\\w)+\\s", "");
//	                
//	             }
//				
//			return str;
//	     }
//     public double coverAddition( double z,double bp,double pa,double ds) {
//    	 
//    	 covers = z+bp+pa+ds;
//    	 
//    	 System.out.println("Covers Addition                      "+covers);
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//          return covers;
//     }
//
	public void matchVehicleModel() {

		Pattern p = Pattern.compile(regexVehiclePrice);
		Matcher match = p.matcher(TestText);
		if (match.find()) {
			
			float vehiclepricepodium = Float.parseFloat(match.group(0).split(":")[1].replaceAll("(R)*(\\s)*(,)*", ""));

			System.out.println(
					"Vehicle Price " +"                       " + vehiclepricepodium);

			if (vehiclepricepodium==(vehiclePrice)) {
				System.out.println("Vehicle Price matched");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Data not match");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Data not found");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
		}

	}
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

	public void matchtotalfinancedamount() throws ParseException {
		
		double calculatedPrincipleDebt = (vehiclePrice + vatableextras + nonvatableextras + initiationFees) - (discount + totalTradeIn + requestedDepositAmount);
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
        int tterm=(int)requestedTerm;
        double calculatedMonthlyVehicleInstalment = (finance.pmt((requestedRate/12), tterm, totalFinancedAmount, -gbbPrice)) *(-1);
        //String MonthlyPrincipleDebtInstalmentResult = isEqual(calculatedMonthlyVehicleInstalment, monthlyVehicleInstalment);
        
        float calculatedTotalInstalmentAmount = (float) (calculatedMonthlyVehicleInstalment + monthlyAdminFee); 
       // String TotalInstalmentAmountResult = isEqual(calculatedTotalInstalmentAmount, totalInstalmentAmount);
		Pattern p = Pattern.compile(regexTotalFinanceAmount);
		Matcher match = p.matcher(TestText);
		
		if (match.find()) {
			
			float totalfinancedpricepodium = Float.parseFloat(match.group(0).split(":")[1].replaceAll("(R)*(\\s)*(,)*", ""));
			
			calculatedTotalInstalmentAmount=Float.parseFloat(String.format("%.2f", calculatedTotalInstalmentAmount));

			System.out.println("Total Finance Amount                       " + totalfinancedpricepodium);

			if (calculatedTotalInstalmentAmount==totalfinancedpricepodium) {
				System.out.println("Vehicle Total Financed Amount matched");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Data not match");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Data not found");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
		}

	}

	public void matchTerm() {
		Pattern p = Pattern.compile(regexTerm);
		Matcher match = p.matcher(TestText);
		if (match.find()) {
			
			float termpodium = Float.parseFloat(match.group(0).split(":")[1].replaceAll("(R)*(\\s)*(,)*", ""));

			System.out
					.println("Term                       " + termpodium);

			if (requestedTerm==termpodium) {
				System.out.println("Term matched");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Data not match");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Data not found");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
		}
	}

	public void matchDeposit() {

		Pattern p = Pattern.compile(regexDeposit);
		Matcher match = p.matcher(TestText);
		if (match.find()) {
			
			float depositpodium = Float.parseFloat(match.group(0).split(":")[1].replaceAll("(R)*(\\s)*(,)*", ""));

			System.out
					.println("Deposit                       " + depositpodium);

			if (requestedDepositAmount==depositpodium) {
				System.out.println("Deposit matched");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Data not match");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Data not found");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
		}
	}

	public void matchExtras() {

		Pattern p = Pattern.compile(regexExtras);
		Matcher match = p.matcher(TestText);
		float calculatedextras = vatableextras+nonvatableextras;
		if (match.find()) {
			float extraspodium = Float.parseFloat(match.group(0).split(":")[1].replaceAll("(R)*(\\s)*(,)*", ""));
			System.out.println("Extras                       " + extraspodium);

			if (calculatedextras==extraspodium) {
				System.out.println("Extras matched");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Data not match");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("Data not found");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
		}
	
	}
	
//	public void matchApprovedDeposit() {
//
//		Pattern p = Pattern.compile(regexAprrovedDeposit);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Approved Deposit                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//
//			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//			
//			depositAmount = checkDecimal(depositAmount);
//
//			if (price.contains(depositAmount)) {
//				System.out.println("Approved Deposit matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		}
//		else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//	}
//	
//	public void matchApprovedIntRate() {
//
//		Pattern p = Pattern.compile(regexApprovedIntRate);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Approved Int Rate                       " + match.group(0).split(":")[0]);
//			
//			Double s=Double.parseDouble(rate);
//			
//			Double s1= s*100;
//			
//			DecimalFormat df = new DecimalFormat("0.00");
//			df.setMaximumFractionDigits(2);
//			String r = df.format(s1);
//			
//			rate = r +" %";
//			
//			if (match.group(0).split(":")[0].contains(rate)) {
//				System.out.println("Approved Int Rate matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		}
//		else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//		}
//	
//	public void matchRequestedDeposit() {
//
//		Pattern p = Pattern.compile(regexRequestedDeposit);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Requested Deposit                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//			
//			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			requestedDepositAmount = checkDecimal(requestedDepositAmount);
//			
//
//			if (price.contains(requestedDepositAmount)) {
//				
//				System.out.println("Requested Deposit matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//			
//		}
//	
//	
//	public void matchRequestedIntRate() {
//
//		Pattern p = Pattern.compile(regexRequestedIntRate);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Requested Int Rate                       " + match.group(0).split(":")[0]);
//			
//
//			Double s=Double.parseDouble(requestedIntRate);
//			
//			Double s1= s*100;
//		
//			DecimalFormat df = new DecimalFormat("0.00");
//			df.setMaximumFractionDigits(2);
//			String r = df.format(s1);
//			
//			requestedIntRate = r +" %";
//			
//			if (match.group(0).split(":")[0].contains(requestedIntRate)) {
//				System.out.println("Requested Int Rate matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		}
//		else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//		}
//	
//	public void matchPricipalDebt() {
//		
//		Pattern p = Pattern.compile(regexPrincipalDebt);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Principal Debt                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//			
//			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			principalDebt = checkDecimal(principalDebt);
//			
//
//			if (price.contains(principalDebt)) {
//				
//				System.out.println("Principal Debt matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//			
//	}
//	
//     public void matchVehicleInstallment() {
//		
//		Pattern p = Pattern.compile(regexVehicleInstalment);
//		Matcher match = p.matcher(TestText);
//		if (match.find()) {
//
//			System.out
//					.println("Vehicle Instalment                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			monthlyPrincipleDebtInstalment = checkDecimal(monthlyPrincipleDebtInstalment);
//			
//			DecimalFormat df = new DecimalFormat("0.00");
//			df.setMaximumFractionDigits(2);
//			monthlyPrincipleDebtInstalment = df.format(Double.parseDouble(monthlyPrincipleDebtInstalment));
//
//			if (price.contains(monthlyPrincipleDebtInstalment)) {
//				
//				System.out.println("Vehicle Instalment matched");
//				vehicleInstallment=price;
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//			
//	}
//     
//     public void matchComprehensiveInsurance() {
// 		
// 		Pattern p = Pattern.compile(regexComprehensiveInsurance);
// 		Matcher match = p.matcher(TestText);
// 		if (match.find()) {
//
// 			System.out
// 					.println("Comprehensive Insurance                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
// 			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			insurancePremium = checkDecimal(insurancePremium);
//
//			if (price.contains(insurancePremium)) {
//				
//				System.out.println("Comprehensive Insurance matched");
//				comprehensiveMotorInsurrance=price;
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
// 	}
//     
//   
//     public void matchTrackingDevice() {
//  		
//  		Pattern p = Pattern.compile(regexTrackingDevice);
//  		Matcher match = p.matcher(TestText);
//  		if (match.find()) {
//
//  			System.out
//  					.println("Tracking Device                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//  			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			monthlyCarTrackSoftwareFee = checkDecimal(monthlyCarTrackSoftwareFee);
//
//			if (price.contains(monthlyCarTrackSoftwareFee)) {
//				
//				System.out.println("Tracking Device matched");
//				trackingDevice=price;
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//  	}
//     
//     public void matchCreditLife() {
//   		
//   		Pattern p = Pattern.compile(regexCreditLife);
//   		Matcher match = p.matcher(TestText);
//   		if (match.find()) {
//
//   			System.out
//   					.println("Credit Life                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//   			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//			String price = s.replace(",", "");
//		
//			CL_MonthlyPremium = checkDecimal(CL_MonthlyPremium);
//
//			if (price.contains(CL_MonthlyPremium)) {
//				
//				System.out.println("Credit Life matched");
//				creditLife=price;
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
//   	}
//     
//     public void matchMonthlyServiceFree() {
//    		
//    		Pattern p = Pattern.compile(regexMonthlyServiceFee);
//    		Matcher match = p.matcher(TestText);
//    		if (match.find()) {
//
//    			System.out
//    					.println("Monthly Service Fee                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//    			String s = match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//    			String price = s.replace(",", "");
//    		
//    			monthlyAdminFee = checkDecimal(monthlyAdminFee);
//
//    			if (price.contains(monthlyAdminFee)) {
//    				
//    				System.out.println("Monthly Service Fee matched");
//    				monthlyServiceFee=price;
//    				System.out.println(
//    						"---------------------------------------------------------------------------------------------------------------");
//    			} else {
//    				System.out.println("Data not match");
//    				System.out.println(
//    						"---------------------------------------------------------------------------------------------------------------");
//    			}
//    		} else {
//    			System.out.println("Data not found");
//    			System.out.println(
//    					"---------------------------------------------------------------------------------------------------------------");
//    		}
//    	}
//     
//     public void matchPeriod() {
// 		
// 		Pattern p = Pattern.compile(regexPeriod);
// 		Matcher match = p.matcher(TestText);
// 		if (match.find()) {
//
// 			System.out
// 					.println("Period                       " + match.group(0));
// 			if (match.group(0).contains(term)) {
//				
//				System.out.println("Period matched");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			} else {
//				System.out.println("Data not match");
//				System.out.println(
//						"---------------------------------------------------------------------------------------------------------------");
//			}
//		} else {
//			System.out.println("Data not found");
//			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//		}
// 			
//     }
//     public void trackingDeviceProrata() {
//  		
//  		Pattern p = Pattern.compile(regexTrackingDeviceProrata);
//  		Matcher match = p.matcher(TestText);
//  		if (match.find()) {
//
//  			System.out
//  					.println("Tracking Device Pro_rate                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//  			TrackingDeviceProrata=match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//  			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//  		}}
//     
//     public void creditLifeProrata() {
//   		
//   		Pattern p = Pattern.compile(regexCreditLifeProrata);
//   		Matcher match = p.matcher(TestText);
//   		
//   		if (match.find()) {
//
//   			System.out
//   					.println("Credit Life Pro_rate                       " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//   			CreditLifeProrata=match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//   			System.out.println(
// 					"---------------------------------------------------------------------------------------------------------------");
//   		}}
//     
//     public void totalMonthlyInstallment() {
//   		
//   		Pattern p = Pattern.compile(regexTotalMonthlyInstalment);
//   		Matcher match = p.matcher(TestText);
//   		if (match.find()) {
//
//   			System.out
//   					.println("Total Monthly Instalment from Podium                     " + match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", ""));
//   			totalMonthlyInstalmentValueFromPodium=match.group(0).split(":")[0].replaceAll("^(\\w)+\\s", "");
//   			totalMonthlyInstalmentValueFromPodium=totalMonthlyInstalmentValueFromPodium.replace(",", "");
//   			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//   		}}
//     
//     public void zeroExcess() {
//  		
//  		Pattern p = Pattern.compile(regexZeroExcess);
//  		Matcher match = p.matcher(TestText);
//  		if (match.find()) {
//
//  			zero =match.group(0).split(":")[0];
//  			
//  			zeroExcess=checkCoverAcceptOrDecline(zero);
//  			System.out.println("Zero Excess                       "+zeroExcess);
//  			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//
//  			}
//  		}
//     
//     public void businessProtect() {
//   		
//   		Pattern p = Pattern.compile(regexBusinessProtct);
//   		Matcher match = p.matcher(TestText);
//   		if (match.find()) {
//
//   			bp =match.group(0).split(":")[0];
//   			
//   			businessProtct=checkCoverAcceptOrDecline(bp);
//   			System.out.println("Business Protct                      "+businessProtct);
//   			System.out.println(
// 					"---------------------------------------------------------------------------------------------------------------");
//
//   			}
//   		}
//     
//     public void personalAccident() {
//    		
//    		Pattern p = Pattern.compile(regexPersonalAccident);
//    		Matcher match = p.matcher(TestText);
//    		if (match.find()) {
//
//    			pa =match.group(0).split(":")[0];
//    			
//    			personalAccident=checkCoverAcceptOrDecline(pa);
//    			System.out.println("Personal Accident                      "+personalAccident);
//    			System.out.println(
//  					"---------------------------------------------------------------------------------------------------------------");
//
//    			}
//    		}
//     
//     public void depositSure() {
// 		
// 		Pattern p = Pattern.compile(regexDepositSure);
// 		Matcher match = p.matcher(TestText);
// 		if (match.find()) {
//
// 			ds =match.group(0).split(":")[0];
// 			
// 			depositSure=checkCoverAcceptOrDecline(ds);
// 			System.out.println("Deposit Sure                      "+depositSure);
// 			System.out.println(
//					"---------------------------------------------------------------------------------------------------------------");
//
// 			}
// 		}
//     
//    
//     
//     public void totalMonthlyInstalmentCalculations() {
//    	covers=coverAddition(Double.parseDouble(zeroExcess),Double.parseDouble(businessProtct),Double.parseDouble(personalAccident),Double.parseDouble(depositSure));
//    	double vehicleInstallmentPodium= Double.parseDouble(vehicleInstallment);
//    	
//    	double comprehensiveInsurancePodium= Double.parseDouble(comprehensiveMotorInsurrance);
//    	double trackingDevicePodium=Double.parseDouble(trackingDevice);
//    	double creditLifePodium=Double.parseDouble(creditLife);
//    	double monthlyServiceFeePodium= Double.parseDouble(monthlyServiceFee);
//    	double trackingDeviceProrataPodium=Double.parseDouble(TrackingDeviceProrata);
//    	double creditLifeProrataPodium=Double.parseDouble(CreditLifeProrata);
//    	
//    	
//    	double totalMonthlyInstallmentCals=vehicleInstallmentPodium+comprehensiveInsurancePodium+covers+trackingDevicePodium
//    			                   +creditLifePodium+monthlyServiceFeePodium+trackingDeviceProrataPodium+creditLifeProrataPodium;
//    	
//    	DecimalFormat df = new DecimalFormat("0.00");
//		df.setMaximumFractionDigits(2);
//		totalMonthlyInstallmentvalueFromCalculations = df.format(totalMonthlyInstallmentCals);
//    	
//    	
//    //	System.out.println("by podium"+totalMonthlyInstalmentValueFromPodium);
//   //.out.println("by cals"+totalMonthlyInstallmentvalueFromCalculations);
//
//    	
//    	 if(totalMonthlyInstalmentValueFromPodium.contains(totalMonthlyInstallmentvalueFromCalculations)) {
//    		 
//    		 System.out.println("Total Monthly Installment Value From Podium:      " +totalMonthlyInstalmentValueFromPodium);
//    		 System.out.println("Total Monthly Installment Value by calculations:  "+ totalMonthlyInstallmentvalueFromCalculations);
//    		 System.out.println("Total Monthly Installment Value Matched");
//    		 System.out.println(
// 					"---------------------------------------------------------------------------------------------------------------");
//    		 
//    	 }
//     }
//     
     
    
}
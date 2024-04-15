//package SourceCode;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.apache.poi.ss.formula.functions.Finance;
//
//import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
//import com.gargoylesoftware.htmlunit.javascript.host.dom.Node;
//import com.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;
//
//public class BlazeCalculations 
//{
//
//	public void CalculateInstallment(String responseFilePath) throws Exception
//	{
//		
//			Finance finance = new Finance();
//		
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document document = dBuilder.parse(new File(responseFilePath));
//			
//			NodeList envelopeList = document.getElementsByTagName("S:Envelope");
//		    Node envelope = (Node) envelopeList.item(0);
//		    
//	        NodeList bodyList = ((Element) envelope).getElementsByTagName("S:Body");
//	        Node body = bodyList.item(0);
//	        
//	        NodeList responseList = ((Element) body).getElementsByTagName("ns4:determineCreditDecisionDecisionsResponse");
//	        Node responseTag = responseList.item(0);
//	        
//	        NodeList returnList = ((Element) responseTag).getElementsByTagName("return");
//	        Node returnTag = returnList.item(0);
//	        
//	        NodeList inputList = ((Element) returnTag).getElementsByTagName("inputData");
//	        Node inputData = inputList.item(0);
//	        
//	        NodeList productList = ((Element) inputData).getElementsByTagName("product");
//	        Node product = productList.item(0);
//	        
//	        
//	        NodeList vehicleList = ((Element) product).getElementsByTagName("vehicle");
//	        Node vehicle = vehicleList.item(0);
//	        
//	        NodeList insuranceList = ((Element) product).getElementsByTagName("insurance");
//	        Node insurance = insuranceList.item(0);
//	        
//	        NodeList applicantList = ((Element) inputData).getElementsByTagName("applicant");
//	        Node applicant = applicantList.item(0);
//	        NodeList openApplicationsList = ((Element) applicant).getElementsByTagName("openApplications");
//	        Node openApplications = openApplicationsList.item(0);
//	        
//	        
//	        double vehiclePrice = GetTagValue(vehicle, "vehiclePrice");
//	        double vatableExtras = GetTagValue(vehicle, "vatableExtras");
//	        double nonVatableExtras = GetTagValue(vehicle, "nonVatableExtras");
//	        double discount = GetTagValue(vehicle, "discount");
//	        double totalTradeIn = GetTagValue(vehicle, "totalTradeIn");
//	        double isFinancetrackUnit = GetTagValue(vehicle, "isFinancetrackUnit");
//	        double isInitiationFeeFinanced = GetTagValue(vehicle, "isInitiationFeeFinanced");
//	        double carTrackHardwareCost = GetTagValue(vehicle, "carTrackHardwareCost");
//	        double initiationFees = GetTagValue(vehicle, "initiationFees");
//	        double carTrackSoftwareMonthlyCost = GetTagValue(vehicle, "carTrackSoftwareMonthlyCost");
//	        double monthlyAdminFees = GetTagValue(vehicle, "monthlyAdminFees");
//	       
//	        
//	        double isCreditLife = GetTagValue(insurance, "isCreditLife");
//	        double insurancePremium = GetTagValue(insurance, "insurancePremium");
//	        double windScreen = GetTagValue(insurance, "windScreen");
//	        double SASRIA = GetTagValue(insurance, "SASRIA");
//	        double windScreenSASRIA = windScreen + SASRIA;
//	        double insuranceProRata = GetTagValue(insurance, "insuranceProRata");
//	        double totalInsuranceAddonAmount = GetTagValue(insurance, "totalInsuranceAddonAmount");
//	        double CL_MonthlyPremium = GetTagValue(insurance, "CL_MonthlyPremium");
//	        double CL_ProRata = GetTagValue(insurance, "CL_ProRata");
//	       
//	        
//	        NodeList outputList = ((Element) returnTag).getElementsByTagName("outputData");
//	        Node outputData = outputList.item(0);
//	        
//	        NodeList productOfferList = ((Element) outputData).getElementsByTagName("productOffer");
//	        Node productOffer = productOfferList.item(0);
//	        
//	        double prorataCarTrackFee = GetTagValue(productOffer, "prorataCarTrackFee");
//	        double depositAmount = GetTagValue(productOffer, "depositAmount");
//	        double principalDebt = GetTagValue(productOffer, "principalDebt");
//	        double monthlyPrincipleDebtInstalment = GetTagValue(productOffer, "monthlyPrincipleDebtInstalment");
//	        double monthlyProrataCarTrackSoftwareFee = GetTagValue(productOffer, "monthlyProrataCarTrackSoftwareFee");
//	        double MonthlyInsuranceProrata = GetTagValue(productOffer, "MonthlyInsuranceProrata");
//	        double monthlyCreditLifeProrata = GetTagValue(productOffer, "monthlyCreditLifeProrata");
//	        double firstMonthCostOfInsuranceAndCreditLifeProrata = GetTagValue(productOffer, "firstMonthCostOfInsuranceAndCreditLifeProrata");
//	        double monthlyTotalInstalmentAmount = GetTagValue(productOffer, "monthlyTotalInstalmentAmount");
//	        double totalPurchasePrice = GetTagValue(productOffer, "totalPurchasePrice");
//	        double vehiclePricePresentValue = GetTagValue(productOffer, "vehiclePricePresentValue");
//	        double vehiclePricePresentValuePMT = GetTagValue(productOffer, "vehiclePricePresentValuePMT");
//	        double monthlyCarTrackInstalment = GetTagValue(productOffer, "monthlyCarTrackInstalment");
//	        double totalTrackingDeviceInstalmentPerMonth = GetTagValue(productOffer, "totalTrackingDeviceInstalmentPerMonth");
//	        double monthlyInsuranceInstalment = GetTagValue(productOffer, "monthlyInsuranceInstalment");
//	        double initiationFeesInstalment = GetTagValue(productOffer, "initiationFeesInstalment");
//	        double monthlyFinanceInstalment = GetTagValue(productOffer, "monthlyFinanceInstalment");
//	        double rate = GetTagValue(productOffer, "rate");
//	        double term = GetTagValue(productOffer, "term");
//	        
//	        int totalTerm = (int) term;
//	        
//	        double purchasePrice = (vehiclePrice + vatableExtras + nonVatableExtras) - (discount + totalTradeIn);
//	        
//	        double calculatedPrincipleDebt = (vehiclePrice + vatableExtras + nonVatableExtras + carTrackHardwareCost + initiationFees) - (discount + totalTradeIn + depositAmount);
//	        String PrincipleDebtResult = isEqual(calculatedPrincipleDebt, principalDebt);
//	        
//	        double calculatedMonthlyProrataCarTrackSoftwareFee = prorataCarTrackFee/term;
//	        String MonthlyProrataCarTrackSoftwareFeeResult = isEqual(calculatedMonthlyProrataCarTrackSoftwareFee, monthlyProrataCarTrackSoftwareFee);
//	        
//	        double calculatedMonthlyPrincipleDebtInstalment = (finance.pmt((rate/12), totalTerm, calculatedPrincipleDebt, 0, 0)) *(-1);
//	        String MonthlyPrincipleDebtInstalmentResult = isEqual(monthlyPrincipleDebtInstalment, calculatedMonthlyPrincipleDebtInstalment);
//	        
//	        double calculatedMonthlyInsuranceProrata  = insuranceProRata/term;
//	        String MonthlyInsuranceProrataResult = isEqual(calculatedMonthlyInsuranceProrata, MonthlyInsuranceProrata);
//	        
//	        double calculatedMonthlyCreditLifeProrata = CL_ProRata/term;
//	        String MonthlyCreditLifeProrataResult = isEqual(calculatedMonthlyCreditLifeProrata, monthlyCreditLifeProrata);
//	        
//	        double calculatedFirstMonthCostOfInsuranceAndCreditLifeProrata = calculatedMonthlyInsuranceProrata + calculatedMonthlyCreditLifeProrata;
//	        String FirstMonthCostOfInsuranceAndCreditLifeProrataResult = isEqual(calculatedFirstMonthCostOfInsuranceAndCreditLifeProrata, firstMonthCostOfInsuranceAndCreditLifeProrata);
//	        
//	        double calculatedMonthlyTotalInstalmentAmount =  (calculatedMonthlyPrincipleDebtInstalment + insurancePremium + windScreen + SASRIA + totalInsuranceAddonAmount + carTrackSoftwareMonthlyCost + CL_MonthlyPremium + monthlyAdminFees + calculatedMonthlyProrataCarTrackSoftwareFee + calculatedFirstMonthCostOfInsuranceAndCreditLifeProrata); 
//	        String MonthlyTotalInstalmentAmountResult = isEqual(calculatedMonthlyTotalInstalmentAmount, monthlyTotalInstalmentAmount);
//	        
//	        double calculatedVehiclePricePresentValue = purchasePrice - depositAmount;
//	        String VehiclePricePresentValueResult = isEqual(calculatedVehiclePricePresentValue, vehiclePricePresentValue);
//	        
//	        double calculatedVehiclePricePresentValuePMT = (finance.pmt((rate/12), totalTerm, calculatedVehiclePricePresentValue)) * (-1);
//	        String VehiclePricePresentValuePMTResult = isEqual(calculatedVehiclePricePresentValuePMT, vehiclePricePresentValuePMT);
//	        
//	        double calculatedMonthlyCarTrackInstalment = (finance.pmt((rate/12), totalTerm, carTrackHardwareCost)) *(-1);
//	        String MonthlyCarTrackInstalmentResult = isEqual(calculatedMonthlyCarTrackInstalment, monthlyCarTrackInstalment);
//	        
//	        double calculatedTotalTrackingDeviceInstalmentPerMonth = calculateTotalTrackingDeviceInstalmentPerMonth(isFinancetrackUnit, calculatedMonthlyCarTrackInstalment, carTrackSoftwareMonthlyCost, calculatedMonthlyProrataCarTrackSoftwareFee);
//	        String TotalTrackingDeviceInstalmentPerMonthResult = isEqual(calculatedTotalTrackingDeviceInstalmentPerMonth, totalTrackingDeviceInstalmentPerMonth);
//	        
//	        double crdeitLifeValue = isCreditLifeTrue(isCreditLife, CL_MonthlyPremium, calculatedMonthlyCreditLifeProrata);
//	        double calculatedMonthlyInsuranceInstalment = insurancePremium + windScreen + SASRIA + calculatedMonthlyInsuranceProrata + crdeitLifeValue + totalInsuranceAddonAmount;
//	        String MonthlyInsuranceInstalmentResult = isEqual(calculatedMonthlyInsuranceInstalment, monthlyInsuranceInstalment);	        
//	        
//	        double calculatedInitiationFeesInstalment = (finance.pmt((rate/12), totalTerm, initiationFees)) * (-1);
//	        String InitiationFeesInstalmentResult = isEqual(calculatedInitiationFeesInstalment, initiationFeesInstalment);	        
//	        
//	        double calculatedMonthlyFinanceInstalment = calculatedMonthlyPrincipleDebtInstalment + carTrackSoftwareMonthlyCost + calculatedMonthlyProrataCarTrackSoftwareFee + monthlyAdminFees + calculatedFirstMonthCostOfInsuranceAndCreditLifeProrata;
//	        String MonthlyFinanceInstalmentResult = isEqual(calculatedMonthlyFinanceInstalment, monthlyFinanceInstalment);
//	        
//	        
//	        double monthlyTotalInstalmentAmount2 = calculatedMonthlyFinanceInstalment + insurancePremium + windScreen + SASRIA + CL_MonthlyPremium + totalInsuranceAddonAmount;
//	        String TotalInstalmentAmount2Result = isEqual(monthlyTotalInstalmentAmount2, monthlyTotalInstalmentAmount);
//	        
//	        
//	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
//	        System.out.printf("%5s %60s %26s %25s", "Field Name", "Actual Value", "Calculated Value", "Are Values Matching");
//	        System.out.println();
//	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
//	        System.out.printf("%5s %60s %25s %20s", "PrincipleDebt", principalDebt, calculatedPrincipleDebt, PrincipleDebtResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %40s %25s %20s", "MonthlyProrataCarTrackSoftwareFee", monthlyProrataCarTrackSoftwareFee, calculatedMonthlyProrataCarTrackSoftwareFee, MonthlyProrataCarTrackSoftwareFeeResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %43s %25s %20s", "MonthlyPrincipleDebtInstalment", monthlyPrincipleDebtInstalment, calculatedMonthlyPrincipleDebtInstalment, MonthlyPrincipleDebtInstalmentResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %50s %25s %20s", "MonthlyInsuranceProrata", MonthlyInsuranceProrata, calculatedMonthlyInsuranceProrata, MonthlyInsuranceProrataResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %49s %25s %20s", "MonthlyCreditLifeProrata", monthlyCreditLifeProrata, calculatedMonthlyCreditLifeProrata, MonthlyCreditLifeProrataResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %28s %25s %20s", "FirstMonthCostOfInsuranceAndCreditLifeProrata", firstMonthCostOfInsuranceAndCreditLifeProrata, calculatedFirstMonthCostOfInsuranceAndCreditLifeProrata, FirstMonthCostOfInsuranceAndCreditLifeProrataResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %45s %25s %20s", "MonthlyTotalInstalmentAmount", monthlyTotalInstalmentAmount, calculatedMonthlyTotalInstalmentAmount, MonthlyTotalInstalmentAmountResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %49s %25s %20s", "VehiclePricePresentValue", vehiclePricePresentValue, calculatedVehiclePricePresentValue, VehiclePricePresentValueResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %46s %25s %20s", "VehiclePricePresentValuePMT", vehiclePricePresentValuePMT, calculatedVehiclePricePresentValuePMT, VehiclePricePresentValuePMTResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %48s %25s %20s", "MonthlyCarTrackInstalment", monthlyCarTrackInstalment, calculatedMonthlyCarTrackInstalment, MonthlyCarTrackInstalmentResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %36s %25s %20s", "TotalTrackingDeviceInstalmentPerMonth", totalTrackingDeviceInstalmentPerMonth, calculatedTotalTrackingDeviceInstalmentPerMonth, TotalTrackingDeviceInstalmentPerMonthResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %47s %25s %20s", "MonthlyInsuranceInstalment", monthlyInsuranceInstalment, calculatedMonthlyInsuranceInstalment, MonthlyInsuranceInstalmentResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %49s %25s %20s", "InitiationFeesInstalment", initiationFeesInstalment, calculatedInitiationFeesInstalment, InitiationFeesInstalmentResult);
//	        System.out.println();
//	        
//	        System.out.printf("%5s %49s %25s %20s", "MonthlyFinanceInstalment", monthlyFinanceInstalment, calculatedMonthlyFinanceInstalment, MonthlyFinanceInstalmentResult);
//	        System.out.println();
//
//	        System.out.printf("%5s %52s %25s %20s", "TotalInstalmentAmount", monthlyTotalInstalmentAmount, monthlyTotalInstalmentAmount2, TotalInstalmentAmount2Result);
//	        System.out.println();
//	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
//	        
//	}
//	
//	public double GetTagValue(Node node, String tagName)
//	{
//		NodeList nodeList = ((Document) node).getElementsByTagName(tagName);
//        Node nodeTag = (Node) nodeList.item(0);
//        String nodeStr = nodeTag.getTextContent();
//        double nodeValue = Double.parseDouble(nodeStr);
//        return nodeValue;
//	}
//	
//	public double calculateTotalTrackingDeviceInstalmentPerMonth(double isFinancetrackUnit, double monthlyCarTrackInstalment, double carTrackSoftwareMonthlyCost, double monthlyProrataCarTrackSoftwareFee)
//	{
//		if (isFinancetrackUnit == 1)
//		{
//			double calculatedTotalTrackingDeviceInstalmentPerMonth = monthlyCarTrackInstalment + carTrackSoftwareMonthlyCost + monthlyProrataCarTrackSoftwareFee;
//			return calculatedTotalTrackingDeviceInstalmentPerMonth;
//		}
//		else
//		{
//			double calculatedTotalTrackingDeviceInstalmentPerMonth = carTrackSoftwareMonthlyCost + monthlyProrataCarTrackSoftwareFee;
//			return calculatedTotalTrackingDeviceInstalmentPerMonth;
//		}
//	}
//	
//	public double isCreditLifeTrue(double isCreditLife, double CL_MonthlyPremium, double MonthlyCreditLifeProrata)
//	{
//		if(isCreditLife == 1)
//		{
//			double creditLifeValue = CL_MonthlyPremium + MonthlyCreditLifeProrata;
//			return creditLifeValue;
//		}
//		else
//		{
//			return 0;
//		}
//	}
//	
//	public String isEqual(double value1, double value2)
//	{
//		if(Math.abs(value1 - value2) <= (0.0001))
//		{
//			return "YES";
//		}
//		else
//		{
//			return "NO";
//		}
//		
//	}
//	
//}

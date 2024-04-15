package Tests;

import java.io.IOException;
import java.text.ParseException;

import org.dom4j.DocumentException;
import org.testng.annotations.*;

import Pages.Podium;

public class PodiumRead {
	Podium podium = new Podium();
	
	@BeforeTest
	public void readprocess() throws Exception {
		podium.readBlazeRequest();
		podium.readBlazeResponse();
		podium.convertPDFToText();
	}
	
	@Test(priority=1)
	public void vehicleprice() throws DocumentException, IOException {
        podium.matchVehicleModel();
	}
	
	@Test(priority=2)
	public void totalfinaceamount() throws ParseException {
        podium.matchtotalfinancedamount();
	}
	
	@Test(priority = 3)
	public void Term() {
		podium.matchTerm();
	}
	
	@Test(priority = 4)
	public void Deposit() {
		podium.matchDeposit();
	}
	
	@Test(priority = 5)
	public void Extras() {
		podium.matchExtras();
	}
	
//	@Test(priority = 6)
//	public void approvedDeposit() {
//		podium.matchApprovedDeposit();;
//	}
//	
//	@Test(priority = 7)
//	public void approvedIntRate() {
//		podium.matchApprovedIntRate();
//	}
//	
//	@Test(priority = 8)
//	public void requestedDeposit() {
//		podium.matchRequestedDeposit();
//	}	
//	
//
//	@Test(priority = 9)
//	public void requestedIntRate() {
//		podium.matchRequestedIntRate();
//	}	
//		
//	@Test(priority = 10)
//	public void principalDebt() {
//		podium.matchPricipalDebt();
//	}
//	
//	@Test(priority = 11)
//	public void vehicleInstallment() {
//		podium.matchVehicleInstallment();
//	}
//	
//
//	@Test(priority = 12)
//	public void comprehensiveInsurance() {
//		podium.matchComprehensiveInsurance();
//	}
//		
//	@Test(priority = 13)
//	public void trackingDevice() {
//		podium.matchTrackingDevice();
//	}
//	
//	@Test(priority = 14)
//	public void creditLife() {
//		podium.matchCreditLife();
//	}
//
//	@Test(priority = 15)
//	public void monthlyServiceFee() {
//		podium.matchMonthlyServiceFree();
//	}
//
//	@Test(priority = 16)
//	public void trackingDeviceProrate() {
//		podium.trackingDeviceProrata();
//	}
//	
//	@Test(priority = 17)
//	public void creditLifeProrate() {
//		podium.creditLifeProrata();
//	}
//	
//	@Test(priority = 18)
//	public void totalMonthlyInstalmentValueFromPodium() {
//		podium.totalMonthlyInstallment();
//	}
//	
//	@Test(priority = 19)
//	public void peroid() {
//		podium.matchPeriod();
//	}
//	
//	@Test(priority = 20)
//	public void TestZeroCover() {
//		podium.zeroExcess();
//	}
//	
//	@Test(priority = 21)
//	public void TestBusinessProtect() {
//		podium.businessProtect();
//	}
//	
//	@Test(priority = 22)
//	public void TestPersonalAccident() {
//		podium.personalAccident();
//	}
//	
//	@Test(priority = 23)
//	public void TestDepositSure() {
//		podium.depositSure();
//	}
//	
//
//	@Test(priority = 24)
//	public void totalMonthlyInstalmentValueByCalculations() {
//		podium.totalMonthlyInstalmentCalculations();
//	}
	
}

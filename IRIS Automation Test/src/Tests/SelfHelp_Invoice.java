package Tests;

import static org.testng.Assert.assertTrue;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.InvoiceStage;
import Pages.SecondCheckerScreen;
import Pages.SignioHomePage;
import Pages.SignioLoginPage;
import Pages.SignioNewApplicationPage;

public class SelfHelp_Invoice extends BasicSetup {
	
	public static WebDriver driver;
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	InvoiceStage invoiceStage = new InvoiceStage();
	SignioLoginPage loginPageObject = new SignioLoginPage();
	SignioHomePage homePageObject = new SignioHomePage();
	SignioNewApplicationPage newApplication = new SignioNewApplicationPage();
	int valscheck;
	String frauddeclinemsg = "Applicant has been flagged for being on the sanctions list. Incident must be referred for fraud decline.";
	String contractgenerated = "Contract documents are available for signature.";
	String validatebankdetails = "Bank Verification failed.";
	String overridearrears = "Unable to generate contract for new/updated values; One or more of the applicant's active Gomo Accounts are in arrears with amount exceeding allowed value.";
	String duplicateasset_Acquire = "Duplicate asset found in Acquire";
	String duplicateasset_IRIS = "duplicate asset has been found for the Incident Number";
	static String EngineNumberOfVehicle = "67223";
	static String ValidationLink_LinkTest = "Validation";
	static String InvoiceLink_LinkTest = "Invoice";
	String InvoiceText_ID = "rndPnlInvoice_RPHT";
	String InvoiceNumberBox_ID = "txtInvoiceNumber";
	String DepositPaidDD_ID = "cmbDepositPaidTo_B-1";
	String EngineNumber_ID = "txtInvoiceEngineNumber";
	String VINNumber_ID = "txtInvoiceVIN";
	String VehicleTypeBanner_Xpath = "//*[@id='divCustomerAccountDetails']/table/tbody/tr[2]/td[8]";
	String eNatisNumber_ID = "txtInvoiceEnatis";
	String SaveInvoice_ID = "btnSaveInvoice_CD";
	String Confirmpopup_ID = "confirmText";
	String ConfirmPopupYES_BTN_ID = "btnConfirmOK_CD";
	String Informationpopup = "customMessageText";
	String GenerateContractBTN_ID = "btnGenerateContract_CD";
	String InformationpopupOKBTN_ID = "btnCustomMessageClose_CD";
	String DDOption_Supplier_ID = "cmbDepositPaidTo_DDD_L_LBI1T0";
	String DocumentValidationTab_ID = "rndPnlDocumentValidation_RPHT";
	String ProceedIncident_ID = "btnProceedIncident_CD";
	String InformationpopupOK = "btnCustomMessageClose_CD";
	SignioNewApplicationPage signioNewApplicationPage = new SignioNewApplicationPage();

	SecondCheckerScreen secondCheckerScreen = new SecondCheckerScreen();

	@BeforeClass(enabled = true)
	public void setup() throws InterruptedException, IOException {

		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}

	@BeforeTest(enabled = true)
	public void login() throws InterruptedException, IOException {

		InvoiceScreen invoiceScreen = new InvoiceScreen();
	}
	
	public SelfHelp_Invoice() {
		driver = getdriver();
		// TODO Auto-generated constructor stub
	}
	
	@Test(priority = 1)
	public void SelfHelp() throws InterruptedException, IOException, UnsupportedFlavorException {
		
		WebDriverWait wait1 = new WebDriverWait(driver, 200);
		WebDriverWait wait2 = new WebDriverWait(driver, 200);

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		int flag[] = iRISHomePage.checkDealinMyDeals(driver);

		if (flag[1] == 0) {

		iRISHomePage.clickOnAllIncidentInbox(driver);
		
		iRISHomePage.searchDealthroughIncidentNumber(driver);
				
		iRISHomePage.clickOnIncidentLink(driver);
		
		valscheck=invoiceStage.checkvalsnote(driver);
		}

		else {
			System.out.println("Incident is present in My Deals");
			iRISHomePage.clickOnMyDeals(driver);
			valscheck=invoiceStage.checkvalsnote(driver);
			Thread.sleep(10000);
		}
		
		if(valscheck==1) {
			String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

			String incidentnumber = signioNewApplicationPage.readDataFromFile(path1, driver);
			loginPageObject.navigateToSignio(driver);
			loginPageObject.loginToSignio(driver);
			homePageObject.searchDealWithReferenceNumber(driver, incidentnumber);
			Thread.sleep(10000);
			String oldTab = driver.getWindowHandle();
			driver.findElement(By.xpath("//*[contains(@data-original-title,'"+incidentnumber+"')]")).click();
			Thread.sleep(8000);
			
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			newTab.remove(oldTab);
			// change focus to new tab
			driver.switchTo().window(newTab.get(0));
			Thread.sleep(10000);
			//invoiceStage.enterinvoiceinformation(driver);
			Thread.sleep(20000);
			
			driver.close();
			
			driver.switchTo().window(oldTab);
			
			Thread.sleep(25000);
			
			//refresh this page to check message from GOMO
			driver.navigate().refresh();
			
			homePageObject.searchDealWithReferenceNumber(driver, incidentnumber);
			
			Thread.sleep(10000);
			String oldTab1 = driver.getWindowHandle();
			driver.findElement(By.xpath("//*[contains(@data-original-title,'"+incidentnumber+"')]")).click();
			Thread.sleep(15000);
			
			ArrayList<String> newTab1 = new ArrayList<String>(driver.getWindowHandles());
			newTab1.remove(oldTab1);
			// change focus to new tab
			driver.switchTo().window(newTab1.get(0));
			Thread.sleep(20000);
			
			driver.navigate().refresh();
			
			//check message from GOMO
			WebElement messagefromGOMO=driver.findElement(By.xpath("//*[@id=\"podiumMessage_panel\"]/div/div[2]/label"));
			Thread.sleep(5000);
			String messagetext=messagefromGOMO.getText();
			Thread.sleep(5000);
			System.out.println("Message is:"+messagetext);
			if(messagetext.contains(frauddeclinemsg)||messagetext.contains(validatebankdetails)||messagetext.contains(overridearrears)||messagetext.contains(duplicateasset_IRIS)||messagetext.contains(duplicateasset_Acquire)||messagetext.contains(contractgenerated)) {
				
				iRISLoginPage.NavigateToOptima(driver);
				Thread.sleep(5000);
				//iRISLoginPage.logoutFromOptima(driver);
				iRISLoginPage.LoginToOptima(driver);
				
				Thread.sleep(7000);
				
				WebElement mydealBox = driver.findElement(By.id("nbClientSideEvents_I0i0_"));
				Thread.sleep(2000);
				mydealBox.click();

				Thread.sleep(5000);
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("grdInbox_grouppanel")));
				Thread.sleep(7000);
				
				int flag1[] = iRISHomePage.checkDealinMyDeals(driver);

				if (flag1[1] == 0) {

				iRISHomePage.clickOnAllIncidentInbox(driver);
				
				iRISHomePage.searchDealthroughIncidentNumber(driver);
						
				iRISHomePage.clickOnIncidentLink(driver);

				Thread.sleep(10000);

				if(flag[0]==0) {
					
					WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
					Thread.sleep(8000);
					retriveDealbtn.click();

					WebElement okbtn = driver.findElement(By.id(InformationpopupOK));
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));

					okbtn.click(); 
					
				}
				
				else {
					
					iRISHomePage.retriveDeal(driver);
					
				}
			Thread.sleep(8000);
				}

				else {
					System.out.println("Incident is present in My Deals");
				}

				iRISHomePage.clickOnMyDeals(driver);

				//WebDriverWait wait1 = new WebDriverWait(driver, 200);

				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("spnIncidentNum")));

				
			}

			invoiceStage.errorMessage(driver,messagetext,1);
			
			invoiceStage.overridecheck(driver,0);
			
			// document validation message for dealer invoice

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

			assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(),
					"Document Validation Pending for Dealer Invoice");

			driver.findElement(By.id("btnCustomMessageClose")).click();

	// go to validation stage

			driver.findElement(By.linkText(ValidationLink_LinkTest)).click(); // Click on Validation Link.

			driver.findElement(By.id("btnConfirmNo1_CD")).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab_ID)));

	// click on validation link for invoice doc

			invoiceStage.validateDealerInvoive(driver);
			Thread.sleep(2000);

	//check creditlife company			
//			if (company.equals("Guardrisk"))
//			{
//				System.out.print("CreditLife Document not required");
//			}
//			
//			else if(company.equals("--Select--"))
//			{
//				System.out.println("Doc not required");
//			}
//			
//			else
//			{
//				System.out.print("CreditLife Document required");
//				invoiceStage.uploadAndValidateCreditLifePolicy(driver);
//				Thread.sleep(2000);
//			}

//			invoiceStage.uploadAndValidateInsurancePolicy(driver);
//			Thread.sleep(2000);
	//
//			invoiceStage.uploadAndValidateDICDocument(driver);
//			Thread.sleep(2000);

	// click on invoice stage
			Thread.sleep(2000);

			driver.findElement(By.linkText(InvoiceLink_LinkTest)).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceText_ID)));

	// click on Generate contract

			WebElement genarateContract = driver.findElement(By.id(GenerateContractBTN_ID));
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(genarateContract));
			genarateContract.click();

	// success messeage

			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
			String message1=driver.findElement(By.id(Informationpopup)).getText();
			invoiceStage.errorMessage(driver,message1,0);

	// click on ok button

			driver.findElement(By.id("btnCustomMessageClose")).click();

	// wait until invoice screen load

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceText_ID)));

	//// click on book cartrack
	//
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id("btnBookCarTrackingDevice_CD")));
	//
//			driver.findElement(By.id("btnBookCarTrackingDevice_CD")).click();
	//
	//// confirmation message
	//
//			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
	//
//			assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(),
//					"Tracking Device booked successfully.");
	//
	//// click on ok button
	//
//			driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

	// wait until proceed button is not clickable and then click

			wait2.until(ExpectedConditions.elementToBeClickable(By.id(ProceedIncident_ID)));

			driver.findElement(By.id(ProceedIncident_ID)).click();

	// confirmation message

			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Confirmpopup_ID)));

			assertTrue(driver.findElement(By.id(Confirmpopup_ID)).isDisplayed(),
					"Are you sure you want to proceed to the next step?");

	// click on yes button

			driver.findElement(By.id(ConfirmPopupYES_BTN_ID)).click();

	// confirmation message

			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

			assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(), "Incident navigated successfully.");

	// click on ok button

			driver.findElement(By.id(InformationpopupOKBTN_ID)).click();
		}
	}

		public static String ReadInvoiceValues(String key) throws IOException {
			InputStream input = new FileInputStream("src/TestData/InvoiceValues.properties");
			Properties prop = new Properties();
			prop.load(input);
			String value = prop.getProperty(key);
			return value;
		}
			
			

}

package Tests;

import static org.testng.Assert.assertTrue;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Pages.InvoiceStage;
import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.SecondCheckerScreen;
import Pages.Validation;

//Pre requisite- deal should be in your mydeal inbox

public class InvoiceScreen extends BasicSetup {

	public static WebDriver driver;

	int flag_insurance = 0;
	int flag_finance = 0;
	static String Browse_finance = "fileUploadFinancialProItem_Browse0";
	String invoiceNumber = "90";
	static String EngineNumberOfVehicle = "67223";
	static String ValidationLink_LinkTest = "Validation";
	static String InvoiceLink_LinkTest = "Invoice";
	Validation validation = new Validation();
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

	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	InvoiceStage invoiceStage = new InvoiceStage();
	

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

	public InvoiceScreen() {
		driver = getdriver();
		// TODO Auto-generated constructor stub
	}

	@Test(priority = 7)
	public void invoiceScreen() throws InterruptedException, IOException, UnsupportedFlavorException {

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		int flag[] = iRISHomePage.checkDealinMyDeals(driver);

		if (flag[1] == 0) {

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

		WebDriverWait wait1 = new WebDriverWait(driver, 200);

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceText_ID)));

		WebElement InvoiceNumber = driver.findElement(By.id(InvoiceNumberBox_ID));
		InvoiceNumber.clear();
		Thread.sleep(5000);
		String InvNum = InvoiceScreen.ReadInvoiceValues("InvoiceNum"); // Read Invoice number from Property file
		InvoiceNumber.sendKeys(InvNum);
		Thread.sleep(2000);

		String DeposiPaidTo = InvoiceScreen.ReadInvoiceValues("DeposiPaidTo");
		Thread.sleep(2000);
		driver.findElement(By.id(DepositPaidDD_ID)).click(); // Click on dropdown
		
		Thread.sleep(2000);

		if (DeposiPaidTo.equalsIgnoreCase("Supplier")) {

			driver.findElement(By.id(DDOption_Supplier_ID)).click();

		}

		else {

			driver.findElement(By.xpath("//*[text()='" + DeposiPaidTo + "']")).click();

		}
		
		Thread.sleep(4000);

		WebElement EngineNumber = driver.findElement(By.id(EngineNumber_ID));
		EngineNumber.clear();
		String EngNum = InvoiceScreen.ReadInvoiceValues("EngineNum"); // Read Engine number from Property file
		EngineNumber.sendKeys(EngNum);
		Thread.sleep(2000);

		WebElement VINNumber = driver.findElement(By.id(VINNumber_ID));
		VINNumber.clear();

		String VINNum = InvoiceScreen.ReadInvoiceValues("VIN"); // Read VIN number from Property file
		VINNumber.sendKeys(VINNum);
		Thread.sleep(2000);
//		// generate VIN random number and send
//		String VINNum = InvoiceStage.getAlphaNumericString();
//		System.out.println("Generated VIN number=" + VINNum);
//		VINNumber.sendKeys(VINNum);

		WebElement vehicleType = driver.findElement(By.xpath(VehicleTypeBanner_Xpath));
		String type = vehicleType.getText();
		System.out.print("\nVehicle type is:" + type + "\n");

		if (type.equals("Pre-Owned")||type.equals("Repo")) {
			WebElement eNatisNumber = driver.findElement(By.id(eNatisNumber_ID));
			eNatisNumber.clear();
			String eNatisNum = InvoiceScreen.ReadInvoiceValues("eNatis"); // Read VIN number from Property file
			eNatisNumber.sendKeys(eNatisNum);
			Thread.sleep(2000);
		}
		
		//exclude initiation fee
		
				if (iRISHomePage.readInputData("excludeinitiationfee").trim().equalsIgnoreCase("Yes")) {

					WebElement isexcludeinitiationfee = driver.findElement(By.id("chkIsExcludeInitiationFee"));

					if (!isexcludeinitiationfee.isSelected()) {

						isexcludeinitiationfee.click();
						System.out.println("\nInitiation fee overridden(excluded) successfully.");
						flag_finance = 1;

					}

					else {

						System.out.println("\nInitiation fee already excluded.");

					}

				}

				// include initiation fee condition

				else if (iRISHomePage.readInputData("excludeinitiationfee").trim().equalsIgnoreCase("No")) {

					WebElement isexcludeinitiationfee = driver.findElement(By.id("chkIsExcludeInitiationFee"));

					if (isexcludeinitiationfee.isSelected()) {

						isexcludeinitiationfee.click();
						System.out.println("\nInitiation fee overridden(included) successfully.");
						flag_finance = 1;
					}

					else {

						System.out.println("\nInitiation fee already included.");

					}
				}

				// initiation fee exclude/include not required
				else {

					System.out.println("\n Initiation fee exclude/ include not required");

				}

// Select Vehicle Tracking company

// Beneficiary Unknown tick

		Thread.sleep(10000);
//		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
//		WebElement creditLifeCompany = driver.findElement(By.id("cmbCreditLifeCompanyName_I"));
//		Thread.sleep(1000);
//		creditLifeCompany.click();
//		Thread.sleep(1000);
//		creditLifeCompany.sendKeys(Keys.CONTROL+"a");
//		creditLifeCompany.sendKeys(copy);
//		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		Transferable contents = clipboard.getContents(null);
//		String company = (String) contents.getTransferData(DataFlavor.stringFlavor);
//		System.out.println("Company Name is:"+company);
//		Thread.sleep(6000);
//
//		if (company.equals("Guardrisk")) {
//			WebElement BeneficiaryCB = driver.findElement(By.id("chkCreditLifeBeneficiaryUnknown"));
//
//			if (BeneficiaryCB.isSelected()) {
//				System.out.print("Checkbox is already checked");
//			}
//
//			else {
//				System.out.print("Checkbox is NOT checked");
//				BeneficiaryCB.click();
//			}
//
//		}

// click on save button

		driver.findElement(By.id(SaveInvoice_ID)).click();

		Thread.sleep(25000);

		if (driver.findElement(By.id(Confirmpopup_ID)).isDisplayed())

		{

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Confirmpopup_ID)));
			
			if (flag_finance == 1) {
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));
				driver.findElement(By.id("btnConfirmOK_CD")).click();
				Thread.sleep(7000);
				driver.findElement(By.id(Browse_finance)).click();
				Thread.sleep(2000);
				Runtime.getRuntime().exec("D:\\Optima\\FileUpload1.exe");

				Thread.sleep(7000);
				driver.findElement(By.id("txtPCExcludeOverrideReason")).sendKeys("Automation Testing.");
				driver.findElement(By.id("btnSaveExcludeOverride")).click();
				Thread.sleep(7000);
				
			}
			
			if(driver.findElement(By.id(Confirmpopup_ID)).getText().contains(
					"Are you sure you want to save the information?")){
						
						// click on yes button
						
						driver.findElement(By.id(ConfirmPopupYES_BTN_ID)).click();
						
					}

		}

//		
		WebDriverWait wait2 = new WebDriverWait(driver, 600);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
		
		String message=driver.findElement(By.id(Informationpopup)).getText();
		invoiceStage.errorMessage(driver,message,0);

		Thread.sleep(30000);

// click on ok button
		if (driver.findElement(By.id(InformationpopupOKBTN_ID)).isDisplayed()) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOKBTN_ID)));

			driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceText_ID)));
		}

		else {
			System.out.println("Generate contract.");
		}

//check override conditions
		
		invoiceStage.overridecheck(driver,0);

// document validation message for dealer invoice

		Thread.sleep(15000);
		
		if(driver.findElement(By.id(Informationpopup)) != null) {
		
		String popupMessage=driver.findElement(By.id(Informationpopup)).getText();
		
		if (popupMessage.contains("Document Validation Pending for Dealer Invoice")) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup:" + message);
			// go to validation stage
			
			driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

			driver.findElement(By.linkText(ValidationLink_LinkTest)).click(); // Click on Validation Link.

			driver.findElement(By.id("btnConfirmNo1_CD")).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab_ID)));

	// click on validation link for invoice doc
			String dealerInvoice = "Dealer Invoice";
			validation.DocsToUpload(driver, dealerInvoice);
			Thread.sleep(2000);
			validation.close_save_popup(driver);
			Thread.sleep(2000);
			validation.ChooseDocumentsToValidate(driver, dealerInvoice);
			Thread.sleep(6000);
		}
		}

		assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(),
				"Document Validation Pending for Dealer Invoice");

		driver.findElement(By.id("btnCustomMessageClose")).click();



		String dealerInvoice = "Dealer Invoice";
		validation.DocsToUpload(driver, dealerInvoice);
		Thread.sleep(2000);
		validation.close_save_popup(driver);
		Thread.sleep(2000);
		validation.ChooseDocumentsToValidate(driver, dealerInvoice);

//check creditlife company			
//		if (company.equals("Guardrisk"))
//		{
//			System.out.print("CreditLife Document not required");
//		}
//		
//		else if(company.equals("--Select--"))
//		{
//			System.out.println("Doc not required");
//		}
//		
//		else
//		{
//			System.out.print("CreditLife Document required");
//			invoiceStage.uploadAndValidateCreditLifePolicy(driver);
//			Thread.sleep(2000);
//		}

//		invoiceStage.uploadAndValidateInsurancePolicy(driver);
//		Thread.sleep(2000);
//
//		invoiceStage.uploadAndValidateDICDocument(driver);
//		Thread.sleep(2000);

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
//		new WebDriverWait(driver, 30)
//				.until(ExpectedConditions.elementToBeClickable(By.id("btnBookCarTrackingDevice_CD")));
//
//		driver.findElement(By.id("btnBookCarTrackingDevice_CD")).click();
//
//// confirmation message
//
//		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
//
//		assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(),
//				"Tracking Device booked successfully.");
//
//// click on ok button
//
//		driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

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

	public static String ReadInvoiceValues(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/InvoiceValues.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

}
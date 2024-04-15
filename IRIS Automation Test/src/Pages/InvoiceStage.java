package Pages;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import Tests.CheckLicense;

public class InvoiceStage {
	public static WebDriver driver;
	static String BrowseButton_ID = "fileUpdValidationDocument_Browse0";

	static String SaveOnManagePP_CSS = "#btnSaveDocValidation_CD > .dx-vam";
	static String UploadSuccessPP_ID = "btnCustomMessageClose";
	IRISHomePage iRISHomePage = new IRISHomePage();
	static String notestab = "tbInvoiceDetailsTababc_T5T";//tbInvoiceDetailsTababc_T5T
	static String notessearchtab = "grdNotes_DXFREditorcol0_I";
	static String searchvalsmessage_id = "grdNotes_DXDataRow0";
	static String Invoice_number = "invoiceRef";
	static String Chassisnumber = "chassisNo";
	static String Enginenumber = "engineNumber";
	static String Registration_number = "registrationNumber";
	static String customer_email = "customerEmail";
	static String dealerinvoice = "//*[@title='Dealer Invoice']";
	static String validationlink = "//*[@id='gvIncidentDocumentValidation_tccell0_1']/a";
	static String validationtitle = "popDocValidation_PWH-1T";
	static String downloadlink = "Download";
	static String yesradiobutton = "rdoValidationStatus_RB0_I_D";
	static String savebutton = "btnSaveDocValidation";
	static String Information_popup = "customMessageText";
	static String popupOKbutton = "btnCustomMessageClose";
	static String closepopup = "popDocValidation_HCB-1";
	static String signedcontractlink = "//*[@title='Signed Contract']/./../td[2]/a";
	static String payoutlink = "//*[@title='PayoutDocument']/./../td[2]/a";
	static String bankdetailsoverridecheckbox = "INV_chkOverrideDebitOrder_S_D";
	static String GenerateContractBTN_ID = "btnGenerateContract_CD";
	static String Signiosubmitbutton = "submitButton";
	CheckLicense cl = new CheckLicense();

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/invoice.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}
	
	public String readInput(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/filepath.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	// function to generate a random string of length n
	public static String getAlphaNumericString() {
		// Get the size n
		int n = 17;
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHJKLMNPRSTUVWXYZ" + "123456789";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public void validateDealerInvoive(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		WebElement dealerInvoice = driver.findElement(By.xpath(dealerinvoice)); // Validate delear
																				// invoice
		WebElement p2 = dealerInvoice.findElement(By.xpath("./.."));
		WebElement s2 = p2.findElement(By.xpath("td[2]"));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s2));
		s2.click();

		driver.switchTo().activeElement();
		Thread.sleep(4000);

		driver.findElement(By.xpath(validationlink)).click();

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(validationtitle)));

		Thread.sleep(4000);
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.linkText(downloadlink)).click();
		Thread.sleep(7000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab);

		Thread.sleep(4000);
		driver.findElement(By.id(yesradiobutton)).click(); // click on Yes button

		Thread.sleep(4000);

		driver.findElement(By.id(savebutton)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));

		assertTrue(driver.findElement(By.id(Information_popup)).isDisplayed(),
				"Document updated and validated successfully.");

		driver.findElement(By.id(popupOKbutton)).click();

		driver.findElement(By.id(closepopup)).click();

	}

//	public void uploadAndValidateCreditLifePolicy(WebDriver driver) throws InterruptedException, IOException {
//		Thread.sleep(2000);
//		boolean creditLifePolicyOption = optimaHomePage.isElementPresent(By.xpath("//*[@title='Credit Life Policy']"),
//				driver);
//		if (creditLifePolicyOption) {
//			WebElement creditLifePolicy = driver.findElement(By.xpath("//*[@title='Credit Life Policy']"));
//			WebElement p2 = creditLifePolicy.findElement(By.xpath("./.."));
//			WebElement s2 = p2.findElement(By.xpath("td[1]/a"));
//			s2.click();
//			driver.switchTo().activeElement();
//			Thread.sleep(4000);
//			new WebDriverWait(driver, 30).until(
//					ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id(BrowseButton_ID))));
//
//			driver.findElement(By.id(BrowseButton_ID)).click();
//			Thread.sleep(10000);
//			Runtime.getRuntime().exec(documentpath);
//
//			Thread.sleep(24000);
//			driver.findElement(By.cssSelector(SaveOnManagePP_CSS)).click(); // Click SAVE after doc upload.
//
//			Thread.sleep(15000);
//			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(UploadSuccessPP_ID)));
//			driver.findElement(By.id(UploadSuccessPP_ID)).click(); // Click OK on upload successful PP.
//
//			driver.findElement(By.id("popDocValidation_HCB-1")).click();
//
//			new WebDriverWait(driver, 30).until(ExpectedConditions
//					.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlDocumentValidation_RPHT"))));
//
//			Thread.sleep(6000);
//			WebElement hyperLink = driver.findElement(By.xpath("//*[@title='Credit Life Policy']/./../td[2]/a"));
//			// WebElement s3 = p2.findElement(By.xpath("td[2]/a"));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hyperLink)));
//			hyperLink.click();
//
//			driver.switchTo().activeElement();
//			Thread.sleep(4000);
//
//			driver.findElement(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")).click();
//
//			WebDriverWait wait1 = new WebDriverWait(driver, 20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("popDocValidation_PWH-1T")));
//
//			Thread.sleep(4000);
//			driver.findElement(By.id("rdoValidationStatus_RB0_I_D")).click(); // click on Yes button
//
//			Thread.sleep(4000);
//
//			driver.findElement(By.id("btnSaveDocValidation")).click();
//
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//
//			assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
//					"Document updated and validated successfully.");
//
//			driver.findElement(By.id("btnCustomMessageClose")).click();
//			Thread.sleep(2000);
//
//			driver.findElement(By.id("popDocValidation_HCB-1")).click();
//		}
//	}
//
//	public void uploadAndValidateInsurancePolicy(WebDriver driver) throws InterruptedException, IOException {
//		Thread.sleep(2000);
//		boolean InsurancePolicyOption = optimaHomePage.isElementPresent(By.xpath("//*[@title='Insurance Policy ']"),
//				driver);
//		if (InsurancePolicyOption) {
//			WebElement insurancePolicy = driver.findElement(By.xpath("//*[@title='Insurance Policy ']"));
//			WebElement p2 = insurancePolicy.findElement(By.xpath("./.."));
//			WebElement s2 = p2.findElement(By.xpath("td[1]/a"));
//			s2.click();
//
//			driver.switchTo().activeElement();
//			Thread.sleep(4000);
//			new WebDriverWait(driver, 30).until(
//					ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id(BrowseButton_ID))));
//			// new WebDriverWait(driver,
//			// 30).until(ExpectedConditions.elementToBeClickable(By.id(BrowseButton_ID)));
//			driver.findElement(By.id(BrowseButton_ID)).click();
//			Thread.sleep(10000);
//			Runtime.getRuntime().exec(documentpath);
//
//			Thread.sleep(20000);
//			driver.findElement(By.cssSelector(SaveOnManagePP_CSS)).click(); // Click SAVE after doc upload.
//
//			Thread.sleep(20000);
//			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(UploadSuccessPP_ID)));
//			driver.findElement(By.id(UploadSuccessPP_ID)).click(); // Click OK on upload successful PP.
//
//			driver.findElement(By.id("popDocValidation_HCB-1")).click();
//
//			new WebDriverWait(driver, 20).until(ExpectedConditions
//					.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlDocumentValidation_RPHT"))));
//
//			WebElement hyperLink = driver.findElement(By.xpath("//*[@title='Insurance Policy ']/./../td[2]/a"));
//
//			// WebElement s3 = p2.findElement(By.xpath("td[2]/a"));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hyperLink)));
//			hyperLink.click();
//
//			driver.switchTo().activeElement();
//			Thread.sleep(4000);
//
//			driver.findElement(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")).click();
//
//			WebDriverWait wait1 = new WebDriverWait(driver, 20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("popDocValidation_PWH-1T")));
//
//			Thread.sleep(4000);
//			driver.findElement(By.id("rdoValidationStatus_RB0_I_D")).click(); // click on Yes button
//
//			Thread.sleep(4000);
//
//			driver.findElement(By.id("btnSaveDocValidation")).click();
//
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//
//			assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
//					"Document updated and validated successfully.");
//
//			driver.findElement(By.id("btnCustomMessageClose")).click();
//			Thread.sleep(2000);
//
//			driver.findElement(By.id("popDocValidation_HCB-1")).click();
//		}
//
//	}

	public void ValidateSignedContractAndPayput(WebDriver driver) throws InterruptedException, IOException {
		Thread.sleep(2000);

		WebElement hyperLink = driver.findElement(By.xpath(signedcontractlink));

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hyperLink)));
		hyperLink.click();

		driver.switchTo().activeElement();
		Thread.sleep(4000);

		driver.findElement(By.xpath(validationlink)).click();

		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(validationtitle)));

		Thread.sleep(6000);

		String oldTab = driver.getWindowHandle();
		driver.findElement(By.linkText(downloadlink)).click();
		Thread.sleep(7000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab);

		Thread.sleep(4000);
		driver.findElement(By.id(yesradiobutton)).click(); // click on Yes button

		Thread.sleep(4000);

		driver.findElement(By.id(savebutton)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));

		assertTrue(driver.findElement(By.id(Information_popup)).isDisplayed(),
				"Document updated and validated successfully.");

		driver.findElement(By.id(popupOKbutton)).click();
		Thread.sleep(2000);

		driver.findElement(By.id(closepopup)).click();

		Thread.sleep(4000);

		WebElement hyperLink1 = driver.findElement(By.xpath(payoutlink));

		Thread.sleep(4000);
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hyperLink)));
		hyperLink1.click();

		driver.switchTo().activeElement();
		Thread.sleep(4000);

		driver.findElement(By.xpath(validationlink)).click();

		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(validationtitle)));

		Thread.sleep(6000);

		String oldTab1 = driver.getWindowHandle();
		driver.findElement(By.linkText(downloadlink)).click();
		Thread.sleep(7000);
		ArrayList<String> newTab1 = new ArrayList<String>(driver.getWindowHandles());
		newTab1.remove(oldTab1);
		// change focus to new tab
		driver.switchTo().window(newTab1.get(0));
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab1);

		Thread.sleep(4000);
		driver.findElement(By.id(yesradiobutton)).click(); // click on Yes button

		Thread.sleep(4000);

		driver.findElement(By.id(savebutton)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));

		assertTrue(driver.findElement(By.id(Information_popup)).isDisplayed(),
				"Document updated and validated successfully.");

		driver.findElement(By.id(popupOKbutton)).click();
		Thread.sleep(2000);

		driver.findElement(By.id(closepopup)).click();

	}

	public int checkvalsnote(WebDriver driver) throws InterruptedException {

		int flag = 0;
		Thread.sleep(15000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(notestab)));
		driver.findElement(By.id(notestab)).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(notessearchtab)));
		WebElement searchnotes=driver.findElement(By.id(notessearchtab));
		searchnotes.sendKeys("Payslips, Bank Statement Verified Successfully.");
		searchnotes.sendKeys(Keys.ENTER);
		Thread.sleep(7000);
		if (driver.findElement(By.id(searchvalsmessage_id)).isDisplayed()) {

			flag = 1;

		}

		return flag;

	}

	public void enterinvoiceinformation(WebDriver driver) throws IOException, InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 750)");
		driver.findElement(By.id("contractRequest")).click();
		Thread.sleep(4000);
		String invoicenumber = readInputData("InvoiceNum");
		driver.findElement(By.id(Invoice_number)).clear();
		driver.findElement(By.id(Invoice_number)).sendKeys(invoicenumber);
		String chassisumber = readInputData("VIN");
		js.executeScript("window.scrollBy(0, 550)");
		driver.findElement(By.id(Chassisnumber)).clear();
		driver.findElement(By.id(Chassisnumber)).sendKeys(chassisumber);
		String enginenumber = readInputData("EngineNum");
		driver.findElement(By.id(Enginenumber)).clear();
		driver.findElement(By.id(Enginenumber)).sendKeys(enginenumber);
		String registrationNumber = readInputData("eNatis");
		js.executeScript("window.scrollBy(0, 500)");
		driver.findElement(By.id(Registration_number)).clear();
		driver.findElement(By.id(Registration_number)).sendKeys(registrationNumber);
		String email = readInputData("emailaddress");
		driver.findElement(By.id(customer_email)).clear();
		driver.findElement(By.id(customer_email)).sendKeys(email);
		Thread.sleep(5000);
		driver.findElement(By.id(Signiosubmitbutton)).click();
		Thread.sleep(30000);

	}

	public void fraudoverride(WebDriver driver,int flag) throws InterruptedException, IOException {

		System.out.println("\nIn Fraud Decline");
		
		if (driver.findElement(By.id("btnCustomMessageClose_CD")).isDisplayed()==true) {
			
			driver.findElement(By.id("btnCustomMessageClose_CD")).click();
			
		Thread.sleep(8000);
			
			new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));
			iRISHomePage.clickOnMyDeals(driver);
			
		}
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnFraudDecline_CD")));
		driver.findElement(By.id("btnFraudDecline_CD")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
				"Are you sure you want to refer this Incident for Fraud Decline?");
		driver.findElement(By.id("btnConfirmOK_CD")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
				"Deal successfully referred for Fraud decline.");
		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

		Thread.sleep(15000);
		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn.click();
		Thread.sleep(10000);
		WebElement okbtn = driver.findElement(By.id("btnCustomMessageClose_CD"));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

		okbtn.click();
		Thread.sleep(8000);

		iRISHomePage.clickOnMyDeals(driver);

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbVerificationDetailsTab_AT0T")));
		driver.findElement(By.id("btnReferOverride_CD")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("cmbManagerFruadOverrideReasons_B-1")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("cmbManagerFruadOverrideReasons_DDD_L_LBI0T0")).click();
		driver.findElement(By.id("txtFruadOverrideComment")).sendKeys("Fraud Test");
		driver.findElement(By.id("btnFruadSaveManagerOverride_CD")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
				"Incident moved to Manager Override inbox.");
		Thread.sleep(5000);
		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn1.click();
		Thread.sleep(10000);
		WebElement okbtn1 = driver.findElement(By.id("btnCustomMessageClose_CD"));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

		okbtn1.click();
		Thread.sleep(8000);

		iRISHomePage.clickOnMyDeals(driver);

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbVerificationDetailsTab_AT0T")));

		if (driver.findElement(By.id("radioButtonListoverride_RB0_I_D")).getAttribute("class")
				.contains("dxeIRadioButton dxichSys   dxEditors_edtRadioButtonUnchecked")) {
			driver.findElement(By.id("radioButtonListoverride_RB0_I_D")).click();
		}

		else {
			System.out.println("Yes already selected");
		}

		driver.findElement(By.id("txtmanagerComment")).sendKeys("Fraud Test");

		driver.findElement(By.id("btnSaveOverride_CD")).click();

//		new WebDriverWait(driver, 30)
//		.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));
//		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
//				"Incident will be referred to Verification inbox. Are you sure you want to proceed ?");
//		driver.findElement(By.id("btnConfirmOKManagerOverrideNO_CD")).click();
		//
//		new WebDriverWait(driver, 30)
//		.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
//				"Incident Moved");
//		driver.findElement(By.id("btnCustomMessageClose_CD")).click();
		//
//		new WebDriverWait(driver, 30)
//		.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));
		//
//		optimaHomePage.clickOnAllIncidentInbox(driver);
		//
//		optimaHomePage.searchDealthroughIncidentNumber(driver);
//				
//		optimaHomePage.clickOnIncidentLink(driver);
		//
//		WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
//		Thread.sleep(8000);
//		retriveDealbtn2.click();
//		Thread.sleep(10000);
//		WebElement okbtn2 = driver.findElement(By.id("btnCustomMessageClose_CD"));
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
		//
//		okbtn2.click();
//		Thread.sleep(8000);
		//
//		optimaHomePage.clickOnMyDeals(driver);
		//
//		new WebDriverWait(driver, 30)
//		.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbVerificationDetailsTab_AT0T")));
		//
//		//WebElement yesbutton=driver.findElement(By.id("radioButtonListoverride_RB0_I_D"));
//		//String yesclass=yesbutton.getClass();
		//
//		if(driver.findElement(By.id("radioButtonListoverride_RB0_I_D")).getAttribute("class").contains("dxeIRadioButton dxichSys   dxEditors_edtRadioButtonUnchecked"))
//		{
//			driver.findElement(By.id("radioButtonListoverride_RB0_I_D")).click();
//		}
		//
//		else
//		{
//			System.out.println("Yes already selected");
//		}
		//
//		driver.findElement(By.id("txtmanagerComment")).sendKeys("Fraud Test");
		//
//		driver.findElement(By.id("btnSaveOverride_CD")).click();
		//
		Thread.sleep(8000);
//		new WebDriverWait(driver, 50)
//		.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));
//		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
//				"Incident will be referred to Invoice for Contract generation. Are you sure you want to proceed ?");
		driver.findElement(By.id("btnConfirmOKManagerOverride_CD")).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
				"Incident moved back to contract generation stage.");
		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn3 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn3.click();
		Thread.sleep(10000);
		WebElement okbtn3 = driver.findElement(By.id("btnCustomMessageClose_CD"));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

		okbtn3.click();
		Thread.sleep(8000);

		iRISHomePage.clickOnMyDeals(driver);

	}

	public void validatebankdetails(WebDriver driver,int flag) throws InterruptedException, IOException {
		
		String documentpath = readInput("documentuploadpath");
		Thread.sleep(5000);
		if(driver.findElement(By.id(popupOKbutton)).isDisplayed()==true) {
			
			driver.findElement(By.id(popupOKbutton)).click();	
			
		}
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(bankdetailsoverridecheckbox)));
		driver.findElement(By.id(bankdetailsoverridecheckbox)).click();
		// click on save button

		driver.findElement(By.id("btnSaveInvoice_CD")).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));

		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
				"Are you sure you want to save the information?");

		// click on yes button

		driver.findElement(By.id("btnConfirmOK_CD")).click();

		// wait for override popup
		new WebDriverWait(driver, 180)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("ManagerOverridePopup_PWH-1T")));
		System.out.println("\nDeal is going to manager override");

		// manager override action

		driver.findElement(By.id("cmbManagerPCOverrideReasons_B-1")).click();

		Thread.sleep(4000);
		String manageroverrideaction = readInputData("manageroverrideaction");
		// check manager override action
		if (manageroverrideaction.equalsIgnoreCase("Approve D/O Payment Method - Manual AVS attached")) {
			driver.findElement(By.id("cmbManagerPCOverrideReasons_DDD_L_LBI0T0")).click();
		}

		else {

			driver.findElement(By.id("cmbManagerPCOverrideReasons_DDD_L_LBI1T0")).click();

		}

		// upload file
		Thread.sleep(4000);
		driver.findElement(By.id("fileUploadPremiumClient_Browse0")).click();
		Thread.sleep(4000);
		Runtime.getRuntime().exec(documentpath);
		Thread.sleep(3000);

		driver.findElement(By.id("btnPCSaveManagerOverride_CD")).click();

		new WebDriverWait(driver, 80).until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));

		driver.findElement(By.id("btnCustomMessageClose")).click();

		new WebDriverWait(driver, 80)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));

		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn1.click();

		WebElement okbtn1 = driver.findElement(By.id("btnCustomMessageClose_CD"));
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

		okbtn1.click();

		Thread.sleep(8000);
		iRISHomePage.clickOnMyDeals(driver);

		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("rndPnlInvoice_RPHT")));

		// go to bank account tab

		driver.findElement(By.id("tbInvoiceDetailsTababc_T2T")).click();

		Thread.sleep(2000);

		// select yes

		driver.findElement(By.id("rbPaymentOverride_RB0_I_D")).click();

		// click on save

		driver.findElement(By.id("btnPCSavePaymentOverride_CD")).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("customMessageText")));

		driver.findElement(By.id("btnCustomMessageClose")).click();

		new WebDriverWait(driver, 80)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));

		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn2.click();

		WebElement okbtn2 = driver.findElement(By.id("btnCustomMessageClose_CD"));
		new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

		okbtn2.click();

		Thread.sleep(8000);
		iRISHomePage.clickOnMyDeals(driver);

	}
	
	public void overrideasset(WebDriver driver, int flag) throws InterruptedException, IOException
	{
		
		WebDriverWait wait1 = new WebDriverWait(driver, 200);
		WebDriverWait wait2 = new WebDriverWait(driver, 600);
		
		// Override Duplicate Asset		
		Thread.sleep(10000);
		
		if (driver.findElement(By.id("btnCustomMessageClose_CD")).isDisplayed()==true) {
			
			driver.findElement(By.id("btnCustomMessageClose_CD")).click();
			
		Thread.sleep(8000);
			
		}
		
		if(flag==0) {
		//deal is deallocated
		
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		iRISHomePage.clickOnAllIncidentInbox(driver);
		
		iRISHomePage.searchDealthroughIncidentNumber(driver);
		
		iRISHomePage.clickOnIncidentLink(driver);
		
		WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn1.click();
		Thread.sleep(10000);
		WebElement okbtn1 = driver.findElement(By.id(popupOKbutton));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(popupOKbutton)));
		
		okbtn1.click();
		Thread.sleep(8000);
		
		iRISHomePage.clickOnMyDeals(driver);
		
	}
		
		if (iRISHomePage.isElementPresent(By.id("btnOverrideAssetInvoice_CD"), driver))
		// if(driver.findElement(By.id("btnOverrideAssetInvoice_CD")).isDisplayed())
		{

			System.out.println("Override Duplicate Asset");
			driver.findElement(By.id("btnOverrideAssetInvoice_CD")).click();
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbActionType_B-1")));
			driver.findElement(By.id("cmbActionType_B-1")).click();
			driver.findElement(By.id("cmbActionType_DDD_L_LBI0T0")).click(); // select override
			driver.findElement(By.id("txtReason")).sendKeys("Test"); // add reason
			driver.findElement(By.id("btnSaveDuplicateAssetOverride_CD")).click();
			Thread.sleep(5000);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));
			assertTrue(driver.findElement(By.id(Information_popup)).isDisplayed(),
					"Duplicate Asset overridden successfully.");

//click on ok			

			driver.findElement(By.id(popupOKbutton)).click();

//retrive the deal

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			iRISHomePage.clickOnIncidentLink(driver);

			WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
			Thread.sleep(8000);
			retriveDealbtn2.click();
			Thread.sleep(10000);
			WebElement okbtn2 = driver.findElement(By.id(popupOKbutton));
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(popupOKbutton)));

			okbtn2.click();
			Thread.sleep(8000);

			iRISHomePage.clickOnMyDeals(driver);

		}

		else {

			System.out.println("\nOverride Duplicate Asset not present");

		}
		
	}
	
	public void overridecheck(WebDriver driver,int flag) throws InterruptedException, IOException {
		
		String documentpath = readInput("documentuploadpath");
		WebDriverWait wait1 = new WebDriverWait(driver, 200);
		WebDriverWait wait2 = new WebDriverWait(driver, 600);
		
//		// Override Duplicate Asset		
//				Thread.sleep(10000);
//				if (iRISHomePage.isElementPresent(By.id("btnOverrideAssetInvoice_CD"), driver))
//				// if(driver.findElement(By.id("btnOverrideAssetInvoice_CD")).isDisplayed())
//				{
//
//					System.out.println("Override Duplicate Asset");
//					driver.findElement(By.id("btnOverrideAssetInvoice_CD")).click();
//					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbActionType_B-1")));
//					driver.findElement(By.id("cmbActionType_B-1")).click();
//					driver.findElement(By.id("cmbActionType_DDD_L_LBI0T0")).click(); // select override
//					driver.findElement(By.id("txtReason")).sendKeys("Test"); // add reason
//					driver.findElement(By.id("btnSaveDuplicateAssetOverride_CD")).click();
//					Thread.sleep(5000);
//					wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));
//					assertTrue(driver.findElement(By.id(Information_popup)).isDisplayed(),
//							"Duplicate Asset overridden successfully.");
//
//		//click on ok			
//
//					driver.findElement(By.id(popupOKbutton)).click();
//
//		//retrive the deal
//
//					new WebDriverWait(driver, 30)
//							.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));
//
//					iRISHomePage.clickOnAllIncidentInbox(driver);
//
//					iRISHomePage.searchDealthroughIncidentNumber(driver);
//
//					iRISHomePage.clickOnIncidentLink(driver);
//
//					WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
//					Thread.sleep(8000);
//					retriveDealbtn2.click();
//					Thread.sleep(10000);
//					WebElement okbtn2 = driver.findElement(By.id(popupOKbutton));
//					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(popupOKbutton)));
//
//					okbtn2.click();
//					Thread.sleep(8000);
//
//					iRISHomePage.clickOnMyDeals(driver);
//
//				}
//
//				else {
//
//					System.out.println("\nOverride Duplicate Asset not present");
//
//				}
				
//				cl.downloadPDF(driver);
//				cl.readPDF();

		// click on generate contract button
		
		if(flag==0) {

				Thread.sleep(5000);
				wait2.until(ExpectedConditions.elementToBeClickable(By.id(GenerateContractBTN_ID)));

				driver.findElement(By.id(GenerateContractBTN_ID)).click();

				String validatebankdetails_contract = "Please validate banking details before generating contract.";
				// validate bank details message at contract stage
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Information_popup)));
				if (driver.findElement(By.id(Information_popup)).getText().contains(validatebankdetails_contract)) {
					System.out.print(
							"\n------------------------------------*******************------------------------------------------------");
					System.out.print("\nMessage from popup: " + driver.findElement(By.id("customMessageText")).getText());
					System.out.print("\nNot able to verify bank details\n");
					System.out.print(
							"\n------------------------------------*******************------------------------------------------------\n");
					Thread.sleep(6000);
					driver.quit();

				}

		//override arrears		

				String overridearrears = "Unable to generate contract for new/updated values; One or more of the applicant's active Gomo Accounts are in arrears with amount exceeding allowed value.";

				Thread.sleep(10000);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
				if (driver.findElement(By.id("customMessageText")).getText().contains(overridearrears)) {
					
					flag=1;
					System.out.println("Overr Arrears");

					driver.findElement(By.id("btnCustomMessageClose_CD")).click();
					Thread.sleep(10000);
				}
				
				else {

					System.out.println("\nOverride Arrears not required");

				}

		}
		
		if(flag==1) {
			
					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnOverrideArrearsInvoice_CD")));
					driver.findElement(By.id("btnOverrideArrearsInvoice_CD")).click();
					Thread.sleep(10000);
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("divArrearsOverridePopupContent")));
					Thread.sleep(5000);
					driver.findElement(By.id("fileUpdArrearsOverrideDocument_Browse0")).click();
					Thread.sleep(4000);
					Runtime.getRuntime().exec(documentpath);

					Thread.sleep(10000);
					driver.findElement(By.id("txtReasonArrearsOverride")).sendKeys("Test");
					driver.findElement(By.id("btnSaveArrearsOverride_CD")).click();
					Thread.sleep(10000);
					new WebDriverWait(driver, 50)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
					assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
							"Deal is referred for manager override.");
					driver.findElement(By.id("btnCustomMessageClose_CD")).click();
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

					iRISHomePage.clickOnAllIncidentInbox(driver);

					iRISHomePage.searchDealthroughIncidentNumber(driver);

					iRISHomePage.clickOnIncidentLink(driver);

					WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
					Thread.sleep(8000);
					retriveDealbtn2.click();
					Thread.sleep(10000);
					WebElement okbtn2 = driver.findElement(By.id("btnCustomMessageClose_CD"));
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

					okbtn2.click();
					Thread.sleep(8000);

					iRISHomePage.clickOnMyDeals(driver);
					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlInvoice_RPHT")));
					driver.findElement(By.id("btnOverrideArrearsInvoice_CD")).click();
					if (driver.findElement(By.id("rblArrearsOverridePassFail_RB0_I_D")).getAttribute("class")
							.contains(" dxeIRadioButton dxichSys dxEditors_edtRadioButtonUnchecked")) {
						driver.findElement(By.id("rblArrearsOverridePassFail_RB0_I_D")).click();
					}

					else {
						System.out.println("Yes already selected");
					}

					driver.findElement(By.id("txtArrearsComment")).sendKeys("Test Arrears");
					driver.findElement(By.id("btnSaveArrearsOverridenResult_CD")).click();
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
					assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
							"Outstanding Arrears overridden successfully.");
					driver.findElement(By.id("btnCustomMessageClose_CD")).click();
					Thread.sleep(10000);
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

					iRISHomePage.clickOnAllIncidentInbox(driver);

					iRISHomePage.searchDealthroughIncidentNumber(driver);

					iRISHomePage.clickOnIncidentLink(driver);

					WebElement retriveDealbtn3 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
					Thread.sleep(8000);
					retriveDealbtn3.click();
					Thread.sleep(10000);
					WebElement okbtn3 = driver.findElement(By.id("btnCustomMessageClose_CD"));
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));

					okbtn3.click();
					Thread.sleep(8000);

					iRISHomePage.clickOnMyDeals(driver);

					// click on generate contract button

					wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnGenerateContract_CD")));

					driver.findElement(By.id("btnGenerateContract_CD")).click();
					Thread.sleep(5000);

				}
		
	}

	public void errorMessage(WebDriver driver,String message,int flag) throws InterruptedException, IOException {
		String frauddeclinemsg = "Applicant has been flagged for being on the sanctions list. Incident must be referred for fraud decline.";
		String fraudmsg = "Applicant has been flagged for being on the sanctions list.";
		String blazeFail = "Unable to generate contract as Blaze integration has failed. Please check the integration status popup / data import log / notes for details. Please rerun the failed integration(s) and then save invoice and process deal accordingly.";
		// String acquireStartDateError="Acquire Error: - Start Date - The Deal Start
		// Date May Not Be Earlier Than The Approval Date Of The Facility It Is Linked
		// To optima.";
		String acquireStartDateError = "Acquire Error: ";
		String technicalError = "Due to some technical error, Contract generation failed.";
		String acquireInvalidUser = "ERROR: invalid user name";
		String acquireApiDown = "Acquire Error: Please check if Acquire Web Api is up and running.";
		String xdsSanctionDown = "There is an error in XDS Sanction call. Please try again.";
		String fundingstructure = "Unable to proceed - No criteria specified in the funding structure master - Contact Manager.";
		String validatebankdetails = "Please validate banking details before proceeding the incident.";
		String VINError = "VIN Of Vehicle";
		String EnatisError = "eNATIS Register";
		String InvoiceNoError = "Invoice Number cannot be blank";
		String EngineNoError = "Engine Number Of Vehicle cannot be blank.";
		String MaxDeposit = "Deposit To Be Paid should contain Maximum of 7 digits plus 2 decimal places.";
		String NegativeDIC = "Dealer DIC Amount cannot be negative.";
		String Depositless = "Deposit should be less than purchase price.";
		String duplicateasset_IRIS = "duplicate asset";
		String duplicateasset_Acquire = "Duplicate asset found in Acquire";
		String contractgenerated = "Contract documents are available for signature.";
		int validatebank = 0;
		
		if (message.contains(validatebankdetails)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nValidation Pending for bank details\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			validatebank = 1;
			//validatebankdetails(driver,0);

		}

		else if (message.contains(blazeFail)) {
			System.out.print("\n message is:" + message);
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(acquireStartDateError)) {
			System.out.print("\n message is:" + message);
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(technicalError)) {
			System.out.print("\n message is:" + message);
			Thread.sleep(6000);
			driver.quit();
		} else if (message.contains(acquireInvalidUser)) {
			System.out.print("\n message is:" + message);
			Thread.sleep(6000);
			driver.quit();
		} else if (message.contains(acquireApiDown)) {
			System.out.print("\n message is:" + message);
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(xdsSanctionDown)==true) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nXDS Sanctions Service is down\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(fundingstructure)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nFunding Structure not Allocated. Please Allocate Funding Structure.\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(VINError)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nError on VIN number field\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(EnatisError)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nError on Enatis number field\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(InvoiceNoError)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nError on Invoice number field\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(EngineNoError)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nError on Engine number field\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(MaxDeposit)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nDeposit should contain maximum of 7 digit.\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(NegativeDIC)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nDIC Amount cannot be negative.\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}

		else if (message.contains(Depositless)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup: " + message);
			System.out.print("\nDeposit should be less than purchase price.\n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			driver.quit();
		}
		
		else if(message.contains(contractgenerated)) {
			
			WebElement status = driver.findElement(By.xpath("//*[@id=\"divHeaderRow\"]/div/div[2]/span[8]"));
			String getstatus = status.getText();
			if(getstatus.trim().equals("Awaiting Signed Contract")) {
				
				System.out.println("Contract generated");
				
			}
		}

		else {
			System.out.println("\nIt may be another message");
			System.out.print("\n message is:" + message);
		}
		
		if (message.toLowerCase().contains(duplicateasset_IRIS)||message.toLowerCase().contains(duplicateasset_Acquire)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup:" + message);
			System.out.print("\nDeal flagged for Fraud Decline \n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			overrideasset(driver,flag);
		}
		
		if (message.contains(frauddeclinemsg)) {
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------");
			System.out.print("\nMessage from popup:" + message);
			System.out.print("\nDeal flagged for Fraud Decline \n");
			System.out.print(
					"\n------------------------------------*******************------------------------------------------------\n");
			Thread.sleep(6000);
			fraudoverride(driver,flag);
		}
		
		if(validatebank==1) {
			
			validatebankdetails(driver,flag);
			
		}
	}

	public void errorMessageHandling(WebDriver driver) {
		final String fraudmsg = "Applicant has been flagged for being on the sanctions list. Incident must be referred for fraud decline.";
		final String blazeFail = "Unable to generate contract as Blaze integration has failed. Please check the integration status popup / data import log / notes for details. Please rerun the failed integration(s) and then save invoice and process deal accordingly.";
		final String acquireStartDateError = "Acquire Error: - Start Date - The Deal Start Date May Not Be Earlier Than The Approval Date Of The Facility It Is Linked To optima.";
		final String technicalError = "Due to some technical error, Contract generation failed.";
		final String acquireInvalidUser = "ERROR: invalid user name";
		final String acquireApiDown = "Acquire Error: Please check if Acquire Web Api is up and running.";

		String[] errorMessages = { fraudmsg, blazeFail, acquireStartDateError, technicalError, acquireInvalidUser,
				acquireApiDown };

		WebElement validationMessage = driver.findElement(By.id("customMessageText"));
		String msg = validationMessage.getText();

		switch (msg) {
		case fraudmsg:
			System.out.print("\nmessage is:" + fraudmsg);
			System.out.print("\nDeal flagged for XDS Sanctions \n");
			break;
		case blazeFail:
			System.out.print("\nmessage is:" + blazeFail);
			System.out.print("\nBlaze Integration failed \n");
			break;
		case acquireStartDateError:
			System.out.print("\n message is:" + acquireStartDateError);
			break;
		case technicalError:
			System.out.print("\n message is:" + technicalError);
			break;
		case acquireInvalidUser:
			System.out.print("\n message is:" + acquireInvalidUser);
			break;
		case acquireApiDown:
			System.out.print("\n message is:" + acquireApiDown);
			break;
		default:
			System.out.println(msg);
		}
	}
}
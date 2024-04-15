package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Validation {

	public static WebDriver driver;
	static String Manage_DealerInvoice_CSS = "#gvValidationMasterDocuments_tccell0_0 > a";
	static String doctypedropdown = "cbDocumentType_B-1";
	static String IncidentNumber = "123616";
	static String ContinueButton_ID = "btnIncidentDuplicate";
	static String BrowseButton_ID = "fileUpdValidationDocument_Browse0";
	static String SaveOnManagePP_CSS = "#btnSaveDocValidation_CD > .dx-vam";
	static String UploadSuccessPP_ID = "btnCustomMessageClose";
	static String Validate_DealerInvoice_CSS = "#gvValidationMasterDocuments_tccell0_0 > a";
	static String ValidationLink_LinkTest = "Validation";
	static String LeavePageButton_ID = "btnConfirmNo1_CD";
	static String Validate_DIC_ID = "gvValidationMasterDocuments_tccell0_1";
	static String Validate_DealerInvoice_ID = "gvValidationMasterDocuments_tccell1_1";
	static String Validate_IDBook_ID = "gvValidationMasterDocuments_tccell2_1";
	static String Validate_InstallationCertificate_ID = "gvValidationMasterDocuments_tccell3_1";
	static String Validate_OperatingLicence_ID = "gvValidationMasterDocuments_tccell4_1";
	static String Validate_POA_ID = "gvValidationMasterDocuments_tccell7_1";
	static String Validate_TAL_ID = "gvValidationMasterDocuments_tccell9_1";
	static String ValidateLinkOnPP_ID = "gvIncidentDocumentValidation_tccell0_1";
	static String YESRadioButton_ID = "rdoValidationStatus_RB0_I_D";
	static String OLTypeDD_ID = "cbOLValidationReason_B-1";
	static String Validatelink = "//*[@id='gvIncidentDocumentValidation_tccell0_1']/a";
	static String savedocvalidation = "btnSaveDocValidation";
	static String closeconfirmationpopup = "btnCustomMessageClose";
	static String closevalidationpopup = "popDocValidation_HCB-1";
	static String membershipradiobutton = "rdoVerified_RB0_I_D";
	static String methodofverificationdropdown = "cbMethodOfVerification_B-1";
	static String poamethodofverification = "checkListBoxMOV_LBI3T1";
	static String docsubtypedropdown = "cbDocumentSubType_B-1";
	static String selectdocsubtype = "cbDocumentSubType_DDD_L_LBI11T0";
	static String checkvalidatepopup = "popDocValidation_PWH-1T";
	static String selectOLType = "checkListBoxOL_LBI5T0";
	static String referencenumber = "txtReferenceNumber";
	static String olmethodofverificationdropdown = "cbOLVerificationMethod_B-1";
	static String olmethodofverification = "checkListBoxOLVM_LBI1C";
	static String talmethodofverification = "checkListBoxMOV_4_D";
	static String checkwaivedoccheckbox = "IsWaiveDocument_S_D";
	static String waivedocoverridepopup = "WaiveDocumentOverridePopup_PWH-1T";
	static String waivereason = "txtWaiveReasons";
	static String waivesave = "btnSaveWaiveDocumentResult_CD";
	static String waivepopup = "customMessageText";
	static String downloadlink = "Download";
	static String VAPSlink = "//*[@title='VAPs Invoice']";
	static String checkvalidaationscreen = "rndPnlDocumentValidation_RPHT";
	static String contractlink = "//*[@title='Signed Contract']";
	static String docuploadedmessage = "customMessageText";
	static String docwaivebrowse = "fileUploadSupportingWaiveDocument_Browse0";
	static String waivenote = "txtWaiveNotes";

	IRISHomePage iRISHomePage = new IRISHomePage();
	
	public String readInputData(String key) throws IOException {
		//InputStream input = new FileInputStream("src/TestData/validationStage.properties");
		InputStream input = new FileInputStream("src/TestData/validationStage.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}
	
	public String readInput(String key) throws IOException {
	//	InputStream input = new FileInputStream("src/TestData/filepath.properties");
		InputStream input = new FileInputStream("src/TestData/filepath.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}
	

	public void uploadDealerInvoice(WebDriver driver) throws InterruptedException, IOException {
		
		Thread.sleep(20000);
		WebElement uploadDealerInvoiceLink = driver.findElement(By.id("gvValidationMasterDocuments_tccell1_0"));
		uploadDealerInvoiceLink.click();
		Thread.sleep(30000);

		driver.switchTo().activeElement();

		WebElement selectDocument = driver.findElement(By.id("fileUpdValidationDocument_Browse0"));
		// selectDocument.clear();
		selectDocument.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec("src\\AutoIt\\uploadFile.exe");
		Thread.sleep(5000);

		WebElement saveButton = driver.findElement(By.id("btnSaveDocValidation_CD"));
		saveButton.click();
		Thread.sleep(10000);

		driver.switchTo().activeElement();

		WebElement okButton = driver.findElement(By.id("btnCustomMessageClose"));
		okButton.click();
		Thread.sleep(10000);

		driver.switchTo().activeElement();
		WebElement closeButton = driver.findElement(By.id("popDocValidation_HCB-1"));
		closeButton.click();
		Thread.sleep(20000);
	}

	public void DocsToUpload(WebDriver driver,String doc) throws IOException, InterruptedException {

		Thread.sleep(5000);

		//String doc = readInputData("Doctouploadname");
		// String DocNames = "Dealer Invoice,ID Book,Installation Certificate,Operating
		// License,Proof of Address,Taxi Association Letter,Bank Statement"; // Select
		// Docs
		// to
		// Upload
		StringTokenizer DocNamesST = new StringTokenizer(doc, ",");
		int flag = 0;
		// int DocCount = DocNamesST.countTokens();

		do {
			
			if(flag==0) {
				
				Thread.sleep(5000);
				driver.findElement(By.id("btnOptimaDocs")).click();
				Thread.sleep(8000);
				flag=1;
				
			}
			
			else if(flag==1) {
				
				close_save_popup(driver);
				Thread.sleep(5000);
				driver.findElement(By.cssSelector(Manage_DealerInvoice_CSS)).click(); // Click on Manage Link.
				flag=2;
				
			}
			Thread.sleep(5000);
			WebElement dropdown = driver.findElement(By.id(doctypedropdown)); // Find drop down
			dropdown.click(); // Open Drop down

			Thread.sleep(1500);
			WebElement documentDropdown = driver
					.findElement(By.xpath(".//tr[@class='dxeListBoxItemRow']//td[contains(text(),'"
							+ DocNamesST.nextToken() + "') and (contains(@id, 'cbDocumentType_DDD_L_LBI'))]"));
			Thread.sleep(2000);
			documentDropdown.click();
			Thread.sleep(2000);
			UploadDocument(driver);
		} while (DocNamesST.hasMoreTokens());

	}

	public void UploadDocument(WebDriver driver) throws IOException, InterruptedException // Upload document
																									// function.
	{
		String documentpath = readInput("documentuploadpath");
		Thread.sleep(5500);
		driver.findElement(By.id(BrowseButton_ID)).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec(documentpath);

		Thread.sleep(3000);
		// new WebDriverWait(driver,
		// 30).until(ExpectedConditions.elementToBeClickable(By.id(SaveOnManagePP_CSS)));
		driver.findElement(By.cssSelector(SaveOnManagePP_CSS)).click(); // Click SAVE after doc upload.

		Thread.sleep(8000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(UploadSuccessPP_ID)));
		driver.findElement(By.id(UploadSuccessPP_ID)).click(); // Click OK on upload successful PP.

	}

	public void close_save_popup(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.id(closevalidationpopup)).click();
		Thread.sleep(2000);
	}

//	public static void ChooseDocumentsToValidateDocument(WebDriver driver) throws IOException, InterruptedException {
//
//		Thread.sleep(7000);
//		
//		WebElement proofOfAddress = driver.findElement(By.xpath("//*[@title='Proof of Address']")); // Validate proof of
//		// address
//		WebElement p3 = proofOfAddress.findElement(By.xpath("./.."));
//		WebElement s3 = p3.findElement(By.xpath("td[2]"));
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s3));
//		s3.click();
//		ValidateDocument(driver);
//		
//		Thread.sleep(4000);
//
//		WebElement c = driver.findElement(By.xpath("//*[@title='ID Book']")); // Validate ID Book.
//
//		WebElement p = c.findElement(By.xpath("./..")); // identify parent element with ./.. expression in xpath
//		WebElement s = p.findElement(By.xpath("td[2]"));
//		s.click();
//		ValidateDocument(driver);
//
//		Thread.sleep(4000);
//		
//		WebElement installationcertificate = driver.findElement(By.xpath("//*[@title='Installation Certificate']")); // Validate ID Book.
//
//		WebElement p6 = installationcertificate.findElement(By.xpath("./..")); // identify parent element with ./.. expression in xpath
//		WebElement s6 = p6.findElement(By.xpath("td[2]"));
//		s6.click();
//		ValidateDocument(driver);
//
//		Thread.sleep(4000);
//		
//		WebElement payslips = driver.findElement(By.xpath("//*[@title='Payslips']")); // Validate
//																														// installation
//																														// certificate
//		WebElement p1 = payslips.findElement(By.xpath("./..")); // identify parent element with ./..
//																				// expression in xpath
//		WebElement s1 = p1.findElement(By.xpath("td[2]"));
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s1));
//		s1.click();
//		ValidateDocument(driver);
//
//		Thread.sleep(4000);
//		WebElement driversLiscense = driver.findElement(By.xpath("//*[@title='Drivers License']")); // Validate
//																										// operating
//																										// liscence
//		WebElement p2 = driversLiscense.findElement(By.xpath("./.."));
//		WebElement s2 = p2.findElement(By.xpath("td[2]"));
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s2));
//		s2.click();
//		ValidateDocument(driver);
//
//		Thread.sleep(8000);
//		
//		WebElement additionalincome = driver.findElement(By.xpath("//*[@title='Proof of additional Income']")); // Validate
//		// Taxi
//		// assoc // Letter
//		WebElement p4 = additionalincome.findElement(By.xpath("./.."));
//		WebElement s4 = p4.findElement(By.xpath("td[2]"));
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s4));
//		s4.click();
//		ValidateDocument(driver);
//		
//		Thread.sleep(8000);
//
//		WebElement bankStatement = driver.findElement(By.xpath("//*[@title='Bank Statement']")); // validate bank
//																									// statement
//		WebElement p5 = bankStatement.findElement(By.xpath("./.."));
//		WebElement s5 = p5.findElement(By.xpath("td[2]"));
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(s5));
//		s5.click();
//		ValidateDocument(driver);
//
//		
//	}

	public void ChooseDocumentsToValidate(WebDriver driver,String doc) throws IOException, InterruptedException {

		Thread.sleep(4000);

		Thread.sleep(5000);
		//String doc = readInputData("Doctouploadname");
		StringTokenizer DocNamesST = new StringTokenizer(doc, ",");

		do {

			String a = DocNamesST.nextToken().toString();
			Thread.sleep(8000);

			WebElement c;

			c = driver.findElement(By.xpath("//*[@title='" + a + "']")); // Validate document.
			Thread.sleep(2000);
			WebElement p = c.findElement(By.xpath("./..")); // identify parent element with ./.. expression in xpath
			Thread.sleep(1500);
			WebElement s = p.findElement(By.xpath("td[2]"));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(s));
			s.click();

				ValidateDocument(driver);

			Thread.sleep(4000);

		} while (DocNamesST.hasMoreTokens());

		Thread.sleep(8000);

	}

	public static void ValidateDocument(WebDriver driver) throws IOException, InterruptedException {

		Thread.sleep(4000);
		driver.switchTo().activeElement();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(Validatelink)));
		driver.findElement(By.xpath(Validatelink)).click(); // Click Validate
															// on 'Validate
															// Document'
															// popup.
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
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id(YESRadioButton_ID)));
		driver.findElement(By.id(YESRadioButton_ID)).click(); // Click 'YES' radio button.
		Thread.sleep(2500);
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id(savedocvalidation)));
		driver.findElement(By.id(savedocvalidation)).click(); // Click SAVE on validate screen.
		Thread.sleep(2500);

		driver.findElement(By.id(closeconfirmationpopup)).click(); // Close confirmation popup.
		Thread.sleep(500);

		driver.findElement(By.id(closevalidationpopup)).click(); // Close main popup.

	}

//	public static void Validate_Proof_of_Address(WebDriver driver) throws IOException, InterruptedException {
//		Thread.sleep(4000);
//		driver.switchTo().activeElement();
//
//		new WebDriverWait(driver, 30).until(ExpectedConditions
//				.elementToBeClickable(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")));
//		driver.findElement(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")).click(); // Click Validate
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("rdoVerified_RB0_I_D")));
//		driver.findElement(By.id("rdoVerified_RB0_I_D")).click(); // Click 'YES' radio button for membership verified.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("cbMethodOfVerification_B-1")).click(); // dropdown click
//		Thread.sleep(2000);
//		driver.findElement(By.id("checkListBoxMOV_LBI3T1")).click();// on 'Validate
//		Thread.sleep(2000);
//		driver.findElement(By.id("cbDocumentSubType_B-1")).click();// doc subtype dropdown
//		Thread.sleep(2000);
//		driver.findElement(By.id("cbDocumentSubType_DDD_L_LBI11T0")).click();// select subtype
//		Thread.sleep(4000);
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id(YESRadioButton_ID)));
//		driver.findElement(By.id(YESRadioButton_ID)).click(); // Click 'YES' radio button.
//		Thread.sleep(2500);
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("btnSaveDocValidation")));
//		driver.findElement(By.id("btnSaveDocValidation")).click(); // Click SAVE on validate screen.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("btnCustomMessageClose")).click(); // Close confirmation popup.
//		Thread.sleep(500);
//
//		driver.findElement(By.id("popDocValidation_HCB-1")).click(); // Close main popup.
//
//	}
//
//	public static void ValidateOperatingLicence(WebDriver driver) throws IOException, InterruptedException {
//		Thread.sleep(4000);
//		driver.switchTo().activeElement();
//		new WebDriverWait(driver, 30).until(ExpectedConditions
//				.elementToBeClickable(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")));
//		driver.findElement(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")).click(); // Click Validate
//																										// on 'Validate
//																										// Document'
//																										// popup.
//		Thread.sleep(2500);
//		new WebDriverWait(driver, 30)
//				.until(ExpectedConditions.visibilityOfElementLocated(By.id("popDocValidation_PWH-1T")));
//		// popup.
//		Thread.sleep(2500);
//		driver.findElement(By.id(YESRadioButton_ID)).click(); // Click 'YES' radio button.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id(OLTypeDD_ID)).click(); // Open O/L Type drop-down.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("checkListBoxOL_LBI5T0")).click(); // Select all O/L types
//		Thread.sleep(2500);
//
//		driver.findElement(By.id(OLTypeDD_ID)).click(); // Open O/L Type drop-down.
//		Thread.sleep(2500);
//
//		WebElement ReferenceNumber = driver.findElement(By.id("txtReferenceNumber")); // Select Refrence number textbox
//		String refNum = getOperationLisceneReferanceNumber();
//		System.out.print("Operating liscene referance number is:" + refNum);
//		ReferenceNumber.clear(); // Clear textbox
//		ReferenceNumber.sendKeys(refNum); // Add Reference Number
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("cbOLVerificationMethod_B-1")).click(); // Open Method of verification drop down
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("checkListBoxOLVM_LBI1C")).click(); // Select board Method of verification
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("btnSaveDocValidation_CD")).click(); // click on save button
//		Thread.sleep(500);
//		driver.findElement(By.id("btnCustomMessageClose_CD")).click(); // Close confirmation popup.
//		Thread.sleep(500);
//
//		driver.findElement(By.id("popDocValidation_HCB-1")).click(); // Close main popup.
//	}
//
//	public static void validate_taxi_association_letter(WebDriver driver) throws InterruptedException {
//
//		Thread.sleep(4000);
//		driver.switchTo().activeElement();
//
//		new WebDriverWait(driver, 30).until(ExpectedConditions
//				.elementToBeClickable(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")));
//		driver.findElement(By.xpath("//*[@id='gvIncidentDocumentValidation_tccell0_1']/a")).click(); // Click Validate
//																										// on 'Validate
//																										// Document'
//																										// popup.
//		Thread.sleep(4000);
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id(YESRadioButton_ID)));
//		driver.findElement(By.id(YESRadioButton_ID)).click(); // Click 'YES' radio button.
//		Thread.sleep(2500);
//
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("rdoVerified_RB0_I_D")));
//		driver.findElement(By.id("rdoVerified_RB0_I_D")).click(); // Click 'YES' radio button for membership verified.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("cbMethodOfVerification_B-1")).click(); // dropdown click
//		Thread.sleep(2000);
//		driver.findElement(By.id("checkListBoxMOV_4_D")).click();
//
//		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("btnSaveDocValidation")));
//		driver.findElement(By.id("btnSaveDocValidation")).click(); // Click SAVE on validate screen.
//		Thread.sleep(2500);
//
//		driver.findElement(By.id("btnCustomMessageClose")).click(); // Close confirmation popup.
//		Thread.sleep(500);
//
//		driver.findElement(By.id("popDocValidation_HCB-1")).click(); // Close main popup.
//	}

	public void upload_SignedContract(WebDriver driver) throws InterruptedException, IOException {
		
		String documentpath = readInput("documentuploadpath");
		boolean signedcontractDocumentOption = iRISHomePage.isElementPresent(By.xpath(contractlink), driver);
		if (signedcontractDocumentOption) {
			WebElement signedC = driver.findElement(By.xpath(contractlink));
			WebElement p2 = signedC.findElement(By.xpath("./.."));
			WebElement s2 = p2.findElement(By.xpath("td[1]/a"));
			s2.click();

			driver.switchTo().activeElement();
			Thread.sleep(4000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id(BrowseButton_ID))));

			driver.findElement(By.id(BrowseButton_ID)).click();
			Thread.sleep(8000);
			Runtime.getRuntime().exec(documentpath);

			Thread.sleep(8000);
			driver.findElement(By.cssSelector(SaveOnManagePP_CSS)).click(); // Click SAVE after doc upload.

			Thread.sleep(8000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(UploadSuccessPP_ID)));
			driver.findElement(By.id(UploadSuccessPP_ID)).click(); // Click OK on upload successful PP.
		}

	}

	public void upload_VAPSdoc(WebDriver driver) throws InterruptedException, IOException {
		
		String documentpath = readInput("documentuploadpath");
		boolean VAPSDocumentOption = iRISHomePage.isElementPresent(By.xpath(VAPSlink), driver);
		if (VAPSDocumentOption) {
			WebElement signedC = driver.findElement(By.xpath(VAPSlink));
			WebElement p2 = signedC.findElement(By.xpath("./.."));
			WebElement s2 = p2.findElement(By.xpath("td[1]/a"));
			s2.click();

			driver.switchTo().activeElement();
			Thread.sleep(4000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id(BrowseButton_ID))));

			driver.findElement(By.id(BrowseButton_ID)).click();
			Thread.sleep(8000);
			Runtime.getRuntime().exec(documentpath);

			Thread.sleep(8000);
			driver.findElement(By.cssSelector(SaveOnManagePP_CSS)).click(); // Click SAVE after doc upload.

			Thread.sleep(8000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(UploadSuccessPP_ID)));
			driver.findElement(By.id(UploadSuccessPP_ID)).click(); // Click OK on upload successful PP.

			driver.findElement(By.id(closevalidationpopup)).click();

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id(checkvalidaationscreen))));

			Thread.sleep(6000);
			WebElement hyperLink = driver.findElement(By.xpath("//*[@title='VAPs Invoice']/./../td[2]/a"));
			// WebElement s3 = p2.findElement(By.xpath("td[2]/a"));
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(hyperLink)));
			hyperLink.click();

			driver.switchTo().activeElement();
			Thread.sleep(4000);

			driver.findElement(By.xpath(Validatelink)).click();

			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(checkvalidatepopup)));

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
			driver.findElement(By.id(YESRadioButton_ID)).click(); // click on Yes button

			Thread.sleep(4000);

			driver.findElement(By.id(savedocvalidation)).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(docuploadedmessage)));

			assertTrue(driver.findElement(By.id(docuploadedmessage)).isDisplayed(),
					"Document updated and validated successfully.");

			driver.findElement(By.id(closeconfirmationpopup)).click();
			Thread.sleep(2000);

			driver.findElement(By.id(closevalidationpopup)).click();
		}

	}

	public static String getOperationLisceneReferanceNumber() {
		// Get the size n
		int n = 14;
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHJKLMNPRSTUVWXYZ" + "23456789" + "abcdefghjkmnpqrstuvwxyz";

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

	public void checkout_waive_checkbox_and_Save(WebDriver driver) throws InterruptedException, IOException {
		
		String documentpath = readInput("documentuploadpath");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(checkvalidatepopup)));

		// check waive doument checkbox

		driver.findElement(By.id(checkwaivedoccheckbox)).click();

		// click on save button
		Thread.sleep(2000);
		driver.findElement(By.id(savedocvalidation)).click();

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(waivedocoverridepopup)));

		driver.findElement(By.id(docwaivebrowse)).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec(documentpath);
		WebElement reason = driver.findElement(By.id(waivereason));
		reason.clear();
		reason.sendKeys("Testing purpose");
		WebElement note = driver.findElement(By.id(waivenote));
		note.clear();
		note.sendKeys("Testing purpose");

		Thread.sleep(2000);
		// click on save button
		driver.findElement(By.id(waivesave)).click();

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id(waivepopup)));

		assertEquals(driver.findElement(By.id(waivepopup)).getText(), "Document Waived Successfully.");

		driver.findElement(By.id(closeconfirmationpopup)).click();
		Thread.sleep(2000);
	}

//	public void waiveDocument(WebDriver driver) throws InterruptedException {
//
//		Thread.sleep(2000);
//
//		WebElement idBook = driver.findElement(By.xpath("//*[@title='ID Book']/./../td[1]/a")); // click o manage ID
//																								// Book.
//		idBook.click();
//
//		checkout_waive_checkbox_and_Save(driver);
//
//		WebElement installationCertificate = driver
//				.findElement(By.xpath("//*[@title='Installation Certificate']/./../td[1]/a")); // click o manage IC.
//		installationCertificate.click();
//
//		Thread.sleep(2000);
//
//		checkout_waive_checkbox_and_Save(driver);
//
//		WebElement operatingLicense = driver.findElement(By.xpath("//*[@title='Operating License']/./../td[1]/a"));
//		operatingLicense.click();
//
//		Thread.sleep(2000);
//
//		checkout_waive_checkbox_and_Save(driver);
//
//		WebElement proofOfAddress = driver.findElement(By.xpath("//*[@title='Proof of Address']/./../td[1]/a"));
//		proofOfAddress.click();
//
//		Thread.sleep(2000);
//
//		checkout_waive_checkbox_and_Save(driver);
//
//		WebElement taxiAccociationLetter = driver
//				.findElement(By.xpath("//*[@title='Taxi Association Letter ']/./../td[1]/a"));
//		taxiAccociationLetter.click();
//
//		Thread.sleep(2000);
//
//		checkout_waive_checkbox_and_Save(driver);
//
//	}

	public void waiveDoc(WebDriver driver) throws InterruptedException, IOException {

		Thread.sleep(4000);

		Thread.sleep(5000);
		String waivedoc = readInputData("Doctowaivename");
		StringTokenizer WaiveDocNamesST = new StringTokenizer(waivedoc, ",");

		do {

			Thread.sleep(8000);

			String a = WaiveDocNamesST.nextToken().toString();

			WebElement doclink;

			doclink = driver.findElement(By.xpath("//*[@title='" + a + "']/./../td[1]/a")); // click o manage
			// ID
			// Book.
			Thread.sleep(4000);
			doclink.click();

			checkout_waive_checkbox_and_Save(driver);
		} while (WaiveDocNamesST.hasMoreTokens());

		Thread.sleep(8000);

	}

}

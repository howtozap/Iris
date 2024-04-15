package Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

import Pages.FirstCheckerScreen;
import Pages.InvoiceStage;
import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.Validation;

// Pre requisite- deal should be in your mydeal inbox
public class FirstChecker extends BasicSetup {

	public static WebDriver driver;
	static String IncidentNumber = "124473";
	static String ContinueButton_ID = "btnIncidentDuplicate";
	static String ValidationLink_LinkTest = "Validation";
	static String ContactLink_LinkTest = "Contract";
	String FirstCheckerTab = "rndPnlFirstChecker_RPHT";
	String DocumentValidationTab = "rndPnlDocumentValidation_RPHT";
	String DocValidationProceedButton = "btnContinueWork_CD";
	String Application_Link = "Application";
	String ApplicationTab = "rndPnlApplicant_RPHT";
	String VehicleDetailsTab = "//a[@id='tbApplicatioDetailsTab_T1T']";
	String VehicleTab = "rndPnlVehicleDetails_RPHT";
	String InformationpopupOK = "btnCustomMessageClose_CD";
	String StagechangepopupOK = "btnConfirmNo1_CD";
	String Allincidenttab_ID = "nbClientSideEvents_I0i2_";
	String ReleaseNotesRecDropDown = "F_cmbdocReleaseNotes_rcv_B-1";
	String ReleaseNotesRecYesDD = "F_cmbdocReleaseNotes_rcv_DDD_L_LBI1T0";
	String ReleaseNotesChkDropDown = "F_cmbdocReleaseNotes_chk_B-1";
	String ReleaseNotesChkYesDD = "F_cmbdocReleaseNotes_chk_DDD_L_LBI1T0";
	String InvoiceRecDropDown = "F_cmbdocInvoice_rcv_B-1";
	String InvoiceRecYesDD = "F_cmbdocInvoice_rcv_DDD_L_LBI1T0";
	String InvoiceChkDropDown = "F_cmbdocInvoice_chk_B-1";
	String InvoiceChkYesDD = "F_cmbdocInvoice_chk_DDD_L_LBI1T0";
	String DICRecDropDown = "F_cmbdocDIC_rcv_B-1";
	String DICRecYesDD = "F_cmbdocDIC_rcv_DDD_L_LBI2T0";
	String DICChkDropDown = "F_cmbdocDIC_chk_B-1";
	String DICChkYesDD = "F_cmbdocDIC_chk_DDD_L_LBI2T0";
	String WarrantyRecDropDown = "F_cmbdocWarrantyInvoice_rcv_B-1";
	String WarrantyRecYesDD = "F_cmbdocWarrantyInvoice_rcv_DDD_L_LBI2T0";
	String WarrantyChkDropDown = "F_cmbdocWarrantyInvoice_chk_B-1";
	String WarrantyChkYesDD = "F_cmbdocWarrantyInvoice_chk_DDD_L_LBI2T0";
	String EnatisRecDropDown = "F_cmbdoceNatis_rcv_B-1";
	String EnatisRecYesDD = "F_cmbdoceNatis_rcv_DDD_L_LBI2T0";
	String EnatisChkDropDown = "F_cmbdoceNatis_chk_B-1";
	String EnatisYesDD = "F_cmbdoceNatis_chk_DDD_L_LBI2T0";
	String ProofofAddressRecDropDown = "F_cmbdocPOA_rcv_B-1";
	String ProofofAddressRecYesDD = "F_cmbdocPOA_rcv_DDD_L_LBI1T0";
	String ProofofAddressChkDropDown = "F_cmbdocPOA_chk_B-1";
	String ProofofAddressChkYesDD = "F_cmbdocPOA_chk_DDD_L_LBI1T0";
	String IncomeRecDropDown = "cmbdocIncomeCalculator_rcv_B-1";
	String IncomeRecYesDD = "cmbdocIncomeCalculator_rcv_DDD_L_LBI1T0";
	String IncomeChkDropDown = "cmbdocIncomeCalculator_chk_B-1";
	String IncomeChkYesDD = "cmbdocIncomeCalculator_chk_DDD_L_LBI1T0";
	String PayslipRecDropDown = "cmbdocPayslip_rcv_B-1";
	String PayslipRecYesDD = "cmbdocPayslip_rcv_DDD_L_LBI1T0";
	String PayslipChkDropDown = "cmbdocPayslip_chk_B-1";
	String PayslipChkYesDD = "cmbdocPayslip_chk_DDD_L_LBI1T0";
	String ProofofInsuranceRecDropDown = "cmbdocProofInsurance_rcv_B-1";
	String ProofofInsuranceRecYesDD = "cmbdocProofInsurance_rcv_DDD_L_LBI1T0";
	String ProofofInsuranceChkDropDown = "cmbdocProofInsurance_chk_B-1";
	String ProofofInsuranceChkYesDD = "cmbdocProofInsurance_chk_DDD_L_LBI1T0";
	String Addcomment = "txtComments";
	String SaveButton = "btnDocSave_CD";
	String ProceedButton = "btnDocProceed_CD";
	String ConfirmPopupOK = "btnConfirmOK_CD";
	
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	InvoiceStage invoiceStage = new InvoiceStage();
	Validation validation = new Validation();
	FirstCheckerScreen firstCheckerScreen = new FirstCheckerScreen();

	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {

		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}

	@BeforeTest(enabled = true)
	public void setup() throws InterruptedException, IOException {

		FirstChecker firstChecker = new FirstChecker();
	}

	public FirstChecker() {
		driver = getdriver();
	}

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/firstchecker.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	@Test(priority = 8)
	public void firstCheckerChecking() throws InterruptedException, IOException {

		WebDriverWait wait1 = new WebDriverWait(driver, 100);

		new WebDriverWait(driver, 180)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(Allincidenttab_ID)));
		
		//check deal in my inbox
		int flag[] = iRISHomePage.checkDealinMyDeals(driver);
		
		if (flag[1] == 0) {

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			iRISHomePage.clickOnIncidentLink(driver);

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));
			
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
				
				System.out.println("In side");
				
				iRISHomePage.retriveDeal(driver);
				
			}
		
			Thread.sleep(8000);
		}

		else {
			System.out.println("Incident is present in My Deals");
		}

		iRISHomePage.clickOnMyDeals(driver);

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));
		
		//click on validation link to upload all the documents
		driver.findElement(By.linkText(ValidationLink_LinkTest)).click();

		driver.findElement(By.id(StagechangepopupOK)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab)));

		Thread.sleep(10000);
		
		//upload all the required documents
		String doctoupload = readInputData("Doctouploadname");
		validation.DocsToUpload(driver, doctoupload);
		Thread.sleep(2000);
		validation.close_save_popup(driver);
		Thread.sleep(2000);
		validation.ChooseDocumentsToValidate(driver, doctoupload); // GO to Validate Documents

		// go to Application Stage to check vaps

		Thread.sleep(5000);
		driver.findElement(By.linkText(Application_Link)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ApplicationTab)));

		Thread.sleep(20000);

		driver.findElement(By.xpath(VehicleDetailsTab)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(VehicleTab)));
		
		//check finance agreement
		
		String financeAgreement = driver.findElement(By.xpath("//table[@id='cmbFinanceAgreement_DDD_L_LBT']//*[@class='dxeListBoxItem dxeListBoxItemSelected']")).getAttribute("innerText");

		Thread.sleep(5000);
		
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@id='grdDealerVapsView_DXMainTable']/tbody/tr[position()!=1]"));
		System.out.println(rows.size());

		int r = rows.size();

		// check number of rows present in dealer VAPS table
		System.out.println("Rows are" + r);
		int j = 1;
		int flag1 = 1;
		for (int i = 0; i < rows.size(); i++) {

			Thread.sleep(2000);
			WebElement nodatarow = rows.get(i)
					.findElement(By.xpath("//table[@id='grdDealerVapsView_DXMainTable']/tbody/tr[" + j + "]"));
			if (nodatarow.getAttribute("class").contains("dxgvEmptyDataRow")) {
				System.out.println("There is no data in Dealer VAP's Table.");
				flag1 = 0;
			}
			j++;
		}

		// go back to validation stage

		driver.findElement(By.linkText(ValidationLink_LinkTest)).click(); // Click on Validation Link.

		driver.findElement(By.id(StagechangepopupOK)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab)));
		
		driver.findElement(By.id("tbDocumentVerification_T3T")).click();
		Thread.sleep(8000);
		WebElement searchnote = driver.findElement(By.id("grdNotes_DXFREditorcol0_I"));
		searchnote.sendKeys("Contract Document generated successfully through Self Help.");
		searchnote.sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		
		if (iRISHomePage.isElementPresent(By.id("grdNotes_tccell0_0"), driver)) {
			
			driver.findElement(By.id("tbDocumentVerification_T0T")).click();
			Thread.sleep(8000);
			String dealerInvoice = "Dealer Invoice";
			validation.DocsToUpload(driver, dealerInvoice);
			Thread.sleep(2000);
			validation.close_save_popup(driver);
			Thread.sleep(2000);
			validation.ChooseDocumentsToValidate(driver, dealerInvoice);
			
		}
		
		else {
			
			driver.findElement(By.id("tbDocumentVerification_T0T")).click();
			Thread.sleep(8000);
			
		}

		if (flag1 == 1) {

			validation.upload_VAPSdoc(driver);
			System.out.println("VAPS Invoice uploaded successfully");

		}

		validation.upload_SignedContract(driver);
		
		if(financeAgreement.equals("Consumer Finance Subscription")) {
			
			driver.quit();
			
		}
		
		Thread.sleep(10000);
		
		iRISHomePage.clickOnAllIncidentInbox(driver);
		
		Thread.sleep(10000);
		
		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

			// optimaHomePage.retriveDeal(driver);

			WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
			Thread.sleep(8000);
			retriveDealbtn.click();

			WebElement okbtn = driver.findElement(By.id(InformationpopupOK));
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));

			okbtn.click();
			
			iRISHomePage.clickOnMyDeals(driver);

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab)));
		
		Thread.sleep(2000);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id(DocValidationProceedButton)));

	//click on proceed button
	driver.findElement(By.id(DocValidationProceedButton)).click();
	
	Thread.sleep(5000);
	
	driver.findElement(By.id(InformationpopupOK)).click();
		
		//again retrive this deal
	
	new WebDriverWait(driver, 180)
	.until(ExpectedConditions.visibilityOfElementLocated(By.id(Allincidenttab_ID)));
		
		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
		Thread.sleep(8000);
		retriveDealbtn1.click();

		WebElement okbtn1 = driver.findElement(By.id(InformationpopupOK));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));

		okbtn1.click();

		Thread.sleep(8000);
		iRISHomePage.clickOnMyDeals(driver);

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));

		// go to validation stage

		driver.findElement(By.linkText(ValidationLink_LinkTest)).click(); // Click on Validation Link.

		driver.findElement(By.id(StagechangepopupOK)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DocumentValidationTab)));

		 // validate signed contract and payout document first

		invoiceStage.ValidateSignedContractAndPayput(driver);

		// click on contract tab
		Thread.sleep(2000);

		driver.findElement(By.linkText(ContactLink_LinkTest)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));

		// Release note
		driver.findElement(By.id(ReleaseNotesRecDropDown)).click(); // Find Release Note received dropdown
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReleaseNotesRecYesDD)));
		driver.findElement(By.id(ReleaseNotesRecYesDD)).click(); // Select 'YES' on Received dropdown

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ReleaseNotesChkDropDown))); // Check
																										// checkDropdown
																										// is clickable
																										// or not

		driver.findElement(By.id(ReleaseNotesChkDropDown)).click(); // Find Release Note checked dropdown
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReleaseNotesChkYesDD)));
		driver.findElement(By.id(ReleaseNotesChkYesDD)).click(); // Select 'YES' on checked dropdown

		// Invoice
		driver.findElement(By.id(InvoiceRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceRecYesDD)));
		driver.findElement(By.id(InvoiceRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(InvoiceChkDropDown)));

		driver.findElement(By.id(InvoiceChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceChkYesDD)));
		driver.findElement(By.id(InvoiceChkYesDD)).click();

		// DIC Invoice

		driver.findElement(By.id(DICRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DICRecYesDD)));
		driver.findElement(By.id(DICRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(DICChkDropDown)));

		driver.findElement(By.id(DICChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DICChkYesDD)));
		driver.findElement(By.id(DICChkYesDD)).click();

		// Warranty invoice

		driver.findElement(By.id(WarrantyRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(WarrantyRecYesDD)));
		driver.findElement(By.id(WarrantyRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(WarrantyChkDropDown)));

		driver.findElement(By.id(WarrantyChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(WarrantyChkYesDD)));
		driver.findElement(By.id(WarrantyChkYesDD)).click();

		// Natis

		driver.findElement(By.id(EnatisRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(EnatisRecYesDD)));
		driver.findElement(By.id(EnatisRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(EnatisChkDropDown)));

		driver.findElement(By.id(EnatisChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(EnatisYesDD)));
		driver.findElement(By.id(EnatisYesDD)).click();

		// Podium

		driver.findElement(By.id("cmbdocPodium_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocPodium_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocPodium_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocPodium_chk_B-1")));

		driver.findElement(By.id("cmbdocPodium_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocPodium_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocPodium_chk_DDD_L_LBI1T0")).click();

		// credit Application

		driver.findElement(By.id("cmbdocCreditApplication_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocCreditApplication_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocCreditApplication_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocCreditApplication_chk_B-1")));

		driver.findElement(By.id("cmbdocCreditApplication_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocCreditApplication_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocCreditApplication_chk_DDD_L_LBI1T0")).click();

		// Contract

		driver.findElement(By.id("F_cmbdocSCD_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("F_cmbdocSCD_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("F_cmbdocSCD_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("F_cmbdocSCD_chk_B-1")));

		driver.findElement(By.id("F_cmbdocSCD_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("F_cmbdocSCD_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("F_cmbdocSCD_chk_DDD_L_LBI1T0")).click();

		// Warranty on veracity of Credit Application

		driver.findElement(By.id("cmbdocWarrantyVeracity_CreditApplication_rcv_B-1")).click();
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("cmbdocWarrantyVeracity_CreditApplication_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocWarrantyVeracity_CreditApplication_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocWarrantyVeracity_CreditApplication_chk_B-1")));

		driver.findElement(By.id("cmbdocWarrantyVeracity_CreditApplication_chk_B-1")).click();
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("cmbdocWarrantyVeracity_CreditApplication_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocWarrantyVeracity_CreditApplication_chk_DDD_L_LBI1T0")).click();

		// Annexure A

		driver.findElement(By.id("cmbdocAnnexureA_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocAnnexureA_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocAnnexureA_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocAnnexureA_chk_B-1")));

		driver.findElement(By.id("cmbdocAnnexureA_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocAnnexureA_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocAnnexureA_chk_DDD_L_LBI1T0")).click();

		// Debit Order authorisation

		driver.findElement(By.id("cmbdocDebitOrderAuthorisation_rcv_B-1")).click();
		wait1.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocDebitOrderAuthorisation_rcv_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocDebitOrderAuthorisation_rcv_DDD_L_LBI2T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocDebitOrderAuthorisation_chk_B-1")));

		driver.findElement(By.id("cmbdocDebitOrderAuthorisation_chk_B-1")).click();
		wait1.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocDebitOrderAuthorisation_chk_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocDebitOrderAuthorisation_chk_DDD_L_LBI2T0")).click();

		// Bank Statement

		driver.findElement(By.id("cmbdocBankStatement_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocBankStatement_rcv_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocBankStatement_rcv_DDD_L_LBI2T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocBankStatement_chk_B-1")));

		driver.findElement(By.id("cmbdocBankStatement_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocBankStatement_chk_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocBankStatement_chk_DDD_L_LBI2T0")).click();

		// Biometric verification

		driver.findElement(By.id("cmbdocBiometricVerification_rcv_B-1")).click();
		wait1.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocBiometricVerification_rcv_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocBiometricVerification_rcv_DDD_L_LBI2T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocBiometricVerification_chk_B-1")));

		driver.findElement(By.id("cmbdocBiometricVerification_chk_B-1")).click();
		wait1.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocBiometricVerification_chk_DDD_L_LBI2T0")));
		driver.findElement(By.id("cmbdocBiometricVerification_chk_DDD_L_LBI2T0")).click();

		// ID copy

		driver.findElement(By.id("cmbdocIDCopy_rcv_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocIDCopy_rcv_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocIDCopy_rcv_DDD_L_LBI1T0")).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id("cmbdocIDCopy_chk_B-1")));

		driver.findElement(By.id("cmbdocIDCopy_chk_B-1")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbdocIDCopy_chk_DDD_L_LBI1T0")));
		driver.findElement(By.id("cmbdocIDCopy_chk_DDD_L_LBI1T0")).click();

		// Proof of Address

		driver.findElement(By.id(ProofofAddressRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofAddressRecYesDD)));
		driver.findElement(By.id(ProofofAddressRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ProofofAddressChkDropDown)));

		driver.findElement(By.id(ProofofAddressChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofAddressChkYesDD)));
		driver.findElement(By.id(ProofofAddressChkYesDD)).click();

		// Income calculator

		driver.findElement(By.id(IncomeRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(IncomeRecYesDD)));
		driver.findElement(By.id(IncomeRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(IncomeChkDropDown)));

		driver.findElement(By.id(IncomeChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(IncomeChkYesDD)));
		driver.findElement(By.id(IncomeChkYesDD)).click();

		// Payslip

		driver.findElement(By.id(PayslipRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(PayslipRecYesDD)));
		driver.findElement(By.id(PayslipRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(PayslipChkDropDown)));

		driver.findElement(By.id(PayslipChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(PayslipChkYesDD)));
		driver.findElement(By.id(PayslipChkYesDD)).click();

		// Proof of Insurance Cover

		driver.findElement(By.id(ProofofInsuranceRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofInsuranceRecYesDD)));
		driver.findElement(By.id(ProofofInsuranceRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ProofofInsuranceChkDropDown)));

		driver.findElement(By.id(ProofofInsuranceChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofInsuranceChkYesDD)));
		driver.findElement(By.id(ProofofInsuranceChkYesDD)).click();

//		// Is Client Paying By Debit Order? *
//
//		if (!(driver.findElement(By.id("cmbDocDebit_B-1")).getAttribute("class")
//				.contains("dxeButton dxeButtonEditButton dxeDisabled dxeButtonDisabled"))) {
//
//			driver.findElement(By.id("cmbDocDebit_B-1")).click();
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbDocDebit_DDD_L_LBI2T0")));
//			driver.findElement(By.id("cmbDocDebit_DDD_L_LBI2T0")).click(); // select No
//		}
//
//		Thread.sleep(2000);
//		if (optimaHomePage.isElementPresent(By.id("FC_chkOverrideDebitOrder_S_D"), driver)) {
//			if (driver.findElement(By.id("FC_chkOverrideDebitOrder_S_D")).getAttribute("class")
//					.contains("dxWeb_edtCheckBoxUnchecked dxICheckBox dxichSys")) {
//
//				driver.findElement(By.id("FC_chkOverrideDebitOrder_S_D")).click();
//			}
//		}
//		// add comment
//		WebElement comment = driver.findElement(By.id("txtComments"));
//		comment.clear();
//		comment.sendKeys("Test");
//
//		// Save click
//
//		driver.findElement(By.id("btnDocSave_CD")).click();
//
//		Thread.sleep(4000);
//
//		driver.switchTo().activeElement();
//
//		if (driver.findElement(By.id("ManagerOverridePopup_PWH-1T")).isDisplayed()) {
//			System.out.println("\nDeal is going to manager override");
//
//			// manager override action
//
//			driver.findElement(By.id("cmbManagerPCOverrideReasons_B-1")).click();
//
//			Thread.sleep(4000);
//			driver.findElement(By.id("cmbManagerPCOverrideReasons_DDD_L_LBI1T0")).click();
//
//			// upload file
//			Thread.sleep(4000);
//			driver.findElement(By.id("fileUploadPremiumClient_Browse0")).click();
//			Thread.sleep(4000);
//			Runtime.getRuntime().exec("E:\\Optima\\Open.exe");
//			Thread.sleep(3000);
//
//			driver.findElement(By.id("btnPCSaveManagerOverride_CD")).click();
//
//			new WebDriverWait(driver, 80)
//					.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//
//			driver.findElement(By.id("btnCustomMessageClose")).click();
//
//			new WebDriverWait(driver, 80)
//					.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));
//
//			optimaHomePage.clickOnAllIncidentInbox(driver);
//
//			optimaHomePage.searchDealthroughIncidentNumber(driver);
//
//			optimaHomePage.clickOnIncidentLink(driver);
//
//			// optimaHomePage.retriveDeal(driver);
//
//			WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
//			Thread.sleep(8000);
//			retriveDealbtn1.click();
//
//			WebElement okbtn1 = driver.findElement(By.id(InformationpopupOK));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));
//
//			okbtn1.click();
//
//			Thread.sleep(8000);
//			optimaHomePage.clickOnMyDeals(driver);
//
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));
//
//			// go to bank account tab
//
//			driver.findElement(By.id("tbContractConfirmation_T1T")).click();
//
//			Thread.sleep(2000);
//
//			// select yes
//
//			driver.findElement(By.id("rbPaymentOverride_RB0_I_D")).click();
//
//			// click on save
//
//			driver.findElement(By.id("btnPCSavePaymentOverride_CD")).click();
//
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//
//			driver.findElement(By.id("btnCustomMessageClose")).click();
//
//			new WebDriverWait(driver, 80)
//					.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));
//
//			optimaHomePage.clickOnAllIncidentInbox(driver);
//
//			optimaHomePage.searchDealthroughIncidentNumber(driver);
//
//			optimaHomePage.clickOnIncidentLink(driver);
//
//			// optimaHomePage.retriveDeal(driver);
//
//			WebElement retriveDealbtn2 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
//			Thread.sleep(8000);
//			retriveDealbtn2.click();
//
//			WebElement okbtn2 = driver.findElement(By.id(InformationpopupOK));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));
//
//			okbtn2.click();
//
//			Thread.sleep(8000);
//			optimaHomePage.clickOnMyDeals(driver);
//			
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(FirstCheckerTab)));
//			
//			driver.findElement(By.id("tbContractConfirmation_T1T")).click();
//
//			Thread.sleep(2000);
//
//			// type of account
//			
//			driver.findElement(By.id("cmbTypeofAccount_B-1")).click();
//			
//			Thread.sleep(2200);
//			
//			driver.findElement(By.id("cmbTypeofAccount_DDD_L_LBI2T0")).click();
//			
//			// bank name
//			
//			driver.findElement(By.id("cmbBankNames_B-1")).click();
//			
//			Thread.sleep(2200);
//			
//			driver.findElement(By.id("cmbBankNames_DDD_L_LBI2T0")).click();
//			
//			Thread.sleep(8000);
//			
//			driver.findElement(By.id("txtAccountNumber")).sendKeys("576887324763257");
//			
//			// click on save
//			
//			Thread.sleep(2200);
//			
//			driver.findElement(By.id("btnDocSaveFirstChecker_CD")).click();
//			
//			Thread.sleep(2200);
//			
//			driver.findElement(By.id(InformationpopupOK)).click();
//			
//			Thread.sleep(8000);
//			
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnDocProceed_CD")));
//			driver.findElement(By.id("btnDocProceed_CD")).click();
//
//			// yes button click after proceed
//
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnConfirmOK_CD")));
//			driver.findElement(By.id("btnConfirmOK_CD")).click();
//			
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
//			
//			driver.findElement(By.id(InformationpopupOK)).click();
//			
//		}

		// add comment
		WebElement comment = driver.findElement(By.id(Addcomment));
		comment.clear();
		comment.sendKeys("Test");

		// Save click

		driver.findElement(By.id(SaveButton)).click();

		// OkButton click
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(InformationpopupOK)));
		driver.findElement(By.id(InformationpopupOK)).click();

		// proceed button click

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(ProceedButton)));
		driver.findElement(By.id(ProceedButton)).click();

		// yes button click after proceed

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(ConfirmPopupOK)));
		driver.findElement(By.id(ConfirmPopupOK)).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));
		driver.findElement(By.id(InformationpopupOK)).click();
	}

}
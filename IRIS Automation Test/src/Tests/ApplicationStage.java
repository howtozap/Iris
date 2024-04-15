package Tests;

import static org.testng.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;

public class ApplicationStage extends BasicSetup {

	public static WebDriver driver;

	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	
	int flag_insurance = 0;
	int flag_finance = 0;
	static String Browse_finance = "fileUploadFinancialProItem_Browse0";
	String ApplicantTitle_ID = "rndPnlApplicant_RPHT";
	String MaritalStatus_ID = "cmbMaritalStatus_I";
	String MarriageType_ID = "cmbMarriageType_I";
	String TribalLawType_ID = "cmbTribalLaw_B-1";
	String TribalLaw_ID = "cmbTribalLaw_DDD_L_LBI1T0";
	String overridProccedId = "btnProceedIncidentPopup_CD";
	String overridePopuptextId = "popManagerEscalation_PWH-1T";
	String WorkTelephoneDailing_ID = "txtWorkDialling";
	String WorkTelephoneNumber_ID = "txtWorkTelephoneNumber";
	String CreditCheckConsent_ID = "chkIsConsent";
	String PayslipConsentCheck_ID = "chkIsConsentPayslip";
	String BankConsentCheck_ID = "chkIsConsentBankStatement";
	String PostalAddressSameCHKBox_ID = "chkIsPostalSame";
	String PostalConfirmPopupYesBTN_ID = "btnConfirmOK_CD";
	String postalConfirmationpopup_ID = "pcConfirmDialog_PWH-1T";
	String VehicleDetailsTab_xpath = "//a[@id='tbApplicatioDetailsTab_T1T']";
	String VehicleDetailsTab_ID = "rndPnlVehicleDetails_RPHT";
	String SaveButton_ID = "btnSaveApplication_CD";
	String informationpopup = "customMessageText";
	String informationpopupOK = "btnCustomMessageClose_CD";
	String LoanTerm_ID = "txtRequestedLoanTerm";
	String ProceedIncident_ID = "btnProceedIncident_CD";
	String ConfirmPopup_ID = "confirmText";
	String ConfirmPopupYes = "btnConfirmOK";
	String ManagerOverridepopup_ID = "popManagerEscalation_PWH-1T";
	String BusinessRulesTab_ID = "tbManagerEscalationTab_T1T";
	String waitformanageroverridepopup = "grdRule_tcheader0";
	String referforoverridebutton = "btnReferForOverride_CD";
	String waitformanagerscreentoload = "tbManagerEscalationTabManger_AT0T";
	String clickonbusinessrulestab = "tbManagerEscalationTabManger_T1T";
	String Allincidenttab_ID = "nbClientSideEvents_I0i2_";
	String ApplicantTab_ID = "tbApplicatioDetailsTab_AT0T";
	String rulesummaryTab_ID = "tbSummaryScreen_AT0T";
	String InformationpopupOK = "btnCustomMessageClose_CD";

	String MainMenu_DXI1_= "MainMenu_DXI1_";

	
	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {
		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}

	@BeforeTest(enabled = true)
	public void setupClass() throws InterruptedException, IOException {
		ApplicationStage applicationStage = new ApplicationStage();
	}

	public ApplicationStage() {
		driver = getdriver();
	}

	@Test(priority = 3, enabled = true)
	public void applicationScreen() throws InterruptedException, IOException, UnsupportedFlavorException {

		Thread.sleep(10000);

		int flag[] = iRISHomePage.checkDealinMyDeals(driver);

		if (flag[1] == 0) {

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);
//
//		// optimaHomePage.searchSeritiDealThroughReferenceNumber(driver);
//		// optimaHomePage.searchSignioDealThroughReferenceNumber(driver);
//
			Thread.sleep(6000);

			iRISHomePage.clickOnIncidentLink(driver);
			Thread.sleep(8000);

			if(flag[0]==0) {
				
				WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
				Thread.sleep(8000);
				retriveDealbtn.click();

				//WebElement okbtn = driver.findElement(By.id(InformationpopupOK));
				//new WebDriverWait(driver, 30)
					//	.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));

				//okbtn.click();
				Thread.sleep(10000);
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
				//driver.findElement(By.id(clickonbusinessrulestab)).click();
			//	driver.findElement(By.id(MainMenu_DXI1_)).click();
				//WebElement arrowbtn = driver.findElement(By.id(MainMenu_DXI1_)).click();
//				new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id(ContentSplitter_1_S_CB)));
//
//				arrowbtn.click();
				
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

		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ApplicantTitle_ID)));

		Thread.sleep(2000);

		// Marriage Tpye check

		String copy = Keys.chord(Keys.CONTROL, Keys.chord("c"));
		WebElement maritalstatus = driver.findElement(By.id(MaritalStatus_ID));
		Thread.sleep(1000);
		// creditLifeCompany.click();
		Thread.sleep(1000);
		maritalstatus.sendKeys(Keys.CONTROL + "a");
		maritalstatus.sendKeys(copy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String maritalstatus1 = (String) contents.getTransferData(DataFlavor.stringFlavor);
		System.out.println("Marriage Type is:" + maritalstatus1);
		Thread.sleep(6000);

		if (maritalstatus1.equalsIgnoreCase("Married")) {

			String copy1 = Keys.chord(Keys.CONTROL, Keys.chord("c"));
			WebElement marriagetype = driver.findElement(By.id(MarriageType_ID));
			Thread.sleep(1000);
			// creditLifeCompany.click();
			Thread.sleep(1000);
			marriagetype.sendKeys(Keys.CONTROL + "a");
			marriagetype.sendKeys(copy1);
			Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents1 = clipboard1.getContents(null);
			String marriagetype1 = (String) contents1.getTransferData(DataFlavor.stringFlavor);
			System.out.println("Marriage Type is:" + marriagetype1);
			Thread.sleep(6000);

			if (marriagetype1.equalsIgnoreCase("Tribal Law")) {

				driver.findElement(By.id(TribalLawType_ID)).click();
				Thread.sleep(2000);
				driver.findElement(By.id(TribalLaw_ID)).click();

			}

			else {

				System.out.println("\nMarriage type is different.");

			}
		} else {

			System.out.println("\nMarital Status is different.");

		}

		// Consent To Undertake Credit Checks Received check box check

		WebElement creditChecks = driver.findElement(By.id(CreditCheckConsent_ID));

		if (creditChecks.isSelected()) {
			System.out.print("\n Credit Checks Checkbox is already checked");
		}

		else {
			System.out.print("\nCheckbox is NOT checked please check it");
			creditChecks.click();
		}

		// Consent to request Payslips received*

		WebElement payslipChecks = driver.findElement(By.id(PayslipConsentCheck_ID));

		if (payslipChecks.isSelected()) {
			System.out.print("\n Payslip Checks Checkbox is already checked");
		}

		else {
			System.out.print("Checkbox is NOT checked please check it");
			payslipChecks.click();
		}

		// Consent to request Bank Statements received*

		WebElement bankChecks = driver.findElement(By.id(BankConsentCheck_ID));

		if (bankChecks.isSelected()) {
			System.out.print(" Bank Checks Checkbox is already checked");
		}

		else {
			System.out.print("Checkbox is NOT checked please check it");
			creditChecks.click();
		}

		// Postal address check box check

		WebElement postalAddress = driver.findElement(By.id(PostalAddressSameCHKBox_ID));

		if (postalAddress.isSelected()) {
			System.out.print(" Postal Address Checkbox is already checked");
		}

		else {
			System.out.print("Checkbox is NOT checked please check it");
			postalAddress.click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(postalConfirmationpopup_ID)));

			driver.findElement(By.id(PostalConfirmPopupYesBTN_ID)).click();
		}

		// change telephone number

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(WorkTelephoneDailing_ID)));

//		WebElement diallingDigit = driver.findElement(By.id(WorkTelephoneDailing_ID));
//		diallingDigit.clear();
//		diallingDigit.sendKeys("520");

		WebElement telephoneNumber = driver.findElement(By.id(WorkTelephoneNumber_ID));
		String TeleNumber = telephoneNumber.getText();
		int Length_Tele = TeleNumber.length();

		System.out.println("The number of digits of telephone number is: " + Length_Tele);

		if (Length_Tele < 7) {
			System.out.println("Telephone number is changed");
			telephoneNumber.clear();
			telephoneNumber.sendKeys("1640701");
		}

		// change Cellular Number(next of kin)

		// click on vehicle detail tab

		Thread.sleep(5000);

		driver.findElement(By.xpath(VehicleDetailsTab_xpath)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(VehicleDetailsTab_ID)));
		
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

//		// click on beneficiary unknown
//
//		WebElement BeneficiaryCB = driver.findElement(By.id("chkCreditLifeBeneficiaryUnknown"));
//
//		if (BeneficiaryCB.isSelected()) {
//			System.out.print(" Beneficiary Checkbox is already checked");
//		}
//
//		else {
//			System.out.print("Checkbox is NOT checked");
//			BeneficiaryCB.click();
//		}

		// click on save button

		driver.findElement(By.id(SaveButton_ID)).click();
		
		//upload document for product exclusion
		
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
		}
		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(informationpopup)));

		if (driver.findElement(By.id(informationpopup)).getText().contains(
				"Please enter valid information for the * marked field(s). Term can not be greater than 48 for consumer buy-back deal. ")) {

			driver.findElement(By.id(informationpopupOK)).click();
			driver.findElement(By.id(LoanTerm_ID)).sendKeys("48");
			driver.findElement(By.id(SaveButton_ID)).click();

		}
		assertTrue(driver.findElement(By.id(informationpopup)).isDisplayed(), "Information saved successfully.");

		// click on ok button

		driver.findElement(By.id(informationpopupOK)).click();

		Thread.sleep(10000);

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ProceedIncident_ID)));

		Thread.sleep(12000);

		driver.findElement(By.id(ProceedIncident_ID)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ConfirmPopup_ID)));

		assertTrue(driver.findElement(By.id(ConfirmPopup_ID)).isDisplayed(),
				"Are you sure you want to proceed to the next step?");

		driver.findElement(By.id(ConfirmPopupYes)).click();

		Thread.sleep(30000);

		driver.switchTo().activeElement();

		if (driver.findElement(By.id(ManagerOverridepopup_ID)).isDisplayed()) {
			System.out.println("\nDeal is going to manager escalation");

			driver.findElement(By.id(BusinessRulesTab_ID)).click();

			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(waitformanageroverridepopup)));

			List<WebElement> rows = driver
					.findElements(By.xpath("//table[@id='grdRule_DXMainTable']/tbody/tr[position()!=1]"));
			System.out.println(rows.size());

			for (int i = 0; i < rows.size(); i++) {
				Thread.sleep(2000);
				WebElement selectCheckbox = rows.get(i).findElement(By.xpath("td[1]/span"));
				if (selectCheckbox.getAttribute("class").contains("dxWeb_edtCheckBoxChecked dxICheckBox dxichSys")) {
					System.out.println("\nSelect check box is checked");

					WebElement reasonBox = rows.get(i).findElement(By.xpath("td[4]/table/tbody/tr/td/input"));
					reasonBox.clear();
					reasonBox.sendKeys("Test" + Integer.toString(i));
					Thread.sleep(5000);
				}
			}
			WebElement referToOverride = driver.findElement(By.id(referforoverridebutton));
			referToOverride.click();

			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(informationpopup)));

			Assert.assertTrue(driver.findElement(By.id(informationpopup)).isDisplayed(),
					"Incident referred for manager override successfully.");

			driver.findElement(By.id(informationpopupOK)).click();

			Thread.sleep(5000);

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			Thread.sleep(6000);

			iRISHomePage.clickOnIncidentLink(driver);

			WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
			Thread.sleep(8000);
			retriveDealbtn.click();

//			WebElement okbtn = driver.findElement(By.id("btnCustomMessageClose_CD"));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
//
//			okbtn.click();
			Thread.sleep(8000);
			iRISHomePage.clickOnMyDeals(driver);

			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(waitformanagerscreentoload)));

			driver.findElement(By.id(clickonbusinessrulestab)).click();

			List<WebElement> rowsCount = driver.findElements(
					By.xpath("//table[@id='grdManagerEscalationRule_DXMainTable']/tbody/tr[position()!=1]"));
			System.out.println(rowsCount.size());

			for (int j = 0; j < rowsCount.size(); j++) {

				Thread.sleep(2000);
				WebElement comment = rowsCount.get(j).findElement(By.xpath("td[6]/table/tbody/tr/td/input"));
				comment.clear();
				comment.sendKeys("Test" + Integer.toString(j));

				Thread.sleep(5000);

				WebElement dropdown = rowsCount.get(j).findElement(By.xpath("td[5]/table/tbody/tr/td[3]"));
				dropdown.click();

				Thread.sleep(5000);

				WebElement yesOption = rowsCount.get(j)
						.findElement(By.xpath("td[5]/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr/td"));
				Thread.sleep(2000);
				yesOption.click();
				Thread.sleep(5000);
			}

			Thread.sleep(2000);

			driver.findElement(By.id(overridProccedId)).click();

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(ConfirmPopup_ID)));

			Assert.assertTrue(driver.findElement(By.id(ConfirmPopup_ID)).isDisplayed(),
					"Incident will be assigned back to the credit assessor assessing the deal and will be removed from your inbox. Are you sure you want to proceed?");

			Assert.assertEquals(driver.findElement(By.id(ConfirmPopup_ID)).getText(),
					"Incident will be assigned back to the credit assessor assessing the deal and will be removed from your inbox. Are you sure you want to proceed?");

			driver.findElement(By.id(ConfirmPopupYes)).click();

			new WebDriverWait(driver, 20)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(informationpopup)));

			Assert.assertEquals(driver.findElement(By.id(informationpopup)).getText(),
					"Incident is redirected to Inbox.");

			driver.findElement(By.id(informationpopupOK)).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(Allincidenttab_ID)));

			Thread.sleep(5000);

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			Thread.sleep(6000);

			iRISHomePage.clickOnIncidentLink(driver);

			WebElement retriveDealbtn1 = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
			Thread.sleep(8000);
			retriveDealbtn1.click();

//			WebElement okbtn1 = driver.findElement(By.id("btnCustomMessageClose_CD"));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
//
//			okbtn1.click();
			Thread.sleep(8000);
			iRISHomePage.clickOnMyDeals(driver);

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(ApplicantTab_ID)));

			new WebDriverWait(driver, 60).until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(By.id("ProceedIncident_ID")),
					ExpectedConditions.elementToBeClickable(By.id("ProceedIncident_ID"))));

			// if (driver.findElement(By.id("btnIncidentDuplicate_CD")).isDisplayed()) {

			driver.findElement(By.id("ProceedIncident_ID")).click();
//


			//driver.findElement(By.id(ProceedIncident_ID)).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ConfirmPopup_ID)));

			assertTrue(driver.findElement(By.id(ConfirmPopup_ID)).isDisplayed(),
					"Are you sure you want to proceed to the next step?");

			driver.findElement(By.id(ConfirmPopupYes)).click();

			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(rulesummaryTab_ID)));

		}

		else {

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(rulesummaryTab_ID)));

			System.out.println("\ndeal is navigated on summary screen");

		}

	}
}

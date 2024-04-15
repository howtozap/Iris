package Tests;

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
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.SummaryScreen;

public class CreditDecisionStage extends BasicSetup {

	public static WebDriver driver;
	String SummaryTab_ID = "tbSummaryScreen_AT0T";
	String TakeCreditDecision_ID = "btnTakeDecision_CD";
	String CreditDecisionpopup_ID = "popCtrlTakeDecision_PWH-1T";
	String ConditionalApprovalCheckbox = "chkManualCreditDecision";
	String ConditionalApprovalMessage = "txtConditionalOfferMessage";
	String CreditDecisionReason = "txtCreditDecisionReason";
	String SaveDecision = "btnSaveDecision_CD";
	String Informationpopup = "customMessageText";
	String InformationpopupOK = "btnCustomMessageClose_CD";
	String ProceedIncident_ID = "btnProceedIncident_CD";
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	SummaryScreen summaryScreen = new SummaryScreen();

	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {

		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}

	@BeforeTest(enabled = true)
	public void setupClass() throws InterruptedException, IOException {
		CreditDecisionStage creditDecisionStage = new CreditDecisionStage();
	}

	public CreditDecisionStage() {
		driver = getdriver();
	}
	
	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/CreditDecisionStage.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	@Test(priority = 4)
	public void creditDecision() throws InterruptedException, IOException {

		Thread.sleep(10000);
		
		String conditionalApproval = readInputData("ConditionalApproval");
		int conditionalApprovalflag = Integer.parseInt(conditionalApproval);  
		String contidionalapprovalmessage = "Conditional Approval Test";

		iRISHomePage.clickOnMyDeals(driver);

//		WebDriverWait wait1 = new WebDriverWait(driver, 100);
//		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(SummaryTab_ID)));

		String creditdecision = "Yes";

		SummaryScreen.ruleOverriding(driver, creditdecision);

		if (creditdecision.equalsIgnoreCase("Yes")) {

			// click on take credit decision button
			driver.findElement(By.id(TakeCreditDecision_ID)).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(CreditDecisionpopup_ID)));
			
			if(conditionalApprovalflag==1) {
				
				driver.findElement(By.id(ConditionalApprovalCheckbox)).click();
				driver.findElement(By.id(ConditionalApprovalMessage)).sendKeys(contidionalapprovalmessage);
				
			}

			// Enter reason for decision
			driver.findElement(By.id(CreditDecisionReason)).sendKeys("Automation Testing Purpose");

			// click on save button

			new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id(SaveDecision)));

			driver.findElement(By.id(SaveDecision)).click();

			// wait for success message
			new WebDriverWait(driver, 500)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

//			Assert.assertEquals(driver.findElement(By.id(Informationpopup)).getText(),
//					"Credit Decision captured successfully \n" +
//							"Incident Proceeded to next stage\n" +
//							"Podium is sent to dealer.", "Credit decision captured");

			// click on ok button
			new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)))
					.click();

		}

		// wait until proceed button is not visible
//		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(By.id(ProceedIncident_ID)))
//				.click();
//
//		// podium messages
//
//		new WebDriverWait(driver, 180).until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
//
//		Assert.assertEquals(driver.findElement(By.id(Informationpopup)).getText(),
//				"Podium sent to dealer. Incident will be moved to Document Validation Inbox.",
//				"Podium send and deal goes to document validation");

		// click on ok

//		new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)))
//				.click();
		
		if(conditionalApprovalflag==1) {
			
			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			Thread.sleep(6000);

			iRISHomePage.clickOnIncidentLink(driver);
			
			new WebDriverWait(driver, 180).until(ExpectedConditions.visibilityOfElementLocated(By.id("tbDocumentVerification_T3T")));
			
			driver.findElement(By.id("tbDocumentVerification_T3T")).click();
			
			new WebDriverWait(driver, 180).until(ExpectedConditions.visibilityOfElementLocated(By.id("grdNotes_DXFREditorcol0_I")));
			
			WebElement searchnotes= driver.findElement(By.id("grdNotes_DXFREditorcol0_I"));
			
			searchnotes.sendKeys("Incident has been approved subject to");
			
			searchnotes.sendKeys(Keys.ENTER);
			
			Thread.sleep(10000);
			
			String notetext=driver.findElement(By.xpath("//*[@id='grdNotes_DXDataRow0']/td[1]")).getText();
			System.out.println(notetext);
			
			if(notetext.contains("Conditional offer: "+contidionalapprovalmessage)) {
				
				System.out.println("Conditional Approval message added");
				
			}
			
		}
	}

}

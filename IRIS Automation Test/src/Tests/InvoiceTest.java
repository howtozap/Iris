package Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.InvoiceStage;
import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.SecondCheckerScreen;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvoiceTest {
	public static WebDriver driver;
	// static String IncidentNumber = "113015";
	static String VIN = "GF554486666633227";

	String invoiceNumber = "90";
	static String EngineNumberOfVehicle = "67223";
	static String ValidationLink_LinkTest = "Validation";
	static String InvoiceLink_LinkTest = "Invoice";

	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	InvoiceStage invoiceStage = new InvoiceStage();

	SecondCheckerScreen secondCheckerScreen = new SecondCheckerScreen();

	@BeforeSuite(enabled = true)
	public void setup() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeTest(enabled = true)
	public void login() throws InterruptedException, IOException {

		 iRISLoginPage.NavigateToOptima(driver);
		 iRISLoginPage.LoginToOptima(driver);

		
	}

	
	@Test(priority = 7)
	public void invoiceScreen() throws InterruptedException, IOException {

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		iRISHomePage.clickOnAllIncidentInbox(driver);

		iRISHomePage.searchDealthroughIncidentNumber(driver);

		iRISHomePage.clickOnIncidentLink(driver);

		// optimaHomePage.retriveDeal(driver);
		iRISHomePage.retriveDeal(driver);
//		WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
//		Thread.sleep(8000);
//		retriveDealbtn.click();

//		WebElement okbtn = driver.findElement(By.id("btnCustomMessageClose_CD"));
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
//
//		okbtn.click();
		Thread.sleep(8000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("overlayElementId")));


		//iRISHomePage.clickOnMyDeals(driver);
	//iRISHomePage.retriveDeal(driver);

//		WebElement searchedlink = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr[2]/td/div[1]/table/tbody/tr/td[3]/div/table/tbody/tr/td/div/div[2]/form/div[3]/table/tbody/tr/td/div[3]/table/tbody/tr[2]/td[1]/a"));
//		searchedlink.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 200);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlInvoice_RPHT")));

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement invoiceNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtInvoiceNumber")));
		invoiceNumber.clear();
//		WebElement invoiceNumber = driver.findElement(By.id("txtInvoiceNumber"));
//		invoiceNumber.clear();
		invoiceNumber.sendKeys("90");

		Thread.sleep(1000);
		driver.findElement(By.id("cmbDepositPaidTo_B-1")).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmbDepositPaidTo_DDD_L_LBI2T0")));

		driver.findElement(By.id("cmbDepositPaidTo_DDD_L_LBI2T0")).click();
		
		Thread.sleep(2000);

		WebElement engineNumber = driver.findElement(By.id("txtInvoiceEngineNumber"));
		engineNumber.clear();
		engineNumber.sendKeys("67223");

		WebElement VINNumber = driver.findElement(By.id("txtInvoiceVIN"));
		VINNumber.clear();

		// generate VIN random number and send
		String VINNum = invoiceStage.getAlphaNumericString();
		System.out.println("Generated invoice number=" + VINNum);
		VINNumber.sendKeys(VINNum);
		
		
		WebElement vahicleType= driver.findElement(By.xpath("//*[@id='divCustomerAccountDetails']/table/tbody/tr[2]/td[8]"));
		String type= vahicleType.getText();
		System.out.print("vahicle type is:" +type);
		
		if(type.equals("Repo")) {
			WebElement enatis = driver.findElement(By.id("txtInvoiceEnatis"));
			enatis.clear();
			enatis.sendKeys("Aish");
		}
		
		// Beneficiary Unknown tick
		
		Thread.sleep(10000);

		WebElement creditLifeCompany= driver.findElement(By.id("cmbCreditLifeCompanyName_I"));
		String company= creditLifeCompany.getText();
		
		if(company.equals("Guardrisk")) {
			WebElement BeneficiaryCB = driver.findElement(By.id("chkCreditLifeBeneficiaryUnknown"));

			if (BeneficiaryCB.isSelected()) {
				System.out.println("Checkbox is already checked");
			}

			else {
				System.out.println("Checkbox is NOT checked");
				BeneficiaryCB.click();
			}
			
		}
		
		// click on save button

		driver.findElement(By.id("btnSaveInvoice_CD")).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));

		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
				"Are you sure you want to save the information?");

		// click on yes button

		driver.findElement(By.id("btnConfirmOK_CD")).click();

		WebDriverWait wait2 = new WebDriverWait(driver, 600);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		
		//invoiceStage.errorMessage(driver);
	//	invoiceStage.errorMessageHandling(driver);
		

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
		
		
		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlInvoice_RPHT")));

		// click on generate contract button

		wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnGenerateContract_CD")));

		driver.findElement(By.id("btnGenerateContract_CD")).click();

		// document validation message for dealer invoice

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		

		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
				"Document Validation Pending for Dealer Invoice");

		driver.findElement(By.id("btnCustomMessageClose")).click();

		// go to validation stage

		driver.findElement(By.linkText(ValidationLink_LinkTest)).click(); // Click on Validation Link.

		driver.findElement(By.id("btnConfirmNo1_CD")).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlDocumentValidation_RPHT")));

		// click on validation link for invoice doc

         invoiceStage.validateDealerInvoive(driver);
		
//         invoiceStage.uploadAndValidateCreditLifePolicy(driver);
//         
//         invoiceStage.uploadAndValidateInsurancePolicy(driver);
         
       //  invoiceStage.uploadAndValidateDICDocument(driver);
		// click on invoice stage

		driver.findElement(By.linkText(InvoiceLink_LinkTest)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlInvoice_RPHT")));

		// click on Generate contract

		driver.findElement(By.id("btnGenerateContract_CD")).click();
		
		// success messeage

		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		
		//invoiceStage.errorMessage(driver);

		// click on ok button

		driver.findElement(By.id("btnCustomMessageClose")).click();

		// wait until invoice screen load

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlInvoice_RPHT")));

		// click on book cartrack
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("btnBookCarTrackingDevice_CD")));

		driver.findElement(By.id("btnBookCarTrackingDevice_CD")).click();

		// confirmation message

		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));

		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(),
				"Tracking Device booked successfully.");

		// click on ok button

		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

		// wait until proceed button is not clickable and then click

		wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnProceedIncident_CD")));

		driver.findElement(By.id("btnProceedIncident_CD")).click();

		// confirmation message

		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmText")));

		assertTrue(driver.findElement(By.id("confirmText")).isDisplayed(),
				"Are you sure you want to proceed to the next step?");

		// click on yes button

		driver.findElement(By.id("btnConfirmOK_CD")).click();

		// confirmation message

		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));

		assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(), "Incident navigated successfully.");

		// click on ok button

		driver.findElement(By.id("btnCustomMessageClose_CD")).click();

	}

}

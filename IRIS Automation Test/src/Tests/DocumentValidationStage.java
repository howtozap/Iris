package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.Validation;

public class DocumentValidationStage extends BasicSetup {

	public WebDriver driver;
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	Validation validation = new Validation();
	static String ValidationLink_LinkTest = "Validation";
	String proceedButton = "btnContinueWork_CD";
	String AMLOverridebutton = "btnoverrideamlrefer";
	String InformationpopupOK = "btnCustomMessageClose_CD";
	String amlComment = "txtamlComment";
	String OverrideReasoncomment = "valamlcommnet_manager";
	String AMLOverridepopup_ID = "AMLOverridePopup_PW-1";
	String AmlOverrideButton_ID = "btnAMLoverride_CD";
	String AmlOverrideOkbutton_ID = "btnCustomMessageClose_CD";
	String Overridebutton_ID = "btnoverrideAML_CD";
	String radiobutton_ID = "rdoamloverrideyes";
	String radiooverrideYEsbutton_ID = "rdoamloverrideyes";
	String AMLmanageroverride_ID = "btnoverrideAML_CD";
	String amlmanagerComment = "txtamlComment_manager";
	String AMLmanageroverridepopup_ID ="AMLOverridePopup_PWH-1";

	String AMLresponseoverridepopup_ID ="btnCustomMessageClose_CD";
	String SaveOverridebutton_ID ="btnAMLoverride_CD";

	String Confirmpopup_ID ="pcCustomMessageDialog_PWC-1";

	String ConfirmpopupYes_ID ="btnCustomMessageClose_CD";
	String clientVerificationTab = "rndPnlKYC_RPHT";
	private boolean documentUploadAndWaiveHandled = false;
	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {
		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}

	@BeforeTest(enabled = true)
	public void setupClass() throws InterruptedException, IOException {
		driver = getdriver();
	}

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/validationStage.properties");
		Properties prop = new Properties();
		prop.load(input);
		return prop.getProperty(key);
	}
	//private boolean documentUploadAndWaiveHandled = false;
	@Test(priority = 5)
	public void validationOfDocument() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));

		int[] flag = iRISHomePage.checkDealinMyDeals(driver);

		if (flag[1] == 0) {
			handleIncident(flag);
		} else {
			System.out.println("Incident is present in My Deals");
		}

		iRISHomePage.clickOnMyDeals(driver);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlDocumentValidation_RPHT")));

		// Call handleDocumentUploadAndWaive() only if it hasn't been called before
		if (!documentUploadAndWaiveHandled) {
			handleDocumentUploadAndWaive();
			documentUploadAndWaiveHandled = true;  // Set the flag to true
		}

		handleAMLOverride();
		handleIncident(flag);
		handleAMLManagerOverride();

//handleIncident(flag);  // Assuming you want to retrieve the deal again
//    iRISHomePage.clickOnMyDeals(driver);
//    wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButton)));
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButton)));

		handleIncident(flag);
		//iRISHomePage.clickOnMyDeals(driver);
		WebDriverWait proceedButtonWait = new WebDriverWait(driver, 10);
		proceedButtonWait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButton)));
		driver.findElement(By.id(proceedButton)).click();

		new WebDriverWait(driver, 100)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id(Confirmpopup_ID)));

		Assert.assertTrue(driver.findElement(By.id(Confirmpopup_ID)).isDisplayed(),
				"Incident is proceeded to Contracts.");

		Assert.assertEquals(driver.findElement(By.id(Confirmpopup_ID)).getText(),
				"Incident is proceeded to Contracts.");

		driver.findElement(By.id(ConfirmpopupYes_ID)).click();

//		new WebDriverWait(driver, 20)
//				.until(ExpectedConditions.visibilityOfElementLocated(By.id(informationpopup)));
//
//		Assert.assertEquals(driver.findElement(By.id(informationpopup)).getText(),
//				"Incident is redirected to Inbox.");
//
//		driver.findElement(By.id(informationpopupOK)).click();




//		wait.until(ExpectedConditions.elementToBeClickable(By.id(Overridebutton_ID)));
//		driver.findElement(By.id(Overridebutton_ID)).click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(radiobutton_ID)));
//		driver.findElement(By.id(radiobutton_ID)).click();
//
//		driver.findElement(By.id(OverrideReasoncomment)).sendKeys("Automation Testing Purpose");
//		Thread.sleep(2000);
//
//		WebElement spanElement = driver.findElement(By.xpath("//div[@id='btnAMLoverride_CD']/span"));
//		spanElement.click();
//
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(AmlOverrideOkbutton_ID)));
//		driver.findElement(By.id(AmlOverrideOkbutton_ID)).click();


	}

	private void handleIncident(int[] flag) throws InterruptedException, IOException {
		iRISHomePage.clickOnAllIncidentInbox(driver);
		iRISHomePage.searchDealthroughIncidentNumber(driver);
		iRISHomePage.clickOnIncidentLink(driver);
		Thread.sleep(10000);

		if (flag[0] == 0) {
			WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
			Thread.sleep(8000);
			retriveDealbtn.click();

//			WebElement okbtn = driver.findElement(By.id(InformationpopupOK));
//			new WebDriverWait(driver, 30)
//					.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));
//
//			okbtn.click();
		} else {
			iRISHomePage.retriveDeal(driver);
		}

		Thread.sleep(10000);
	}

	private void handleDocumentUploadAndWaive() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 100);

		if (validation.readInputData("DocumentUpload").trim().equalsIgnoreCase("Yes")) {
			String doctoupload = readInputData("Doctouploadname");
			validation.DocsToUpload(driver, doctoupload);
			Thread.sleep(2000);
			validation.close_save_popup(driver);
			Thread.sleep(2000);
			validation.ChooseDocumentsToValidate(driver, doctoupload);
		}

		if (validation.readInputData("Waive").trim().equalsIgnoreCase("Yes")) {
			validation.waiveDoc(driver);
		}
	}

	private void handleAMLOverride() throws InterruptedException {
		driver.findElement(By.id(AMLOverridebutton)).click();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(AMLOverridepopup_ID)));

		driver.findElement(By.id(amlComment)).sendKeys("Automation Testing Purpose");
		Thread.sleep(2000);

		driver.findElement(By.id(AmlOverrideButton_ID)).click();

		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(AmlOverrideOkbutton_ID)));

		driver.findElement(By.id(AmlOverrideOkbutton_ID)).click();

//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButton)));
	}


	private void handleAMLManagerOverride() throws InterruptedException {
		driver.findElement(By.id(AMLmanageroverride_ID )).click();

		//rdoamloverrideyes
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(AMLmanageroverridepopup_ID)));
		driver.findElement(By.id(radiooverrideYEsbutton_ID)).click();
		driver.findElement(By.id(amlmanagerComment)).sendKeys("Automation Manger Comments Testing");
		driver.findElement(By.id(SaveOverridebutton_ID)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(AMLresponseoverridepopup_ID)));
		driver.findElement(By.id(AMLresponseoverridepopup_ID)).click();
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(AMLresponseoverridepopup_ID)));
//pcCustomMessageDialog_PWC-1
//		driver.findElement(By.id(AMLresponseoverridepopup_ID)).click();
//
////		Thread.sleep(2000);
////		wait.until(ExpectedConditions.elementToBeClickable(By.id(proceedButton)));
	}

}

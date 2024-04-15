package Tests;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.SignioHomePage;
import Pages.SignioLoginPage;
import Pages.SignioNewApplicationPage;

public class SignioTest extends BasicSetup {
	public static WebDriver driver;
	SignioLoginPage loginPageObject = new SignioLoginPage();
	SignioHomePage homePageObject = new SignioHomePage();
	SignioNewApplicationPage newApplication = new SignioNewApplicationPage();
	String mySignioOptions = "//body/div[2]/div[1]/div[2]/ul[2]/li[3]/a[1]";
	String myMerchant = "//a[contains(text(),'My Merchant')]";
	String saTaxiDealershipValue = "//*[@value='48394']";
	String saTaxiDealerValue = "//*[@value='48391']";
	String saTaxiTestValue = "//*[@value='61430']";
	String wbcValue = "//*[@value='61561']";
	String selectInstitutionButton = "selectMerch";


	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {

		loginPageObject.navigateToSignio(driver);
		loginPageObject.loginToSignio(driver);

	}

	@BeforeTest(enabled = true)
	public void setup() {

		SignioTest signioTest = new SignioTest();
	}

	public SignioTest() {
		driver = getdriver();
	}

	@Test(priority = 2)
	public void CreateNewApplicationThroughSignio() throws InterruptedException, IOException, UnsupportedFlavorException {

		new WebDriverWait(driver, 40).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(mySignioOptions)));

		
		Thread. sleep(6000);
		
		WebElement mySignioOption = driver.findElement(By.xpath(mySignioOptions));
		mySignioOption.click();

		Thread.sleep(5000);

		WebElement myMerchandOption = driver.findElement(By.xpath(myMerchant));
		myMerchandOption.click();

		Thread.sleep(8000);
		
		//new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("selectMerch")));

		String type= newApplication.readInputData("Merchant");
		
		System.out.println("merchant Type is:" +type );
			
		if(type.trim().equalsIgnoreCase("SA Taxi Dealership")) {
			WebElement merchanttype = driver
					.findElement(By.xpath(saTaxiDealershipValue));
			Thread.sleep(4000);
			merchanttype.click();
		
		}
	
		else if(type.trim().equalsIgnoreCase("SA Taxi Dealer")) {
			WebElement merchanttype = driver
					.findElement(By.xpath(saTaxiDealerValue));
			Thread.sleep(4000);
			merchanttype.click();

		}
		
		else if(type.trim().equalsIgnoreCase("SA Taxi Test")) {
			WebElement merchanttype = driver
					.findElement(By.xpath(saTaxiTestValue));
			Thread.sleep(4000);
			merchanttype.click();
		
		}
		else if(type.trim().equals("WBC")) {
			
			WebElement merchanttype = driver
					.findElement(By.xpath(wbcValue));
			Thread.sleep(4000);
			merchanttype.click();
		
		}
		else if(type.trim().equals("IRIS")) {
			
			WebElement merchanttype = driver
					.findElement(By.xpath("//*[@value='62207']"));
			Thread.sleep(4000);
			merchanttype.click();
		
		}
		else {
		
			System.out.println("Merchant not selected");
		}

		Thread.sleep(2000);
		WebElement selectInstitution = driver.findElement(By.id(selectInstitutionButton));
		Thread.sleep(2000);
		selectInstitution.click();
		
		Thread.sleep(10000);

		String parentWindow = homePageObject.goToNewApplicationPage(driver);

		newApplication.SwitchToNewApplicationWindow(driver);
		String id_signio = newApplication.FillCustomerInfo(driver, parentWindow);

		String id=homePageObject.searchDealWithReferenceNumber(driver, id_signio);
		
		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

		newApplication.writeDataIntoFile(driver, id, path1);
		
		homePageObject.checkFinanceAgreement(driver);
		
	}

	

}

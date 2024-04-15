package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Reporter;

import Pages.SeritiHomePage;
import Pages.SeritiLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeritiTest {
	public static WebDriver driver;
	SeritiLoginPage loginPage = new SeritiLoginPage();
	SeritiHomePage homePage = new SeritiHomePage();

	@BeforeSuite(enabled = true)
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Reporter.log("Chrome browser launched");
	}

	@BeforeTest
	public void login() throws InterruptedException, IOException {
		loginPage.NavigateToSeriti(driver);
		loginPage.LoginToSeriti(driver);
		Reporter.log("User has logged in seriti",true);
		
		
		//SeritiTest seritiTest= new SeritiTest();
	}
	 
//	public SeritiTest() {
//		driver = getdriver();
//	}

	@Test(priority=1,enabled = true)
	public void createDealThroughSeriti() throws InterruptedException, IOException {
		
	
		homePage.clickOnRecentlyViewedButton(driver);
		Reporter.log("clicked on recently viewed button", true);
		homePage.copyPolicy(driver);
		Reporter.log("policy copied",true);
		homePage.clickOnRecentlyViewedButton(driver);
		Reporter.log("clicked on recently viewed button",true);
		homePage.viewCopiedPolicy(driver);
		Reporter.log("view copied policy",true);
		//homePage.InsuranceAddons(driver);
		//Reporter.log("Add insurance addons",true);
		//homePage.AddAccessories(driver);
		//Reporter.log("add Accessories",true);
//Thread.sleep(8000);
		homePage.ClickFinanceApplication(driver);
		Reporter.log("clicked on finance application",true);
		homePage.ClickSATaxiFinance(driver);
		Reporter.log("clicked on SA Taxi Finance",true);
		homePage.FillApplicationForm(driver);
		Reporter.log("filled application for with necessary field",true);
	    homePage.ClickApplyButton(driver);
		Reporter.log("Clicked on Apply after fill the form",true);
		Reporter.log("Deal is addded successfully",true);
		
	}

	@AfterTest(enabled = false)
	public void logout() throws InterruptedException {
		loginPage.Logout(driver);
	}

	@AfterSuite(enabled = false)
	public void closeBrowser() {
		driver.quit();
	}

}

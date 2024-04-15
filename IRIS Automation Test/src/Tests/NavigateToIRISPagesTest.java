package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Pages.NavigateToIRISPages;
import Pages.IRISLoginPage;

public class NavigateToIRISPagesTest extends BasicSetup {
	public static WebDriver driver;
	IRISLoginPage login = new IRISLoginPage();
	NavigateToIRISPages page = new NavigateToIRISPages();

	public NavigateToIRISPagesTest() {
		driver = getdriver();
	}

	@BeforeClass(enabled = true)
	public void loginToOptima() throws InterruptedException, IOException {
		login.NavigateToOptima(driver);
		login.LoginToOptima(driver);
	}

	@Test(priority = 1)
	public void NavigateToApplicationPage() throws InterruptedException {
		page.NavigateToArchiveDeals(driver);
		page.SearchTheStatus(driver);
		page.ClickOnFirstDealInTable(driver);
		page.ClickOnApplicationsPage(driver);
	}

	@Test(priority = 2)
	public void NavigateToCreditDecissionPage() throws InterruptedException {
		page.ClickOnCreditDecission(driver);
	}

	@Test(priority = 3)
	public void NavigateToValidationPage() throws InterruptedException {
		page.ClickOnValidation(driver);
	}

	@Test(priority = 4)
	public void NavigateToVerificationPage() throws InterruptedException {
		page.ClickOnVerification(driver);
	}

	@Test(priority = 5)
	public void NavigateToInvoicePage() throws InterruptedException {
		page.ClickOnInvoice(driver);
	}

	@Test(priority = 6)
	public void NavigateToContractPage() throws InterruptedException {
		page.ClickOnContract(driver);
	}
	
	@Test(priority = 7)
	public void NavigateToDataImportLog() throws InterruptedException {
		page.ClickOnDataImportLog(driver);
	}
	
	@Test(priority = 8)
	public void NavigateToDecisionSummary() throws InterruptedException {
		page.ClickOnDecisionSummary(driver);
	}
	
	@Test(priority = 9)
	public void NavigateToIntegrationStatus() throws InterruptedException {
		page.ClickOnIntegrationStatus(driver);
	}
	
	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}
}

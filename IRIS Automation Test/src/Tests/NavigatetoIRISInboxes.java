package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.NavigateToIRISPages;
import Pages.IRISLoginPage;

public class NavigatetoIRISInboxes extends BasicSetup {
	public static WebDriver driver;
	IRISLoginPage login = new IRISLoginPage();
	NavigateToIRISPages page = new NavigateToIRISPages();

	public NavigatetoIRISInboxes() {
		driver = getdriver();
	}

	@BeforeClass(enabled = true)
	public void loginToOptima() throws InterruptedException, IOException {
		login.NavigateToOptima(driver);
		login.LoginToOptima(driver);
	}

	@Test(priority = 1)
	public void NavigateToApplicationPage() throws InterruptedException {
		page.NavigateToMyDeals(driver);
		page.NavigateToPendDeals(driver);
		page.NavigateToAllIncidents(driver);
		page.NavigateToActiveDeals(driver);
		page.NavigateToArchiveDeals(driver);
		page.NavigateToDeclineAndCancelation(driver);
		page.NavigateToAssesment(driver);
		page.NavigateToVerification(driver);
		page.NavigateToFaceToFaceVerification(driver);
		page.NavigateToDocumentValidation(driver);
		page.NavigateToContractGeneration(driver);
		page.NavigateToContractValidation(driver);
		page.NavigateToAwaitingPayout(driver);
		page.NavigateToManagerOverride(driver);
		String version = page.CheckVersion(driver);
		System.out.println("Version Information:"+version);
		
	}

	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}
}


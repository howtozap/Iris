package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.VerificationScreen;

public class VerificationStage extends BasicSetup{
	public static WebDriver driver;
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	VerificationScreen verificationScreen = new VerificationScreen();

	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {
		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}
	
	@BeforeTest(enabled = true)
	public void setup() throws InterruptedException, IOException {;
		
		VerificationStage stage= new VerificationStage();
	}
	
	public  VerificationStage() {
		driver= getdriver();
		
	}
	
	@Test(priority = 6)
	public void kycOvverride() throws InterruptedException {
		
		iRISHomePage.clickOnMyDeals(driver);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 100);

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlKYC_RPHT")));

		
		// verificationScreen.attemptSelection(driver); // removal part
		// verificationScreen.questionsList(driver); // removal
        verificationScreen.kycOverride(driver);
      //  verificationScreen.xdsOverride(driver);
	}

}

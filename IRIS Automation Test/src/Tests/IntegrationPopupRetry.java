package Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import Pages.IRISLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IntegrationPopupRetry {

	public static WebDriver driver;
	IRISLoginPage iRISLoginPage= new IRISLoginPage();
	
	@BeforeSuite
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@BeforeTest
	public void login() throws InterruptedException, IOException {
		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
	}
}

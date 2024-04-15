package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignioLoginPage 
{
	String signioURL = "https://lb-sb-stage.signio.co.za/Signing-Boardroom/";
	String IDNumber_css = "#idnumber";
	String password_css = "#password";
	String submitButton_css = "#submit";
	SignioNewApplicationPage application = new SignioNewApplicationPage();
	
	public void navigateToSignio(WebDriver driver)
	{
		driver.get(signioURL);
	}
	
	public void loginToSignio(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		WebElement IDNumberTextbox = driver.findElement(By.cssSelector(IDNumber_css));
		IDNumberTextbox.sendKeys(application.readInputData("username"));
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector(password_css));
		passwordTextbox.sendKeys(application.readInputData("password"));
		
		WebElement submitButton = driver.findElement(By.cssSelector(submitButton_css));
		submitButton.click();
	}

}

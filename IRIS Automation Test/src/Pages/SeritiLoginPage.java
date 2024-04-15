package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeritiLoginPage 
{
	String seritiURL = "https://www.seritisolutions.co.za/DEMO/web/Unauth/logon.aspx#!\n";
	String username_css = "#txtUserName";
	String username = "bhausaheb";
	String password_css = "#txtPassword";
	String password = "Password.05";
	String loginButton_css = "#btnLogon";
	String profileButton_xpath = ".//img[@class='dropbtn css-menu-user-a']";
	String logoffLinkText = "Logoff";
	
	public void NavigateToSeriti(WebDriver driver)
	{
		//driver.get("https://www.seritisolutions.co.za/DEMO/web/Unauth/logon.aspx#!\n");
		//driver.manage().window().maximize();
		driver.get(seritiURL);
	}
	
	public void LoginToSeriti(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		
		WebElement usernameTextbox = driver.findElement(By.cssSelector(username_css));
		usernameTextbox.sendKeys(username);
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector(password_css));
		passwordTextbox.sendKeys(password);
		
		WebElement loginButton = driver.findElement(By.cssSelector(loginButton_css));
		loginButton.click();
		
	}
	
	public void Logout(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(1000);
		WebElement profileButton = driver.findElement(By.xpath(profileButton_xpath));
		profileButton.click();
		
		WebElement logoffLink = driver.findElement(By.partialLinkText(logoffLinkText));
		logoffLink.click();
		
	}

}

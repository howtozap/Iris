package Pages;

import static org.testng.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignioHomePage {
	String application_xpath = "/html/body/div[2]/div/div[2]/ul[1]/li[2]/a";
	String newApplication_xpath = "//a[@href='/Signing-Boardroom/application/index?deal=0']";
	String logoutLink_xpath = ".//a[@href='/Signing-Boardroom/inbox/logoutDealer']";
	String searchCriteria_xpath = ".//a[@class='text-left']";
	String searchDropDown = "//*[@name='searchTarget']";
	String searchDropDown_ID = "searchTarget";
	String searchDropdown_css = "#searchTarget";
	String searchParameter_css = "#searchParameter";
	String searchButton_css = "#searchButton";
	String okButton = "buttonOk";
	String clearButton_css = ".//button[@class = 'btn btn-default btn-sm']";
	String VehicleDetailsTab_xpath = "//a[@id='tbApplicatioDetailsTab_T1T']";
	String VehicleDetailsTab_ID = "rndPnlVehicleDetails_RPHT";
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();
	
	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/SignioInputFile.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	public String goToNewApplicationPage(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);

		String parentWindow = driver.getWindowHandle();

		WebElement applicationLink = driver.findElement(By.xpath(application_xpath));
		applicationLink.click();

		WebElement newApplication = driver.findElement(By.xpath(newApplication_xpath));
		newApplication.click();
		Thread.sleep(6000);

		return parentWindow;
	}

	public void Logout(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement logoutLink = driver.findElement(By.xpath(logoutLink_xpath));
		logoutLink.click();
		Thread.sleep(2000);
	}

	public String searchDealWithReferenceNumber(WebDriver driver, String id_signio) throws InterruptedException, IOException {
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		
//		String number = "30117  </br></br>2019 TOYOTA QUANTUM/HIACE 2.7 SESFIKILE 16s";
//		
//		number = number.substring(0, number.indexOf(' '));

		Thread.sleep(15000);
		WebElement searchCriteria = driver.findElement(By.xpath(searchCriteria_xpath));
		searchCriteria.click();
		Thread.sleep(2000);
		WebElement clearButton = driver.findElement(By.xpath(clearButton_css));
		clearButton.click();

		Thread.sleep(6000);
		WebElement searchCriteria1 = driver.findElement(By.xpath(searchCriteria_xpath));
		searchCriteria1.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchDropDown)));

		WebElement searchDropdown = driver.findElement(By.xpath(searchDropDown));
		searchDropdown.click();

		Select option = new Select(driver.findElement(By.id(searchDropDown_ID)));
		option.selectByValue("ID Number");

		WebElement searchParameter = driver.findElement(By.cssSelector(searchParameter_css));
		searchParameter.clear();
		String customerid = readInputData("newApplicationID");
		searchParameter.sendKeys(customerid);

		WebElement searchButton = driver.findElement(By.cssSelector(searchButton_css));
		searchButton.click();

		Thread.sleep(10000);
		
		WebElement okbtn = driver.findElement(By.id(okButton));
		okbtn.click();
		
		Thread.sleep(5000);
		
		int ref= Integer.parseInt(id_signio);  
		ref=ref+1;
		
		String id=driver.findElement(By.xpath("//input[contains(@id,'"+ref+"')]")).getAttribute("data-original-title");
		id = id.substring(0, id.indexOf(' '));
		System.out.println(id);
		
		return id;
	}
	
	public void checkFinanceAgreement(WebDriver driver) throws InterruptedException, IOException, UnsupportedFlavorException {
		
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		Thread.sleep(5000);
		
		String isballoon=readInputData("isballoon");
		String isbuyback=readInputData("isbuyback");
		String isrental=readInputData("isrental");
		String issubscription=readInputData("issubscription");
		String balloonpercentage = readInputData("balloonPercentage");
		String Category = readInputData("Category");
		
		iRISLoginPage.NavigateToOptima(driver);
		iRISLoginPage.LoginToOptima(driver);
		
		iRISHomePage.clickOnAllIncidentInbox(driver);
		
		Thread.sleep(10000);
		iRISHomePage.searchSignioDealThroughReferenceNumber(driver);
		
		iRISHomePage.searchDealthroughIncidentNumber(driver);

		Thread.sleep(6000);

		iRISHomePage.clickOnIncidentLink(driver);
		
		Thread.sleep(5000);

		driver.findElement(By.xpath(VehicleDetailsTab_xpath)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(VehicleDetailsTab_ID)));
		
		Thread.sleep(5000);
		
		String financeAgreement = driver.findElement(By.xpath("//table[@id='cmbFinanceAgreement_DDD_L_LBT']//*[@class='dxeListBoxItem dxeListBoxItemSelected']")).getAttribute("innerText");
		String FinanceGrade = driver.findElement(By.id("txtFinanceGrade")).getText();
		
		System.out.println("Finance Agreement: "+financeAgreement+" ,Finance Grade: "+FinanceGrade);
		
		if(isbuyback.equalsIgnoreCase("Yes")) {
			
			if(financeAgreement.equals("Consumer Finance Buyback")) {
				
				System.out.println("Consumer Finance Buyback is created successfully");
			}
			
			if(financeAgreement.equals("Consumer Finance Rental")) {
				
				System.out.println("Rental deal is created successfully");
			}
			
			Assert.assertTrue(FinanceGrade.equals(Category), "Category is correct");
			
		}
		
		if(isrental.equalsIgnoreCase("Yes")) {
			
			Assert.assertTrue(financeAgreement.equals("Consumer Finance Rental"), "Rental Deal created");
			
			Assert.assertTrue(FinanceGrade.equals(Category), "Category is correct");
			
		}
		
		if(issubscription.equalsIgnoreCase("Yes")) {
			
			Assert.assertTrue(financeAgreement.equals("Consumer Finance Subscription"), "Subscription Deal created");
			
			if(financeAgreement.equals("Consumer Finance Rental")) {
				
				System.out.println("Rental deal is created successfully");
			}
			
			Assert.assertTrue(FinanceGrade.equals(Category), "Category is correct");
			
		}
		
		if(isbuyback.equalsIgnoreCase("No") && isballoon.equalsIgnoreCase("No") && isrental.equalsIgnoreCase("No")) {
			
			if(financeAgreement.equals("Consumer Instalment Sale")) {
				
				System.out.println("Consumer Instalment Sale is created successfully");
			}
			
			if(financeAgreement.equals("Consumer Finance Rental")) {
				
				System.out.println("Rental deal is created successfully");
			}
			
			Assert.assertTrue(FinanceGrade.equals(Category), "Category is correct");
			
		}
		
		if(isballoon.equalsIgnoreCase("Yes") && isbuyback.equalsIgnoreCase("No"))
		{	
			
			boolean ans;
			
			if(ans=financeAgreement.equalsIgnoreCase("Consumer Finance Balloon")) {
				
				System.out.println("Ballon deal is created");
				
				String BalloonPercentage=driver.findElement(By.id("txtResidualPercentage")).getText();
				Assert.assertTrue(BalloonPercentage.equals(balloonpercentage), "Percentage is equal");
				
			}
			
			else {
				
				assertTrue(ans, "This is not ballon deal");
				
			}
		}
		
		else {
			
			System.out.println("There is no request for balloon deal");
			
		}
	}

//	public void uploadsuppdoc(WebDriver driver) throws InterruptedException, IOException {
//
//		String oldTab = driver.getWindowHandle();
//		driver.findElement(By.id("supdocs_0")).click();
//		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
//		newTab.remove(oldTab);
//		// change focus to new tab
//		driver.switchTo().window(newTab.get(0));
//		System.out.println(driver.getTitle());
//
//		WebElement uploadElement = driver.findElement(By.name("files[]"));
//
//		// enter the file path onto the file-selection input field
//		uploadElement.sendKeys("E:\\upload Document.pdf");
//		driver.findElement(By.id("uploadButton")).click();
//		Thread.sleep(7000);
//
//		Thread.sleep(5000);
//		driver.findElement(By.id("classify")).click();
//
//		Thread.sleep(10000);
//
//		driver.findElement(By.xpath("//input[@id='selectall']"));
//
//		Thread.sleep(3000);
//		WebElement bucket = driver.findElement(By.id("newBucket"));
//
//		bucket.sendKeys("INVOICE");
//
//		driver.findElement(By.id("classifyPages")).click();
//
//		driver.findElement(By.id("submissions")).click();
//		Thread.sleep(7000);
//		driver.findElement(
//				By.xpath("/html/body/div/div[3]/form/div[1]/div[2]/div/table/tbody/tr[1]/td[3]/div/input[2]")).click();
//		driver.findElement(By.id("checkboxSelectAllBank17737")).click();
//		driver.findElement(By.id("submitButton")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.id("swal2-confirm swal2-styled")).click();
//		Thread.sleep(10000);
//		driver.close();
//		driver.switchTo().window(oldTab);
//	}

}

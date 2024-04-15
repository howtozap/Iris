package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationStage 
{
	String allIncident_xpath = ".//*[text()='All Incidents']/parent::li";
	String incidentNumberSearchBox_css = "#grdGlobalDeals_DXFREditorcol0_I";
	String retriveButton_xpath = ".//*[@id='MainSplitter_2']//span[@id='btnRetrieveDealAllIncident']";
	String continueButton_css = "#btnIncidentDuplicate_CD";
	String selectDocumentTextbox_css = "#fileUpdValidationDocument_TextBox0_FakeInput";
	String saveButton_css = "#btnSaveDocValidation_CD";
	String dealerInvoiceUploadLink_xpath = ".//*[text()='Dealer Invoice']/preceding::td/a[@title='Manage']";
	String okButton_css = "#btnCustomMessageClose_CD";
	String closeButton_xpath = ".//*[@id='popDocValidation_PW-1']//*[@class='dxWeb_pcCloseButton']";
	String uploadIDBookLink_xpath = "(.//*[text()='ID Book']/preceding::td/a[@title='Manage'])[2]";
	String uploadOperatingLicenseLink_xpath = ".//*[text()='Operating License']/preceding-sibling::td/a[@title='Manage']";
	String uploadProofOfAddressLink_xpath = "(.//*[text()='Proof of Address']/preceding-sibling::td/a[@title='Manage'])";
	String uploadTaxiAssociationLetterLink_xpath = "(.//*[text()='Taxi Association Letter ']/preceding::td/a[@title='Manage'])[9]";
	String proofOfAddressDocSubtype_css = "#cbDocumentSubType_I";
	
	
	
	public String readInputData(String key) throws IOException
	{
		InputStream input = new FileInputStream("src/TestData/validationStage.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	public void searchDeal(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		WebElement incidentNumberSearchBox = driver.findElement(By.cssSelector(incidentNumberSearchBox_css));
		String dealNumber = readInputData("dealNumber");
		incidentNumberSearchBox.clear();
		Thread.sleep(1000);
		incidentNumberSearchBox.sendKeys(dealNumber);
		Thread.sleep(1000);
		incidentNumberSearchBox.sendKeys(Keys.ENTER);
		
	}
	
	public void clickOnDeal(WebDriver driver) throws IOException, InterruptedException
	{
		Thread.sleep(60000);
		String dealNumber = readInputData("dealNumber");
		WebElement deal = driver.findElement(By.linkText(dealNumber));
		deal.click();
		Thread.sleep(60000);
	}
	
	public void clickOnRetriveButton(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(20000);
		WebElement retriveButton = driver.findElement(By.xpath(retriveButton_xpath));
		retriveButton.click();
	}
	
	public void clickOnContinueButton(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(20000);
		driver.switchTo().activeElement();
		
		WebElement continueButton = driver.findElement(By.cssSelector(continueButton_css));
		continueButton.click();
		Thread.sleep(60000);
	}	
	
	public void uploadDealerInvoice(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(20000);
		WebElement uploadDealerInvoiceLink = driver.findElement(By.xpath(dealerInvoiceUploadLink_xpath));
		uploadDealerInvoiceLink.click();
		Thread.sleep(30000);
		
		driver.switchTo().activeElement();
		WebElement selectDocument = driver.findElement(By.cssSelector(selectDocumentTextbox_css));
		//selectDocument.clear();
		selectDocument.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec("E:\\Optima\\Open.exe");
		Thread.sleep(5000);
		
		WebElement saveButton = driver.findElement(By.cssSelector(saveButton_css));
		saveButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement okButton = driver.findElement(By.cssSelector(okButton_css));
		okButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement closeButton = driver.findElement(By.xpath(closeButton_xpath));
		closeButton.click();
		Thread.sleep(20000);
	}
	
	public void uploadIDBook(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(20000);
		WebElement uploadIDBookLink = driver.findElement(By.xpath(uploadIDBookLink_xpath));
		uploadIDBookLink.click();
		Thread.sleep(30000);
		
		driver.switchTo().activeElement();
		WebElement selectDocument = driver.findElement(By.cssSelector(selectDocumentTextbox_css));
		//selectDocument.clear();
		selectDocument.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec("E:\\Optima\\Open.exe");
		Thread.sleep(5000);
		
		WebElement saveButton = driver.findElement(By.cssSelector(saveButton_css));
		saveButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement okButton = driver.findElement(By.cssSelector(okButton_css));
		okButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement closeButton = driver.findElement(By.xpath(closeButton_xpath));
		closeButton.click();
		Thread.sleep(20000);
		
	}
	
	public void uploadOperatingLicense(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(20000);
		WebElement uploadOperatingLicense = driver.findElement(By.xpath(uploadOperatingLicenseLink_xpath));
		uploadOperatingLicense.click();
		Thread.sleep(30000);
		
		driver.switchTo().activeElement();
		WebElement selectDocument = driver.findElement(By.cssSelector(selectDocumentTextbox_css));
		//selectDocument.clear();
		selectDocument.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec("E:\\Optima\\Open.exe");
		Thread.sleep(5000);
		
		WebElement saveButton = driver.findElement(By.cssSelector(saveButton_css));
		saveButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement okButton = driver.findElement(By.cssSelector(okButton_css));
		okButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement closeButton = driver.findElement(By.xpath(closeButton_xpath));
		closeButton.click();
		Thread.sleep(20000);
	}
	
	public void uploadProofOfAddress(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(30000);
		WebElement uploadProofOfAddress = driver.findElement(By.xpath(uploadProofOfAddressLink_xpath));
		uploadProofOfAddress.click();
		Thread.sleep(30000);
		
		driver.switchTo().activeElement();
		WebElement docSubtype = driver.findElement(By.cssSelector(proofOfAddressDocSubtype_css));
		docSubtype.sendKeys("Bank Statement");
		docSubtype.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		WebElement selectDocument = driver.findElement(By.cssSelector(selectDocumentTextbox_css));
		//selectDocument.clear();
		selectDocument.click();
		Thread.sleep(1000);
		Runtime.getRuntime().exec("E:\\Optima\\Open.exe");
		Thread.sleep(5000);
		
		WebElement saveButton = driver.findElement(By.cssSelector(saveButton_css));
		saveButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement okButton = driver.findElement(By.cssSelector(okButton_css));
		okButton.click();
		Thread.sleep(10000);
		
		driver.switchTo().activeElement();
		WebElement closeButton = driver.findElement(By.xpath(closeButton_xpath));
		closeButton.click();
		Thread.sleep(20000);
		
	}
	
}

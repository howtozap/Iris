package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.testng.Assert;
import org.testng.asserts.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IDITHomePage 
{
	String contactDropdown_xpath = "(.//*[contains(text(),'Contact')]//parent::a)[4]";
	String proposalOption_xpath = ".//div[contains(text(),'Proposal')]";
	String showHideButton_css = "#showHiedAllFieldsButton";
	String policyProposalDetails_css = "#panel-141462";
	String policyProposalNumberTextBox_xpath = ".//input[@id='IDITForm@policyNumber']";
	String proposalNumber = "MPPR0000001238";
	String findButton_css = "#homepageButtonsB_Search";
	String tableRow_xpath = ".//tr[@role='row']";
	String selectButton_xpath = "#selectButtonSearch";
	String updateProposalLink_css = "#updateProposal_Link";
	String nextButton_css = "#Next";
	String formTitle_xpath = ".//*[@class='FormTitle FormTitle']";
	String selectVehicleRow_xpath = "(.//tr[@role='row'])[3]";
	String updateButton_xpath = ".//a[@title='Update']";
	String discountPercentTextbox_xpath = ".//input[@id='IDITForm@adjustmentPercent']";
	int discount = 10;
	String saveDiscountButton_css = "#partialSave";
	String footerCellData_xpath = "(.//*[@class='footerCellData'])[2]";
	
	
	
	
	public void SearchProposalNumber(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		
		WebElement contractDropdown = driver.findElement(By.xpath(contactDropdown_xpath));
		contractDropdown.click();
		
		WebElement proposalOption = driver.findElement(By.xpath(proposalOption_xpath));
		proposalOption.click();
		
		WebElement showHideButton = driver.findElement(By.cssSelector(showHideButton_css));
		showHideButton.click();
		
		Thread.sleep(1000);
		WebElement policyProposalDetails =  driver.findElement(By.cssSelector(policyProposalDetails_css));
		policyProposalDetails.click();
		
		Thread.sleep(1000);
		WebElement policyProposalNumberTextBox = driver.findElement(By.xpath(policyProposalNumberTextBox_xpath));
		policyProposalNumberTextBox.clear();
		policyProposalNumberTextBox.sendKeys(proposalNumber);
		
		WebElement findButton = driver.findElement(By.cssSelector(findButton_css));
		findButton.click();
		
		Thread.sleep(10000);
		
		String pageSource = driver.getPageSource();
		
		if(pageSource.contains("Proposal Search Results"))
		{
			List<WebElement> tableRow = driver.findElements(By.xpath(tableRow_xpath));
			WebElement tableData = null;
			
			Iterator tableIterator = tableRow.iterator();
			
			while(tableIterator.hasNext())
			{
				tableData = (WebElement) tableIterator.next();
			}
			
			tableData.click();
			
			WebElement selectButton = driver.findElement(By.cssSelector(selectButton_xpath));
			selectButton.click();
			Thread.sleep(25000);
			
		}
		
	}
	
	public void UpdateProposal(WebDriver driver) throws InterruptedException
	{
		WebElement updateProposalLink = driver.findElement(By.cssSelector(updateProposalLink_css));
		updateProposalLink.click();
		Thread.sleep(25000);
		
		WebElement formTitle = driver.findElement(By.xpath(formTitle_xpath));
		String title = formTitle.getText();
		System.out.println(title);
		
		
		WebElement nextButton = driver.findElement(By.cssSelector(nextButton_css));
		nextButton.click();
		
		Thread.sleep(10000);
		
		WebElement selectVehicleRow = driver.findElement(By.xpath(selectVehicleRow_xpath));
		selectVehicleRow.click();
		
		WebElement updateButton = driver.findElement(By.xpath(updateButton_xpath));
		updateButton.click();
		
		WebElement nextButton1 = driver.findElement(By.cssSelector(nextButton_css));
		nextButton1.click();
		
		AddDiscount(driver);
		
		
	}
	
	public void AddDiscount(WebDriver driver) throws InterruptedException
	{
		WebElement discountPercentTextbox = driver.findElement(By.xpath(discountPercentTextbox_xpath));
		discountPercentTextbox.clear();
		
		Thread.sleep(15000);
		
		WebElement footerCellData = driver.findElement(By.xpath(footerCellData_xpath));
		String installmentValue = footerCellData.getText();
		
		double installmentBeforeDiscount = extractInstallmentValue(installmentValue);
		System.out.println(installmentBeforeDiscount);
		
		double expectedInstallmentAfterDiscount = calculateDiscountValue(installmentBeforeDiscount);
		System.out.println(expectedInstallmentAfterDiscount);
		
		
		WebElement discountPercentTextbox1 = driver.findElement(By.xpath(discountPercentTextbox_xpath));
		String discountST = Integer.toString(discount);	
		discountPercentTextbox1.sendKeys(discountST);
		discountPercentTextbox1.sendKeys(Keys.ENTER);
		
		
		Thread.sleep(15000);
		WebElement footerCellData1 = driver.findElement(By.xpath(footerCellData_xpath));
		String installmentValue1 = footerCellData1.getText();
		
		double actualInstallmentAfterDiscount = extractInstallmentValue(installmentValue1);
		Assert.assertEquals(expectedInstallmentAfterDiscount, actualInstallmentAfterDiscount, 0.0002);
		
		Thread.sleep(15000);
		
		WebElement saveDiscountButton = driver.findElement(By.cssSelector(saveDiscountButton_css));
		saveDiscountButton.click();
		
		Thread.sleep(50000);
		
		
	}
	
	public double extractInstallmentValue(String installmentValue)
	{
		StringTokenizer st = new StringTokenizer(installmentValue, " ");
		String installmentText = null;
		
		while(st.hasMoreTokens())
		{
			installmentText = st.nextToken();
		}
		
		StringTokenizer st1 = new StringTokenizer(installmentText, ",");
		String str1 = st1.nextToken();
		String str2 = st1.nextToken();
		
		String installment = str1 + str2;
		
		double installmentValueDouble = Double.parseDouble(installment);
		System.out.println(installmentValueDouble);
		return installmentValueDouble;
		
	}
	
	public double calculateDiscountValue(double installment)
	{
		double discountedValue = (installment)*(discount/100);
		double discountedInstallment = installment - discountedValue;
		return discountedInstallment;
		
	}
	

}

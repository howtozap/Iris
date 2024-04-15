package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignioNewApplicationPage {

	String custIDNumber_css = "#customerIdNumber";
	String dealerCodeLabel_xpath = ".//label[text()='Dealer Code']";
	String isGomoCheckbox_css = "#isGomo";
	String isPreviousApplication = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-dialog-buttons ui-draggable']/div/div/button[@type='button']/span[text()='Ok']";
	String typeOfSale_ID = "typeOfSaleDOC";
	String dealerCode_ID = "dealerCodeGomo";
	String selectVehicleButton_css = "#vehicleLookup_";
	String searchMMCodeTextbox_css = "#searchMMCode";
	String searchMMCodeButton_css = "#cmdMMCode";
	String vehicleOKButton = "cmdVehicleOk";
	String continueNewApplication = "//span[contains(text(),'Continue New App')]";
	String purchasePrice = "purchasePrice";
	String userArticleCondition = "usedArticleCondtion";
	String financeGrade_ID = "financeGrade";
	String residualBalloonPercentageTextbox_id = "residualPercent";
	String vehicleLookupPopupOKButton_css = "#cmdVehicleOk";
	String yearModel_css = "#selectYear";
	String regTrackDropdown_css = "#regtrackIndicator";
	String cashDepositeTextbox_css = "#cashDeposit";
	String consumerproduct = "buyBack";
	String annualAllowableKilometers_ID = "annualAllowableKilometers";
	String postalAddressSameAsResidential = "#sameAsRes";
	String sourceOfFund = "//*[@id=\"sourceOfFundsAllowance\"]";
	String sourceOfFundsAllowance = "sourceOfFundsAllowance";
	String sourceOfFundsBonus = "sourceOfFundsBonus";
	String sourceOfFundsSalary = "sourceOfFundsSalary";
	String sameAsApplicant = "chkAccApplicant";
	String marketingConsentIndicator = "#marketingConsentIndicator";
	String insuranceConsentIndicator = "#insuranceConsentIndicator";
	String customerBankStatementConsentIndicator = "#customerBankStatementConsentIndicator";
	String customerIncomeVerificationConsentIndicator = "#customerIncomeVerificationConsentIndicator";
	String taxiAssociationTextbox_css = "#taxiAssociation";
	String taxiAssociationName_xpath = "//*[@id=\"ui-id-11\"]";
	String submitApplicationButton_css = "#cmdSubmit";
	String taxiRouteDropdown_css = "#routeFromTo";
	String driverWages_css = "#driverWagePerMonth";
	String numberOfPassangersPerTrip_css = "#averageNumberOfPassengersPerTrip";
	String farePerPassenger_css = "#farePerPassenger";
	String dayWorkedPerMonthDropdown_css = "#dayWorkedPerMonth";
	String taxiAssociationProvinceDropdown_css = "#taxiAssociationProvince";
	String kmPerTrip_css = "#kmPerTrip";
	String noOfTripsPerDay_css = "#noOfTripsPerDay";
	String insuranceCompanyDropdown_css = "#supplier";
	String routeLable_xpath = ".//label[text()='Route From/To ']";
	String successMessage_xpath = ".//h2";
	String expectedMessage = "OK";
	String successPopupOKButton_css = ".confirm";
	String successMessage2_xpath = ".//*[@class='logoContainer landscape']//label";
	String addExtrasButton_css = "#addExtraButton";
	String daysWorkedLabel_css = "label[for='dayWorkedPerMonth']";

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/SignioInputFile.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	public void SwitchToNewApplicationWindow(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		String newWindow = null;

		while (it.hasNext()) {
			newWindow = it.next();
		}

		driver.switchTo().window(newWindow);
	}

	public String FillCustomerInfo(WebDriver driver, String parentHandle) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement customerID = driver.findElement(By.cssSelector(custIDNumber_css));
		customerID.clear();
		customerID.sendKeys(readInputData("newApplicationID")); // Send application ID from properties file

		WebElement dealerCodeLabel = driver.findElement(By.xpath(dealerCodeLabel_xpath)); // Click on label to get auto
																							// fill popup.
		dealerCodeLabel.click();

		Thread.sleep(4000);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement element = wait1.until(
		ExpectedConditions.elementToBeClickable(By.xpath(isPreviousApplication)));
		element.click();
		
		Thread.sleep(5000);
		
		String name = driver.findElement(By.id("customerFirstName")).getText();

		WebElement typeOfSale = driver.findElement(By.id(typeOfSale_ID));
		typeOfSale.click();

		Select type = new Select(driver.findElement(By.id(typeOfSale_ID)));
		type.selectByValue("Trade");

		WebElement isGomoCheckbox = driver.findElement(By.cssSelector(isGomoCheckbox_css));
		isGomoCheckbox.click();
		Thread.sleep(1000);
		
		WebElement dealercode = driver.findElement(By.id(dealerCode_ID));
		dealercode.click();

		Select code = new Select(driver.findElement(By.id(dealerCode_ID)));
		code.selectByValue(readInputData("dealercode"));
		
		Thread.sleep(2000);

		WebElement selectVehicleButton = driver.findElement(By.cssSelector(selectVehicleButton_css));
		selectVehicleButton.click();
		Thread.sleep(2000);

		driver.switchTo().activeElement(); // to switch driver control to popups

		WebElement searchMMCodeTextBox = driver.findElement(By.cssSelector(searchMMCodeTextbox_css));
		searchMMCodeTextBox.sendKeys(readInputData("MMCode"));

		WebElement searchMMCodeButton = driver.findElement(By.cssSelector(searchMMCodeButton_css));
		searchMMCodeButton.click();
		Thread.sleep(1000);

		WebElement okbtn = driver.findElement(By.id(vehicleOKButton));
		okbtn.click();
		
		Thread.sleep(5000);
		
		driver.switchTo().activeElement();
		
		driver.findElement(By.xpath(continueNewApplication)).click();
		
		driver.findElement(By.id(purchasePrice)).sendKeys("130000");	//purchase price
		
		Select articlecondition = new Select(driver.findElement(By.id(userArticleCondition)));
		articlecondition.selectByVisibleText("Excellent");
		
		Select financeGrade = new Select(driver.findElement(By.id(financeGrade_ID)));
		String category = readInputData("Category");
		financeGrade.selectByVisibleText(category);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 850)");

		addExtras(driver);
		
		String isballoon=readInputData("isballoon");
		String isbuyback=readInputData("isbuyback");
		String isrental=readInputData("isrental");
		String issubscription=readInputData("issubscription");
		
		if(isballoon.equalsIgnoreCase("Yes") && isbuyback.equalsIgnoreCase("No"))
		{	
		
		WebElement residualBalloonPercentageTextbox = driver.findElement(By.id(residualBalloonPercentageTextbox_id));
		residualBalloonPercentageTextbox.clear();
		residualBalloonPercentageTextbox.sendKeys(readInputData("balloonPercentage")); //Balloon % Value reading from propertyFile
		
		}

		WebElement cashDepositeTextbox = driver.findElement(By.cssSelector(cashDepositeTextbox_css));
		cashDepositeTextbox.sendKeys("50000");
		
		//Buyback deal
		
		if(isbuyback.equalsIgnoreCase("Yes"))
		{
			Select buyback = new Select(driver.findElement(By.id(consumerproduct)));
			buyback.selectByValue("Yes");
			
			Select allowablekilometer = new Select(driver.findElement(By.id(annualAllowableKilometers_ID)));
			allowablekilometer.selectByValue(readInputData("annualAllowableKilometers"));	
			
		}
		
		if(isrental.equalsIgnoreCase("Yes"))
		{
			Select rental = new Select(driver.findElement(By.id(consumerproduct)));
			rental.selectByValue("Rental");
			
			Select allowablekilometer = new Select(driver.findElement(By.id(annualAllowableKilometers_ID)));
			allowablekilometer.selectByValue(readInputData("annualAllowableKilometers"));	
			
		}
		
		if(issubscription.equalsIgnoreCase("Yes"))
		{
			Select subscription = new Select(driver.findElement(By.id(consumerproduct)));
			subscription.selectByValue("Subscription");
			
			Select allowablekilometer = new Select(driver.findElement(By.id(annualAllowableKilometers_ID)));
			allowablekilometer.selectByValue(readInputData("annualAllowableKilometers"));	
			
		}
		
		
		
		js.executeScript("window.scrollBy(0, 550)");
		
		adddealervaps(driver);

		js.executeScript("window.scrollBy(0, 1350)");

		Thread.sleep(1000);
		
		Select postaladdress = new Select(driver.findElement(By.cssSelector(postalAddressSameAsResidential)));
		postaladdress.selectByValue("Use Residential Address");
		Thread.sleep(3000);

//		js.executeScript("window.scrollBy(0, 1350)");
//		Thread.sleep(3000);
//		
//		driver.findElement(By.id("sumExpensesSpouseFoodAndEntertainmentLabel")).click();
//		
//		js.executeScript("window.scrollBy(0, 700)");
//		Thread.sleep(3000);
		
		//select source of fund
		WebElement element1 = driver.findElement(By.xpath(sourceOfFund));
		Actions actions = new Actions(driver);
		actions.moveToElement(element1);
		actions.perform();
		wait1.until(ExpectedConditions.elementToBeClickable(element1));
		js.executeScript("window.scrollBy(0, 200)");
		actions.click().build().perform();
		driver.findElement(By.id(sourceOfFundsAllowance)).click();
		driver.findElement(By.id(sourceOfFundsBonus)).click();
		driver.findElement(By.id(sourceOfFundsSalary)).click();
		
		if(!driver.findElement(By.id(sameAsApplicant)).isSelected())
		{
			driver.findElement(By.id(sameAsApplicant)).click();
		}
		
//		Select salespersonSelect = new Select(driver.findElement(By.id("salespersonSelect")));//select salesperson
//		salespersonSelect.selectByVisibleText("Tester Testing");

		Select consent = new Select(driver.findElement(By.cssSelector(marketingConsentIndicator)));
		consent.selectByValue("Yes");
		Thread.sleep(1000);
		Select consenti = new Select(driver.findElement(By.cssSelector(insuranceConsentIndicator)));
		consenti.selectByValue("Yes");
		Thread.sleep(1000);

		js.executeScript("window.scrollBy(0, 1000)");
		Thread.sleep(1000);
		Select applicant = new Select(driver.findElement(By.cssSelector(customerBankStatementConsentIndicator)));
		applicant.selectByValue("Yes");
		Thread.sleep(5000);
		
		Select income = new Select(driver.findElement(By.cssSelector(customerIncomeVerificationConsentIndicator)));
		income.selectByValue("Yes");
		Thread.sleep(5000);
		
		WebElement submitApplicationButton = driver.findElement(By.cssSelector(submitApplicationButton_css));
		submitApplicationButton.click();
		Thread.sleep(5000);
		

		String message = driver.switchTo().activeElement().getText();
		System.out.println(message);
		Assert.assertEquals(message, expectedMessage);

		WebElement successPopupOKButton = driver.findElement(By.cssSelector(successPopupOKButton_css));
		successPopupOKButton.click();
		Thread.sleep(2000);
		
		String URL = driver.getCurrentUrl();
		
		URL = URL.substring(URL.indexOf("=")+1, URL.length());
		
		System.out.println(URL);

		WebElement successMessage2 = driver.findElement(By.xpath(successMessage2_xpath));
		String message2 = successMessage2.getText();

		StringTokenizer tokenizer = new StringTokenizer(message2, " ");
		System.out.println("Token is:" + tokenizer.nextToken());
		String referenceNumber = tokenizer.nextToken(); 
		System.out.println("ref is:"+ referenceNumber);
		driver.close();

		driver.switchTo().window(parentHandle);

		Thread.sleep(1000);
		return URL;

	}
	
	public void writeDataIntoFile(WebDriver driver, String FinalRefID,String path) throws IOException {

		FileWriter fw = new FileWriter(path);
		PrintWriter out = new PrintWriter(fw);
		out.flush();
		out.println(FinalRefID); // out.append(finalNumber);
		out.close();

	}
	

	public String readDataFromFile(String path,WebDriver driver) throws IOException {

		File file = new File(path);
		String referanceID = FileUtils.readFileToString(file,"UTF-8");
				//readFileToString(file);
		
//		System.out.println("length of mPIN:" + mpin.length());
		return referanceID;
	}

	public void addExtras(WebDriver driver) throws IOException, InterruptedException {
		StringTokenizer extrasDescriptionTokenizer = new StringTokenizer(readInputData("extrasDescription"), ",");
		StringTokenizer extrasValueTokenizer = new StringTokenizer(readInputData("extrasValue"), ",");
		int flag = 1;
		WebElement addExtrasButton = driver.findElement(By.cssSelector(addExtrasButton_css));
		while (extrasDescriptionTokenizer.hasMoreTokens()) {
			addExtrasButton.click();
			Thread.sleep(1000);
			String extrasDescription_css = "#extraDescription_" + flag;
			String extrasValue_css = "#extraAmtInclVat_" + flag;

			Select extrasDescription = new Select(driver.findElement(By.cssSelector(extrasDescription_css)));
			extrasDescription.selectByValue(extrasDescriptionTokenizer.nextToken());

			WebElement extrasValue = driver.findElement(By.cssSelector(extrasValue_css));
			extrasValue.clear();
			extrasValue.sendKeys(extrasValueTokenizer.nextToken());
			flag++;
		}
	}
	
	public void adddealervaps(WebDriver driver) throws IOException, InterruptedException {
		
		String category = "Tracking,Tyre and Rim Warranty"; // Select category
		String supplier = "Netstar,Innovation Group";
		String product = "Netstar SS BAnking,WE BUY CARS TYRE & RIM WARRANTY (01-18)";
		String productoption = "SAFE AND SOUND (CASH OPTION),WE BUY CARS TYRE WARR 2YR OPT 2 (01-18)";
		StringTokenizer CategoryST = new StringTokenizer(category, ",");
		StringTokenizer SupplierST = new StringTokenizer(supplier, ",");
		StringTokenizer ProductST = new StringTokenizer(product, ",");
		StringTokenizer ProductOptionST = new StringTokenizer(productoption, ",");
		int flag = 0;

		do {
			
			driver.findElement(By.id("addVap")).click();
			Thread.sleep(4000);
			String category_id="vapCategory_" + flag;
			String supplier_id="vapSupplier_" + flag;
			String product_id="vapProduct_" + flag;
			String productoption_id="vapProductOption_" + flag;
			
			Select category1=new Select(driver.findElement(By.id(category_id)));  // Find category dropdown
			Thread.sleep(1500);
			category1.selectByVisibleText(CategoryST.nextToken());
			Select supplier1=new Select(driver.findElement(By.id(supplier_id)));  // Find category dropdown
			Thread.sleep(1500);
			supplier1.selectByVisibleText(SupplierST.nextToken());
			Select product1=new Select(driver.findElement(By.id(product_id)));  // Find category dropdown
			Thread.sleep(1500);
			product1.selectByVisibleText(ProductST.nextToken());
			Select productoption1=new Select(driver.findElement(By.id(productoption_id)));  // Find category dropdown
			Thread.sleep(1500);
			productoption1.selectByVisibleText(ProductOptionST.nextToken());
			
			flag++;
		} while (CategoryST.hasMoreTokens());
		
	}

	public void addInsuranceAddons(WebDriver driver) throws IOException, InterruptedException {
		if (readInputData("insuranceAddons") != null) {
			StringTokenizer checkboxes = new StringTokenizer(readInputData("insuranceAddons"), ",");
			do {
				Thread.sleep(1000);
				String token = checkboxes.nextToken();
				System.out.println(token);
				WebElement addon = driver.findElement(By.cssSelector("#" + token));
				addon.click();
			} while (checkboxes.hasMoreTokens());
		}
	}

	public void checkBusinessProtect(WebDriver driver) throws IOException {
		WebElement businessProtect = driver.findElement(By.id("checkBusinessProtect"));
		businessProtect.click();

		if (businessProtect.isSelected()) {
			WebElement value= driver.findElement(By.xpath("//*[@value='8000']"));
			value.click();
		}

	}
}

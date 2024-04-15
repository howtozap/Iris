package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
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

import io.appium.java_client.android.AndroidDriver;

public class SeritiHomePage {
	String recentlyViewedButton_css = "#btnRecentlyViewed";
	String copyPolicyButton_css = "#repPolicySearch_policycopycontrol1_0_imgCopy_0";
	String frame_css = "#ifrm";
	String groupDropdown_css = "#ddlGroupId";
	String branchDropdown_css = "#ddlBranchId";
	String selectAllLinkText = "Select All";
	String copyButton_css = "#btnCopy";
	String closeDialogButton_css = ".ui-dialog-titlebar-close.ui-corner-all";
	String viewCopiedPolicyButton_css = "#repPolicySearch_btnGridView_0";
	String applicationButton_css = "#ctl09_1714";
	String taxiAssociationStartLetter_css = "#ddlTaxiAssociationNameStartLetter";
	String taxiAssociationName_css = "#ddlTaxiAssociationId";
	String taxiAssociationRoute_css = "#ddlTaxiAssociationRouteId";
	String taxiAssociationArea_css = "#ddlTaxiAssociationArea";
	String numberOfLicensedSeats_css = "#txtNumberOfLicensedSeats";
	String estimatedMonthlyFuelSpend_css = "#txtCarEstimatedMonthlyFuel";
	String estimatedMonthlyMaintenanceSpend_css = "#txtCarEstimatedMonthlyMaintenance";
	String estimatedMonthlyTollsSpend_css = "#txtCarEstimatedMonthlyTolls";
	String taxiKMPerTrip_css = "#txtTaxiKilometresPerTrip";
	String taxiFarePerPassanger_css = "#txtTaxiFarePerPassenger";
	String taxiPassangerPerTrip_css = "#txtTaxiPassengersPerTrip";
	String taxiTripPerDay_css = "#txtTaxiTripsPerDay";
	String taxiDaysWorkedPerMonth_css = "#txtTaxiDaysWorkedPerMonth";
	String taxiDriverWagesPerMonth_css = "#txtTaxiDriversWagesPerMonth";
	String searchButton_xpath = ".//input[@value='Search']";
	String vehicleCodeFrame_css = "#vsVehicleCodeId_ifrm";
	String vehicleCode_css = "#txtVehicleCode";
	String searchForVehicle_css = "#btnSearchForVehicle";
	String selectButton_xpath = ".//input[@value='Select']";
	String registrationDate_css = "#txtFirstRegistrationDate";
	String sellingPrice_css = "#txtRetailPrice";
	String cashDeposite_css = "#txtCarCashDeposit";
	String chequeDeposite_css = "#txtCarChequeDeposit";
	String EFTDeposite_css = "#txtCarEFTDeposit";
	String saveButton_css = "#btnSaveInfo";
	String applyButton_css = "#btnApply";
	String productsButton_css = "#ctl09_1712";
	String accessoriesLinkText = "Accessories";
	String accessoriesDropdown_css = "#ddlAccessoryName";
	String accessoriesDetails_css = "#txtDetail";
	String addButton_css = "#btnAdd";
	String txtAmountExclVat_css = "#txtAmountExclVat";
	String byCashCheckbox_css = "#cbCashInd";
	String personalAccident_xpath = ".//*[contains(text(),'PERSONAL ACCIDENT')]//preceding::input[1]";
	String businessProtect_xpath = ".//*[contains(text(),'COVER OF R12000')]//preceding::input[1]";
	String zeroExcess_xpath = ".//*[text()='ZERO EXCESS']//preceding::input[1]";
	String depositSure_xpath = ".//*[text()='DEPOSIT PROTECT']//preceding::input[1]";
	String addonSaveButton_css = "#btnSaveBottom";
	String notOfferedPA_css = "#P3816_PO3816_rbnNotOffered";
	String notOfferedBP_css = "#P3821_PO3821_rbnNotOffered";
	String notOfferedZE_css = "#P3057_PO3057_rbnNotOffered";
	String notOfferedDS_css = "#P3063_PO3063_rbnNotOffered";

	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/SeritiInputFile.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	public void clickOnRecentlyViewedButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		WebElement recentlyViewedButton = driver.findElement(By.cssSelector(recentlyViewedButton_css));
		recentlyViewedButton.click();
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 2000)");
	}

	public void copyPolicy(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);

		String parentWindow = driver.getWindowHandle();

		WebElement copyPolicyButton = driver.findElement(By.cssSelector(copyPolicyButton_css));
		copyPolicyButton.click();

		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.cssSelector(frame_css)));

		Select group = new Select(driver.findElement(By.cssSelector(groupDropdown_css)));
		group.selectByValue("112");

		Thread.sleep(2000);
		Select branch = new Select(driver.findElement(By.cssSelector(branchDropdown_css)));
		branch.selectByValue("197");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 2000)");

		WebElement selectAllLink = driver.findElement(By.linkText(selectAllLinkText));
		selectAllLink.click();

		WebElement copyButton = driver.findElement(By.cssSelector(copyButton_css));
		copyButton.click();

		Thread.sleep(10000);

		driver.switchTo().window(parentWindow);

		WebElement closeDialog = driver.findElement(By.cssSelector(closeDialogButton_css));
		closeDialog.click();
		Thread.sleep(5000);
	}

	public void viewCopiedPolicy(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement view = driver.findElement(By.cssSelector(viewCopiedPolicyButton_css));
		view.click();

	}

	public void ClickFinanceApplication(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement applicationButton = driver.findElement(By.cssSelector(applicationButton_css));
		applicationButton.click();
		Thread.sleep(1000);
		WebElement financeApplication = driver.findElement(By.linkText("Finance Applications"));
		financeApplication.click();
	}

	public void ClickSATaxiFinance(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement SATaxiFinanceLink = driver.findElement(By.partialLinkText("SA TAXI FINANCE"));
		SATaxiFinanceLink.click();
	}

	public void FillApplicationForm(WebDriver driver) throws InterruptedException, IOException {
		Thread.sleep(1000);
		WebElement customerType = driver.findElement(By.xpath(
				".//*[contains(text(), '* Customer Type')]//parent::td//following-sibling::td//label[contains(text(), '"
						+ readInputData("customerType").toUpperCase() + "')]//preceding::input[1]"));
		customerType.click();

		WebElement financeTerm = driver.findElement(
				By.xpath(".//*[contains(text(), '* Finance Term ')]//parent::td//following-sibling::td//input[@value='"
						+ readInputData("financeTerm") + "']"));
		financeTerm.click();

		WebElement vehicleUse = driver.findElement(
				By.xpath(".//*[contains(text(), '* Vehicle Use')]//parent::td//following-sibling::td//input[@value='"
						+ readInputData("vehicleUse").toUpperCase() + "']"));
		vehicleUse.click();
		Thread.sleep(1000);

		String vehicle = readInputData("vehicleUse").toUpperCase();
		System.out.println(vehicle);
		if (vehicle.equalsIgnoreCase("taxi")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 2000)");

			Select taxiAssociationStartLetter = new Select(
					driver.findElement(By.cssSelector(taxiAssociationStartLetter_css)));
			taxiAssociationStartLetter.selectByValue(readInputData("taxiAssociationNameInitial").toUpperCase());
			Thread.sleep(1000);

			Select taxiAssociationName = new Select(driver.findElement(By.cssSelector(taxiAssociationName_css)));
			taxiAssociationName.selectByVisibleText(readInputData("taxiAssociationName").toUpperCase());
			Thread.sleep(1000);

			Select taxiAssociationRoute = new Select(driver.findElement(By.cssSelector(taxiAssociationRoute_css)));
			taxiAssociationRoute.selectByVisibleText(readInputData("taxiAssociationRoute").toUpperCase());
			Thread.sleep(1000);

			Select taxiAssociationArea = new Select(driver.findElement(By.cssSelector(taxiAssociationArea_css)));
			taxiAssociationArea.selectByVisibleText(readInputData("taxiAssociationArea").toUpperCase());
			Thread.sleep(1000);

			WebElement numberOfLicensedSeats = driver.findElement(By.cssSelector(numberOfLicensedSeats_css));
			numberOfLicensedSeats.clear();
			numberOfLicensedSeats.sendKeys(readInputData("numberOfLicensedSeats"));

			WebElement estimatedMonthlyFuelSpend = driver.findElement(By.cssSelector(estimatedMonthlyFuelSpend_css));
			estimatedMonthlyFuelSpend.clear();
			estimatedMonthlyFuelSpend.sendKeys(readInputData("estimatedMonthlyFuelSpend"));

			WebElement estimatedMonthlyMaintenanceSpend = driver
					.findElement(By.cssSelector(estimatedMonthlyMaintenanceSpend_css));
			estimatedMonthlyMaintenanceSpend.clear();
			estimatedMonthlyMaintenanceSpend.sendKeys(readInputData("estimatedMonthlyMaintenanceSpend"));

			WebElement estimatedMonthlyTollsSpend = driver.findElement(By.cssSelector(estimatedMonthlyTollsSpend_css));
			estimatedMonthlyTollsSpend.clear();
			estimatedMonthlyTollsSpend.sendKeys(readInputData("estimatedMonthlyTollsSpend"));

			WebElement taxiKMPerTrip = driver.findElement(By.cssSelector(taxiKMPerTrip_css));
			taxiKMPerTrip.clear();
			taxiKMPerTrip.sendKeys(readInputData("taxiKMPerTrip"));

			WebElement taxiFarePerPassanger = driver.findElement(By.cssSelector(taxiFarePerPassanger_css));
			taxiFarePerPassanger.clear();
			taxiFarePerPassanger.sendKeys(readInputData("taxiFarePerPassanger"));

			WebElement taxiPassangerPerTrip = driver.findElement(By.cssSelector(taxiPassangerPerTrip_css));
			taxiPassangerPerTrip.clear();
			taxiPassangerPerTrip.sendKeys(readInputData("taxiPassangerPerTrip"));

			WebElement taxiTripPerDay = driver.findElement(By.cssSelector(taxiTripPerDay_css));
			taxiTripPerDay.clear();
			taxiTripPerDay.sendKeys(readInputData("taxiTripPerDay"));

			WebElement taxiDaysWorkedPerMonth = driver.findElement(By.cssSelector(taxiDaysWorkedPerMonth_css));
			taxiDaysWorkedPerMonth.clear();
			taxiDaysWorkedPerMonth.sendKeys(readInputData("taxiDaysWorkedPerMonth"));

			WebElement taxiDriverWagesPerMonth = driver.findElement(By.cssSelector(taxiDriverWagesPerMonth_css));
			taxiDriverWagesPerMonth.clear();
			taxiDriverWagesPerMonth.sendKeys(readInputData("taxiDriverWagesPerMonth"));

		}

		//SelectVehicle(driver);

		WebElement isVehicleNew = driver
				.findElement(By.xpath(".//input[@value='" + readInputData("isVehicleNew").toUpperCase() + "']"));
		isVehicleNew.click();

		WebElement registrationDate = driver.findElement(By.cssSelector(registrationDate_css));
		registrationDate.clear();
		registrationDate.sendKeys(readInputData("registrationDate"));

		WebElement sellingPrice = driver.findElement(By.cssSelector(sellingPrice_css));
		sellingPrice.clear();
		sellingPrice.sendKeys(readInputData("sellingPrice"));

		Actions actions = new Actions(driver);

		WebElement cashDeposite = driver.findElement(By.cssSelector(cashDeposite_css));
		cashDeposite.clear();
		actions.doubleClick(cashDeposite).perform();
		cashDeposite.sendKeys(readInputData("cashDeposite"));

		WebElement chequeDeposite = driver.findElement(By.cssSelector(chequeDeposite_css));
		chequeDeposite.clear();
		actions.doubleClick(chequeDeposite).perform();
		chequeDeposite.sendKeys(readInputData("chequeDeposite"));

		WebElement EFTDeposite = driver.findElement(By.cssSelector(EFTDeposite_css));
		EFTDeposite.clear();
		actions.doubleClick(EFTDeposite).perform();
		EFTDeposite.sendKeys(readInputData("EFTDeposite"));

		WebElement yesbtn = driver.findElement(By.xpath("//*[@id='rbnOtherCompanyConsentInd_0']"));
		yesbtn.click();

		WebElement yesbtn1 = driver.findElement(By.xpath("//*[@id='rbnMarketingConsentInd_0']"));
		yesbtn1.click();

		WebElement yesbtn2 = driver.findElement(By.xpath("//*[@id='rbnMarketResearchConsentInd_0']"));
		yesbtn2.click();

	}
        // boolean f= false;
	public void SelectVehicle(WebDriver driver) throws InterruptedException, IOException {
		String parentWindow = driver.getWindowHandle();

		WebElement vehicleSearch = driver.findElement(By.xpath(searchButton_xpath));
		vehicleSearch.click();
		Thread.sleep(1000);

		driver.switchTo().frame(driver.findElement(By.cssSelector(vehicleCodeFrame_css)));

		WebElement vehicleCode = driver.findElement(By.cssSelector(vehicleCode_css));
		vehicleCode.sendKeys(readInputData("MMCode"));

		WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement searchForVehicle = driver.findElement(By.xpath("//*[@id='btnSearchForVehicle']"));	
        searchForVehicle.click();
        
		wait1.until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='btnSearchForVehicle']"), "disabled","disabled"));
		
	//	wait1.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.xpath("//*[@id='btnSearchForVehicle']"), "disabled","disabled")));
		
		WebDriverWait wait3 = new WebDriverWait(driver, 40);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSelect14691")));
		
		WebElement selectButton = driver.findElement(By.xpath(selectButton_xpath));
		selectButton.click();

		driver.switchTo().window(parentWindow);
	}

	public void ClickSaveButton(WebDriver driver) {
		WebElement saveButton = driver.findElement(By.cssSelector(saveButton_css));
		saveButton.click();
	}

	public void ClickApplyButton(WebDriver driver) throws IOException {
		WebElement applyButton = driver.findElement(By.cssSelector(applyButton_css));
		applyButton.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 2000)");
		
		WebElement refreshbtn= driver.findElement(By.id("imgRefresh"));
		refreshbtn.click();
		
		String spanText=driver.findElement(By.xpath("//div[@class='marquee']/span")).getText();

        System.out.print("Total span line text: "+ spanText);

		StringTokenizer tokenizer = new StringTokenizer(spanText, " ");
		
		System.out.println("text is:" + tokenizer.nextToken());

		String transactionID = tokenizer.nextToken(); 
		
		System.out.println("transaction is:"+ transactionID);

        String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\SeritiTransactionID.txt";

        writeDataIntoFile(driver,transactionID,path1);
		
	}

	public void writeDataIntoFile(WebDriver driver, String FinalTrasactionID,String path) throws IOException {

		FileWriter fw = new FileWriter(path);
		PrintWriter out = new PrintWriter(fw);
		out.flush();
		out.println(FinalTrasactionID); // out.append(finalNumber);
		out.close();

	}
	
	
	public String readDataFromFile(String path, WebDriver driver) throws IOException {

		File file = new File(path);
		String trancationID = FileUtils.readFileToString(file);

//		System.out.println("length of mPIN:" + mpin.length());
		return trancationID;
	}

	
	public void AddAccessories(WebDriver driver) throws InterruptedException, IOException {
		String readAccessories = readInputData("accessories");

		if (!(readAccessories.isEmpty())) {
			Thread.sleep(1000);
			WebElement productsButton = driver.findElement(By.cssSelector(productsButton_css));
			productsButton.click();

			WebElement accessoriesLink = driver.findElement(By.linkText(accessoriesLinkText));
			accessoriesLink.click();

			StringTokenizer accessoryTokens = new StringTokenizer(readInputData("accessories"), ",");

			int flag = 0;

			String detailsText = readInputData("details");
			List<String> items = Arrays.asList(detailsText.split(","));

			String amtExclTax = readInputData("txtAmountExclVat");
			List<String> txAmtExcltax = Arrays.asList(amtExclTax.split(","));

			String byCash = readInputData("byCash");
			List<String> byCashList = Arrays.asList(byCash.split(","));

			String costGroup = readInputData("costGroup");
			List<String> costGrpList = Arrays.asList(costGroup.split(","));

			while (accessoryTokens.hasMoreTokens()) {
				Select accessoriesDropdown = new Select(driver.findElement(By.cssSelector(accessoriesDropdown_css)));
				accessoriesDropdown.selectByVisibleText(accessoryTokens.nextToken().toUpperCase());

				WebElement details = driver.findElement(By.cssSelector(accessoriesDetails_css));
				details.clear();
				details.sendKeys(items.get(flag));

				WebElement txtAmountExclVat = driver.findElement(By.cssSelector(txtAmountExclVat_css));
				txtAmountExclVat.clear();
				txtAmountExclVat.sendKeys(txAmtExcltax.get(flag));

				WebElement byCashCheckbox = driver.findElement(By.cssSelector(byCashCheckbox_css));
				if ((byCashList.get(flag)).equalsIgnoreCase("true")) {
					if (!(byCashCheckbox.isSelected())) {
						byCashCheckbox.click();
					}
				} else {
					if (byCashCheckbox.isSelected()) {
						byCashCheckbox.click();
					}
				}

				String cstGrp = costGrpList.get(flag);
				WebElement costGroupRadioButton = driver.findElement(By.xpath(".//input[@value='" + cstGrp + "']"));
				costGroupRadioButton.click();

				WebElement addButton = driver.findElement(By.cssSelector(addButton_css));
				addButton.click();

				flag++;
			}

		}

	}

	public void InsuranceAddons(WebDriver driver) throws IOException, InterruptedException {
		String addonDetails = readInputData("insuranceAddons");
		if (!(addonDetails.isEmpty())) {
			Thread.sleep(1000);
			WebElement productsButton = driver.findElement(By.cssSelector(productsButton_css));
			productsButton.click();

			WebElement insuranceAddonLink = driver.findElement(By.linkText("Value Added Products (Insurance)"));
			insuranceAddonLink.click();

			Thread.sleep(1000);

			WebElement notOffered1 = driver.findElement(By.cssSelector(notOfferedPA_css));
			notOffered1.click();

			WebElement notOffered2 = driver.findElement(By.cssSelector(notOfferedBP_css));
			notOffered2.click();

			WebElement notOffered3 = driver.findElement(By.cssSelector(notOfferedZE_css));
			notOffered3.click();

			Thread.sleep(1000);
			WebElement notOffered4 = driver.findElement(By.cssSelector(notOfferedDS_css));
			notOffered4.click();

			StringTokenizer addon = new StringTokenizer(addonDetails, ",");

			while (addon.hasMoreTokens()) {
				String addonName = addon.nextToken().toUpperCase();
				switch (addonName) {
				case "PERSONAL ACCIDENT":
					WebElement personalAccident = driver.findElement(By.xpath(personalAccident_xpath));
					personalAccident.click();
					break;

				case "BUSINESS PROTECT":
					WebElement businessProtect = driver.findElement(By.xpath(businessProtect_xpath));
					businessProtect.click();
					break;

				case "ZERO EXCESS":
					WebElement zeroExcess = driver.findElement(By.xpath(zeroExcess_xpath));
					zeroExcess.click();
					break;

				case "DEPOSIT SURE":
					WebElement depositSure = driver.findElement(By.xpath(depositSure_xpath));
					depositSure.click();
					break;

				}

				WebElement addonSave = driver.findElement(By.cssSelector(addonSaveButton_css));
				addonSave.click();
			}
		}
	}

}

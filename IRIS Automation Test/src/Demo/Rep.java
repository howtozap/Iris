package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Rep {
	@Test
	public void Testallure() {
		 WebDriverManager.chromedriver().setup();
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--Remote-allow-Origins=*");
         WebDriver d = new ChromeDriver(options);
         d.get("https://www.instagram.com/?hl=en");
         d.manage().window().maximize();
         boolean logo = d.findElement(By.xpath("//i[@aria-label='Instagram']")).isDisplayed();
         if (logo == true) {
             System.out.println("Logo is displayed");
         } else {
             System.out.println("Logo is not displayed");
         }
         d.close();
    
	}

}

package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SummaryScreen {

	static public String ruleOverriding(WebDriver driver, String creditdecision) throws InterruptedException, IOException {

		 Thread.sleep(20000);
		
		List<WebElement> rows = driver.findElements(
				By.xpath("//table[@id='grdOverrideReasonBusinessRule_DXMainTable']/tbody/tr[position()!=1]"));
        System.out.println(rows.size());
        
        if(rows.size()!=0)
        {
    
        	creditdecision="Yes";
	        if(!(driver.findElement(By.xpath("//table[@id='grdOverrideReasonBusinessRule_DXMainTable']/tbody/tr[position()!=1]"))
	        		.getAttribute("class").contains("dxgvEmptyDataRow"))) {
	        
	         for(int i=0; i<rows.size(); i++) {
	
	        	 Thread.sleep(2000);
	        	 WebElement comment = rows.get(i).findElement(By.xpath("td[4]/table/tbody/tr/td/input"));
	        	 
	        	 if(comment.getAttribute("class").contains("dxeEditArea dxeEditAreaSys dxeDisabled")) {
	        		 System.out.println("\nYou can not override this rule because commnet section is disable");  
	        	 }
	        	 else {
	        	 comment.clear(); 
	        	 comment.sendKeys("Test"+Integer.toString(i));
	        	  Thread.sleep(5000);
	        	 }
	      
	        	 WebElement dropdown =rows.get(i).findElement(By.xpath("td[3]/table/tbody/tr/td[3]"));
	        	 if(dropdown.getAttribute("class").contains("dxeButton dxeButtonEditButton dxeDisabled dxeButtonDisabled")) {
	        		System.out.println("\nYou can not override this rule because dropdown is disable"); 
	        	 }
	        	 else {
	        		 Thread.sleep(3000);
	        		 dropdown.click(); 
	        		 Thread.sleep(5000);
	            	 WebElement yesOption = rows.get(i).findElement(By.xpath("td[3]/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr/td"));
	            	 Thread.sleep(2000);
	            	 yesOption.click();
	            	 Thread.sleep(5000);
	        	 }
	        
	         }
	                 
	        }
        
        else {
        	System.out.println("\n No data to display ");
        }
	}
        
    else
    {
    	
    	System.out.println("\n There are no rules");
        	
    }
        return creditdecision;
}
	
}

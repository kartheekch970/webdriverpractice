package _22_webdriverpractice.pageobjects.loadablecomponent.pages;

import static driverfactory.DriverFactory.getChromeDriver;
import static driverfactory.DriverFactory.getWebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcessedFromPage {
	
	private WebDriver driver = getChromeDriver();
	private WebDriverWait wait = getWebDriverWait();

    private ProcessedFromPage() {
 		// Hide it. Prevent Instantiation
 	}
     
    public static ProcessedFromPage getProcessedFromPage() {
     	return new ProcessedFromPage();
    }
	
    public ProcessedFromPage waitUntilPageIsLoaded() {
        
    	wait.until(
        		ExpectedConditions.titleIs("Processed Form Details"));
    	
    	return this;
    	
    }

    public String getValueFor(String valueID) {
       
    	WebElement fieldValueElement = driver.findElement(By.id("_value" + valueID));
        return fieldValueElement.getText();
        
    }
}

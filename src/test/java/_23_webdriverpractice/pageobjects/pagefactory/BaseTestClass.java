package _23_webdriverpractice.pageobjects.pagefactory;

import static driverfactory.DriverFactory.getChromeDriver;
import static driverfactory.DriverFactory.getWebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTestClass {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeSuite
	public void setupTest() {
		driver = getChromeDriver();
		wait = getWebDriverWait();
	}
	
    @AfterSuite(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }
	
}

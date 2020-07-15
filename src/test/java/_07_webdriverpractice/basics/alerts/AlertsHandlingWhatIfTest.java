package _07_webdriverpractice.basics.alerts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsHandlingWhatIfTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com/"+
                "styled/alerts/alert-test.html");
		
	}
	
	@Test
	public boolean doesAlertExist() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			System.out.println("Exception thrown: No Alert Present " + e.getMessage());
			return false;
		}
	}
			
	@AfterClass
	public void tearDown() {
//		driver.close();
	}
		
}

package webdriverpractice.synchronization.webdriverwait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import webdriverpractice.utils.TestUtils;

public class WebDriverWaitFAQTest extends TestUtils {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");
		
	}
	
	@Test(description = "Producing TimeoutException: Example")
	public void timeOutExceptionTestWithWebDriverWait() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(
										By.cssSelector("notexists")));
		} catch (TimeoutException e) {
			System.out.println("Exception occured: " + e.getMessage());
			e.printStackTrace();
		}
		fail("Element does not exists!");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

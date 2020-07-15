package _02_webdriverpractice.basics.interrogation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInterrogateTest {
	
	private static WebDriver driver;
	final public String PROTOCOL = "HTTP";
	final public String DOMAIN = "www.compendiumdev.co.uk/selenium/basic_web_page.html";
	final public String ROOT_URL = PROTOCOL + "://" + DOMAIN;
	
	@BeforeClass
	public static void setupManager() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod()
	public static void setupDriver() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void driverLevelPageInterrogateMethods() {
		
		driver.get(ROOT_URL);
		// assert title
		assertEquals(driver.getTitle().trim(), "Basic Web Page Title");
		
		// assert current url
		assertTrue(driver.getCurrentUrl().endsWith("html"), "getCurrentUrl assertion passed!");
		
		//assert from page source
		assertTrue(driver.getPageSource().contains("A paragraph of text"));	
	}
	
	@AfterMethod
	public static void teardown() {
		driver.close();
	}
	
}

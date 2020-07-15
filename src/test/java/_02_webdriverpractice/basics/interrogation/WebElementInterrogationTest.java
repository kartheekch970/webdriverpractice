package _02_webdriverpractice.basics.interrogation;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementInterrogationTest {
	
	static WebDriver driver;
    final String theTestPageURL =
            "http://www.compendiumdev.co.uk" +
            "/selenium/basic_web_page.html";
	
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
	public void webElementInterrogation() {
		driver.get(theTestPageURL);
		WebElement para = driver.findElement(By.id("para1"));
		assertTrue(para.getText().equalsIgnoreCase("A paragraph of text"));
	}
	
	@Ignore
	@AfterMethod
	public static void teardown() {
		driver.close();
	}
	
}

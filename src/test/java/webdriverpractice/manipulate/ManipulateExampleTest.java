package webdriverpractice.manipulate;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManipulateExampleTest {
	
	static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void setupDriverByManager() {
		
		WebDriverManager.chromedriver().setup();
	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");
	}
	
	@Test
	public void simpleInteraction() {
		element = driver.findElement(
						 By.cssSelector("input[value='rd1']"));
		
		assertFalse(element.isSelected(),
						"Element not selected at first");
		
		element.click();
		
		assertTrue(element.isSelected(),
						"Element must be selected after clicking on it.");
		
	}
	
	@AfterClass
	public static void teardown() {}
}

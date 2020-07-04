package webdriverpractice.manipulate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManipulateWhatIfTest {
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");
	}
	
	@Test
	public void canIClearACheckbox() {
		try {
			driver.findElement(By.cssSelector("input[value='cb1']")).clear();
		} catch (RuntimeException e) {
			throw new WebDriverException("Expection occured: " + e.getMessage());
		}
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

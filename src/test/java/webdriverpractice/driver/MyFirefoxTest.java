package webdriverpractice.driver;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirefoxTest {
	
	@Test
	public void driverIsKing() {
		
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = new FirefoxDriver();
		driver.get("test");
		
		assertTrue(driver.getTitle().startsWith("Selenium"));
		
		driver.quit();
	}
}

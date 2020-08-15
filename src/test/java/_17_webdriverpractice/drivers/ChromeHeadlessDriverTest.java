package _17_webdriverpractice.drivers;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeHeadlessDriverTest {
	
	@Test
	public void headlessModeChrome() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}
	
	@Test
	public void headlessChromeUsageViaArguments() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}	
	
}


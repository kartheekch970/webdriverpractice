package _17_webdriverpractice.drivers;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternetExplorerDriverTest {
	
	@Test(description = "Using system path variable")
	public void basicIEDriverTest() {
		
		WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}
	
	@Test
	public void manualSetUpIEDriverUsingSystemProperty() {
		
		String ieDriverLocation = "C:\\webdrivers\\iedriver\\IEDriverServer.exe";
		
		System.setProperty("webdriver.ie.driver", ieDriverLocation);
										
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}
	
	@Test
	public void usingWebDriverManager() {
		
		WebDriverManager.iedriver().setup();
		
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.quit();
		
	}	
}


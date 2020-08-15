package _17_webdriverpractice.drivers;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverTest {
	
	@Test(description = "Using system path variable")
	public void basicChromeDriverTest() {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}
	
	@Test
	public void manualSetUpChromeDriverUsingSystemProperty() {
		
		String chromeDriverLocation = "C:\\Users\\admin\\Desktop\\chromedriver\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
										
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
	}
	
	@Test
	public void usingWebDriverManager() {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
		
	}
	
	
	@Test
	public void basicChromeDriverOptions() {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions chromeOptions = new ChromeOptions();		
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.addArguments("disable-plugins");
		chromeOptions.addArguments("disable-extensions");
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.close();
		
	}
	
	
	
}


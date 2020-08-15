package _18_webdriverpractice.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverConfigManager {
	
	private static WebDriver driver = null;
	private static String browser;
	
	public WebDriverConfigManager() {
		try {
			String browser = System.getProperty("BrowserUnderTest");
			WebDriverConfigManager.browser = browser;
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public static WebDriver invokeBrowser() {
				
		if (browser.equalsIgnoreCase("CHROME") || browser.equalsIgnoreCase("GOOGLECHROME")) {
			driver = invokeChromeBrowser(false); 
		}
		else if(browser.equalsIgnoreCase("CHROMEHEADLESS")) 
		{
			driver = invokeChromeBrowser(false);
		}
		
		driver.manage().window().maximize();
		return driver;
		
	}

	private static WebDriver invokeChromeBrowser(boolean headlessmode) {
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(headlessmode);
		options.addArguments("--disable-gpu");
		options.setAcceptInsecureCerts(true);
		
		return new ChromeDriver(options);
	}
	
}

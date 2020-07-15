package _15_webdriverpractice.cookies;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CookiesExampleTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://compendiumdev.co.uk/" + 
				"selenium/search.php");	
	}
	
	@Test
	public void interactWithCookies() {
		
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		Cookie cookie = driver.manage().
							getCookieNamed("SeleniumSimplifiedLastSearch");
		
		assertEquals(cookie, null);
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
	
}

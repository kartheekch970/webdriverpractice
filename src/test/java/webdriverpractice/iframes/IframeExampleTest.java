package webdriverpractice.iframes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IframeExampleTest {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com"
						+ "/styled/iframes-test.html");	
	}
	
	
	@Test
	public void switchToFrameAndFindElement() {
		
		WebElement iFrameElement;
		
		driver.switchTo().frame("thedynamichtml");
		
		iFrameElement = driver.findElement(By.cssSelector("h1"));
		assertEquals(iFrameElement.getText(), "iFrame");
		
		driver.switchTo().defaultContent();

		System.out.println(driver.getTitle());
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
}

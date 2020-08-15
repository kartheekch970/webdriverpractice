package _10_webdriverpractice.basics.windows;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsExerciseTest extends TestUtils {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		
		//set headless mode - make it true/false to test both ways of windowhandles
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		
		driver = new ChromeDriver(options);
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com/"
								+ "styled/windows-test.html");	
	}
	
	
	@Test
	public void playingWithWindowHandlesTest() {
		
		explicitWait();
		driver.findElement(By.cssSelector("a#gobasicajax")).click();
		
		explicitWait();
		driver.switchTo().window("windowsindex"); 	
		assertEquals(driver.getTitle(), "Windows Example Test");
		
		explicitWait();
		driver.switchTo().window("basicajax"); 
		assertEquals(driver.getTitle(), "Test Page For Basic Ajax Example");
		
		driver.switchTo().window("windowsindex");
		explicitWait();
		driver.findElement(By.cssSelector("a#goattributes")).click();
		
		driver.switchTo().window("windowsindex");
		
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			explicitWait();
			if (driver.getTitle().equals("Test Page For Element Attributes") ) {
				break;
			}
		}
		
		assertEquals(driver.getTitle(), "Test Page For Element Attributes");	
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

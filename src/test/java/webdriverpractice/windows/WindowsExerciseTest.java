package webdriverpractice.windows;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Set;

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
import webdriverpractice.utils.TestUtils;

public class WindowsExerciseTest extends TestUtils {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
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
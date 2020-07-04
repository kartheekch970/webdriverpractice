package webdriverpractice.windows;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import webdriverpractice.utils.TestUtils;

public class WindowsFAQTest extends TestUtils {
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
	
	@Test(description = "What if we try to switch to the window that does not exist!")
	public void replicateNoSuchWindowException() {
			
		String handle1 = driver.getWindowHandle();
		System.out.println(handle1);
		
		explicitWait();
		driver.findElement(By.cssSelector("a#gobasicajax")).click();
		
		explicitWait();
		driver.switchTo().window("basicajax"); 	
		
		String handle2 = driver.getWindowHandle();
		System.out.println(handle2);
		
		driver.close();
		
		try {
			driver.switchTo().window("basicajax");
		} catch (NoSuchWindowException e) {
			System.out.println("Eception is: " + e.getMessage());
			fail("Expected a exception as window is closed already!");
		}	
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

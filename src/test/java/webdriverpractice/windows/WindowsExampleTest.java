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

public class WindowsExampleTest {
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
	public void usingWindowHandles() {
		
		int windowHandlesCount;
		String currentWindowHandle;
		
		currentWindowHandle = driver.getWindowHandle();
		System.out.println(currentWindowHandle);
		
		windowHandlesCount = driver.getWindowHandles().size();
		assertEquals(windowHandlesCount, 1);
		
		driver.findElement(By.cssSelector("a#gobasicajax")).click();
		
		windowHandlesCount = driver.getWindowHandles().size();
		assertEquals(windowHandlesCount, 2);
		
		assertEquals(driver.getTitle(), "Test Page For Basic Ajax Example");
	}	
	
	
	@Test
	public void switchToWindowByName() {
		
		String currentWindowHandle = driver.getWindowHandle();
		
		driver.findElement(By.cssSelector("a#gobasicajax")).click();
		
		driver.switchTo().window(currentWindowHandle);
		System.out.println(currentWindowHandle);
		
		driver.switchTo().window("basicajax");

		String newWindowhandle = driver.getWindowHandle();
		System.out.println(newWindowhandle);
		
		assertEquals(driver.getTitle(), "Test Page For Basic Ajax Example");
		
	}
	
	
	@Test
	public void switchToNewWindowByHandle() {
		
		String currentWindowHandle = driver.getWindowHandle();
		
		driver.findElement(By.cssSelector("a#gobasicajax")).click();
		
		String newWindowHandle = "";
		Set<String> windowHandles =  driver.getWindowHandles();
		
		for (String windowHandle : windowHandles) {
			if (!currentWindowHandle.equals(windowHandle)) {
				newWindowHandle = windowHandle;
				System.out.println(newWindowHandle);
				break;
			}
		}
		
//		try {
//		Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
		
		driver.switchTo().window(newWindowHandle);
		assertEquals(driver.getTitle(), "Test Page For Basic Ajax Example");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

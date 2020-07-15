package _00_webdriverpractice.basics.driver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyChromeTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setupManager() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod
	public void setupDriver() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void driverIsTheKing() {
		
		driver.navigate().to("https://testpages.herokuapp.com/");	
		assertTrue(driver.getTitle().startsWith("Selenium"));
		
		driver.close();	
	}
	
	@Test
	public void quitBeforeCloseTest() {
		
		driver.navigate().to("https://testpages.herokuapp.com/");
		driver.quit();
		try {
			driver.close();
			fail("Expected an exception: NoSuchSessionException");
		} catch (NoSuchSessionException e) {
			//throw new NoSuchSessionException("New Exception found: " + e.getMessage());
			assertTrue(true, "We should get an exception when try to use driver after quit() method.");
		}
		
	}
	
	@Test
	public void closeWithNoBrowserOpen() {
		assertEquals(driver.getTitle(), "");	
		driver.close();
	}
	
	@AfterClass
	public void tearDown() {
		// nothing as of now as I was testing quit() and close() methods in @Test
	}

}

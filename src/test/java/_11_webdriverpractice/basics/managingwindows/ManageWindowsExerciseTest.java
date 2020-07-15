package _11_webdriverpractice.basics.managingwindows;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ManageWindowsExerciseTest extends TestUtils {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.get("https://testpages.herokuapp.com/"
								+ "styled/css-media-queries.html");	
		driver.manage().window().maximize();
	}
	
	@Test
	public void resizeAndTestForElements() {
		
		WebElement element;
		Dimension size = driver.manage().window().getSize();
		
		if (size.width >= 1800) {
			element = driver.findElement(By.cssSelector("h2.s1800"));
			assertTrue(element.isDisplayed());
		} 		
		
		driver.manage().window().setSize(
							new Dimension(1700, size.height));
		explicitWait();
		if (size.width >= 1600) {
			element = driver.findElement(By.cssSelector("h2.s1600"));
			assertTrue(element.isDisplayed());
		} 
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

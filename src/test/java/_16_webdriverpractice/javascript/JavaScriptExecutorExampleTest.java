package _16_webdriverpractice.javascript;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutorExampleTest {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private String URL = "http://www.compendiumdev.co.uk/selenium/canvas_basic.html";
	
	@BeforeClass
	public void setupDriver() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	@Test
	public void callJSFunctionOnThePage() {
		
		int commandCount;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		commandCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertEquals(commandCount, 2);
		
		js.executeScript("draw(1, 150, 150,40, '#FF1C0A');");
		
		commandCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertEquals(commandCount, 3);
		
	}
	
	@AfterClass
	public void tearDown() {
//		driver.close();
	}

}

package _16_webdriverpractice.javascript;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

public class JavaScriptExecutorExerciseTest {
	
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
		driver.navigate().refresh();
	}
	
	@Test
	public void changeTitleUsingJavaScript() {
		
		assertEquals(driver.getTitle(), "Javascript Canvas Example");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.title=arguments[0]", "Changed by JSE");
		
		assertEquals(driver.getTitle(), "Changed by JSE");
	}
	
	@Test
	public void returnHardCodedValueUsingJS() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	
		assertEquals(js.executeScript("return 10;"), 10L, "Returning hard coded value 10");
		
	}
	
	@Test
	public void returnCalculatedValueUsingJS() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	
		assertEquals(js.executeScript("return (arguments[0]+arguments[1]);", 10, 10),
					  20L,
					  "Returning calculated value from JS");
	}
	
	@Test(description = "You can hide an element using JavaScript")
	public void hideWebElementPassedAsParam() {
		
		WebElement commands = driver.findElement(By.cssSelector("#commands"));
		
		assertTrue(commands.isDisplayed(),"Commands is displayed before hiding!");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).hide();", commands);
		
		assertFalse(commands.isDisplayed(),"Commands is NOT displayed after hiding!");		
	}
	
	@Test(description = "You can hide entire using JavaScript's JQuery")
	public void useJQueryToHideBodyWithNoParams() {
		
		WebElement commands = driver.findElement(By.cssSelector("#commands"));
		
		assertTrue(commands.isDisplayed(),"Commands is displayed before hiding the entire body!");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('body').hide();");
		
		assertFalse(commands.isDisplayed(),"Commands is NOT displayed after hiding the entire body!");		
	}
	
	@Test
	public void passArgsToTheJavaScript() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int commandCount= 0;
		
		commandCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertEquals(commandCount, 2);
		
		int count = 0;
		for (int testLoop = 0; testLoop < 10; testLoop++) {
			js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);",
							 testLoop * 20, testLoop * 20,
							 "#" + testLoop + testLoop + "0000");
			count++;
		}
		
		commandCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertEquals(commandCount, 2 + count);
		
	}
	
	@Test
	public void generatesAlerts() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("alert('alert triggered by webdriver');");
		assertEquals(driver.switchTo().alert().getText(),
						"alert triggered by webdriver");
		
		driver.switchTo().alert().accept();
	}
	
	
	@AfterClass
	public void tearDown() {
//		driver.close();
	}

}

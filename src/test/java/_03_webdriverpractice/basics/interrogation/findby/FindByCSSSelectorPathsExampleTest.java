package _03_webdriverpractice.basics.interrogation.findby;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindByCSSSelectorPathsExampleTest {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/find_by_playground.php");
	}
	
	@Test
	public void directDecendent() {
				
		assertEquals(driver.findElements(By.cssSelector("div > p")).size(), 41,
							"div p - direct decendent example");
		
		assertEquals(driver.findElements(By.cssSelector("div > li")).size(), 0,
							"div p - direct decendent example");
	}
	
	@Test
	public void anyDecendent() {
			
		assertEquals(driver.findElements(By.cssSelector("div p")).size(), 41,
							"div > p - any decendent example");
		
		assertEquals(driver.findElements(By.cssSelector("div li")).size(), 25,
							"div > p - any decendent example");
	}
	
	@Test
	public void sibilingsOfPreceding() {
			
		assertEquals(driver.findElements(By.cssSelector("li + li")).size(), 24,
							"li + li - sibilingsOfPreceding example");
		
		assertEquals(driver.findElements(By.cssSelector("li")).size(), 25,
							"li - sibilingsOfPreceding example");
	}
	
	@Test
	public void firstChild() {
			
		assertEquals(driver.findElements(By.cssSelector("li:first-child")).size(), 1,
							"li:first-child - first child example");

	}
	
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
	
}

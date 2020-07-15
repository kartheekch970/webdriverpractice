package _03_webdriverpractice.basics.interrogation.findby;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChainedFindByExample {
	
	static WebDriver driver;
	private WebElement element;
	
	@BeforeClass
	public static void setupDriverByManager() {
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/find_by_playground.php");
	}
	
	@Test
	public void chainingByFindElement() {
		element = driver.findElement(By.id("id")).
							findElement(By.className("className")).
								findElement(By.name("name"));
		
		assertEquals(element.getText(), "Some Text");
	}				
	
	@Test
	public void chainingBySupportClassByChained() {
		element = driver.findElement(
							new ByChained(By.id("id"),
										  By.name("name"),
										  By.className("className")));
		
		assertEquals(element.getText(), "Some Text");
	}
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}

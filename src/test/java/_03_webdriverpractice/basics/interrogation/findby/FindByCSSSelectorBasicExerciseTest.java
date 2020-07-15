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

public class FindByCSSSelectorBasicExerciseTest {
	private WebElement element;
	private List<WebElement> elements;
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
	public void findByIdUsingCSS() {
		element = driver.findElement(
						By.cssSelector("#p31"));
		
		assertEquals(element.getAttribute("name"), "pName31");
	}
	
	@Test
	public void findByNameUsingCSS() {
		element = driver.findElement(
						By.cssSelector("[name='ulName1']")); 
		
		assertEquals(element.getAttribute("id"), "ul1");
	}
	
	@Test
	public void findByClassNameUsingCSS() {
		element = driver.findElement(
						By.cssSelector(".specialDiv"));
		
		assertEquals(element.getAttribute("id"), "div1");
	}
	
	@Test
	public void findBytagNameUsingCSS() {
		element = driver.findElement(
						By.cssSelector("li"));
		
		assertEquals(element.getAttribute("name"), "liName1");
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

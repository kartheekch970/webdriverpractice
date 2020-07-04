package webdriverpractice.interrogation.findby;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindElementsExerciseTest {
	
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
	public void doesFindElementsThrowsExceptionWhenNoMatch() {
		elements = driver.findElements(
						By.id("WrongLocator"));
		assertEquals(elements.size(), 0);
		assertTrue(elements.isEmpty());
	}
	
	
	@Test
	public void checkForDivTags() {
		elements = driver.findElements(
						By.tagName("div"));
		assertEquals(elements.size(), 19);
	}	
	
	@Test
	public void checkForAtags() {
		elements = driver.findElements(
						By.partialLinkText("jump to para"));
		assertEquals(elements.size(), 25);
	}
	
	@Test
	public void checkForParasgraphs() {
		elements = driver.findElements(
						By.tagName("p"));
		
		int nextedParas = 0;
		for (WebElement element : elements) {
			if (element.getText().contains("nested para")) {
				nextedParas ++;
			}
		}
		
		assertEquals(elements.size(), 41);
		assertEquals(nextedParas, 16);
		
	}
	
	@Test
	public void nestedParasByClass() {
		elements = driver.findElements(
						By.className("nestedDiv"));
		assertTrue(elements.size() > 0,
						"Nested Paragraphs by class: " + elements.size());
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}

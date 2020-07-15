package _03_webdriverpractice.basics.interrogation.findby;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindElementsExampleTest {
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
	public void returnAListOfElementsByClassName() {
		
		List<WebElement> elements;
		elements = driver.findElements(
						By.className("normal"));
		
		Set<String> foundTags = new HashSet<String>();
		
		for (WebElement element : elements) {
			foundTags.add(element.getTagName().toLowerCase());
		}
		
		assertTrue(foundTags.contains("p"), "Tags will contains p tag");
		assertTrue(foundTags.contains("ul"), "Tags will contains ul tag");
		assertTrue(foundTags.contains("li"), "Tags will contains li tag");
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

package webdriverpractice.interrogation.findby;

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

public class FindByCSSSelectorExampleTest {
	
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
	public void findByCSSID() {
		element = driver.findElement(
							By.cssSelector("#p3"));
		assertTrue(!element.getText().isEmpty());
	}
	
	@Test
	public void findbyCSSTagName() {
		elements = driver.findElements(By.tagName("div"));
		
		assertTrue(elements.size() > 0);
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}

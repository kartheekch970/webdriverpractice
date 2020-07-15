package _03_webdriverpractice.basics.interrogation.findby;

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

public class FindByXPathExampleTest {
	
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
	public void findNumberOfParagraphs() {
		elements = driver.findElements(
							By.xpath("//p"));

		int nestedParas = 0;
		for (WebElement element : elements) {
			if (element.getText().contains("nested")) {
				nestedParas ++;
			}
		}
		
		assertTrue(elements.size() > 0);
		assertTrue(nestedParas > 0);
	}
	
	
	@Test
	public void findSpecificPara() {
		
		element = driver.findElement(By.xpath("//p[@name = 'pName5']"));
		assertTrue(element.getText().contains("para"));
		
	}
	
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}

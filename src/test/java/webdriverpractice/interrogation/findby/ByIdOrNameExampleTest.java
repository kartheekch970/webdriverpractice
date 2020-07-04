package webdriverpractice.interrogation.findby;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ByIdOrNameExampleTest {
	
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
	public void byIdOrNameExample() {
		WebElement element = driver.findElement(
								new ByIdOrName(""));
		
		assertEquals(element.getText(), "SomeText");
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
	
}

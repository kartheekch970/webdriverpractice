package webdriverpractice.manipulate;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManipulationFirstTryExampleTest {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");
	}
	
	@Ignore("Without waits this will only work in debug mode")
	@Test()
	public void manipulateFirstTest() {
		
		driver.findElement(By.cssSelector("option[value='3']")).click();
		driver.findElement(By.cssSelector("")).click();
		
		driver.findElement(By.name("")).click();
		
		assertEquals(driver.findElement(By.cssSelector("")).getText(), "");
		
	} 
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

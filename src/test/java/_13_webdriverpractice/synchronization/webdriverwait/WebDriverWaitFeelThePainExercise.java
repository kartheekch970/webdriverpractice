package _13_webdriverpractice.synchronization.webdriverwait;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWaitFeelThePainExercise extends TestUtils {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");
	}
	
	@Test(description = "This test will fail without wait")
	public void feelThePainExercise() {
		
		try {
			
			WebElement combo1, combo2;
			combo1 = driver.findElement(By.cssSelector("#combo1"));
			combo2 = driver.findElement(By.cssSelector("#combo2"));
			
			Select selectCategory = new Select(combo1);
			selectCategory.selectByVisibleText("Server");
			
			Select selectLanguage = new Select(combo2);
			selectLanguage.selectByVisibleText("Java");
			
			driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
			
			String result = driver.findElement(By.cssSelector("#_valuelanguage_id")).getText();
			assertEquals(result, "23");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(description = "Works with hard but not the best way.")
	public void feelThePainExerciseHardWait() {
		
		WebElement combo1, combo2;
		combo1 = driver.findElement(By.cssSelector("#combo1"));
		combo2 = driver.findElement(By.cssSelector("#combo2"));
		
		Select selectCategory = new Select(combo1);
		selectCategory.selectByVisibleText("Server");
		
		explicitWait(); // hard wait
		
		Select selectLanguage = new Select(combo2);
		selectLanguage.selectByVisibleText("Java");
		
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		String result = driver.findElement(By.cssSelector("#_valuelanguage_id")).getText();
		assertEquals(result, "23");
		
	}
	
	@Test(description = "Works with implicitWait. Still not recommended and unreliable!")
	public void feelThePainExerciseImplicitWait() {
		
		WebElement combo1, combo2;
		combo1 = driver.findElement(By.cssSelector("#combo1"));
		combo2 = driver.findElement(By.cssSelector("#combo2"));
		
		Select selectCategory = new Select(combo1);
		selectCategory.selectByVisibleText("Server");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		Select selectLanguage = new Select(combo2);
		selectLanguage.selectByVisibleText("Java");
		
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		String result = driver.findElement(By.cssSelector("#_valuelanguage_id")).getText();
		assertEquals(result, "23");
		
	}
	
	
	@Test(description = "Works with emplicitWait. Best way to sync objects in WebDriver and reliable!")
	public void feelThePainExerciseExplicitWait() {
		
		WebElement combo1, combo2;
		Select selectCategory, selectLanguage;
		
		combo1 = driver.findElement(By.cssSelector("#combo1"));
		combo2 = driver.findElement(By.cssSelector("#combo2"));
		
		selectCategory = new Select(combo1);
		selectCategory.selectByVisibleText("Server");
				
		selectLanguage = new Select(combo2);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(
										By.cssSelector("option[value='23']")));
		selectLanguage.selectByVisibleText("Java");
		
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		String result = driver.findElement(By.cssSelector("#_valuelanguage_id")).getText();
		assertEquals(result, "23");
		
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

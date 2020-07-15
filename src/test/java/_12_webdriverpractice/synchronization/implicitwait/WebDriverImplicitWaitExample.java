package _12_webdriverpractice.synchronization.implicitwait;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverImplicitWaitExample {
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
	}
		
	@Test(description = "Works with implicitWait. Still not recommended and unreliable!")
	public void feelThePainExerciseImplicitWait() {
		
		driver.get("http://compendiumdev.co.uk/selenium/" +
													"basic_ajax.html");
		
		WebElement combo1, combo2;
		combo1 = driver.findElement(By.cssSelector("#combo1"));
		combo2 = driver.findElement(By.cssSelector("#combo2"));
		
		Select selectCategory = new Select(combo1);
		selectCategory.selectByVisibleText("Server");
		
		// implicit wait example (only!!!)
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		Select selectLanguage = new Select(combo2);
		selectLanguage.selectByVisibleText("Java");
		
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		String result = driver.findElement(By.cssSelector("#_valuelanguage_id")).getText();
		assertEquals(result, "23");
		
	}
	
	@Test(description = "These buttons are simple to work with implicit wait!")
	public void implicitlyWaitToSyncOnButtonDisplayAndClick(){
        
        driver.get("https://eviltester.github.io/"
        								+ "synchole/buttons.html");

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        final WebElement button00 = driver.findElement(By.id("easy00"));
        button00.click();

        final WebElement button01 = driver.findElement(By.id("easy01"));
        button01.click();

        final WebElement button02 = driver.findElement(By.id("easy02"));
        button02.click();

        final WebElement button03 = driver.findElement(By.id("easy03"));
        button03.click();

        assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(),
        														"All Buttons Clicked");
    }
	
	
	@Test(description = "These buttons are tough to work with implicit wait!")
	public void secondButtonsWithImplicitWait(){
        
        driver.get("https://eviltester.github.io/"
        								+ "synchole/buttons.html");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        final WebElement button00 = driver.findElement(By.id("button00"));
        button00.click();

        final WebElement button01 = driver.findElement(By.id("button01"));
        button01.click();

        final WebElement button02 = driver.findElement(By.id("button02"));
        button02.click();

        final WebElement button03 = driver.findElement(By.id("button03"));
        button03.click();

        assertEquals(driver.findElement(By.id("buttonmessage")).getText(),
        		"All Buttons Clicked");
    }
	
	@AfterMethod
	public void resetImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

package webdriverpractice.synchronization.webdriverwait;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import webdriverpractice.utils.TestUtils;

public class WebDriverWaitFeelThePainExerciseRefactored extends TestUtils {
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
	
	@Test
	public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample() {
		selectServer();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(
									By.cssSelector("#ajaxBusy"))));
		selectJavaSubmitFormAndAssertResult();
	}
	
	@Test
	public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample() {
		selectServer();
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("option[value='23']")));
		selectJavaSubmitFormAndAssertResult();
	}
	
	@Test
	public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample() {
		selectServer();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("option[value='23']")));
		selectJavaSubmitFormAndAssertResult();
	}
	
	@Test
	public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample() {
		selectServer();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("option[value='23']")));
		selectJavaSubmitFormAndAssertResult();
	}
	
	private void selectServer() {
		
		WebElement combo1 = driver.findElement(By.cssSelector("#combo1"));
		Select selectCombo1 = new Select(combo1);
		selectCombo1.selectByVisibleText("Server");
	}
	
	private void selectJavaSubmitFormAndAssertResult() {
		
		WebElement combo2 = driver.findElement(By.cssSelector("#combo2"));
		Select selectLanguage = new Select(combo2);
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

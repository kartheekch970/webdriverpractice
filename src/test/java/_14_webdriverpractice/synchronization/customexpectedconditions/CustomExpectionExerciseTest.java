package _14_webdriverpractice.synchronization.customexpectedconditions;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomExpectionExerciseTest extends TestUtils {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10, 1000);
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
	}
	
	@Test(description = "Inline Expected Conditions Exercise")
	public void synchronisationWithInlineExpectedConditionExercise() {
		
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

		selectServer();
		
		final By locateJavaBy = By.cssSelector("option[value='23']");
		
		wait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver input) {
				System.out.println("executed more than once?");
				return driver.findElement(locateJavaBy);
			}
		});
		
		assertEquals(driver.findElement(locateJavaBy).getText(), "Java");
		
		selectJavaSubmitFormAndAssertResult();
	}
	
	
	@Test(description = "Custom Expected Conditions Exercise")
	public void synchronisationWithCustomExpectedConditionExercise() {
		
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_redirect.html");
		
		String currentTitle = driver.getTitle();
		
		assertEquals(currentTitle, "Basic Redirects");
		
		driver.findElement(By.cssSelector("a#delaygotobasic")).click();
		
		wait.until(new TitleDoesNotContain(currentTitle));
		explicitWait();
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
	
	// this function executes no of times as we call it via until.
	private class TitleDoesNotContain implements ExpectedCondition<Boolean> {
	
		String titleNotToBePresent;
		
		public TitleDoesNotContain(String titleNotToBePresent) {
			this.titleNotToBePresent = titleNotToBePresent;
		}
		
		public Boolean apply(WebDriver webDriver) {	
			if (!webDriver.getTitle().equals(this.titleNotToBePresent)) {
				System.out.println(webDriver.getTitle());
				return true;
			}
			System.out.println("False has occured at least once!");
			return false;
		}
		
		@Override
		public String toString() {
			return "Title does not contain" + this.titleNotToBePresent;
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

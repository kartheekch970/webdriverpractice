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

public class InlineExpectedConditionsExampleTest extends TestUtils {
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
	
	@Test(description = "Inline Expected Conditions Example")
	public void synchronisationWithInlineExpectedCondition() {
		
		selectServer();
		
		wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver input) {
				System.out.println("executed more than once?");
				return driver.findElement(
									By.cssSelector("option[value='23']")).
										isDisplayed();
			}
		});
		
		selectJavaSubmitFormAndAssertResult();
	}
	
	@Test(description = "Custom Expected Conditions with Method Example")
	public void synchronisationWithExpectedConditionInMethod() {
		
		selectServer();
		wait.until(optionWithValueDisplayed(
							By.cssSelector("option[value='23']")));
		selectJavaSubmitFormAndAssertResult();
	}
	
	public ExpectedCondition<WebElement> optionWithValueDisplayed(final By locator) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver input) {
				System.out.println("executed more than once?");
				return driver.findElement(locator);
			}
		};
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

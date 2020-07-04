package webdriverpractice.synchronization.customexpectedconditions;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import webdriverpractice.utils.TestUtils;

public class CustomExpectionExampleTest extends TestUtils {
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
	
	@Test(description = "Custom Expected Conditions Example")
	public void synchronisationWithCustomExpectedCondition() {
		selectServer();
		wait.until(new SelectContainsText(
							By.cssSelector("#combo2"), "Java"));
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
	
	// this function executes no of times as we call it via until.
	private class SelectContainsText implements ExpectedCondition<Boolean> {
		
		By locator;
		String optionToBePresent;
		
		public SelectContainsText(By locator, String optionToBePresent) {
			this.locator = locator;
			this.optionToBePresent = optionToBePresent;
		}
		
		public Boolean apply(WebDriver webDriver) {
			
			WebElement combo = webDriver.findElement(locator);
			
			Select selectCombo = new Select(combo);
			List<WebElement> selectOptions =  selectCombo.getOptions();
			
			for (WebElement selectOption : selectOptions) {
				try {
					if (selectOption.getText().equalsIgnoreCase(optionToBePresent)) {
						System.out.println("True has occured");
						return true;
					}
				} catch (StaleElementReferenceException e) {	
				}
			}
			System.out.println("False has occured at least once!");
			return false;
		}
		
		@Override
		public String toString() {
			return "WebElement located by " + 
							this.locator + "has option " + 
								this.optionToBePresent;
		}
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

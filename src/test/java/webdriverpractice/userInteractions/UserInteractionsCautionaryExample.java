package webdriverpractice.userInteractions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserInteractionsCautionaryExample {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");
	}
	
	@Test(description = "Intentionally not using Select support class to demostrate chained findElement method." +
						"And this test will click only last option as it is using Actions." + 
						"The way WebDriver Select class click and Actions behave is not same.")
	public void multiSelectWithUserInteractions() {
		
		WebElement multiSelect = driver.findElement(
							By.cssSelector("select[name='multipleselect[]']"));
		
		List<WebElement> 
		multiSelectOptions =  multiSelect.findElements(
							By.cssSelector("option"));
		
		Actions actions = new Actions(driver);
		actions.click(multiSelectOptions.get(0))
			   .click(multiSelectOptions.get(1))
			   .click(multiSelectOptions.get(2))
			   .perform();
		
        clickSubmitButton();

        new WebDriverWait(driver,10).until(
        				ExpectedConditions.titleIs("Processed Form Details"));
        
	}
	
	
	@Test(description = "Intentionally not using Select support class to demostrate chained findElement method." +
			"And this test will click all options as we are using control press.")
	public void multiSelectWithUserInteractionsControlKey() {
	
	WebElement multiSelect = driver.findElement(
					By.cssSelector("select[name='multipleselect[]']"));
	
	List<WebElement> 
	multiSelectOptions =  multiSelect.findElements(
					By.cssSelector("option"));
	
	Actions actions = new Actions(driver);
	actions.keyDown(Keys.CONTROL).
			click(multiSelectOptions.get(0)).
			click(multiSelectOptions.get(1)).
			click(multiSelectOptions.get(2)).
			keyUp(Keys.CONTROL).
			build().
			perform();
	
	
	clickSubmitButton();
	
	new WebDriverWait(driver,10).until(
				ExpectedConditions.titleIs("Processed Form Details"));
	
	}
	
	
    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

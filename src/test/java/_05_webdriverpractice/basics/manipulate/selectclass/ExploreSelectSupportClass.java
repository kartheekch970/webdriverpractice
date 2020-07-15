package _05_webdriverpractice.basics.manipulate.selectclass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class ExploreSelectSupportClass {
	
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
		
		
	@Test
	public void submitFormUsingSelectClass() {
		
		WebElement selectMultiple;
		
		selectMultiple = driver.findElement(
				By.cssSelector("select[name='multipleselect[]']"));
		
		Select dropdownSelect = new Select(selectMultiple);
		
		assertTrue(dropdownSelect.isMultiple());
		
		List<WebElement> selectOptions = dropdownSelect.getOptions();
		for (WebElement selectOption : selectOptions) {
			assertTrue(!selectOption.getText().isEmpty(), 
							selectOption.getText());
		}
		
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

}
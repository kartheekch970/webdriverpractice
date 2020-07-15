package _04_webdriverpractice.basics.manipulate;

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

public class ManipulateExerciseMultipleSelect {
	
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
		dropdownSelect.selectByVisibleText("Selection Item 1");
		dropdownSelect.selectByVisibleText("Selection Item 2");
		dropdownSelect.selectByVisibleText("Selection Item 3");
		
		dropdownSelect.deselectByVisibleText("Selection Item 4");
		
        clickSubmitButton();

        new WebDriverWait(driver,10).until(
        				ExpectedConditions.titleIs("Processed Form Details"));

        assertOnMultipleSelectValues();
	}
	
    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }

    private void assertOnMultipleSelectValues() {
        WebElement multiSelect1Selected, 
        		   multiSelect2Selected, 
        		   multiSelect3Selected;
        
        List<WebElement> multiSelect4Selected;

        multiSelect1Selected = driver.findElement(By.cssSelector("#_valuemultipleselect0"));
        multiSelect2Selected = driver.findElement(By.cssSelector("#_valuemultipleselect1"));
        multiSelect3Selected = driver.findElement(By.cssSelector("#_valuemultipleselect2"));
        multiSelect4Selected = driver.findElements(By.cssSelector("#_valuemultipleselect3"));
        
        
        assertEquals(multiSelect1Selected.getText(), "ms1");
        assertEquals(multiSelect2Selected.getText(), "ms2");
        assertEquals(multiSelect3Selected.getText(), "ms3");
        assertTrue(multiSelect4Selected.isEmpty(), "multiSelect4Selected should be empty");
        
    } 
		
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

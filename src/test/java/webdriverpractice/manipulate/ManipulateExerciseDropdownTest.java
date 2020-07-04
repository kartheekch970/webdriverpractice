package webdriverpractice.manipulate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

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

public class ManipulateExerciseDropdownTest {
	
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
	public void submitFormWithDropDown5SelectedChainOfFindElements() {
		
		WebElement dropDownSelect, dropDownOption;
		
		dropDownSelect = driver.findElement(
								By.cssSelector("select[name='dropdown']"));
		dropDownOption = dropDownSelect.findElement(
								By.cssSelector("option[value='dd5']"));
		dropDownOption.click();
		
        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
 
        assertDropdownValueIsCorrect();
	}
	
	@Test
	public void submitFormWithDropDown5SelectedOptionFiveDirect() {
		
		WebElement dropDownOption;
		
		dropDownOption = driver.findElement(
								By.cssSelector("option[value='dd5']"));
		dropDownOption.click();
		
        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
	}
	
	@Test
	public void submitFormWithDropDownFiveUsingKeyboardFullText() {
		
		WebElement dropDownSelect;
		
		dropDownSelect = driver.findElement(
								By.cssSelector("select[name='dropdown']"));
		dropDownSelect.sendKeys("Drop Down Item 5");
		
		waitForOption5ToBeSelected();
        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
	}
	
	@Test
	public void submitFormUsingSelectClass() {
		
		WebElement dropDown;
		
		dropDown = driver.findElement(
				By.cssSelector("select[name='dropdown']"));
		
		Select dropdownSelect = new Select(dropDown);
		dropdownSelect.selectByVisibleText("Drop Down Item 5");
		
		waitForOption5ToBeSelected();
        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
	}
	
	
	private void waitForOption5ToBeSelected() {
		
		WebElement option5Item;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		option5Item = driver.findElement(
						By.cssSelector("option[value='dd5']"));
		
		wait.until(ExpectedConditions.
						elementToBeSelected(option5Item));
	}
	
    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }

    private void assertDropdownValueIsCorrect() {
        WebElement dropDownResult;

        dropDownResult = driver.findElement(By.id("_valuedropdown"));

        assertEquals(dropDownResult.getText(), "dd5");
    }
		
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

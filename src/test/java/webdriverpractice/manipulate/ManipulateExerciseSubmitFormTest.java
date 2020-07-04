package webdriverpractice.manipulate;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManipulateExerciseSubmitFormTest {
	
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
	public void submitWithButtonSubmit() {
		WebElement submitButton;
		submitButton = driver.findElement(
						 By.cssSelector("input[type='submit'][name='submitbutton']"));
		assertTrue(submitButton.isEnabled(),
						"Submit is enabled to click.");
		submitButton.submit();
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details")); 		
	}
	
	@Test
	public void submitWithButttonClick() {
		WebElement submitButton;
		submitButton = driver.findElement(
						 By.cssSelector("input[type='submit'][name='submitbutton']"));
		assertTrue(submitButton.isEnabled(),
						"Submit is enabled to click.");
		submitButton.click();
		
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details"));		
	}
	
	@Test
	public void submitWithFormSubmit() {
		WebElement submitForm;
		submitForm = driver.findElement(
						 By.cssSelector("#HTMLFormElements"));

		submitForm.submit();
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details"));		
	}
	
	@Test
		public void submitWithKeyPress() {
		WebElement submitForm;
		submitForm = driver.findElement(
						By.cssSelector("input[type='submit'][name='submitbutton']"));;

		submitForm.sendKeys(Keys.ENTER);
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details"));		
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

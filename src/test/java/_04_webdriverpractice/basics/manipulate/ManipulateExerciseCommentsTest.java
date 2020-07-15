package _04_webdriverpractice.basics.manipulate;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManipulateExerciseCommentsTest {
	
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
		WebElement commentsField;
		WebElement commentsEntered;
		String commentToEnter = "Comments entered using sendKeys method!";
		
		commentsField = driver.findElement(
						By.cssSelector("textarea[name='comments']"));
		commentsField.clear(); 
		commentsField.sendKeys(commentToEnter);
		
		submitButton = driver.findElement(
						 By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.submit();
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details")); 		
		
		commentsEntered = driver.findElement(By.cssSelector("#_valueComments"));
		String comments = commentsEntered.getText();
		
		assertEquals(comments, commentToEnter);
	}
		
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

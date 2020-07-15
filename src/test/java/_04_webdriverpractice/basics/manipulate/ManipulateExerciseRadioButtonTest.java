package _04_webdriverpractice.basics.manipulate;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
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

public class ManipulateExerciseRadioButtonTest {
	
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
	public void submitWithRadioButtonSelected() {
		
		WebElement submitButton;
		WebElement radioButton2;
		
		radioButton2 = driver.findElement(
						 By.cssSelector("input[type='radio'][value='rd2']"));
		submitButton = driver.findElement(
						 By.cssSelector("input[type='submit'][name='submitbutton']"));
		
		if (!radioButton2.isSelected()) {
			radioButton2.click();
		} 
		submitButton.submit();
		
		new WebDriverWait(driver, 10).until(
							ExpectedConditions.titleIs("Processed Form Details")); 		
	}
		
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

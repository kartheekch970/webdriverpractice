package webdriverpractice.alerts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsHandlingExerciseTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com/"+
                "styled/alerts/alert-test.html");
		
	}
	
	@Test
	public void acceptAlert() {
		
		String alertTextMessage;
		
		WebElement showAlertBox = driver.findElement(
										By.cssSelector("#alertexamples"));
		
		showAlertBox.click();
		
		alertTextMessage = driver.switchTo().alert().getText();
		assertEquals(alertTextMessage, "I am an alert box!");
		
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void dismissAlert() {
		
		String alertTextMessage;
		
		WebElement showAlertBox = driver.findElement(
										By.cssSelector("#alertexamples"));
		
		showAlertBox.click();
		
		alertTextMessage = driver.switchTo().alert().getText();
		assertEquals(alertTextMessage, "I am an alert box!");
		
		driver.switchTo().alert().dismiss();
	}
	
	@Test
	public void acceptConfirmBox() {
		
		String confirmTextMessage;
		
		WebElement confirmAlertBox = driver.findElement(
										By.cssSelector("#confirmexample"));
		
		confirmAlertBox.click();
		
		confirmTextMessage = driver.switchTo().alert().getText();
		assertEquals(confirmTextMessage, "I am a confirm alert");
		
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void dismissConfirmBox() {
		
		String confirmTextMessage;
		
		WebElement confirmAlertBox = driver.findElement(
										By.cssSelector("#confirmexample"));
		
		confirmAlertBox.click();
		
		confirmTextMessage = driver.switchTo().alert().getText();
		assertEquals(confirmTextMessage, "I am a confirm alert");
		
		driver.switchTo().alert().dismiss();
	}
	
	@Test
	public void acceptPromptAlert() {
		
		String promptTextMessage;
		
		WebElement promptAlertBox = driver.findElement(
										By.cssSelector("#promptexample"));
		promptAlertBox.click();
		
		driver.switchTo().alert().sendKeys("");
		driver.switchTo().alert().sendKeys("Sample Prompt Entered!");
		
		promptTextMessage = driver.switchTo().alert().getText();
		assertEquals(promptTextMessage, "I prompt you");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void dismissPromptAlert() {
		
		String promptTextMessage;
		
		WebElement promptAlertBox = driver.findElement(
										By.cssSelector("#promptexample"));
		promptAlertBox.click();
		
		driver.switchTo().alert().sendKeys("");
		driver.switchTo().alert().sendKeys("Sample Prompt Entered!");
		
		promptTextMessage = driver.switchTo().alert().getText();
		assertEquals(promptTextMessage, "I prompt you");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().dismiss();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
		
}

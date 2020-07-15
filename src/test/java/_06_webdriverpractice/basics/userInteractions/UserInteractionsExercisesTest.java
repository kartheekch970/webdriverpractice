package _06_webdriverpractice.basics.userInteractions;

import static org.testng.Assert.assertEquals;

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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserInteractionsExercisesTest {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("http://compendiumdev.co.uk/selenium/" +
                "gui_user_interactions.html");
	}
	
    // user interactions can be intermittent
    // so click on the html to force focus to the page
	@BeforeMethod
	public void resetAndFocusPage() {
		driver.navigate().refresh();
		driver.findElement(By.tagName("html")).click();
	}
	
	
	@Test(description = "Move exercise")
	public void moveDraggable1ToDroppable1() {
		
		WebElement draggable1, droppable1;
		
		draggable1 = driver.findElement(By.cssSelector("#draggable1"));
		droppable1 = driver.findElement(By.cssSelector("#droppable1"));
		
		Actions actions = new Actions(driver);
		
		actions.clickAndHold(draggable1)
			   .moveToElement(droppable1)
			   .release()
			   .perform();
		
		assertEquals(droppable1.getText(), "Dropped!");
	}
	
	
	@Test(description = "DragAndDrop exercise")
	public void dragAndDropDraggable2ToDroppable1() {
		
		WebElement draggable2, droppable1;
		
		draggable2 = driver.findElement(By.cssSelector("#draggable2"));
		droppable1 = driver.findElement(By.cssSelector("#droppable1"));
		
		Actions actions = new Actions(driver);
		
		actions.dragAndDrop(draggable2, droppable1)
			   .release()
			   .perform();
		
		assertEquals(droppable1.getText(), "Get Off Me!");
	}
	
	
	@Test(description = "control+B exercise")
	public void ControlB() {
		
		WebElement draggable1;
		
		draggable1 = driver.findElement(By.cssSelector("#draggable1"));
		
		Actions actions = new Actions(driver);
		
		actions.keyDown(Keys.CONTROL)
			   .sendKeys("b")
			   .keyUp(Keys.CONTROL)
			   .perform();
		
		assertEquals(draggable1.getText(), "Bwa! Ha! Ha!");
	}
	
	
	@Test(description = "controlSpace exercise - This test won't work!")
	public void ControlSpace() {
		
		WebElement droppable1, droppable2;
		
		droppable1 = driver.findElement(By.cssSelector("#droppable1"));
		droppable2 = driver.findElement(By.cssSelector("#droppable2"));
		
		Actions actions = new Actions(driver);
		
		actions.keyDown(Keys.CONTROL)
			   .sendKeys(Keys.SPACE)
			   .build()
			   .perform();
		
		assertEquals(droppable1.getText(), "Let GO!!");
		assertEquals(droppable2.getText(), "Let GO!!");
		
		actions.release()
			   .perform();
	}
	
	@Test(description = "draw something on canvas exercise")
	public void drawOnCanvas() {
		
		WebElement canvas, droppable2;
		
		canvas = driver.findElement(By.cssSelector("#canvas"));
				
		Actions actions = new Actions(driver);
		
		actions.clickAndHold()
			   .moveByOffset(10, 10)
			   .release()
			   .perform();

	}
		
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

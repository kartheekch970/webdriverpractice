package webdriverpractice.iframes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IframesExercisesTest {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.manage().window().maximize();
		driver.get("https://testpages.herokuapp.com/" +
				"styled/iframes-test.html");	
	}
	
	@Test
	public void switchToiFrameByIndex() {
		
		WebElement frameElement;
		
		driver.switchTo().frame(1); // index starts with 0
		
		frameElement = driver.findElement(By.cssSelector("h1"));
		assertEquals(frameElement.getText(), "Nested Page Example");
	}
	
	
	@Test
	public void switchToiFrameById() {
		
		WebElement iFrameElement;
		
		driver.switchTo().frame("thedynamichtml");
		
		iFrameElement = driver.findElement(By.cssSelector("#iframe0"));
		assertEquals(iFrameElement.getText(), "iFrame List Item 0");
	}
	
	@Test
	public void switchToFrameByWebElement() {
		
		WebElement iFrame;
		WebElement iFrameElement;
		
		
		iFrame = driver.findElement(By.cssSelector("iframe#theheaderhtml"));
		driver.switchTo().frame(iFrame);
		
		iFrameElement = driver.findElement(By.cssSelector("h1"));
		assertEquals(iFrameElement.getText(), "Nested Page Example");
	}
	
	
	@Test
	public void switchToParentiFrameAndDefaultiFrame() {
		
		WebElement iFrameElement;
		
		driver.switchTo().frame("thedynamichtml");
		
		iFrameElement = driver.findElement(By.cssSelector("#iframe0"));
		assertEquals(iFrameElement.getText(), "iFrame List Item 0");
		
		driver.switchTo().parentFrame();
//		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector(".page-footer"));		
	}
	
	@Test
    public void whatDoesTitleShowWhenSwitchedToFrame(){
        // main page
        System.out.println("On Main Page Title Is:");
        System.out.println(driver.getTitle());

        // left frame
        System.out.println("In First iFrame Title Is:");
        driver.switchTo().frame(0);
        System.out.println(driver.getTitle());

        // title is the title of the page, not the frame

    }
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
}

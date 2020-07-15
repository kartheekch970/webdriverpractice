package _08_webdriverpractice.basics.frames;

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

public class FramesWhatIfTest {
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
				"styled/frames/frames-test.html");	
	}
	
	@Test
	public void whatIfWeDoNotSwitchToFrame() {
		
		WebElement frameElement = null;
		
//		driver.switchTo().frame("left");
		
		try {
			frameElement = driver.findElement(By.cssSelector("#left0"));
			
		} catch (NoSuchElementException e) {
				e.printStackTrace();
		}
		assertEquals(frameElement.getText(), "Left List Item 0");
	
	}
	
	
	@Test
	public void whatIfSwitchToParentFrame() {
		
		WebElement frameElement;
		WebElement footerFrameElement;
		
		driver.switchTo().frame("left");
		frameElement = driver.findElement(By.cssSelector("#left0"));
		assertEquals(frameElement.getText(), "Left List Item 0");
		
		System.out.println("Can find and access footer because I am on the main page");
		driver.switchTo().parentFrame();
		footerFrameElement = driver.findElement(By.cssSelector("frame[name='footer']"));
		assertTrue(footerFrameElement.getAttribute("src").contains("frames-footer.html"));
		
		driver.switchTo().frame("left");
		frameElement = driver.findElement(By.cssSelector("#left1"));
		assertEquals(frameElement.getText(), "Left List Item 1");
		
		System.out.println("Can find and access footer because I am on the main page");
		driver.switchTo().defaultContent();
		footerFrameElement = driver.findElement(By.cssSelector("frame[name='footer']"));
		assertTrue(footerFrameElement.getAttribute("src").contains("frames-footer.html"));
			
	}
	
	@Test
	public void whatDoesTitleGetWhenSwitchToFrame() {
		
		driver.switchTo().frame("left");
		System.out.println(driver.getTitle());
		
		driver.switchTo().defaultContent();
		System.out.println(driver.getTitle());
		
		// title is the title of the page, not the frame
	}
	
	
	
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
}

package webdriverpractice.interrogation.findby;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindByInterrogationExercise {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setupDriverByManager() {
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/find_by_playground.php");
	}
	
	@Test
	public void findById() {
		WebElement elemById = driver.findElement(By.id("p1"));
		
		assertEquals(elemById.getText(), "This is a paragraph text");
	}
	
	@Test
	public void findByName() {
		WebElement elemByName = driver.findElement(By.name("pName2"));
		
		assertEquals(elemByName.getText(), "This is b paragraph text");
	}
	
	@Test
	public void findByClassName() {
		WebElement elemByName = driver.findElement(By.className("normal"));
		
		assertEquals(elemByName.getClass(), "this has multiple values");
	}
	
	@Test
    public void findByLinkText(){
        WebElement jumpToPara0 = driver.findElement(
                                            By.linkText("jump to para 0"));

        assertEquals("a26", jumpToPara0.getAttribute("id"));
    }
	
	@Test
    public void findByPartialLinkText(){
        WebElement jumpToPara0 = driver.findElement(
                                            By.partialLinkText("jump to para 0"));
        assertEquals("jump to para 0", jumpToPara0.getText());
        
        jumpToPara0 = null;
        jumpToPara0 = driver.findElement(By.partialLinkText("0"));
        
        assertEquals("jump to para 0", jumpToPara0.getText().endsWith("0"));
        
    }
	
	@Test
	public void noSuchElementException_thrownWhenLocatorWrong() {
		try {
			driver.findElement(By.id("sfg"));
			fail("Element does not exist!!  NoSuchElementException thrown.");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void noSuchElementException_thrownWhenLocatorWrongExcepted() {
		driver.findElement(By.id("sfg"));
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}

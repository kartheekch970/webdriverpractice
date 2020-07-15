package _11_webdriverpractice.basics.managingwindows;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _00_webdriverpractice.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ManageWindowsExampleTest extends TestUtils {
	private static WebDriver driver;
	
	@BeforeClass
	public void setUpDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setupBrowser() {
		driver.get("https://testpages.herokuapp.com/"
								+ "styled/css-media-queries.html");	
	}
	
	@Test
	public void canManageWindows() {
		
		Dimension size = driver.manage().window().getSize();
		Point point = driver.manage().window().getPosition();
		
		explicitWait();
		driver.manage().window().setSize(
						new Dimension(size.height/2, size.width/2));
		
		explicitWait();
		driver.manage().window().maximize();
		
		explicitWait();
		driver.manage().window().setPosition(
						new Point(size.width/4, size.height/4));
		
		explicitWait();
		driver.manage().window().fullscreen();
		
		driver.manage().window().setSize(size);
		driver.manage().window().setPosition(point);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

package _19_webdriverpractice.takesscreenshot;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotsExerciseTest1 {
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
		driver.manage().window().maximize();
	}
	
	@Test
	public void getScreenshotAsFileWithChromeDriver() {
		
		try {
			TakesScreenshot snapper = (TakesScreenshot) driver;
			final File fileScreenshot = snapper.getScreenshotAs(OutputType.FILE);
			
			File myScreenshotsDir = new File(System.getProperty("user.dir"), "screenshots");
			myScreenshotsDir.mkdirs();
			
			System.out.println("Screenshot location:" + fileScreenshot.getAbsolutePath());
			
		} catch (ClassCastException e) {
			// if we cannot cast it to TakesScreenshot we will get a ClassCastException
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

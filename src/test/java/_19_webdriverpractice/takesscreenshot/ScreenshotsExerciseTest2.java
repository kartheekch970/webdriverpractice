package _19_webdriverpractice.takesscreenshot;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenshotsExerciseTest2 {
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
	public void getScreenshotAsFile() throws IOException {
		
		TakesScreenshot snapper = (TakesScreenshot) driver;
		final File fileScreenshot = snapper.getScreenshotAs(OutputType.FILE);
		
		System.out.println(fileScreenshot.getAbsolutePath());
		System.out.println(fileScreenshot.getName());
		
		File myScreenshotsDir = createADirForScreenshots();
		
		FileUtils.copyFile(fileScreenshot, new File(myScreenshotsDir, fileScreenshot.getName()));
		
		File gotoPageScreen = new File(myScreenshotsDir, "gotoPageScreen.png");
		if (gotoPageScreen.exists()) {
			FileUtils.deleteQuietly(gotoPageScreen);
		}
		FileUtils.moveFile(fileScreenshot, gotoPageScreen);
		
	}
	
	@Test
	public void getScreenshotAsByteArray() throws IOException {
		
		TakesScreenshot snapper = (TakesScreenshot) driver;
		final byte[] byteArrayScreenshot = snapper.getScreenshotAs(OutputType.BYTES);
		
	
		File myScreenshotsDir = createADirForScreenshots();
		
		File byteImage = new File(myScreenshotsDir, "byteimage.png");
		
		@SuppressWarnings("resource")
		FileOutputStream fos = new FileOutputStream(byteImage);
		fos.write(byteArrayScreenshot);
		fos.flush();
		
		assertTrue(byteArrayScreenshot.length > 0);
		
	}
	
	@Test
	public void getScreenshotAsBase64() throws IOException {
		
		TakesScreenshot snapper = (TakesScreenshot) driver;
		final String base64ImageSnapper = snapper.getScreenshotAs(OutputType.BASE64);
		
	
		File myScreenshotsDir = createADirForScreenshots();
				
		byte[] imgBytes = Base64.getDecoder().decode(base64ImageSnapper);
		File base64Image = new File(myScreenshotsDir, "base64image.png");
		
		@SuppressWarnings("resource")
		FileOutputStream fos = new FileOutputStream(base64Image);
		fos.write(imgBytes);
		fos.flush();
			
	}
	
	
	private File createADirForScreenshots() {
		String currentTime = new SimpleDateFormat("yyyyddmmss").format(new Date());
		
		File myScreenshotsDir = new File(System.getProperty("user.dir"), "screenshots");
		myScreenshotsDir.mkdirs();
		
		File currentExecDir = new File(myScreenshotsDir, currentTime);
		currentExecDir.mkdirs();
		
		return currentExecDir;
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}

package _01_webdriverpractice.basics.navigation;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigationExcercise {
	
	private static WebDriver driver;
	final public String PROTOCOL = "HTTP";
	final public String DOMAIN = "www.compendiumdev.co.uk";
	final public String ROOT_URL = PROTOCOL + "://" + DOMAIN;
	
	@BeforeClass
	public static void setupManager() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod()
	public static void setupDriver() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@Test(priority = -1)
	public void navigateWithGet() {
		try {
			System.out.println("URL is " + ROOT_URL);
			driver.get(ROOT_URL + "/selenium");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
	}
	
	@Test(priority = 0)
	public void navigateWithNavigateTo() {
		
		driver.navigate().to(ROOT_URL + "/selenium/search.php"); 
		assertTrue(driver.getTitle().equals("Selenium Simplified Search Engine"));
		
	}
	
	@Test(priority = 1)
	public void navigateWithNavigateToURL() {
		
		final URL serachPageURL;
		
		try {
			serachPageURL = new URL(PROTOCOL, DOMAIN, "/selenium/search.php");
			
			driver.navigate().to(serachPageURL);
			assertTrue(driver.getTitle().equals("Selenium Simplified Search Engine"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void navigateWithForwardAndBack() {
		
		driver.navigate().to(ROOT_URL + "/selenium/basic_html_form.html"); 
		assertTrue(driver.getTitle().equals("HTML Form Elements"));
		
		driver.navigate().to(ROOT_URL + "/selenium/basic_web_page.html"); 
		assertTrue(driver.getTitle().equals("Basic Web Page Title"));
		
		driver.navigate().back();
		assertTrue(driver.getTitle().equals("HTML Form Elements"));
		
		driver.navigate().forward();
		assertTrue(driver.getTitle().equals("Basic Web Page Title"));
	}
	
	
	@Test(priority = 3)
	public void refreshPage() {
		
		final String refreshPageConstant = "Refreshed Page on";
		
		driver.navigate().to(ROOT_URL + "/selenium/refresh.php"); 
		assertTrue(driver.getTitle().startsWith(refreshPageConstant));
		
		long startTime = Long.parseLong(
				driver.getTitle().replaceFirst(refreshPageConstant, ""));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.navigate().refresh();
		assertTrue(driver.getTitle().startsWith(refreshPageConstant));
		
		long endTime = Long.parseLong(
				driver.getTitle().replaceFirst(refreshPageConstant, ""));
		
		assertTrue(endTime > startTime, "Expected that end time is grater than start time!!");
	}
	
	
	@AfterMethod
	public static void teardown() {
		driver.close();
	}
	
}

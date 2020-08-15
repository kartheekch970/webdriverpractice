package _17_webdriverpractice.drivers;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MicrosoftEdgeDriverTest {
	

	@Test(description = "Not working due to version mismatch!!")
	public void usingWebDriverManager() {
		
		WebDriverManager.edgedriver().setup();
		
		WebDriver driver = new EdgeDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		
		assertEquals(driver.getTitle(), "HTML Form Elements");
		
		driver.quit();
	}	
}


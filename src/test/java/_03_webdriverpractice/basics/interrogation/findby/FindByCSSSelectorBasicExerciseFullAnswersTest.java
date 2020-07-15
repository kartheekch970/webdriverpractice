package _03_webdriverpractice.basics.interrogation.findby;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindByCSSSelectorBasicExerciseFullAnswersTest {
	
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
	public void findByUsingCSSDataDriven() {
		
		class TestData{
	        public String css;
	        public String name;
	        public String value;
	        public String alt;

	        public TestData(String css, String name,
	                        String value, String alternativeTo) {
	            this.css = css;
	            this.name = name;
	            this.value = value;
	            this.alt = alternativeTo;
	        }
		}
		
		List<TestData> cssItems = Arrays.asList(
				new TestData("#p31", "name", "pName31", "By.id(\"p31\")"),
	            new TestData("*[id='p31']", "name", "pName31", "By.id(\"p31\")"),
	            new TestData("[id='p31']", "name", "pName31", "By.id(\"p31\")"),
	            new TestData("[id=\"p31\"]", "name", "pName31", "By.id(\"p31\")"),
	            new TestData("[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("*[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("*[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("ul[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("ul[name='ulName1']", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("[name=\"ulName1\"]", "id", "ul1", "By.name(\"ulName1\")"),
	            new TestData("div.specialDiv", "id", "div1","By.className(\"specialDiv\")"),
	            new TestData(".specialDiv", "id", "div1","By.className(\"specialDiv\")"),
	            new TestData("*.specialDiv", "id", "div1","By.className(\"specialDiv\")"),
	            new TestData("li", "name", "liName1", "By.tagName(\"li\")")
				);
		
		WebElement element;
		
		for (TestData cssItem : cssItems) {
            element = driver.findElement(By.cssSelector(cssItem.css));

            System.out.println(
                    String.format(
                            "Instead of %s use By.cssSelector(\"%s\")",
                            cssItem.alt, cssItem.css.replaceAll("\"", "\\\\\"")));
		}
		
	}
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}

package _22_webdriverpractice.pageobjects.loadablecomponent.pages;

import static driverfactory.DriverFactory.getChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class BasicAjaxPageObject extends LoadableComponent<BasicAjaxPageObject> {

	private WebDriver driver = getChromeDriver();
	private BasicAjaxActController act;
	
	
	@Override
	protected void load() {
		driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");		
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			driver.findElement(By.id("combo1"));
		} catch (NoSuchElementException e) {
			throw new Error("basic_ajax page not loaded");
		}	
	}
	
    public BasicAjaxActController act() {
		return act;
	}
    
    private BasicAjaxPageObject(BasicAjaxActController act) {
		this.act = act;
	}
    
    private BasicAjaxPageObject() {
		// hide it
	}
    
	public static BasicAjaxPageObject getBasicAjaxPage() {
    	return new BasicAjaxPageObject(new BasicAjaxActController());
    }


	
}

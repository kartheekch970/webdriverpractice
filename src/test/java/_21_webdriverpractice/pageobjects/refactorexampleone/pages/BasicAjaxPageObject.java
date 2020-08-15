package _21_webdriverpractice.pageobjects.refactorexampleone.pages;

import static driverfactory.DriverFactory.getChromeDriver;

import org.openqa.selenium.WebDriver;

public class BasicAjaxPageObject {

	private WebDriver driver = getChromeDriver();
	private BasicAjaxActController act;
	
	// get the ajax page
	public void get() {
		driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");	
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

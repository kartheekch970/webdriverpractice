package _21_webdriverpractice.pageobjects.refactorexampleone.pages;

import static driverfactory.DriverFactory.getChromeDriver;
import static driverfactory.DriverFactory.getWebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxActController {
    
	private WebDriver driver = getChromeDriver();
	private WebDriverWait wait = getWebDriverWait();
	
    public enum Category {
		WEB(1), 
		DESKTOP(2), 
		SERVER(3);

        private int dropDownValue;

        Category(int value) {
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
	}
	
    public enum Language {
        JAVASCRIPT(0), VBSCRIPT(1), FLASH(2),
        COBOL(20), FORTRAN(21), SERVER_Cpp(22), JAVA(23),
        DESKTOP_Cpp(10), ASSEMBLER(11), C(12), VISUAL_BASIC(13);

        private int dropDownValue;

        Language(int value){
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
    }
    
	// Select Category
	public BasicAjaxActController seletCategory(Category category) {
		
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(
        			By.cssSelector("option[value='" + category.value() + "']")).click();
        
        wait.until(ajaxActionIsComplete());
        
        return this;
        
	}
	
    public BasicAjaxActController selectLanguage(Language languageValue) {
        
    	WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(
        			By.cssSelector("option[value='" + languageValue.value() + "']")).click();
        
        return this;
    }

	private ExpectedCondition<Boolean> ajaxActionIsComplete() {
		return ExpectedConditions.
					invisibilityOfElementLocated(
										By.id("ajaxBusy"));
	}

    public BasicAjaxActController clickCodeInIt() {
        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();
        return this;
    }
	
}

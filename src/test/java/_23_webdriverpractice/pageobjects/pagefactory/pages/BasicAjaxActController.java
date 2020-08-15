package _23_webdriverpractice.pageobjects.pagefactory.pages;

import static driverfactory.DriverFactory.getWebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxActController {
    
	private WebDriverWait wait = getWebDriverWait();
	
	//page factory elements
    @FindBy(how= How.ID, using="combo1")
    private WebElement categorySelect;

    @FindBy(how= How.ID, using="combo2")
    private WebElement languageSelect;

    @FindBy(how= How.NAME, using="submitbutton")
    private WebElement codeInIt;
	
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
        categorySelect.findElement(
        			By.cssSelector("option[value='" + category.value() + "']")).click();
        wait.until(ajaxActionIsComplete());    
        return this;
        
	}
	
	// Select Language
    public BasicAjaxActController selectLanguage(Language languageValue) {
        languageSelect.findElement(
        			By.cssSelector("option[value='" + languageValue.value() + "']")).click();
        return this;
    }
    
	private ExpectedCondition<Boolean> ajaxActionIsComplete() {
		return ExpectedConditions.
					invisibilityOfElementLocated(
										By.id("ajaxBusy"));
	}
	
	public boolean isSelectCategoryDisplayed() {
		return categorySelect.isDisplayed();
	}

    public BasicAjaxActController clickCodeInIt() {
        codeInIt.click();
        return this;
    }
	
}

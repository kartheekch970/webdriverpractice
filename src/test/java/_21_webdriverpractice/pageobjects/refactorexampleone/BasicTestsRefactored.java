package _21_webdriverpractice.pageobjects.refactorexampleone;

import static _21_webdriverpractice.pageobjects.refactorexampleone.pages.BasicAjaxPageObject.getBasicAjaxPage;
import static _21_webdriverpractice.pageobjects.refactorexampleone.pages.ProcessedFromPage.getProcessedFromPage;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _21_webdriverpractice.pageobjects.refactorexampleone.pages.BasicAjaxActController.Category;
import _21_webdriverpractice.pageobjects.refactorexampleone.pages.BasicAjaxActController.Language;
import _21_webdriverpractice.pageobjects.refactorexampleone.pages.BasicAjaxPageObject;
import _21_webdriverpractice.pageobjects.refactorexampleone.pages.ProcessedFromPage;

public class BasicTestsRefactored extends BaseTestClass {
	
	BasicAjaxPageObject basicAjaxPage = getBasicAjaxPage();
	ProcessedFromPage processedFromPage = getProcessedFromPage();

	@BeforeMethod
	public void getPage() {
		basicAjaxPage.get();
	}

	@Test
	public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample() {
		
		basicAjaxPage.act()
					 .seletCategory(Category.SERVER)
					 .selectLanguage(Language.JAVA)
					 .clickCodeInIt();
		
		String languageID = processedFromPage.waitUntilPageIsLoaded()
						 					 .getValueFor("language_id");
		
		assertEquals(languageID, String.valueOf(Language.JAVA.value()), "Expected Language Code for Java");
	}
	
	@Test
	public void chooseToCodeInCppOnDesktop() {
		
		basicAjaxPage.act()
					 .seletCategory(Category.DESKTOP)
					 .selectLanguage(Language.DESKTOP_Cpp)
					 .clickCodeInIt();
		
		String languageID = processedFromPage.waitUntilPageIsLoaded()
				 							 .getValueFor("language_id");
		
		assertEquals(languageID, String.valueOf(Language.DESKTOP_Cpp.value()), "Expected Language Code for Deskop_Cpp");	
	}
	
}

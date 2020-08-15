package _23_webdriverpractice.pageobjects.pagefactory;

import static _22_webdriverpractice.pageobjects.loadablecomponent.pages.BasicAjaxPageObject.getBasicAjaxPage;
import static _22_webdriverpractice.pageobjects.loadablecomponent.pages.ProcessedFromPage.getProcessedFromPage;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _22_webdriverpractice.pageobjects.loadablecomponent.pages.BasicAjaxActController.Category;
import _22_webdriverpractice.pageobjects.loadablecomponent.pages.BasicAjaxActController.Language;
import _22_webdriverpractice.pageobjects.loadablecomponent.pages.BasicAjaxPageObject;
import _22_webdriverpractice.pageobjects.loadablecomponent.pages.ProcessedFromPage;

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
		
		String languageID;
		languageID = processedFromPage.waitUntilPageIsLoaded()
						 			  .getValueFor("language_id");
		
		assertEquals(languageID, String.valueOf(Language.JAVA.value()),
													"Expected Language Code for Java");
	}
	
	@Test
	public void chooseToCodeInCppOnDesktop() {
		
		basicAjaxPage.act()
					 .seletCategory(Category.DESKTOP)
					 .selectLanguage(Language.DESKTOP_Cpp)
					 .clickCodeInIt();
		
		String languageID;
		languageID = processedFromPage.waitUntilPageIsLoaded()
						 			  .getValueFor("language_id");
		
		assertEquals(languageID, String.valueOf(Language.DESKTOP_Cpp.value()),
													"Expected Language Code for Deskop_Cpp");	
	}
	
}

package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory(){
        // prevent instantiation of driver
    }

    public static WebDriver getChromeDriver(){

        if(driver == null){
        	WebDriverManager.chromedriver().setup();
        	
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("--start-maximized", "--incognito");
        	
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait(){
        if(wait == null){
            wait = new WebDriverWait(getChromeDriver(), 10);
        }
        return wait;
    }
}

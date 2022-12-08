package utils;

import io.github.bonigarcia.wdm.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void closeBrowser(){
        driver.quit();
    }

    //we use this method instead of send keys method throughout the framework
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }

    //to get webdriver wait
    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    //this method will wait for the element to be clickable
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //this method will perform click operation but before perform click, it will wait
    //for the element to be clickable
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    //this method will return JavascriptExecutor Object

    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    //this function will perform click on element using javascript executor
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    //selecting the dropdown using text
    public static void selectDropdown(WebElement element, String text){
        Select s = new Select(element);
        s.selectByVisibleText(text);
    }

}
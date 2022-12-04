package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {
    // WebDriver driver;

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
        // WebDriverManager.chromedriver().setup();
        // driver=new ChromeDriver();
        // driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        //  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //  driver.manage().window().maximize();
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        usernameField.sendKeys(ConfigReader.getPropertyValue("username"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        WebElement welcomeMessage = driver.findElement(By.id("welcome"));
        if (welcomeMessage.isDisplayed()) {
            System.out.println("Test case is passed");
        } else {
            System.out.println("Test is failed");
        }
    }

}


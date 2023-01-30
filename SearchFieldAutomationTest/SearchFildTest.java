package SearchFieldAutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchFildTest {
    public static final String PASSWORD = "metodi86";
    public static final String USERNAME = "m_puh";
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testSearchField() {

        // LogIn the profile page, so we can find Search field
        driver.get("http://training.skillo-bg.com:4200/posts/all");
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/users/login"));

        WebElement signInElement = driver.findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(USERNAME);
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(PASSWORD);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        // finding the Search field
        WebElement searchField = driver.findElement(By.id("search-bar"));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys("TestUserUserUserUser");
        WebElement searchIcon = driver.findElement(By.cssSelector("#navbarColor01 > form > i"));
        searchIcon.click();
        searchField.click();
        searchIcon.click();
        searchField.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement dropDownUser = driver.findElement(By.xpath("//*[text()='TestUserUserUserUser']"));
        dropDownUser.click();
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/users/31"));

        driver.close();
    }
}

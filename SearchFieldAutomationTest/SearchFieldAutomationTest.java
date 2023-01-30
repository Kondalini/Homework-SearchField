package org.Tedi.SearchFieldAutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class SearchFieldAutomationTest {



    @Test
    public void testSearchField() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));


        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));


        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("teddy");

        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123456");

        WebElement signButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signButton));
        signButton.click();

        WebElement homeLink = driver.findElement(By.id("nav-link-home"));
        wait.until(ExpectedConditions.visibilityOf(homeLink));

        //Search field on Home page

        WebElement searchField = driver.findElement(By.id("search-bar"));
        searchField.sendKeys("MARIELKATA");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement userMarielkata = driver.findElement(By.partialLinkText("MARIELKATA"));

        wait.until(ExpectedConditions.visibilityOf(userMarielkata));
        userMarielkata.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/32"));

        //Search field on Profile page

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        profileLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/3927"));

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "teddy"));
        Assert.assertTrue(isTextDisplayed, "The username is not displayed");

        WebElement searchFieldOnProfilePage = driver.findElement(By.id("search-bar"));
        searchField.sendKeys("MARIELKATA");

        WebElement userMarielkata1 = driver.findElement(By.partialLinkText("MARIELKATA"));

        wait.until(ExpectedConditions.visibilityOf(userMarielkata1));
        userMarielkata1.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/32"));


        //Search field on New post page

        WebElement newPostLink = driver.findElement(By.id("nav-link-new-post"));
        newPostLink.click();

        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/create"));
        Boolean isPostTitleDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h3"), "Post a picture to share with your awesome followers"));
        Assert.assertTrue(isPostTitleDisplayed, "The New Post page is not displayed");

        WebElement searchFieldOnNewPostPage = driver.findElement(By.id("search-bar"));
        searchField.sendKeys("MARIELKATA");

        WebElement userMarielkata2 = driver.findElement(By.partialLinkText("MARIELKATA"));

        wait.until(ExpectedConditions.visibilityOf(userMarielkata2));
        userMarielkata2.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/32"));

        driver.close();


    }



}

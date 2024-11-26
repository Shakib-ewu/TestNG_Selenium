package day1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseClass.main;

import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login extends main {
    WebDriver driver;
    main base;
    
    // Method to initialize the browser and navigate to the login page
    @BeforeMethod
    public void setup() {
    	base = new main();
    	driver = base.initializeBrowserAndOpenURL(); 
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']"))).click();
    	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();
    }
    

	// Method to quit the browser after each test
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Ensure the driver is closed after the test
        }
    }
    
    // Method to generate a random email for testing
    public String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 5) + "@sparkroi.com";
    }
    
    // Test case to verify successful login with valid credentials
    @Test(priority = 1 ,groups= {"sanity"})
    public void verifyLogin() {
        driver.findElement(By.id("input-email")).sendKeys("vesot82226@sparkroi.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        
        // Assert that account info page is displayed after successful login
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }
    
    // Test case to verify login with invalid email and password
    @Test(priority = 2, groups= {"sanity"})
    public void verifyLoginInvalidCreds() {
        String randomEmail = generateRandomEmail();
        System.out.println("Generated Random Email: " + randomEmail);

        driver.findElement(By.id("input-email")).sendKeys(randomEmail);
        driver.findElement(By.id("input-password")).sendKeys("154345");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        
        // Assert that the warning message is displayed for invalid credentials
        String actWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actWarningMessage.contains(expectedMessage), "Expected Warning message");
    }
    
    // Test case to verify login with valid email but invalid password
    @Test(priority = 3,groups= {"sanity"})
    public void verifyLoginInvalidPass() {
        String randomEmail = generateRandomEmail();
        System.out.println("Generated Random Email: " + randomEmail);

        driver.findElement(By.id("input-email")).sendKeys(randomEmail);
        driver.findElement(By.id("input-password")).sendKeys("1543343445");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        
        // Assert that the warning message is displayed for invalid password
        String actWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actWarningMessage.contains(expectedMessage), "Expected Warning message");
    }
}

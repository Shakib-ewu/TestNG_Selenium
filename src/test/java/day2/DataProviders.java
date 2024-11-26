package day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.main;

public class DataProviders extends main {
	WebDriver driver;
    main base;

	@BeforeClass
	void setup() {
		base = new main();
    	driver = base.initializeBrowserAndOpenURL(); 
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']"))).click();
    	    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();
	}
	
	@Test (dataProvider="ss")
	void testLogin(String email,String pwd) throws InterruptedException {
		
		driver.findElement(By.id("input-email")).clear();
	    driver.findElement(By.id("input-password")).clear();
		
		 driver.findElement(By.id("input-email")).sendKeys(email);
	        driver.findElement(By.id("input-password")).sendKeys(pwd);
	        driver.findElement(By.xpath("//input[@value='Login']")).click();
	        Thread.sleep(2000);
	        
	        // Assert that account info page is displayed after successful login
	        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
	}
	
	@AfterClass
	void tearDown() {
		if (driver != null) {
            driver.quit();  // Ensure the driver is closed after the test
        }
	}
	
	@DataProvider (name="ss", indices= {0,1,2})
	Object[][]loginData()
	{
		Object data[][]= {
				{"abc@gmail.com", "ValidPass123!"},        // Valid credentials
			        {"sakib@gmail", "ValidPass123!"},         // Invalid email
			        {"noyon@gmail.com", "WrongPass123"},        // Invalid password
			        {"lxp@gmail.com", "ValidPass123!"},  // Email not registered
			        {"abn@gmail.com", "ValidPass123!"}, 
					
		};
		return data;
	}
	

}

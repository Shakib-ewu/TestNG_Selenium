package day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OrangeHRM {
	
	public class DataProviders  {
		WebDriver driver;
	   

		@BeforeClass
		@Parameters({"browser"})
		void setup() throws InterruptedException {

         
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}
		
		@Test (priority=1)
		void testLogo() {
			
			boolean status=driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
			Assert.assertEquals(status, true);
			
		}
		
		@Test (priority=2)
		void testTitle() {
			
			Assert.assertEquals(driver.getTitle(), "OrangehRM");
			
			
		}
		
		@Test (priority=3, dependsOnMethods= {"testTitle"})
		void testURL() {
			
			Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			
		}
		
		@AfterClass
		void tearDown() {
			if (driver != null) {
	            driver.quit();  // Ensure the driver is closed after the test
	        }
		}
		}
	}
	



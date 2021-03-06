package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.crypto.prng.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_02_Exercise {
	WebDriver driver;

	@Test(priority = 1,alwaysRun = true)
	public void TC01_VerifyUrlAndTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homePageTitle);
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[text() = 'My Account']")).click();
		driver.findElement(By.xpath("//*[@id='login-form']//a[@title = 'Create an Account']")).click();
		driver.navigate().back();
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		String createAccountPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAccountPageUrl, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test(testName = "Test Script 02: Login empty",alwaysRun = true)
	public void TC02_LoginEmpty() {
		String expectedMessage = "This is a required field.";
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[text() = 'My Account']")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("send2")).click();
		String emailValidation = driver.findElement(By.id("advice-required-entry-email")).getText();
		String passValidation = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailValidation, expectedMessage);
		Assert.assertEquals(passValidation, expectedMessage);
	}
	
	@Test(testName = "Test Script 03: Login with Email invalid", alwaysRun = true)
	public void TC03_LoginWithInvalidEmail() {
		String expectedMessage = "Please enter a valid email address. For example johndoe@domain.com.";
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[text() = 'My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		String errMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(errMessage, expectedMessage);	
	}
	
	@Test(testName = "Test Script 04: Login with Password incorrect")
	public void TC04_LoginWithIncorrectPassword() {
		String expectedMessage = "Please enter 6 or more characters without leading or trailing spaces.";
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[text() = 'My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String errMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(errMessage, expectedMessage);	
	}
	
//	@Test(testName = "Test Script 05: Create an account", alwaysRun = true)
//	public void TC05_CreateAnAccount() {
//		Random randomGenerator = new Random();  
//		int randomInt = randomGenerator.nextInt(1000);  
//		String email =   "username"+ randomInt +"@yopmail.com";
//		String expectedMessage = "Thank you for registering with Main Website Store.";
//		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[text() = 'My Account']")).click();
//		driver.findElement(By.xpath("//*[@id='login-form']//a[@title = 'Create an Account']")).click();
//		driver.findElement(By.id("firstname")).sendKeys("user");
//		driver.findElement(By.id("lastname")).sendKeys("name");
//		driver.findElement(By.id("email_address")).sendKeys(email);
//		driver.findElement(By.id("password")).sendKeys("123456");
//		driver.findElement(By.id("confirmation")).sendKeys("123456");
//		driver.findElement(By.xpath("//button[@title='Register']")).click();
//		String successMessage = driver.findElement(By.className("success-msg")).getText();
//		Assert.assertEquals(successMessage, expectedMessage);
//		driver.findElement(By.xpath("//*[@id = 'header']//span[text() = 'Account']")).click();
//		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
///*		String homePageTitle = driver.getTitle();
//		Assert.assertEquals(homePageTitle, "Magento Commerce");*/
//	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		/*System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();*/
        //options.addArguments("--headless");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver(options);
		driver.get("http://live.guru99.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun = true)
	public void teaDown() {
		driver.close();
		driver.quit();
	}

}

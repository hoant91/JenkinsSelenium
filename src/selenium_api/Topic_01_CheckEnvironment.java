package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
  WebDriver driver;
  @Test
  public void f() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Google");
  }
  @BeforeClass
  public void beforeClass() {
	  
	  //chrome
	  System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
	  driver = new ChromeDriver();
	  
	  // firefox
	 // driver = new FirefoxDriver();
	  driver.get("https://google.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

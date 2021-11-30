package test;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestNGToResultExcel {
	WebDriver driver;
	String url = "http://phptravels.net/login";
  @Test
  public void lauchWebsite() {
	  driver.get(url);
	  driver.manage().window().maximize(); 
  }
  @BeforeClass
  public void suiteSetup() {
	  WebDriverManager.chromedriver().setup();
	  try {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	} catch (Exception e) {
		// TODO: handle exception
	}
  }

  @AfterClass
  public void afterClass() {
  }

}

package com.poly.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	WebDriver driver;
	String url = "http://phptravels.net/login";
	String username = "nguyenthin34hd@gmail.com";
	String pass = "thin280101";
	Map<String , Object> testNgResult;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
  @Test(priority = 1)
  public void lauchWebsite() {
	 try {
		 driver.get(url);
		  driver.manage().window().maximize(); 
		  testNgResult.put("2", new Object[] {1d, "Navigation to demo Website", "Site gets Opened", "pass"});
	} catch (Exception e) {
		testNgResult.put("2", new Object[] {1d, "Navigation to demo Website", "Site gets Opened", "fail"});
	}
  }
  
  
  @Test(priority = 2)
  public void filllogin() throws InterruptedException {
	  try {
		  WebElement user = driver.findElement(By.name("email"));
		  user.sendKeys(username);
		  WebElement password = driver.findElement(By.name("password"));
		  password.sendKeys(pass);
		  Thread.sleep(1000);
		  testNgResult.put("3", new Object[] {2d, "Fill login form data", "login from gets fills", "pass"});
	} catch (Exception e) {
		testNgResult.put("3", new Object[] {2d, "Fill login form data", "login from gets fills", "fail"});
	}
  }
  
  @Test(priority = 3)
  public void dologin() throws InterruptedException {
	  try {
		  WebElement user = driver.findElement(By.className("ladda-button"));
		  user.click();
		  Thread.sleep(1000);
		  testNgResult.put("4", new Object[] {3d, "click login", "login success", "pass"});
	} catch (Exception e) {
		testNgResult.put("4", new Object[] {3d, "click login", "login fail", "fail"});
	}
  }
  
  
  @BeforeClass
  public void suiteSetup() {
	  workbook = new HSSFWorkbook();
	  sheet = workbook.createSheet("Test NG Result");
	  WebDriverManager.chromedriver().setup();
	  testNgResult= new LinkedHashMap<String, Object>();
	  testNgResult.put("1", new Object[] {"Test Step No.1", "Action", "Expected output", "Actual Output"});
	  try {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	} catch (Exception e) {
		throw new IllegalStateException("Connot Driver");
		
	}
  }

  @AfterClass
  public void suiteTearDown() {
	  Set<String> keysSet = testNgResult.keySet();
	  int rownum = 0;
	  for(String key : keysSet) {
		  Row row = sheet.createRow(rownum++);
		  Object[] objArr = (Object[]) testNgResult.get(key);
		  int cellnum = 0;
		  for(Object obj : objArr) {
			  Cell cell = row.createCell(cellnum ++);
			  if(obj instanceof Date) {
				  cell.setCellValue((Date) obj);
			  }else if(obj instanceof Boolean) {
				  cell.setCellValue((Boolean) obj);
			  }else if(obj instanceof String) {
				  cell.setCellValue((String) obj);
			  }else if(obj instanceof Double) {
				  cell.setCellValue((Double) obj);
			  }
		  }
		  try {
			FileOutputStream out = new FileOutputStream(new File("D:\\SOF304\\Assignment\\Assignment_KT\\excel\\TestLogin.xls"));
			workbook.write(out);
			System.out.println("success");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
  }

}

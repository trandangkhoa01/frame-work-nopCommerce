package com.nopCommerce.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Use_BasePage_Extend extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String OS = System.getProperty("os.name");
 
	@BeforeClass
	public void beforeClass() {
		if(OS.contains("Mac OS")){
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/chromedriver");
		}else {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
  
	@Test
	public void Register_01_Empty_Data() {
		openUrl(driver,"https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[text() = 'Register']");
		clickToElement(driver, "//button[@id= 'register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id= 'FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id= 'LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id= 'Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id= 'Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id= 'ConfirmPassword-error']"), "Password is required.");
		
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		openUrl(driver,"https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[text() = 'Register']");
		senKeysToElement(driver,"//input[@id= 'FirstName']" ,"Tran Dang");
		senKeysToElement(driver,"//input[@id= 'LastName']" ,"Khoa");
		senKeysToElement(driver,"//input[@id= 'Email']" ,"khoa01@com");
		senKeysToElement(driver,"//input[@id= 'Password']" ,"Khoa0195@");
		senKeysToElement(driver,"//input[@id= 'ConfirmPassword']" ,"Khoa0195@");
		clickToElement(driver, "//button[@id= 'register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "Wrong email");
		
	}
	
  @Test
  public void Register_03_Invalid_Password() {
	  openUrl(driver,"https://demo.nopcommerce.com/");
	  clickToElement(driver, "//a[text() = 'Register']");
	  clickToElement(driver, "//button[@id= 'register-button']");
	  senKeysToElement(driver,"//input[@id= 'FirstName']" ,"Tran Dang");
	  senKeysToElement(driver,"//input[@id= 'LastName']" ,"Khoa");
	  senKeysToElement(driver,"//input[@id= 'Email']" ,"khoa01@gmail.com");
	  senKeysToElement(driver,"//input[@id= 'Password']" ,"Kho");
	  senKeysToElement(driver,"//input[@id= 'ConfirmPassword']" ,"Kho");
	  clickToElement(driver, "//button[@id= 'register-button']");

	  Assert.assertEquals(getElementText(driver, "//span[@id ='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	  
  }
  
  @Test
	public void Register_04_Incorrect_Confirm_Password() {
		openUrl(driver,"https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[text() = 'Register']");
		clickToElement(driver, "//button[@id= 'register-button']");
		senKeysToElement(driver,"//input[@id= 'FirstName']" ,"Tran Dang");
		senKeysToElement(driver,"//input[@id= 'LastName']" ,"Khoa");
		senKeysToElement(driver,"//input[@id= 'Email']" ,"khoa01@com");
		senKeysToElement(driver,"//input[@id= 'Password']" ,"Khoa0195@");
		senKeysToElement(driver,"//input[@id= 'ConfirmPassword']" ,"Khoa0195");
		clickToElement(driver, "//button[@id= 'register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id= 'ConfirmPassword-error']"), "The password and confirmation password do not match.");

	}
  
  @Test
	public void Register_05_Register_Success() {
		openUrl(driver,"https://demo.nopcommerce.com/");
		clickToElement(driver, "//a[text() = 'Register']");
		clickToElement(driver, "//button[@id= 'register-button']");
		senKeysToElement(driver,"//input[@id= 'FirstName']" ,"Tran Dang");
		senKeysToElement(driver,"//input[@id= 'LastName']" ,"Khoa");
		senKeysToElement(driver,"//input[@id= 'Email']" ,getRandomEmail());
		senKeysToElement(driver,"//input[@id= 'Password']" ,"Khoa0195@");
		senKeysToElement(driver,"//input[@id= 'ConfirmPassword']" ,"Khoa0195@");
		clickToElement(driver, "//button[@id= 'register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class ='result']"), "Your registration completed");

	}
  
  public String getRandomEmail() {
	  Random rd = new Random();
	  return "khoa" + rd.nextInt(99999)  + "@gmail.com";
  }

  @AfterClass (alwaysRun = true)
  public void afterClass() {
	  driver.quit();
  }
}

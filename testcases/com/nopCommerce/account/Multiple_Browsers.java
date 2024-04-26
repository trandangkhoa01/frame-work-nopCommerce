package com.nopCommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Multiple_Browsers extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	String emailSignIn = getRandomEmail();

	@Parameters ("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowser(browserName);
		homePage = new HomePageObject(driver);

	}

	@Test
	public void TC_01_Empty_Data() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailError(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordError(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirnPasswordError(), "Password is required.");
		registerPage.clickToWebLogo();
	}

	//@Test
	public void TC_02_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.SendKeysToFirstNameTextBox("Tran Dang");
		registerPage.SendKeysToLastNameTextBox("Khoa");
		registerPage.SendKeysToEmailTextBox("khoa@com");
		registerPage.SendKeysToPasswordTextBox("123456");
		registerPage.SendKeysToConfirnPasswordTextBox("123456");
		registerPage.clickToRegisterButton();
		registerPage.clickToWebLogo();
	}

	@Test
	public void TC_03_Invalid_Password() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.SendKeysToFirstNameTextBox("Tran Dang");
		registerPage.SendKeysToLastNameTextBox("Khoa");
		registerPage.SendKeysToEmailTextBox(emailSignIn);
		registerPage.SendKeysToPasswordTextBox("123");
		registerPage.SendKeysToConfirnPasswordTextBox("123");registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getPasswordError(),
				"Password must meet the following rules:\nmust have at least 6 characters");

		registerPage.clickToWebLogo();
	}

	@Test
	public void TC_04_Missmatch_Confirn_Password() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.SendKeysToFirstNameTextBox("Tran Dang");
		registerPage.SendKeysToLastNameTextBox("Khoa");
		registerPage.SendKeysToEmailTextBox(emailSignIn);
		registerPage.SendKeysToPasswordTextBox("123456");
		registerPage.SendKeysToConfirnPasswordTextBox("123");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getConfirnPasswordError(), "The password and confirmation password do not match.");

		registerPage.clickToWebLogo();
	}

	@Test
	public void TC_05_Register_Success() {
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.SendKeysToFirstNameTextBox("Tran Dang");
		registerPage.SendKeysToLastNameTextBox("Khoa");
		registerPage.SendKeysToEmailTextBox(emailSignIn);
		registerPage.SendKeysToPasswordTextBox("123456");
		registerPage.SendKeysToConfirnPasswordTextBox("123456");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterResult(), "Your registration completed");

		registerPage.clickToWebLogo();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeDriver();
	}

	
}

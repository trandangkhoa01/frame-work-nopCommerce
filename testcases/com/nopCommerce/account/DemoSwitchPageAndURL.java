package com.nopCommerce.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AdminPageObject.AdminDashBoardPageObject;
import AdminPageObject.AdminLoginpageObject;
import commons.AdminPageGenerator;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class DemoSwitchPageAndURL extends BaseTest {
	WebDriver driver;
	String adminURL = GlobalConstants.WEB_ADMIN_URL;
	String userURL = GlobalConstants.WEB_USER_URL;
	HomePageObject homepage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	CustomerInforPageObject customerInfoPage;
	AddressPageObject addressPage;
	AdminLoginpageObject adminLoginPage;
	AdminDashBoardPageObject adminDashboardPage;
	String emailSignIn = getRandomEmail();
	String password = "123456";

	@Parameters("browser")
	@BeforeClass
	public void initBrowser(String browserName) {
		driver = getBrowser(browserName, userURL);
		
		homepage = PageGeneratorManager.openHomePage(driver);
		
		
		
	}
	
	@Test
	public void TC_O1_Switch_Page() {
		
		registerPage = homepage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.SendKeysToFirstNameTextBox("Tran Dang");
		registerPage.SendKeysToLastNameTextBox("Khoa");
		registerPage.SendKeysToEmailTextBox(emailSignIn);
		registerPage.SendKeysToPasswordTextBox(password);
		registerPage.SendKeysToConfirnPasswordTextBox(password);
		registerPage.clickToRegisterButton();
		
		loginPage = registerPage.clickLogin();
		
		homepage = loginPage.loginSuccess(emailSignIn, password);
		
		customerInfoPage = homepage.clickToMyAccountLink();
		customerInfoPage.clickToMenu("Addresses");
		
		addressPage = PageGeneratorManager.openAddressPage(driver);
		addressPage.clickToMenu("Customer info");
		
		customerInfoPage = PageGeneratorManager.openCustomerInforPage(driver);
		
		addressPage = (AddressPageObject) customerInfoPage.clickToMenuByName("Addresses");
		
		customerInfoPage = (CustomerInforPageObject) addressPage.clickToMenuByName("Customer info");
		
		homepage = customerInfoPage.clickLogout();
	}

	//@Test
	public void TC_O2_SwitchURL() {
		
		loginPage = homepage.clickToLoginLink();
		
		homepage = loginPage.loginSuccess(emailSignIn, password);
		
		homepage.openUrl(driver, adminURL);
		
		adminLoginPage = AdminPageGenerator.openLoginPage(driver);	
		
		adminDashboardPage = adminLoginPage.clickLoginButton();
		Assert.assertTrue(adminDashboardPage.isPageReady(driver));
		
		adminLoginPage.openUrl(driver, userURL);
		
		homepage = PageGeneratorManager.openHomePage(driver);
		
		
	}

	@AfterClass(alwaysRun = true)
	public void CloseBrowser() {
		closeDriver();
	}

}

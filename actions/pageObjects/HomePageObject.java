package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToRegisterLink() {
		waitForElementToBeClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver,  HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.openRegisterPage(driver);
	}
	
	public CustomerInforPageObject clickToMyAccountLink() {
		waitForElementToBeClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver,  HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.openCustomerInforPage(driver);
	}
	
	public LoginPageObject clickToLoginLink() {
		waitForElementToBeClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver,  HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.openLoginPage(driver);
	}
	

	
}

package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import pageUIs.CustomerInfoPageUI;

public class CustomerInforPageObject extends MyAccountMenuPageObject{
	WebDriver driver;

	public CustomerInforPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public HomePageObject clickLogout() {
		clickToElement(driver, CustomerInfoPageUI.LOGOUT_LINK);
		return PageGeneratorManager.openHomePage(driver);
	}
}

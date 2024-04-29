package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.MyAccountMenuPageUI;

public class MyAccountMenuPageObject extends BasePage {
	WebDriver driver;

	public MyAccountMenuPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AddressPageObject openAddressPage() {
		clickToElement(driver, MyAccountMenuPageUI.ADDRESS_LINK);
		return PageGeneratorManager.openAddressPage(driver);
	}
	
	public CustomerInforPageObject openCustomerInfoPage() {
		clickToElement(driver, MyAccountMenuPageUI.ADDRESS_LINK);
		return PageGeneratorManager.openCustomerInforPage(driver);
	}
	
}

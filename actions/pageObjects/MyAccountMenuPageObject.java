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
	
	
	public void  clickToMenu(String pageName) {
		clickToElement(driver, MyAccountMenuPageUI.MENU_LEFT_PAGE_LINK, pageName);
	}
	
	public MyAccountMenuPageObject  clickToMenuByName(String pageName) {
		clickToElement(driver, MyAccountMenuPageUI.MENU_LEFT_PAGE_LINK, pageName);
		switch (pageName) {
		case "Addresses": {
			return PageGeneratorManager.openAddressPage(driver);
		}
		case "Customer info": {
			return PageGeneratorManager.openCustomerInforPage(driver);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + pageName);
		}

	}
	
}

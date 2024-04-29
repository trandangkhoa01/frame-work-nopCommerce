package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager  extends BasePage{
	
	public static HomePageObject openHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static RegisterPageObject openRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject openLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static CustomerInforPageObject openCustomerInforPage(WebDriver driver) {
		return new CustomerInforPageObject(driver);
	}
	
	public static AddressPageObject openAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	
	
}

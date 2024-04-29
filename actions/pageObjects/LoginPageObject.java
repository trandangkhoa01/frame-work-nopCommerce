package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageUIs.LoginPageUI;

public class LoginPageObject extends PageGeneratorManager {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterEmail(String emailValue) {
		senKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX,emailValue );
	}
	
	public void enterPassWord(String passwordValue) {
		senKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX,passwordValue );
	}
	
	public void clickLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	
	public HomePageObject loginSuccess(String email, String password) {
		enterEmail(email);
		enterPassWord(password);
		clickLoginButton();
		return PageGeneratorManager.openHomePage(driver);
	}
	
}

package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementToBeClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public String getFirstNameError() {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
	}

	public String getLastNameError() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
	}

	public String getEmailError() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
	}

	public String getPasswordError() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
	}

	public String getConfirnPasswordError() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
		return getElementText(driver,  RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}

	public HomePageObject clickToWebLogo() {
		waitForElementToBeClickable(driver, RegisterPageUI.WEB_LOGO);
		clickToElement(driver,RegisterPageUI.WEB_LOGO);
		return PageGeneratorManager.openHomePage(driver);
	}

	public void SendKeysToFirstNameTextBox(String string) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX );
		senKeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX , string);
	}

	public void SendKeysToLastNameTextBox(String string) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX );
		senKeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX , string);

	}

	public void SendKeysToEmailTextBox(String string) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX );
		senKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX , string);

	}

	public void SendKeysToPasswordTextBox(String string) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX );
		senKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX , string);
	}

	public void SendKeysToConfirnPasswordTextBox(String string) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX );
		senKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX , string);
	}

	public String getRegisterResult() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_RESULT );
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_RESULT);
	}
	
	public LoginPageObject clickLogin() {
		clickToElement(driver, RegisterPageUI.LOGIN_LINK);
		return PageGeneratorManager.openLoginPage(driver);
	}

}

package pageUIs;

import org.openqa.selenium.WebDriver;

public class RegisterPageUI {
	public static final String REGISTER_BUTTON = "xpath=//button[@id= 'register-button']";
	public static final String FIRSTNAME_TEXTBOX = "xpath=//input[@id= 'FirstName']";
	public static final String LASTNAME_TEXTBOX = "css=input#LastName";
	public static final String EMAIL_TEXTBOX = "xpath=//input[@id= 'Email']";
	public static final String PASSWORD_TEXTBOX = "xpath=//input[@id= 'Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "xpath=//input[@id= 'ConfirmPassword']";
	public static final String REGISTER_SUCCESS_RESULT = "xpath=//div[@class = 'result']";
	public static final String FIRST_NAME_ERROR_MSG = "xpath=//span[@id = 'FirstName-error']";
	public static final String LAST_NAME_ERROR_MSG = "xpath=//span[@id = 'LastName-error']";
	public static final String EMAIL_ERROR_MSG = "xpath=//span[@id = 'Email-error']";
	public static final String PASSWORD_ERROR_MSG = "xpath=//span[@id = 'Password-error']";
	public static final String CONFIRM_PASSWORD_ERROR_MSG = "xpath=//span[@id = 'ConfirmPassword-error']";
	public static final String WEB_LOGO = "xpath=//div[@class = 'header-logo']//img";
	public static final String LOGIN_LINK = "css=a.ico-login";
	
	
}

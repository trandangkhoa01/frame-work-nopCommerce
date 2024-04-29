package AdminPageObject;

import org.openqa.selenium.WebDriver;

import AdminpageUIs.AdminLoginPageUI;
import commons.AdminPageGenerator;
import commons.BasePage;

public class AdminLoginpageObject extends BasePage {
	WebDriver driver;

	public AdminLoginpageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AdminDashBoardPageObject clickLoginButton() {
		clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
		return AdminPageGenerator.openDashBoard(driver);
		
	}
}

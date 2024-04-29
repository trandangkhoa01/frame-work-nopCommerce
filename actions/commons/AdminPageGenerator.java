package commons;

import org.openqa.selenium.WebDriver;

import AdminPageObject.AdminDashBoardPageObject;
import AdminPageObject.AdminLoginpageObject;

public class AdminPageGenerator extends BasePage{
	

	public static AdminDashBoardPageObject openDashBoard(WebDriver driver) {
		return new AdminDashBoardPageObject(driver);
	}

	public static AdminLoginpageObject openLoginPage(WebDriver driver) {
		return new AdminLoginpageObject(driver);
	}
	
	
}

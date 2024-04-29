package pageObjects;

import org.openqa.selenium.WebDriver;

public class AddressPageObject extends MyAccountMenuPageObject{
	WebDriver driver;

	public AddressPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}

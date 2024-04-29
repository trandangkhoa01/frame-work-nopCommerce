package commons;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {

	private WebDriver driver;

	protected WebDriver getBrowser(String browserName, String url) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		switch (browser) {
		case CHROME:
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	public String getRandomEmail() {
		Random rd = new Random();
		return "khoa" + rd.nextInt(99999) + "@gmail.com";
	}
	
	public void closeDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}

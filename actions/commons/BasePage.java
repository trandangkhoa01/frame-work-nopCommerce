package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextFromAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void senKeysToAlert(WebDriver driver, String valueToSend) {
		waitForAlertPresence(driver).sendKeys(valueToSend);
		;
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> pageList = driver.getWindowHandles();
		for (String page : pageList) {
			driver.switchTo().window(page);
			if (driver.getTitle().equals(expectedPageTitle)) {
				break;
			}

		}
	}

	public void closeWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> pageList = driver.getWindowHandles();
		for (String page : pageList) {
			driver.switchTo().window(page);
			if (!driver.getTitle().equals(expectedPageTitle)) {
				driver.close();
			}

		}
	}

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void deleteCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	public void sleepInSecond(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDynamicLocator(String locator, String...RestParameter) {
		return String.format(locator,(Object[]) RestParameter);
	}
	
	
	public By getByLocator(String locator) {
		By by = null;
		if(locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPath=") || locator.startsWith("XPATH=")) {
			by = By.xpath(locator.substring(6));
		}else if(locator.startsWith("css=")||locator.startsWith("Css=")||locator.startsWith("CSS=")) {
			by = By.cssSelector(locator.substring(4));
		}else if(locator.startsWith("id=")||locator.startsWith("Id=")||locator.startsWith("ID=")) {
			by = By.id(locator.substring(3));
		}else if(locator.startsWith("class=")||locator.startsWith("Class=")||locator.startsWith("CLASS=")) {
			by = By.className(locator.substring(6));
		}else if(locator.startsWith("tagname=")||locator.startsWith("tagName=")||locator.startsWith("TAGNAME=") ||locator.startsWith("TagName=")) {
			by = By.tagName(locator.substring(8));
		}else {
			throw new RuntimeException("Locator is invalid");
		}
			
			return by;
	}
	
	
	public WebElement findElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}
	

	public void clickToElement(WebDriver driver, String locator) {
		waitForElementToBeClickable(driver, locator);
		findElement(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String...RestParameter) {
		waitForElementToBeClickable(driver, getDynamicLocator(locator, RestParameter));
		findElement(driver, getDynamicLocator(locator, RestParameter)).click();
	}
	
	
	
	public void senKeysToElement(WebDriver driver, String locator, String valueToSend) {
		waitForElementVisible(driver, locator);
		findElement(driver, locator).clear();
		findElement(driver, locator).sendKeys(valueToSend);
	}

	public void senKeysToElement(WebDriver driver, String locator, String valueToSend, String...RestParameter) {
		waitForElementVisible(driver, getDynamicLocator(locator, RestParameter));
		findElement(driver, getDynamicLocator(locator, RestParameter)).clear();
		findElement(driver, getDynamicLocator(locator, RestParameter)).sendKeys(valueToSend);
	}

	public String getElementText(WebDriver driver, String locator) {
		waitForElementVisible(driver, locator);
		return findElement(driver, locator).getText();
	}
	public String getElementText(WebDriver driver, String locator, String RestParameter) {
		waitForElementVisible(driver, getDynamicLocator(locator, RestParameter));
		return findElement(driver, getDynamicLocator(locator, RestParameter)).getText();
	}

	public List<WebElement> getListElement(WebDriver driver, String locator) {
		waitForAllElementIsPresent(driver, locator);
		return driver.findElements(getByLocator(locator));
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String expectedItem) {
		new Select(findElement(driver, locator)).selectByVisibleText(expectedItem);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String expectedItem,String RestParameter) {
		new Select(findElement(driver, getDynamicLocator(locator, RestParameter))).selectByVisibleText(expectedItem);
	}
    
	public String getFirstSelectedItem(WebDriver driver, String locator) {
		return new Select(findElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public Boolean isDropdownMultiple(WebDriver driver, String locator) {
		return new Select(findElement(driver, locator)).isMultiple();
	}

	public void selectItem(WebDriver driver, String parentPath, String itemListPath, String expectedValue) {
		findElement(driver, parentPath).click();
		List<WebElement> list = new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(itemListPath)));
		for (WebElement element : list) {
			if (element.getText().trim().equals(expectedValue)) {
				element.click();
				break;
			}
		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		waitForElementVisible(driver, locator);
		return findElement(driver, locator).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locator, String cssPropety) {
		waitForElementVisible(driver, locator);
		return findElement(driver, locator).getCssValue(cssPropety);
	}

	public String convertRGBAToHexaColor(WebDriver driver, String locator) {
		waitForElementVisible(driver, locator);
		return Color.fromString(getElementCssValue(driver, locator, "background-color")).asHex();
	}

	public int getListElementSize(WebDriver driver, String locator) {
		waitForAllElementIsPresent(driver, locator);
		return getListElement(driver, locator).size();
	}

	/**
	 * checkToElement use for checkbox and radio button
	 * 
	 * @param locator
	 */
	public void checkToElment(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			findElement(driver, locator).click();
		}
	}
	
	public void checkToElment(WebDriver driver, String locator,String RestParameter) {
		if (!isElementSelected(driver, getDynamicLocator(locator, RestParameter))) {
			findElement(driver, getDynamicLocator(locator, RestParameter)).click();
		}
	}

	/**
	 * unCheckToElement only use for Checkbox
	 * 
	 * @param locator
	 */
	public void unCheckToElment(WebDriver driver, String locator,String RestParameter) {
		if (findElement(driver, getDynamicLocator(locator, RestParameter)).isSelected()) {
			findElement(driver, getDynamicLocator(locator, RestParameter)).click();
		}
	}
	
	public void unCheckToElment(WebDriver driver, String locator) {
		if (findElement(driver, locator).isSelected()) {
			findElement(driver, locator).click();
		}
	}
	
	public Boolean isElementDisplay(WebDriver driver, String locator) {
		return findElement(driver, locator).isDisplayed();
	}

	public Boolean isElementEnable(WebDriver driver, String locator) {
		return findElement(driver, locator).isEnabled();
	}

	public Boolean isElementSelected(WebDriver driver, String locator) {
		return findElement(driver, locator).isSelected();
	}
	
	public Boolean isElementSelected(WebDriver driver, String locator,String RestParameter) {
		return findElement(driver, getDynamicLocator(locator, RestParameter)).isSelected();
	}

	public void switchToIFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findElement(driver, locator));
	}

	public void switchToDefaultConten(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(findElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator,String RestParameter) {
		new Actions(driver).moveToElement(findElement(driver, getDynamicLocator(locator, RestParameter))).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(findElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		new Actions(driver).contextClick(findElement(driver, locator)).perform();
	}

	public void dragAndDropElement(WebDriver driver, String actualLocator, String expectedLocator) {
		new Actions(driver).dragAndDrop(findElement(driver, actualLocator), findElement(driver, expectedLocator))
				.perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(findElement(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javascript) {
		return ((JavascriptExecutor) driver).executeScript(javascript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnertext(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("windown.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location ='" + url + "';");
	}

	public void highlightElement(WebDriver driver, String locator) throws InterruptedException {
		WebElement element = findElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", findElement(driver, locator));
	}

	public void scrolltoElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", findElement(driver, locator));
	}

	public void scrolltoElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", findElement(driver, locator));
	}

	public void senKeysToElementUseJavascript(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value'" + value + "'",
				findElement(driver, locator));
	}

	public String getValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				findElement(driver, locator));

	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined && arguments[0].naturalWidth >0'",
				findElement(driver, locator));
		return status;
	}

	public void deleteAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElement(driver, locator));

	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOfAllElements(getListElement(driver, locator)));
	}

	public void waitForListElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver,Duration.ofSeconds(30))
				.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
	}

	public void waitForElementToBeClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForAllElementIsPresent(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	public void waitForSwitchToFrame(WebDriver driver, String locator) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
	}
	
	public boolean isPageReady(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
}

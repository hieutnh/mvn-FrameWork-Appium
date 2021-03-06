package commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import pageUIAndroid.AbstractPageUI;

public class AbstractPage {

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);

	}

	public String getCurrentPage(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextlAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendTextlAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public String getDynamicLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	public void clickToElement(WebDriver driver, String locator) {
		if (driver.toString().toLowerCase().contains("firefox") || driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		element = getElement(driver, locator);
		element.click();
	}

	public boolean retryingFindClick(WebDriver driver, String locator) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 10) {
			try {
				element = getElement(driver, locator);
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public boolean retryingFindClick(WebDriver driver, String locator, String... values) {
		if (driver.toString().toLowerCase().contains("firefox") || driver.toString().toLowerCase().contains("chrome")) {
			sleepInMiliSecond(500);
		}
		boolean result = false;
		int attempts = 0;
		while (attempts < 10) {
			try {
				element = getElement(driver, getDynamicLocator(locator, values));
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public WebElement retryingFindClick2(WebDriver driver, String locator, String... values) {
		element = null;
		for (int i = 0; i < 10; i++) {
			try {
				element = getElement(driver, getDynamicLocator(locator, values));
				element.click();
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
		return element;
	}

	public void checkDisplayToClick(WebDriver driver, String locator) {
		if (driver.toString().toLowerCase().contains("firefox") || driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(1000);
		}
		element = getElement(driver, locator);
		if (element.isDisplayed()) {
			sleepInMiliSecond(1000);
			element.click();
			sleepInMiliSecond(1000);
		}

	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		if (driver.toString().toLowerCase().contains("firefox") || driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		element = getElement(driver, getDynamicLocator(locator, values));
		element.click();
	}

	public void sendKeyKeyBoardMobile(WebDriver driver, AndroidKey key) {
		((AndroidDriver) driver).pressKey(new KeyEvent(key));
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = getElement(driver, locator);
		element.clear();
		if (driver.toString().toLowerCase().contains("chrome") || driver.toString().toLowerCase().contains("edge")) {
			sleepInMiliSecond(500);
		}
		element.sendKeys(value);
	}

	public void sendkeyToElementClear(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		element.clear();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		element.clear();
		if (driver.toString().toLowerCase().contains("chrome") || driver.toString().toLowerCase().contains("edge") || driver.toString().toLowerCase().contains("firefox")) {
			sleepInMiliSecond(500);
		}
		element.sendKeys(value);
	}

	public void sendKeyBoardEnterToElement(WebDriver driver, String locator, Keys value) {
		element = getElement(driver, locator);
		if (driver.toString().toLowerCase().contains("chrome") || driver.toString().toLowerCase().contains("edge") || driver.toString().toLowerCase().contains("firefox")) {
			sleepInMiliSecond(500);
		}
		element.sendKeys(value);
	}

	public void sendKeyBoardEnterToElement(WebDriver driver, String locator, Keys value, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		// if (driver.toString().toLowerCase().contains("chrome") ||
		// driver.toString().toLowerCase().contains("edge") ||
		// driver.toString().toLowerCase().contains("firefox")) {
		// sleepInMiliSecond(500);
		// }
		element.sendKeys(value);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
		element = getElement(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		select = new Select(element);
		select.selectByVisibleText(itemValue);
	}

	public void selectItemByIndexInDropdown(WebDriver driver, String locator, int itemValue) {
		element = getElement(driver, locator);
		select = new Select(element);
		select.selectByIndex(itemValue);
	}

	public void selectItemByIndexInDropdown(WebDriver driver, String locator, int itemValue, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		select = new Select(element);
		select.selectByIndex(itemValue);
	}

	public int getAllElementInLocator(WebDriver driver, String locator) {
		elements = getElements(driver, locator);
		select = new Select(element);
		return select.getOptions().size();
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultiple(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		select = new Select(element);
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		element = getElement(driver, parentLocator);
		element.click();
		sleepInSecond(5);
		explicitWait = new WebDriverWait(driver, 20);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		elements = getElements(driver, childItemLocator);
		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	// Get all items in a column
	public void getAllItemInColumn(WebDriver driver, String locatorRow, String locatorColumn, String locatorRandC) {
		List<WebElement> numerRows = getElements(driver, locatorRow);
		int rowSize = numerRows.size();
		System.out.println("row is" + rowSize);
		List<WebElement> numberColumn = getElements(driver, locatorColumn);
		int columnSize = numberColumn.size();
		System.out.println("column is" + columnSize);
		for (int i = 1; i <= rowSize; i++) {
			getElement(driver, locatorRandC);
		}
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepInMiliSecond(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		element = getElement(driver, locator);
		return element.getAttribute(attributeName);
	}

	public String getElementAttributeAndorid(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.getAttribute("text");
	}

	public String getElementAttributeiOS(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.getAttribute("label");
	}

	public boolean getElementAttribute(WebDriver driver, String locator, String attributeName, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		return element.getAttribute(attributeName) != null;
	}

	public String getElementText(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.getText();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		return element.getText();
	}

	public int countElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public int countElementSize(WebDriver driver, String locator, String... values) {
		return getElements(driver, getDynamicLocator(locator, values)).size();
	}

	public boolean verifyCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.isSelected();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToCheckbox(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		return element.isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeInSecond) {
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = getElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/display");
			return true;
		} else {
			System.out.println("Element in DOM and visible/display");
			return false;
		}

	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = getElements(driver, getDynamicLocator(locator, values));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/display");
			return true;
		} else {
			System.out.println("Element in DOM and visible/display");
			return false;
		}

	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		elements = getElements(driver, getDynamicLocator(locator, values));
		return element.isEnabled();
	}

	public boolean isElementDisabled(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return !element.isEnabled();
	}

	public boolean isElementDisabled(WebDriver driver, String locator, String... values) {
		elements = getElements(driver, getDynamicLocator(locator, values));
		return !element.isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		return element.isSelected();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();

	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();

	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();

	}

	public void clickAndHoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.clickAndHold(getElement(driver, locator)).perform();

	}

	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();

	}

	public void sendKeyboardToElement(WebDriver driver, Keys key, String locator) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();

	}

	public void hoverToClickElement(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		action = new Actions(driver);
		action.moveToElement(element).perform();
		sleepInMiliSecond(500);
		action.click().perform();
	}

	public void sendKeyboardToClearElement(WebDriver driver, String locator, String... values) {
		element = getElement(driver, getDynamicLocator(locator, values));
		action = new Actions(driver);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.chord(Keys.BACK_SPACE));

	}

	public void sendKeyboardEnter(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		element.sendKeys(Keys.chord(Keys.ENTER));

	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, getDynamicLocator(locator, values));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementiOS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		RemoteWebElement element = (RemoteWebElement) getElement(driver, locator);
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID);
		scrollObject.put("toVisible", "not an empty string");
		jsExecutor.executeScript("mobile:scroll", scrollObject);

	}

	public void scrollAndSwipeToPoint(WebDriver driver, String direction) {
		Dimension dim = driver.manage().window().getSize();
		int x = dim.getWidth() / 2;
		int y = dim.getHeight() / 2;
		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;

		switch (direction) {
		case "up":
			startY = (int) (dim.getHeight() * 0.8);
			endY = (int) (dim.getHeight() * 0.2);
			TouchAction t = new TouchAction((PerformsTouchActions) driver);
			t.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			break;
		case "down":
			startY = (int) (dim.getHeight() * 0.2);
			endY = (int) (dim.getHeight() * 0.8);
			TouchAction t1 = new TouchAction((PerformsTouchActions) driver);
			t1.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			break;
		case "right":
			startX = (int) (dim.getWidth() * 0.05);
			endX = (int) (dim.getWidth() * 0.90);
			TouchAction t2 = new TouchAction((PerformsTouchActions) driver);
			t2.press(PointOption.point(startX, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endX, y)).release().perform();
			break;
		case "left":
			startX = (int) (dim.getWidth() * 0.90);
			endX = (int) (dim.getWidth() * 0.05);
			TouchAction t3 = new TouchAction((PerformsTouchActions) driver);
			t3.press(PointOption.point(startX, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endX, y)).release().perform();
			break;
		}
	}

	public void scrollFromElementToElement(WebDriver driver, String direction, String locator1, String locator2) {
		TouchAction t = new TouchAction((PerformsTouchActions) driver);
		t.press(ElementOption.element(getElement(driver, locator1))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(ElementOption.element(getElement(driver, locator2))).release().perform();
	}


	public void scrollFromPointToPoint(WebDriver driver, String locator, String directory) {
		for (int i = 0; i < 3; i++) {
			if (isDisplayToScroll(driver, locator)) {
				break;
			} else {
				if (directory.equalsIgnoreCase("up")) {
					scrollAndSwipeToPoint(driver, "up");
				} else if (directory.equalsIgnoreCase("down")) {
					scrollAndSwipeToPoint(driver, "down");
				} else if (directory.equalsIgnoreCase("left")) {
					scrollAndSwipeToPoint(driver, "left");
				} else {
					scrollAndSwipeToPoint(driver, "right");
				}
			}
		}

	}

	public boolean isDisplayToScroll(WebDriver driver, String locator) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1);
			return wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					element = getElement(driver, locator);
					if (element.isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception ex) {
			return false;
		}
	}

	public void scrollToElement(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, getDynamicLocator(locator, values));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToLoadMore(WebDriver driver) {
		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(1000);

				long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void zoomINandOut(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		int center_X = element.getLocation().getX() + (element.getSize().width / 2);
		int center_Y = element.getLocation().getY() + (element.getSize().height / 2);

		MultiTouchAction multiTouchAction = new MultiTouchAction((PerformsTouchActions) driver);

		TouchAction zoomOut = new TouchAction((PerformsTouchActions) driver);
		zoomOut.longPress(PointOption.point(center_X, center_Y - 10)).moveTo(PointOption.point(center_X, center_Y)).perform();
		TouchAction zoomIn = new TouchAction((PerformsTouchActions) driver);
		zoomOut.longPress(PointOption.point(center_X, center_Y + 10)).moveTo(PointOption.point(center_X, center_Y + 200)).perform();

		multiTouchAction.add(zoomOut).add(zoomIn).perform();
	}

	public void zoomINandOut2(WebDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		int x = dim.getWidth() / 2;
		int startY = 0;
		int endY = 0;
		int endY2 = 0;

		// switch (direction) {
		// case "up":
		// startY = (int) (dim.getHeight() * 0.7);
		// endY = (int) (dim.getHeight() * 0.4);
		// break;
		// case "down":
		// startY = (int) (dim.getHeight() * 0.2);
		// endY = (int) (dim.getHeight() * 0.8);
		// break;
		// }
		TouchAction t = new TouchAction((PerformsTouchActions) driver);
		t.longPress(PointOption.point(508, 1472)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(508, 1800));
		TouchAction t1 = new TouchAction((PerformsTouchActions) driver);
		t1.longPress(PointOption.point(514, 911)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(508, 318));
		MultiTouchAction multiTouchAction = new MultiTouchAction((PerformsTouchActions) driver);
		multiTouchAction.add(t).add(t1).perform();
	}

	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = System.getProperty("user.dir") + getDirectorySlash("UploadFiles");

		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);

	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, getDynamicLocator(locator, values));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean verifyImage(WebDriver driver, String locator) {
		element = getElement(driver, locator);
		boolean status = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		}
		return false;
	}

	public void getToolTipMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = getElement(driver, locator);
		jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public boolean waitToJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
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

	public void waitToElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
	}

	public void waitToElementStaleness(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.stalenessOf(getElement(driver, locator)));
	}

	public void waitToElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitToElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
	}

	public void waitToElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitToElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitToElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}

	public void waitToElementClickAble(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
	}

	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println("------------------Data on UI ------------------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted on code ------------------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}

	public boolean isPriceSortAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}

		System.out.println("------------------Data on UI ------------------");
		for (Float name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted on code ------------------");
		for (Float name : arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}

	public boolean isDateSortAscending(WebDriver driver, String locator) throws ParseException {
		ArrayList<Date> arrayList = new ArrayList<Date>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
			arrayList.add(date.parse(element.getText()));
		}

		System.out.println("------------------Data on UI ------------------");
		for (Date name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Date> sortedList = new ArrayList<>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted on code ------------------");
		for (Date name : arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}

	public boolean isDataSortedDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("------------------Data on UI ------------------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted ASC on code ------------------");
		for (String name : arrayList) {
			System.out.println(name);
		}

		Collections.reverse(arrayList);
		System.out.println("------------------Data sorted DESC on code ------------------");
		for (String name : arrayList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);

	}

	public boolean isPriceSortedDescending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}

		System.out.println("------------------Data on UI ------------------");
		for (Float name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted on code ------------------");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		Collections.reverse(arrayList);
		System.out.println("------------------Data sorted DESC on code ------------------");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);

	}

	public boolean isDateSortDescending(WebDriver driver, String locator) throws ParseException {
		ArrayList<Date> arrayList = new ArrayList<Date>();
		List<WebElement> elementList = getElements(driver, locator);
		for (WebElement element : elementList) {
			SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
			arrayList.add(date.parse(element.getText()));
		}

		System.out.println("------------------Data on UI ------------------");
		for (Date name : arrayList) {
			System.out.println(name);
		}
		ArrayList<Date> sortedList = new ArrayList<>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("------------------Data sorted on code ------------------");
		for (Date name : arrayList) {
			System.out.println(name);
		}
		Collections.reverse(arrayList);
		System.out.println("------------------Data sorted DESC on code ------------------");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		return sortedList.equals(arrayList);

	}

	// Dynamic link

	private FluentWait<WebElement> fluentelement;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private WebElement element;
	private Actions action;
	private List<WebElement> elements;
	private Select select;
	private String osName = System.getProperty("os.name");

}

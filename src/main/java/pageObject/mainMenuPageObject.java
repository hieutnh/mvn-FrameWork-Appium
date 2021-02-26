package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIAndroid.LoginPageUIAndroid;
import pageUIAndroid.MainMenuPageUIAndroid;
import pageUIiOS.LoginPageUIiOS;

public class mainMenuPageObject extends AbstractPage {
	WebDriver driver;

	public mainMenuPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickFilter() {
		waitToElementClickAble(driver, MainMenuPageUIAndroid.FILTER_BUTTON);
		clickToElement(driver, MainMenuPageUIAndroid.FILTER_BUTTON);
	}

	public void clickFilterZtoA() {
		waitToElementClickAble(driver, MainMenuPageUIAndroid.FILTER_BUTTON_ZTOA);
		clickToElement(driver, MainMenuPageUIAndroid.FILTER_BUTTON_ZTOA);
	}

	public void isNameSortAscending() {
		waitToElementsVisible(driver, MainMenuPageUIAndroid.NAME_SORT);
		isDataSortedDescending(driver, MainMenuPageUIAndroid.NAME_SORT);

	}

	public void scrollTobottomPageMain(String directory) {
		// scrollToElementMobile(driver, directory);
		scrollToelementMobileAll(driver, MainMenuPageUIAndroid.SCROLLFINDBACKPACK, directory);
	}

	public void sendKeyToUrl(String value, Keys key) {
		waitToElementVisible(driver, MainMenuPageUIAndroid.Clickurl);
		clickToElement(driver, MainMenuPageUIAndroid.Clickurl);
		sendkeyToElement(driver, MainMenuPageUIAndroid.url, value);
		sendKeyBoardMobile(driver);
	}
}
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
		scrollFromPointToPoint(driver, MainMenuPageUIAndroid.SCROLLFINDBACKPACK, directory);
	}

	public void sendKeyToUrl(String value, Keys key) {
		waitToElementVisible(driver, MainMenuPageUIAndroid.Clickurl);
		clickToElement(driver, MainMenuPageUIAndroid.Clickurl);
		sendkeyToElement(driver, MainMenuPageUIAndroid.url, value);
	}

	public void zoomInAndOut() throws InterruptedException {
//		zoomINandOut(driver, "ZOOM_IN");
//		Thread.sleep(5000);
		zoomINandOut(driver, "ZOOM_OUT");
	}

	public void clickAddToCard() {
		waitToElementVisible(driver, MainMenuPageUIAndroid.ADDTOCARDBUTTON);
		clickToElement(driver, MainMenuPageUIAndroid.ADDTOCARDBUTTON);
	}

	public void clickToViewCard() {
		waitToElementVisible(driver, MainMenuPageUIAndroid.BUTTONVIEWCARD);
		clickToElement(driver, MainMenuPageUIAndroid.BUTTONVIEWCARD);
	}

	public void swipeToRemove() {
//		waitToElementVisible(driver, MainMenuPageUIAndroid.SWIPETOREMOVELINK);
		scrollAndSwipeToPoint(driver,"left");
	}

	public void clickImage() {
		waitToElementVisible(driver, MainMenuPageUIAndroid.zoomImage);
		clickToElement(driver, MainMenuPageUIAndroid.zoomImage);
		zoomINandOut(driver, "ZOOM_OUT");
	}
}
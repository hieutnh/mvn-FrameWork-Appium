package pageObject;

import org.openqa.selenium.WebDriver;

import pageUIAndroid.MainMenuPageUIAndroid;

public class PageGeneratorManager {

	public static loginPageObject getLoginPage(WebDriver driver) {
		return new loginPageObject(driver);
	}

	public static mainMenuPageObject getMainMenu(WebDriver driver) {
		return new mainMenuPageObject(driver);
	}

}

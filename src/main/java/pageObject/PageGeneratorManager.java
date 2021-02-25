package pageObject;

import io.appium.java_client.AppiumDriver;

public class PageGeneratorManager {

	public static loginPageObject getLoginPage(AppiumDriver driver) {
		return new loginPageObject(driver);
	}
	

}

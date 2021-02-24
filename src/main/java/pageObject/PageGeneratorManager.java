package pageObject;

import io.appium.java_client.AppiumDriver;

public class PageGeneratorManager {

	public static loginPageObject loginPage(AppiumDriver driver) {
		return new loginPageObject(driver);
	}
	

}

package pageObject;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIAndroid.LoginPageUIAndroid;
import pageUIiOS.LoginPageUIiOS;

public class loginPageObject extends AbstractPage {
	WebDriver driver;

	public loginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public mainMenuPageObject clicktoLoginButton(String platformName) {
		if (platformName.equalsIgnoreCase("Android")) {
			waitToElementClickAble(driver, LoginPageUIAndroid.LOGINBUTTON);
			clickToElement(driver, LoginPageUIAndroid.LOGINBUTTON);
		} else {
			waitToElementClickAble(driver, LoginPageUIiOS.LOGINBUTTON);
			clickToElement(driver, LoginPageUIiOS.LOGINBUTTON);
		}
		return PageGeneratorManager.getMainMenu(driver);
	}

	public void getAttributeMobile(String platformName) {
		if (platformName.equalsIgnoreCase("Android")) {
			waitToElementVisible(driver, LoginPageUIiOS.LOGINBUTTON);
			getElementAttributeAndorid(driver, LoginPageUIiOS.LOGINBUTTON);
		} else {
			waitToElementVisible(driver, LoginPageUIAndroid.LOGINBUTTON);
			getElementAttributeAndorid(driver, LoginPageUIAndroid.LOGINBUTTON);
		}
	}

	public void inputUserName(String platformName, String value) {
		if (platformName.equalsIgnoreCase("Android")) {
			waitToElementVisible(driver, LoginPageUIAndroid.USERNAME_TEXTBOX);
			sendkeyToElement(driver, LoginPageUIAndroid.USERNAME_TEXTBOX, value);
		} else {
			waitToElementVisible(driver, LoginPageUIiOS.USERNAME_TEXTBOX);
			sendkeyToElement(driver, LoginPageUIiOS.USERNAME_TEXTBOX, value);
		}
	}

	public void inputPassword(String value) {
		waitToElementVisible(driver, LoginPageUIAndroid.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUIAndroid.PASSWORD_TEXTBOX, value);
	}
}
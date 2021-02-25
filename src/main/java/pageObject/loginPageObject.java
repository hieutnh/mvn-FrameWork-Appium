package pageObject;

import org.testng.annotations.Parameters;

import commons.AbstractPage;
import io.appium.java_client.AppiumDriver;
import pageUIAndroid.LoginPageUIAndroid;
import pageUIiOS.LoginPageUIiOS;

public class loginPageObject extends AbstractPage {
	AppiumDriver driver;

	public loginPageObject(AppiumDriver driver) {
		this.driver = driver;
	}

	public void clicktoLoginButton(String platformName) {
		if (platformName.equalsIgnoreCase("Android")) {
			waitToElementClickAble(driver, LoginPageUIAndroid.LOGINBUTTON);
			clickToElement(driver, LoginPageUIAndroid.LOGINBUTTON);
		} else {
			waitToElementClickAble(driver, LoginPageUIiOS.LOGINBUTTON);
			clickToElement(driver, LoginPageUIiOS.LOGINBUTTON);
		}

	}

	public void getAttributeMobile(String platformName ) {
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
			waitToElementVisible(driver, LoginPageUIiOS.USERNAME_TEXTBOX);	
			sendkeyToElement(driver, LoginPageUIiOS.USERNAME_TEXTBOX, value);
		} else {
			waitToElementVisible(driver, LoginPageUIiOS.USERNAME_TEXTBOX);	
			sendkeyToElement(driver, LoginPageUIiOS.USERNAME_TEXTBOX, value);
		}
	}
}
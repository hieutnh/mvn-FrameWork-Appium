package pageObject;

import org.openqa.selenium.support.FindBy;

import commons.AbstractPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pageUI.LoginPageUI;

public class loginPageObject extends AbstractPage{
	AppiumDriver driver;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]") public MobileElement LOGIN_BUTTON;
	public loginPageObject(AppiumDriver driver) {
		this.driver = driver;
	}

	public void clicktoLoginButton() {
		waitToElementClickAble(driver, LOGIN_BUTTON);
		clickToElement(driver, LOGIN_BUTTON);
	}
//	public void clicktoLoginButton() {
//		waitToElementClickAble(driver, LoginPageUI.LOGINBUTTON);
//		clickToElement(driver, LoginPageUI.LOGINBUTTON);
//	}

	public void inputUserName(String value) {
		waitToElementsVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX,value);
		
	}


}

package pageUI;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;

public class LoginPageUI {
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]") MobileElement LOGIN_BUTTON;
	public static final String 	LOGINBUTTON = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]";
	public static final String 	USERNAME_TEXTBOX = "//android.widget.EditText[@content-desc=\"test-Username\"]";

}

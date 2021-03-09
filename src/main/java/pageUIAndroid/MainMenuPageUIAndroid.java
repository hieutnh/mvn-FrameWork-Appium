package pageUIAndroid;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;

public class MainMenuPageUIAndroid {
	public static final String 	FILTER_BUTTON = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView";
	public static final String 	FILTER_BUTTON_ZTOA = "(//android.view.ViewGroup/android.widget.TextView)[3]";
	public static final String 	NAME_SORT = "//android.view.ViewGroup//android.widget.TextView[@content-desc=\"test-Item title\"]";
	public static final String 	SCROLLFINDBACKPACK = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[3]";
	public static final String 	Clickurl = "//android.widget.EditText[@resource-id='com.android.chrome:id/search_box_text']";
	public static final String 	url = "//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']";
	public static final String 	IMAGEBACKPACK1 = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView";
	public static final String 	IMAGEBACKPACK2 = "//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView";
	public static final String 	ADDTOCARDBUTTON = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]";
	public static final String 	BUTTONVIEWCARD = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView";
	public static final String 	SWIPETOREMOVELINK = "//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]";
	public static final String 	zoomImage = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView";
}

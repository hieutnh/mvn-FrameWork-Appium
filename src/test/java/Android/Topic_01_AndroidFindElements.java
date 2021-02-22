package Android;

import org.openqa.selenium.By;

import commons.CreateDriverSession;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class Topic_01_AndroidFindElements {

	public static void main(String[] args) throws Exception {
		AppiumDriver driver = CreateDriverSession.initializeDriver("Android");

		MobileElement myElement = (MobileElement) driver.findElementByAccessibilityId("Accessibility");
		System.out.println(myElement.getText());

		myElement = (MobileElement) driver.findElementsById("android:id/text1").get(1);
		System.out.println(myElement.getText());

		myElement = (MobileElement) driver.findElementsByClassName("android.widget.TextView").get(2);
		System.out.println(myElement.getText());

		myElement = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Accessibility\"]");
		System.out.println(myElement.getText());

		myElement = (MobileElement) driver.findElementByXPath("//*[@text=\"Accessibility\"]");
		System.out.println(myElement.getText());

		// not support android
//        myElement = (MobileElement) driver.findElementByTagName("Accessibility");
//        System.out.println(myElement.getText());

	}
}
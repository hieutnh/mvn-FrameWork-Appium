package commons;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CreateBrowserSession {
	AppiumDriver driver;
	static String sourceFolder = System.getProperty("user.dir");

	public static AppiumDriver initializeDriver(String platformName) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		caps.setCapability("newCommandTimeout", 300);
		URL url = new URL("http://0.0.0.0:4723/wd/hub");

		switch (platformName) {
		case "Android":
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

			// adb devices to get UDID
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
//			caps.setCapability(MobileCapabilityType.UDID, "1876ca52");
			
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			caps.setCapability("chromedriverExecutableDir", sourceFolder + File.separator + "Browser_Driver");
			

			// 1.Open android studio
			// 2.Name of device
//			caps.setCapability("avd", "Pixel_3");
			// 3.Set time
//			caps.setCapability("avdLaunchTimeout", 180000);
			
			// unlock device
			// caps.setCapability("unlockType", "password");
			// caps.setCapability("unlockKey", "123456");

			return new AndroidDriver(url, caps);

		case "iOS":
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			caps.setCapability(MobileCapabilityType.UDID, "77F6B8F0-8877-4EDF-8C8C-99DBE64A93FF");
			caps.setCapability("xcodeOrgId", "L8T9J4R323");
			caps.setCapability("xcodeSigningId", "iPhone Developer");
			// caps.setCapability("avd", "Pixel_3");
			// 3.Set time
			// caps.setCapability("avdLaunchTimeout", 180000);

			String iosFile = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "UIKitCatalog-iphonesimulator.app";
			// caps.setCapability(MobileCapabilityType.APP, iosFile);
			// Open app installed
			caps.setCapability("simulatorStartupTimeout", 180000);
			caps.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
			// install file apk

			return new IOSDriver(url, caps);
		default:
			throw new Exception("invalid platform");

		}
	}
}

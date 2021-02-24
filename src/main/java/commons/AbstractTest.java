package commons;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AbstractTest {
	protected AppiumDriver driver;
	protected Properties pros;
	InputStream inputStream;
	String sourceFolder = System.getProperty("user.dir");

	public AppiumDriver getBrowserDriver(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws Exception {
		URL url;
		try {
			pros = new Properties();
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			pros.load(inputStream);

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", platformName);
			caps.setCapability("deviceName", deviceName);

			switch (platformName) {
			case "Android":
				caps.setCapability("automationName", pros.getProperty("androidAutomationName"));
				caps.setCapability("appPackage", pros.getProperty("androdiAppPackage"));
				caps.setCapability("appActivity", pros.getProperty("androidActivity"));
				if(emulator.equalsIgnoreCase("true")) {
					caps.setCapability("platformVersion", platformVersion);
					caps.setCapability("avd", deviceName);
				}else {
					caps.setCapability("udid", udid);
				}
				URL androidUrl = getClass().getClassLoader().getResource(pros.getProperty("androidAppLocation"));
				caps.setCapability("app", androidUrl);
				url = new URL(pros.getProperty("appiumURL"));

				driver = new AndroidDriver(url, caps);
				break;
			case "iOS":
				caps.setCapability("automationName", pros.getProperty("iOSAutomationName"));
				caps.setCapability("platformVersion", platformVersion);
				URL iOSUrl = getClass().getClassLoader().getResource(pros.getProperty("iOSAppLocation"));
				caps.setCapability("app", iOSUrl);
				url = new URL(pros.getProperty("appiumURL"));

				driver = new IOSDriver(url, caps);
				break;
			default:
				throw new Exception("Invalid platform :" + platformName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return driver;

	}

	public void afterClass() {
		driver.quit();
	}
}

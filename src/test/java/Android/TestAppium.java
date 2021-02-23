package Android;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestAppium {
	AppiumDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "11");
		caps.setCapability("deviceName", "any device name");
		caps.setCapability("deviceName", "UiAutomation2");
		caps.setCapability("appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
		String apkFile = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "Android.SauceLabs.apk";
		caps.setCapability("app", apkFile);
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url,caps);
		String sessionID = driver.getSessionId().toString();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}
	
	@Test
	public void f() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
package Android;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Topic_11_Basic_code {
	AppiumDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "9.0");
		caps.setCapability("deviceName", "any device name");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
		String apkFile = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "Android.SauceLabs.apk";
//		caps.setCapability("app", apkFile);
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url, caps);
		String sessionID = driver.getSessionId().toString();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_invalidUsername() {
		MobileElement userNameTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement passwordTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userNameTextBox.sendKeys("invalidusername");
		passwordTextBox.sendKeys("123123");
		loginButton.click();
		MobileElement errorText = (MobileElement) driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView"));

		String actualText = errorText.getAttribute("text");
		System.out.println(actualText);
		
		Assert.assertEquals(actualText, "Username and password do not match any user in this service.");
	}

	@Test
	public void TC_02_invalidPassword() {
		MobileElement userNameTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement passwordTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userNameTextBox.sendKeys("standard_user");
		passwordTextBox.sendKeys("invalidpassword");
		loginButton.click();
		MobileElement errorText = (MobileElement) driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView"));

		String actualText = errorText.getAttribute("text");
		System.out.println(actualText);
		
		Assert.assertEquals(actualText, "Username and password do not match any user in this service.");
	}

	@Test
	public void TC_03_SuccessfulLogin() {
		MobileElement userNameTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Username");
		MobileElement passwordTextBox = (MobileElement) driver.findElementByAccessibilityId("test-Password");
		MobileElement loginButton = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

		userNameTextBox.sendKeys("standard_user");
		passwordTextBox.sendKeys("secret_sauce");
		loginButton.click();
		MobileElement product = (MobileElement) driver.findElement(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup//android.widget.TextView"));

		String actualProduct = product.getAttribute("text");
		System.out.println(actualProduct);
		
		Assert.assertEquals(actualProduct, "PRODUCTS");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

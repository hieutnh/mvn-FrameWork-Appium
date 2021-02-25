package Android;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import io.appium.java_client.AppiumDriver;
import pageObject.PageGeneratorManager;
import pageObject.loginPageObject;

public class Topic_12_BasicTest extends AbstractTest {
	AppiumDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@Parameters({ "emulator", "platformName", "platformVersion", "udid", "deviceName" })
	@BeforeClass
	public void BeforeClass(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws Exception {
		driver = getBrowserDriver(emulator, platformName, platformVersion, udid, deviceName);
	}

	@Parameters({ "platformName" })
	@Test
	public void TC_01_invalidUsername(String platformName) {
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputUserName(platformName,"username");
		loginPage.clicktoLoginButton(platformName);
	}

	loginPageObject loginPage;
}

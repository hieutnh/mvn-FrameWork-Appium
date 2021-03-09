package Android;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import io.appium.java_client.AppiumDriver;
import pageObject.PageGeneratorManager;
import pageObject.loginPageObject;
import pageObject.mainMenuPageObject;
import reportConfigAllure.AllureTestListener;

@Listeners({ AllureTestListener.class })
public class Topic_06_SwipeLeft extends AbstractTest {
	WebDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@Parameters({ "emulator", "platformName", "platformVersion", "udid", "deviceName", "appUrl" })
	@BeforeClass
	public void BeforeClass(String emulator, String platformName, String platformVersion, String udid, String deviceName, String appUrl) throws Exception {
		driver = getBrowserDriver(emulator, platformName, platformVersion, udid, deviceName, appUrl);
	}


	@Test
	public void TC_02_Filter() {
		mainMenuPage = PageGeneratorManager.getMainMenu(driver);
		mainMenuPage.swipeToRemove();

	}

	loginPageObject loginPage;
	mainMenuPageObject mainMenuPage;
}

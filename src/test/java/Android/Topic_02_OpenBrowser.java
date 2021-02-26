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
public class Topic_02_OpenBrowser extends AbstractTest {
	WebDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@Parameters({ "emulator", "platformName", "platformVersion", "udid", "deviceName" })
	@BeforeClass
	public void BeforeClass(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws Exception {
		driver = getBrowserDriver(emulator, platformName, platformVersion, udid, deviceName);
	}

	@Parameters({ "platformName" })
	@Test
	public void TC_01_Login_Successfully(String platformName) throws InterruptedException {
		mainMenuPage = PageGeneratorManager.getMainMenu(driver);
		mainMenuPage.sendKeyToUrl("https://eform-dev.acb.com.vn/", Keys.ENTER);
		Thread.sleep(3000);
		

	}


	loginPageObject loginPage;
	mainMenuPageObject mainMenuPage;
}

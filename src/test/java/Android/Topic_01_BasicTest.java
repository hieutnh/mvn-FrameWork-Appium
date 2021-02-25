package Android;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import io.appium.java_client.AppiumDriver;
import pageObject.PageGeneratorManager;
import pageObject.loginPageObject;
import pageObject.mainMenuPageObject;

public class Topic_01_BasicTest extends AbstractTest {
	WebDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	@Parameters({ "emulator", "platformName", "platformVersion", "udid", "deviceName" })
	@BeforeClass
	public void BeforeClass(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws Exception {
		driver = getBrowserDriver(emulator, platformName, platformVersion, udid, deviceName);
	}

	@Parameters({ "platformName" })
	@Test
	public void TC_01_Login_Successfully(String platformName) {
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputUserName(platformName, "standard_user");
		loginPage.inputPassword("secret_sauce");
		mainMenuPage = loginPage.clicktoLoginButton(platformName);

	}

	@Test
	public void TC_02_Filter() {
		mainMenuPage.clickFilter();
		mainMenuPage.clickFilterZtoA();
		mainMenuPage.isNameSortAscending();

	}

	loginPageObject loginPage;
	mainMenuPageObject mainMenuPage;
}

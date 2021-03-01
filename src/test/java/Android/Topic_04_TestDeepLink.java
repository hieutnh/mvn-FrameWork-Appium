package Android;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.AbstractTest;
import pageObject.PageGeneratorManager;
import pageObject.mainMenuPageObject;
import reportConfigAllure.AllureTestListener;

public class Topic_04_TestDeepLink {

	@Listeners({ AllureTestListener.class })
	public class Topic_03_ZoomIn_Out extends AbstractTest {
		WebDriver driver;
		String sourceFolder = System.getProperty("user.dir");

		@Parameters({ "emulator", "platformName", "platformVersion", "udid", "deviceName", "appUrl" })
		@BeforeClass
		public void BeforeClass(String emulator, String platformName, String platformVersion, String udid, String deviceName, String appUrl) throws Exception {
			driver = getBrowserDriver(emulator, platformName, platformVersion, udid, deviceName, appUrl);
		}

		@Parameters({ "platformName" })
		@Test
		public void TC_01_Test_Deeplink(String platformName) {
			mainMenuPage = PageGeneratorManager.getMainMenu(driver);
			commons.DeepLink.OpenAppWith("swaglabs://swag-overview/0,5");

		}
	}

	mainMenuPageObject mainMenuPage;
}

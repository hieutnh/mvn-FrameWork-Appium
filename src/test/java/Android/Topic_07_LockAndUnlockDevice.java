package Android;

import java.util.concurrent.TimeUnit;

import commons.CreateDriverSession;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Topic_07_LockAndUnlockDevice {

	public static void main(String[] args) throws Exception {
		AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		((AndroidDriver) driver).lockDevice();
		System.out.println(((AndroidDriver) driver).isDeviceLocked());
		((AndroidDriver) driver).unlockDevice();
	}
}

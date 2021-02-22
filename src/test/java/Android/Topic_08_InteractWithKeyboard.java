package Android;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

import commons.CreateDriverSession;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Topic_08_InteractWithKeyboard {

	public static void main(String[] args) throws Exception {
		AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		By views = MobileBy.AccessibilityId("Views");
		By textFields = MobileBy.AccessibilityId("TextFields");
		By editText = MobileBy.id("io.appium.android.apis:id/edit");

		driver.findElement(views).click();

		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int endX = startX;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		for (int i = 0; i < 3; i++) {
			TouchAction t = new TouchAction(driver);
			t.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endX, endY)).release().perform();
		}

		driver.findElement(textFields).click();
		driver.findElement(editText).click();
		Thread.sleep(3000);

		System.out.println(((AndroidDriver) driver).isKeyboardShown());

		((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.C));

		// Cách 1 : Sử dụng android driver (new)
		// ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.HOME));
		// ((AndroidDriver) driver).pressKey(new
		// KeyEvent().withKey(AndroidKey.CALENDAR));
		// ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.B));
		// Thread.sleep(3000);

		// Cách 2: Sử dụng driver của appium (old)
		driver.getKeyboard().pressKey(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		driver.getKeyboard().pressKey(Keys.HOME);
		// driver.hideKeyboard();

	}
}

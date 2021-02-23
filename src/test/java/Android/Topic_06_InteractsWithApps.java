package Android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import org.openqa.selenium.By;

import commons.CreateDriverSession;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_InteractsWithApps {

	public static void main(String[] args) throws Exception {
		AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		By views = MobileBy.AccessibilityId("Views");
		driver.findElement(views).click();

		// Tắt 1 app đang chạy xuống chạy ẩn và phải reload như mới. = với close
		// driver.terminateApp("io.appium.android.apis");

		// check 1 app đã được cài đặt
		driver.isAppInstalled("io.appium.android.apis");

		// remove 1 app đã được cài đặt
		// driver.removeApp("io.appium.android.apis");

		// khởi chạy 1 ứng dụng đang mở và restart lại session
		// driver.launchApp();

		// Ứng đụng đang chạy,TestAppium cho ẩn xuống sau n giây và mở lên lại
		 driver.runAppInBackground(Duration.ofMillis(5000));

		// Kích hoạt và mở ứng dụng đã được cài đặt nhưng không được chạy
		// driver.activateApp("io.appium.android.apis");

		// Check ứng dụng đang chạy hay không
		System.out.println(driver.queryAppState("io.appium.android.apis"));
		String apkFile = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "ApiDemos-debug.apk";

	}
}

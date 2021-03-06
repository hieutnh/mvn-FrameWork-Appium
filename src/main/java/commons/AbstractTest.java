package commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AbstractTest {
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	protected final Logger log;
	private static AppiumDriverLocalService server;

	protected AbstractTest() {
		log = Logger.getLogger(getClass());
	}

	protected WebDriver driver;
	String sourceFolder = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String emulator, String platformName, String platformVersion, String udid, String deviceName, String appUrl) {
		URL url;
		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			url = new URL("http://127.0.0.1:4723/wd/hub");
			switch (platformName) {
			case "Android":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
				caps.setCapability("appPackage", "com.swaglabsmobileapp");
				caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
				// caps.setCapability("appPackage", "com.dmitrybrant.android.multitouch");
				// caps.setCapability("appActivity",
				// "com.dmitrybrant.android.multitouch.MultiTouchActivity");
				if (emulator.equalsIgnoreCase("true")) {
					caps.setCapability("avd", deviceName);
					caps.setCapability("avdLaunchTimeout", 120000);
				} else {
					caps.setCapability(MobileCapabilityType.UDID, udid);
				}
				String apkFile = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "Android.SauceLabs.apk";
				caps.setCapability(MobileCapabilityType.APP, apkFile);

				// driver = new AndroidDriver(url, caps);
				setDriver(new AndroidDriver(url, caps));
				log.info("Test With Android");
				break;
			case "iOS":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
				caps.setCapability("bundleId", "org.askomdch.SwagLabMobileApp");
				String iOSUrl = System.getProperty("user.dir") + File.separator + "APK" + File.separator + "Android.SauceLabs.apk";
				caps.setCapability(MobileCapabilityType.APP, iOSUrl);

				setDriver(new IOSDriver(url, caps));
				log.info("Test With IOS");
				break;
			default:
				throw new Exception("Invalid platform :" + platformName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// getDriver().get(appUrl);
		return getDriver();

	}

	protected void removeDriver() {
		getDriver().quit();
		threadLocalDriver.remove();
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	private void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}

	// stop video capturing and create *.mp4 file
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();

		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") + File.separator + dateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();

		File videoDir = new File(dirPath);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
		} catch (Exception e) {
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@BeforeSuite
	public void deleteAllFileInReportNGScreenShot() {
		System.out.println("-------------------START delete file in folder-------------------");
		deleteAllFileInFolder();
		System.out.println("-------------------END delete file in folder-------------------");
	}

	public void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\allure-results\\";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@BeforeSuite
	public void beforeSuite() throws Exception, Exception {
		ThreadContext.put("ROUTINGKEY", "ServerLogs");
		// server = getAppiumService();
		server = getAppiumServerDefault();
		if (!checkIfAppiumServerIsRunnning(4723)) {
			server.start();
			// server.clearOutPutStreams();
			log.info("Appium server started");
		} else {
			log.info("Appium server already running");
		}
	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	@AfterSuite
	public void afterSuite() {
		server.stop();
		log.info("Appium server stopped");
	}

	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();

	}

	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		// environment.put("PATH", "E:\\Automation\\2.Appium\\sdk
		// AndroidStudio\\platform-tools;E:\\Automation\\2.Appium\\sdk
		// AndroidStudio\\tools;E:\\Automation\\2.Appium\\sdk
		// AndroidStudio\\tools\\bin;" + System.getenv("PATH"));
		// environment.put("PATH",
		// "C:\\Users\\APC\\AppData\\Local\\Android\\Sdk\\platform-tools;C:\\Users\\APC\\AppData\\Local\\Android\\Sdk\\tools;C:\\Users\\APC\\AppData\\Local\\Android\\Sdk\\tools\\bin"
		// + System.getenv("PATH"));
		// environment.put("ANDROID_HOME", "E:\\Automation\\2.Appium\\sdk
		// AndroidStudio");
		// environment.put("ANDROID_HOME",
		// "C:\\Users\\APC\\AppData\\Local\\Android\\Sdk");
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				// .usingDriverExecutable(new
				// File("E:\\Automation\\2.Appium\\3.NodeJS\\node.exe"))
				// .usingDriverExecutable(new File("D:\\java-2020-12\\NodeJS\\node.exe"))
				// .withAppiumJS(new
				// File("C:\\Users\\hieut\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				// .withAppiumJS(new
				// File("C:\\Users\\APC\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.usingPort(4723).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				// .withEnvironment(environment)
				.withLogFile(new File("ServerLogs/server.log")));
	}

}

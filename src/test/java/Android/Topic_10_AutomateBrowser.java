package Android;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import commons.CreateBrowserSession;
import io.appium.java_client.AppiumDriver;

public class Topic_10_AutomateBrowser {

	public static void main(String[] args) throws Exception {
		AppiumDriver driver = CreateBrowserSession.initializeDriver("Android");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		driver.navigate().back();
		driver.navigate().forward();
	}
}

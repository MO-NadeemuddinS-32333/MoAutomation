package nadeem;

import java.net.URL;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.HomePage;
import utils.Commons;

public class testuiautomator {
	private AndroidDriver driver;

	@BeforeTest
	public void initializeDriver() throws Exception {
		System.out.println(".............initializing Appium");
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("CPH2467");
		options.setPlatformName("Android");
		options.setAppPackage(Commons.getGlobalPropertiesValue("Rise_app_package"));
		options.setAppActivity(Commons.getGlobalPropertiesValue("Rise_app_activity"));
		options.setCapability("autoGrantPermissions", true);
		options.setCapability("noReset", true);

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
	}

	@Test
	public void getCurrentActivity() throws InterruptedException {
		System.out.println("app launched succesfully");
		Thread.sleep(10000);
		HomePage homepage = new HomePage(driver);
		homepage.portfolioBottombar.click();
		String activity = driver.currentActivity();
		System.out.println("Current Activity: " + activity);
	}

}

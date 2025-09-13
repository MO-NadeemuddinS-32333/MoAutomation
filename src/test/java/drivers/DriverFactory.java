package drivers;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import utils.Commons;

public class DriverFactory {

	private static ThreadLocal<AndroidDriver> drivers = new ThreadLocal<AndroidDriver>();
	private static List<AppiumDriver> storedDrivers = new ArrayList<AppiumDriver>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					storedDrivers.forEach(AppiumDriver::quit);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
	}

	public static AndroidDriver getDriver() {
		return drivers.get();
	}

	public static void addDriver(AndroidDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);
	}

	public static void removeDriver(AndroidDriver driver) {
		storedDrivers.remove(driver);
		drivers.remove();
	}

	
	public static void initDriver() throws IOException, InterruptedException {
		System.out.println("Initializing Appium...");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "13");
		capabilities.setCapability("deviceName", "CPH2467");
		capabilities.setCapability("udid", "97957054");
		capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package"));
		capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("noReset", true);

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		addDriver(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println("App launch request sent. Waiting for verification...");
		Thread.sleep(5000);
	}

}

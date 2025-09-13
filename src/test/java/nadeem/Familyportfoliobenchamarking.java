package nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.Portfolio;
import utils.Commons;

public class Familyportfoliobenchamarking {
	AndroidDriver Driver;
	String status;
	int loop = 15;

	@BeforeTest
	public void Verify_user_launch_application() throws InterruptedException, IOException {
		if ("RealDevice".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {
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

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			//DriverFactory.addDriver(Driver);
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			System.out.println("app launch succesfully");
		}

		else if ("BrowserStack".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {

			System.out.println("Starting browserstack");
			UiAutomator2Options capabilities = new UiAutomator2Options();

			HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
			bstackOptions.put("userName", "dheeraj142");
			bstackOptions.put("accessKey", "MhpLs2spj2FFtYv9TFiV");
			bstackOptions.put("appiumVersion", "2.0.1");
			bstackOptions.put("debug", "true");
			bstackOptions.put("interactiveDebugging", "true");
			// bstackOptions.put("build", buildName);

			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("appium:platformVersion", "14.0");
			capabilities.setCapability("appium:deviceName", "Google Pixel 8 Pro");
			capabilities.setCapability("appium:app", "bs://30835cecdc1668bee867197b6dcbd3d06bbe28b4");
			capabilities.setCapability("appium:automationName", "UIAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("bstack:options", bstackOptions);

			Driver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
			System.out.println("app launch succesfully");

			//DriverFactory.addDriver(Driver);

			Thread.sleep(500);

		}
	}

	@Test(priority = 1, enabled = true)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {
		LoginPage loginpage = new LoginPage(Driver);
		try {
			if (loginpage.loginButton.isDisplayed()) {
				loginpage.loginButton.click();
				Thread.sleep(1000);
				loginpage.userID.click();
				loginpage.userID.sendKeys(Commons.getGlobalPropertiesValue("userId"));
				Driver.hideKeyboard();
				loginpage.nextbutton.click();
				Thread.sleep(1000);
				loginpage.passwordTextField.click();
				loginpage.passwordTextField.sendKeys(Commons.getGlobalPropertiesValue("password"));
				Driver.hideKeyboard();
				loginpage.loginButton.click();
				Thread.sleep(5000);
				loginpage.dobTextField.get(0).click();
				loginpage.dobTextField.get(0).sendKeys(Commons.getGlobalPropertiesValue("dob"));
				Driver.hideKeyboard();
				loginpage.confirmDobButton.click();
				Thread.sleep(500);
				loginpage.exploreTheAppButton.click();
				Thread.sleep(500);
				loginpage.iUnderstandRddButton.click();
				Thread.sleep(500);
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			Thread.sleep(5000);
		}

	}

	@Test(priority = 2, enabled = true)
	public void FamilysummaryAll() throws InterruptedException, IOException {
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		String tableName = "Family Summary All";
		logTableStart(tableName);
		homepage.portfolioBottombar.click();
		//portfolio.familyportfoliosyperlink.click();
		for (int i = 1; i <= loop; i++) {
			portfolio.AllTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				//portfolio.familynetworth.isDisplayed();
				portfolio.individualnetworth.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			} finally {
				portfolio.StocksTabPortfolio.click();
			}
		}
		logTableEnd(tableName);
	}

	@Test(priority = 3, enabled = true)
	public void IndividualStocks() throws InterruptedException, IOException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "Individual portfolio Stocks Tab";
		logTableStart(tableName);
		for (int i = 1; i <= loop; i++) {
			portfolio.StocksTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				//portfolio.script.isDisplayed();
				portfolio.marginpledge.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			} finally {
				portfolio.MFTabPortfolio.click();
			}
		}
		logTableEnd(tableName);
	}
	@Test(priority = 4, enabled = true)
	public void IndividualMF() throws InterruptedException, IOException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "Individual portfolio MF Tab";
		logTableStart(tableName);

		for (int i = 1; i <= loop; i++) {
			try {
				portfolio.MFTabPortfolio.click();
				long startTime = System.currentTimeMillis();
				portfolio.marginpledge.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			} finally {
				portfolio.StocksTabPortfolio.click();
			}
		}
		logTableEnd(tableName);
	}

	// Helper Methods for Logging Tables
	public void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>", true);

	}

	public void logTableRow(String tableName, int iteration, long timeTaken, String status) {
		// Define the color and text styles based on the status
		String statusColor = "";
		String statusTextStyle = "color: white; font-weight: bold;";

		if ("Fail".equalsIgnoreCase(status)) {
			statusColor = "background-color: red;";
		} else if ("Pass".equalsIgnoreCase(status)) {
			statusColor = "background-color: green;";
		}

		Reporter.log("<tr><td>" + iteration + "</td><td>" + timeTaken + "</td><td style='" + statusColor
				+ statusTextStyle + "'>" + status + "</td></tr>", true);
	}

	public void logTableEnd(String tableName) {
		Reporter.log("</table>", true);
	}

	@AfterTest
	public void verify_User_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}

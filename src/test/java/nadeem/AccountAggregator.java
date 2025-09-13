package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.Portfolio;
import pageobjects.ResusableMethods;
import utils.Commons;

public class AccountAggregator {
	// Use NadeemId=MOQ15960

	AndroidDriver Driver;
	String status;
	ExtentReports extent;
	ExtentTest test;
	TableLogger logger = new TableLogger();
	WebDriverWait wait;

	@BeforeTest
	public void launchapp() throws MalformedURLException, IOException, InterruptedException {
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
		System.out.println("App launch request sent. Waiting for verification...");
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait = new WebDriverWait(Driver, Duration.ofSeconds(10));

		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {

		LoginPage loginpage = new LoginPage(Driver);
		try {
			if (loginpage.loginButton.isDisplayed()) {
				// Manual Login
				loginpage.loginButton.click();
				Thread.sleep(1000);
				loginpage.userID.click();
				loginpage.userID.sendKeys(Commons.getGlobalPropertiesValue("userId"));
				Driver.hideKeyboard();
				loginpage.nextbutton.click();
				Thread.sleep(3000);
				loginpage.passwordTextField.click();
				loginpage.passwordTextField.sendKeys(Commons.getGlobalPropertiesValue("password"));
				Driver.hideKeyboard();
				loginpage.loginButton.click();
				Thread.sleep(5000);
				loginpage.dobTextField.get(0).click();
				loginpage.dobTextField.get(0).sendKeys(Commons.getGlobalPropertiesValue("dob"));
				Driver.hideKeyboard();
				loginpage.confirmDobButton.click();
				Thread.sleep(3000);
				loginpage.exploreTheAppButton.click();
				Thread.sleep(5000);
				loginpage.iUnderstandRddButton.click();
				Thread.sleep(3000);
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			Thread.sleep(5000);
		}
	}

	@Test(priority = 2)
	public void accountmainmethod() {
		logger.logTableStart("Execution Report");

		angeltest();
		growwtest();
		pulsebroker();
		miraebroker();
		others();
		upstox();

		logger.logTableEnd();
	}

	public void angeltest() {
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		homepage.portfolioBottombar.click();
		portfolio.externalTab.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.angelOneLimited.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Angel one Broker", status, endTime - startTime);
		}
	}

	public void growwtest() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		try {
			portfolio.groww.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Groww Broker", status, endTime - startTime);
		}
	}

	public void pulsebroker() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		try {
			portfolio.marketPulseSecurities.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Pulse Broker", status, endTime - startTime);
		}
	}

	public void miraebroker() {
		ResusableMethods.swipeCorinates(Driver, 479, 1893, 479, 900, 1);
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		try {
			portfolio.miraeAssetCapitalMarkets.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mirae Broker", status, endTime - startTime);
		}
	}

	public void others() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		try {
			portfolio.othersTab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("other Broker", status, endTime - startTime);
		}
	}

	public void upstox() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		try {
			portfolio.upstox.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("upstox Broker", status, endTime - startTime);
		}
	}

	// Helper Methods for Logging Tables
	public class TableLogger {
		private int rowCounter = 0; // To keep track of the serial number

		// Start the table with a title
		public void logTableStart(String tableName) {
			Reporter.log("<h3>" + tableName + "</h3>", true);
			Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status, long timeTaken) {
			rowCounter++; // Increment the serial number

			// Define the color and text styles based on the status
			String statusColor = "";
			String statusTextStyle = "color: white; font-weight: bold;";

			if ("Fail".equalsIgnoreCase(status)) {
				statusColor = "background-color: red;";
			} else if ("Pass".equalsIgnoreCase(status)) {
				statusColor = "background-color: green;";
			}

			// Create the row with the styled status cell
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td style='" + statusColor
					+ statusTextStyle + "'>" + status + "</td><td>" + timeTaken + "</td></tr>", true);
		}

		// End the table
		public void logTableEnd() {
			Reporter.log("</table>", true);
		}

	}

	@AfterTest
	public void Verify_user_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}

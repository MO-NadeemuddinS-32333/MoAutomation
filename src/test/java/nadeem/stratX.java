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

import io.appium.java_client.android.AndroidDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.ResusableMethods;
import pageobjects.StratX;
import utils.Commons;

public class stratX {
	// User VandaID Y05120
	AndroidDriver Driver;
	String status;
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
		capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package_pilot"));
		capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("noReset", true);

		Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//		DriverFactory.addDriver(Driver);
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
	public void mainMethod() {
		logger.logTableStart("Execution Report");

		stratxTab();
		hideicon();
		strategytradesummarydropdown();
		strategytradesummaryopendropdown();
		subscribedstrategydetails();
		popularstategyviewall();
		strategiesbottombar();
		sxtradesbottombar();
		sxhistorybottombar();
		stratxexplorebottombar();
		premiumstrategiesviewall();

		logger.logTableEnd();
	}

	public void stratxTab() {
		HomePage homePage = new HomePage(Driver);
		StratX stratx = new StratX(Driver);
		homePage.stratXtab.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.bookedEarnings.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("StatX Tab Redirection", status, endTime - startTime);
		}

	}

	public void hideicon() {
		StratX stratx = new StratX(Driver);
		stratx.hideicon.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.hideddetails.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			stratx.hideicon.click();
			logger.logTableRow("Strategy Trade details Hide Icon", status, endTime - startTime);
		}
	}

	public void strategytradesummarydropdown() {
		StratX stratx = new StratX(Driver);
		stratx.tradestatsdropdown.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.tradedetails.isDisplayed();
			status = "Fail";
		} catch (Exception e) {
			// test case is to verify element is not displayed therefore status is set to
			// pass here
			status = "Pass";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Strategy Trade Summary close Dropdown", status, endTime - startTime);
		}
	}

	public void strategytradesummaryopendropdown() {
		StratX stratx = new StratX(Driver);
		stratx.tradestatsdropdown.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.tradedetails.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Strategy Trade Summary open Dropdown", status, endTime - startTime);
		}
	}

	public void subscribedstrategydetails() {
		StratX stratx = new StratX(Driver);
		stratx.bookedEarnings.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.strategysubscriptionpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Subscribed Strategy Trade Details", status, endTime - startTime);
		}
	}

	public void popularstategyviewall() {
		StratX stratx = new StratX(Driver);
		stratx.viewAllButton.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.allpopularstategies.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Popular Strategy View All", status, endTime - startTime);
		}
	}

	public void strategiesbottombar() {
		StratX stratx = new StratX(Driver);
		stratx.strategiesbottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.strategysubscriptionpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Strategies Bottom Bar redirection", status, endTime - startTime);
		}
	}

	public void sxtradesbottombar() {
		StratX stratx = new StratX(Driver);
		stratx.sxTradesbottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.tradehistorypage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("SX Trades Bottom Bar redirection", status, endTime - startTime);
		}
	}

	public void sxhistorybottombar() {
		StratX stratx = new StratX(Driver);
		stratx.sxHistorybottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.sxhistorypage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("SX History Bottom Bar redirection", status, endTime - startTime);
		}
	}

	public void stratxexplorebottombar() {
		StratX stratx = new StratX(Driver);
		stratx.explorebottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.bookedEarnings.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("StratX Explore Bottom Bar redirection", status, endTime - startTime);
		}
	}

	public void premiumstrategiesviewall() {
		StratX stratx = new StratX(Driver);
		ResusableMethods.verticalswipetillElement(Driver, stratx.bottomviewall, 0, 5, 480, 1981, 489);
		stratx.bottomviewall.click();
		long startTime = System.currentTimeMillis();
		try {
			stratx.strategysubscriptionpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Premium Strategies bottom View All", status, endTime - startTime);
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

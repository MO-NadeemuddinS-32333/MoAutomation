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

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.Portfolio;
import pageobjects.ResusableMethods;
import utils.Commons;

public class AppRegressionII {
//User VandaID Y05120
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
		capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package_pilot"));
		capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("noReset", true);

		Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		DriverFactory.addDriver(Driver);
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
	public void mainMethod() throws InterruptedException {
		logger.logTableStart("Execution Report");

		homeportfolioredirection();
		edisredirection();
		portfolioscriptsearch();
		gainers();
		loser();
		sortZA();
		sortAZ();
		homemfredirection();
		editindices();

		logger.logTableEnd();
	}

	public void homeportfolioredirection() {
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		homepage.homeTabHeader.click();
		homepage.stocksbutton.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.StocksTabPortfolio.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Stocks Button homescreen Redirection", status, endTime - startTime);
		}
	}

	public void edisredirection() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.authorisedsell.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.edispage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Authorised Sell EDIS Redirection", status, endTime - startTime);
		}

	}

	public void portfolioscriptsearch() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.searchicon.click();
		portfolio.searchtextbox.click();
		portfolio.searchtextbox.sendKeys("HAZOOR");
		long startTime = System.currentTimeMillis();
		try {
			portfolio.hazoorSearchResult.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.hideKeyboard();
			for (int i = 0; i < 2; i++) {
				portfolio.searchclose.click();
				Thread.sleep(300);
			}
			logger.logTableRow("Portfolio Script Search", status, endTime - startTime);
		}
	}

	public void gainers() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.gainers.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.gainerverification.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio Gainers", status, endTime - startTime);
		}

	}

	public void loser() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.loser.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.loserverification.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			// Driver.navigate().back();
			logger.logTableRow("Portfolio Losers", status, endTime - startTime);
		}
	}

	public void sortZA() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.kababmenu.click();
		portfolio.sortZA.click();
		portfolio.applybutton.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.sortedresultZA.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio Sort Z-A", status, endTime - startTime);
		}
	}

	public void sortAZ() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.kababmenu.click();
		portfolio.sortAZ.click();
		portfolio.applybutton.click();
		long startTime = System.currentTimeMillis();
		try {
			portfolio.sortedresultAZ.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Portfolio Sort A-Z", status, endTime - startTime);
		}

	}

	public void homemfredirection() {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomePage = new MfHomePage(Driver);
		homepage.mfinvestButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomePage.dematHoldingbutton.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("MF Invest Button homescreen Redirection", status, endTime - startTime);
		}
	}

	public void editindices() {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.horizontalSwipetillElement(Driver, homepage.editindices, 0, 5, 991, 109, 626);
		homepage.editindices.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.niftycpse, 0, 5, 470, 1911, 830);
		homepage.indicescheckbox.click();
		long startTime = System.currentTimeMillis();
		try {
			homepage.indiceserrormessage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("6 indices Edit Indices Error message", status, endTime - startTime);
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

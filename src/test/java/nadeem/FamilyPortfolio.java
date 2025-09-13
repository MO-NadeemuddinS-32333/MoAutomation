package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import utils.Commons;

public class FamilyPortfolio {
//Use ImranId=EMUM187598
	
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
	public void familyportfoliosanity() throws InterruptedException {
		logger.logTableStart("Execution Report");

		familyportfolioCTAinPortfolio();
		HeadofFamilyTab();
		stocksTab();
		abhinavID();
		abhinavIDStocksTab();
		abhinavIDMFTab();
		abhinavIDBtxTab();

		logger.logTableEnd();
	}

	public void familyportfolioCTAinPortfolio() {
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.portfolioBottombar.click();
		portfolio.familyPortfolioIcon.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.familySummaryTab));
			portfolio.familySummaryTab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio button", status, endTime - startTime);
		}

	}

	public void HeadofFamilyTab() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.familySummaryTab.click();
		portfolio.HOFname.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.portfolioNews));
			portfolio.HOFname.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Imran Head of Family All Tab", status, endTime - startTime);
		}
	}

	public void stocksTab() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.StocksTabPortfolio.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.imranportfoliostocks));
			portfolio.imranportfoliostocks.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Imran HOF Portfolio stocks Tab", status, endTime - startTime);
		}
	}

	public void abhinavID() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.imranIDportfolio.click();
		portfolio.AbhinavIDportfolio.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.abhinavidalltab));
			portfolio.abhinavidalltab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Abhinav agarwal ID All Tab", status, endTime - startTime);
		}
	}

	public void abhinavIDStocksTab() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.StocksTabPortfolio.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.imranportfoliostocks));
			portfolio.imranportfoliostocks.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Abhinav Agarwal ID Portfolio stocks Tab", status, endTime - startTime);
		}
	}

	public void abhinavIDMFTab() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.MFTabPortfolio.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.portfoliomfpage));
			portfolio.portfoliomfpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Abhinav Agarwal ID Portfolio MF Tab", status, endTime - startTime);
		}
	}

	public void abhinavIDBtxTab() {
		Portfolio portfolio = new Portfolio(Driver);
		portfolio.BasketTabPortfolio.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.btxpage));
			portfolio.btxpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Family Portfolio Abhinav Agarwal ID Portfolio BTX Tab", status, endTime - startTime);
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
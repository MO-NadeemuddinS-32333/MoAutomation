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
import pageobjects.ResusableMethods;
import utils.Commons;

public class MutualFunds {
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
	public void MutualfundSanity() throws InterruptedException {
		logger.logTableStart("Execution Report");

		TestViewDematHolding();
		Currentvalue();
		InvestButton();
		SipPlaceOrder();
		LumpsumPlaceOrder();
		OrderBookBuy();
		OrderBookSips();
		OrderBookSell();
		OrderBookSwitch();
		OrderBookSwp();
		OrderBookSTP();
		MutualfundsPortfolio();
		holdingSummaryOverview();
		holdingSummaryYourRetrun();
		holdingSummaryTransactions();
		MfwithdrawOneTime();
		withdrawSWP();
		switchFullAmount();
		switchSTP();

		logger.logTableEnd();
	}

	public void TestViewDematHolding() {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.swipeCorinates(Driver, 906, 332, 261, 332, 1);
		homepage.MfTab.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.dematHoldingbutton.click();
			mfhomepage.marketValuetext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund View Demat Holdings Returns", status, endTime - startTime);
		}
	}

	public void Currentvalue() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.currentvalue.click();
			mfhomepage.overviewholdingSummarytext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Current Value", status, endTime - startTime);
		}
	}

	public void InvestButton() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.InvestButton.click();
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(500);
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Invest Button", status, endTime - startTime);
		}
	}

	public void SipPlaceOrder() throws InterruptedException {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.MFviewallbutton.click();
		mfhomepage.schemeMF.click();
		mfhomepage.InvestNowButton.click();
		Thread.sleep(1000);
		ResusableMethods.tapWithActions(Driver, 999, 901);
		mfhomepage.netbankingbutton.click();
		mfhomepage.paybutton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.paymenttext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			mfhomepage.yescanceltransaction.click();
			Driver.navigate().back();
			logger.logTableRow("Mutual Fund SIP Place Order", status, endTime - startTime);
		}
	}

	public void LumpsumPlaceOrder() throws InterruptedException {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.schemeMF.click();
		mfhomepage.InvestNowButton.click();
		Thread.sleep(1000);
		ResusableMethods.tapWithActions(Driver, 999, 880);
		mfhomepage.netbankingbutton.click();
		mfhomepage.paybutton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.paymenttext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			mfhomepage.yescanceltransaction.click();
			Driver.navigate().back();
			Driver.navigate().back();
			logger.logTableRow("Mutual Fund Lumpsum Place Order", status, endTime - startTime);
		}
	}

	public void OrderBookBuy() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrdersButtonBottom.click();
		mfhomepage.OrderBookBuyButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.BuyTransactionscreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Order Book Buy", status, endTime - startTime);
		}
	}

	public void OrderBookSips() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrderBookSipButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.SipTransactionScreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Order Book Sips", status, endTime - startTime);
		}
	}

	public void OrderBookSell() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrderBookSellButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.SellTransactionScreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Order Book Sell", status, endTime - startTime);
		}
	}

	public void OrderBookSwitch() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrderBookSwitchButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.SwitchTransactionScreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Order Book Switch", status, endTime - startTime);
		}
	}

	public void OrderBookSwp() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrderBookSwpButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.SwpTransactionScreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund Order Book SWP", status, endTime - startTime);
		}
	}

	public void OrderBookSTP() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.OrderBookStpButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.StpTransactionScreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Mutual Fund Order Book STP", status, endTime - startTime);
		}

	}

	public void MutualfundsPortfolio() {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		homepage.portfolioBottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.overviewholdingSummarytext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Funds Portfolio", status, endTime - startTime);
		}
	}

	public void holdingSummaryOverview() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.mftabportfolio.click();
		mfhomepage.MfHolding.click();
		mfhomepage.investedAmountText.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.mfholdingoverviewscreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Funds Holdings Summary Overview", status, endTime - startTime);
		}
	}

	public void holdingSummaryYourRetrun() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.yourreturnstab.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.Returncalculatortext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			// Driver.navigate().back();
			logger.logTableRow("Mutual Funds Holdings Summary Your Returns", status, endTime - startTime);
		}
	}

	public void holdingSummaryTransactions() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.transactionstab.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.alltransactionstext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Funds Holdings Summary Transactions", status, endTime - startTime);
		}
	}

	public void MfwithdrawOneTime() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.withdrawbutton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.withdrawOneTimeFullAmountButton.click();
			mfhomepage.setupWithdrawalButton.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Mutual Funds Holdings Withdraw OneTime", status, endTime - startTime);
		}
	}

	public void withdrawSWP() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.withdrawbutton.click();
		mfhomepage.withdrawswpButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.setupWithdrawalButton.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Mutual Funds Holdings Withdraw SWP", status, endTime - startTime);
		}
	}

	public void switchFullAmount() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.switchButton.click();
		mfhomepage.switchFullAmountButton.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.selectFundToSwitchText.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Mutual Funds Holdings Switch FullAmount", status, endTime - startTime);
		}
	}

	public void switchSTP() {
		MfHomePage mfhomepage = new MfHomePage(Driver);
		mfhomepage.SwitchSTP.click();
		long startTime = System.currentTimeMillis();
		try {
			mfhomepage.selectFundToSwitchText.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Mutual Funds Holdings switch STP", status, endTime - startTime);
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

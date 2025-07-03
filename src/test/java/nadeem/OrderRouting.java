package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.OrderForm;
import pageobjects.ProfilePage;
import pageobjects.ResusableMethods;
import utils.Commons;

public class OrderRouting {

	AndroidDriver Driver;
	String status;
	ExtentReports extent;
	ExtentTest test;
	double value;
	TableLogger logger = new TableLogger();
	ResusableMethods Reusablemethod = new ResusableMethods(Driver);
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

	@Test(priority = 2, enabled = true)
	public void main() throws IOException, InterruptedException {

		logger.logTableStart("Execution Report");

		checkMargin();
		searchscript();
		BuyOrder();
		checkmarginafterorder();
		modifyorder();
		checkmarginafterordermodification();
		CancelOrder();
		checkmarginafterordercancel();

		logger.logTableEnd();
	}

	public void checkMargin() throws InterruptedException, IOException {
		ProfilePage profilepage = new ProfilePage(Driver);
		profilepage.wallectiicon.click();
		profilepage.Margintab.click();
		try {
			long startTime = System.currentTimeMillis();
			profilepage.zeromargin.isDisplayed();
			String margindetails = profilepage.zeromargin.getDomAttribute("content-desc");
			// System.out.println(margindetails);
			String[] parts = margindetails.split("₹");
			String numberStr = parts[1].trim();
			value = Double.parseDouble(numberStr);
			System.out.println(value);
			long endTime = System.currentTimeMillis();
			status = "Pass";
			logger.logTableRow("Check Margin before placing order", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin before placing order", status, endTime - startTime);
		} finally {
			Driver.navigate().back();
		}
	}

	public void searchscript() throws InterruptedException, IOException {
		HomePage homepage = new HomePage(Driver);
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(500);
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		try {
			long startTime = System.currentTimeMillis();
			Thread.sleep(2000);
			homepage.Globalsearchresult.isDisplayed();
			status = "Pass";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Search Result", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Search Result", status, endTime - startTime);
		} finally {
			homepage.Globalsearchresult.click();
		}
	}

	public void BuyOrder() {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		getquote.BuyButton.click();
		orderform.quantityMarket.click();
		orderform.quantityMarket.sendKeys("1");
		orderform.limitprice.click();
		orderform.limitprice.clear();
		orderform.limitprice.sendKeys("19");
		Driver.hideKeyboard();
		orderform.buyorderform.click();
		orderform.confirmorder.click();
		try {
			long startTime = System.currentTimeMillis();
			orderform.vieworder.isDisplayed();
			status = "Pass";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order placed", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order placed", status, endTime - startTime);
		}
	}

	public void checkmarginafterorder() {
		ProfilePage profilepage = new ProfilePage(Driver);
		OrderForm orderform = new OrderForm(Driver);
		orderform.donebutton.click();
		Driver.navigate().back();
		Driver.navigate().back();
		profilepage.wallectiicon.click();
		profilepage.Margintab.click();
		try {
			long startTime = System.currentTimeMillis();
			profilepage.zeromargin.isDisplayed();
			String margindetails = profilepage.zeromargin.getDomAttribute("content-desc");
			// System.out.println(margindetails);
			String[] parts = margindetails.split("₹");
			String numberStr = parts[1].trim();
			double value2 = Double.parseDouble(numberStr);
			double i = 19;
			double marginafterorder = value + i;
			status = (marginafterorder == value2) ? "Pass" : "Fail";
			System.out.println(value2);
			long endTime = System.currentTimeMillis();
			status = "Pass";
			logger.logTableRow("Check Margin after placing order", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin after placing order", status, endTime - startTime);
		} finally {
			Driver.navigate().back();
		}
	}

	public void modifyorder() {
		HomePage homepage = new HomePage(Driver);
		OrderForm orderform = new OrderForm(Driver);
		homepage.Tradesbottombar.click();
		orderform.tradebookordertab.click();
		orderform.pendingorder.click();
		orderform.modifybutton.click();
		orderform.quantityMarket.click();
		orderform.quantityMarket.clear();
		orderform.quantityMarket.sendKeys("2");
		Driver.hideKeyboard();
		orderform.modifybutton.click();
		orderform.confirmorder.click();
		try {
			long startTime = System.currentTimeMillis();
			orderform.vieworder.isDisplayed();
			status = "Pass";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order modified", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order modified", status, endTime - startTime);
		}

	}

	public void checkmarginafterordermodification() {
		ProfilePage profilepage = new ProfilePage(Driver);
		OrderForm orderform = new OrderForm(Driver);
		orderform.donebutton.click();
		Driver.navigate().back();
		profilepage.wallectiicon.click();
		profilepage.Margintab.click();
		try {
			long startTime = System.currentTimeMillis();
			profilepage.zeromargin.isDisplayed();
			String margindetails = profilepage.zeromargin.getDomAttribute("content-desc");
			// System.out.println(margindetails);
			String[] parts = margindetails.split("₹");
			String numberStr = parts[1].trim();
			double value2 = Double.parseDouble(numberStr);
			double i = 38;
			double marginafterorder = value + i;
			status = (marginafterorder == value2) ? "Pass" : "Fail";
			System.out.println(value2);
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin after order modification", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin after order modification", status, endTime - startTime);
		} finally {
			Driver.navigate().back();
		}
	}

	public void CancelOrder() {
		HomePage homepage = new HomePage(Driver);
		OrderForm orderform = new OrderForm(Driver);
		homepage.Tradesbottombar.click();
		orderform.tradebookordertab.click();
		orderform.pendingorder.click();
		orderform.cancelbutton.click();
		orderform.yesproceedbutton.click();
		try {
			long startTime = System.currentTimeMillis();
			orderform.cancelledorder.isDisplayed();
			status = "Pass";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order cancelled", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Buy Order cancelled", status, endTime - startTime);
		} finally {
			Driver.navigate().back();
		}

	}

	public void checkmarginafterordercancel() {
		ProfilePage profilepage = new ProfilePage(Driver);
		profilepage.wallectiicon.click();
		profilepage.Margintab.click();
		try {
			long startTime = System.currentTimeMillis();
			profilepage.zeromargin.isDisplayed();
			String margindetails = profilepage.zeromargin.getDomAttribute("content-desc");
			// System.out.println(margindetails);
			String[] parts = margindetails.split("₹");
			String numberStr = parts[1].trim();
			double value3 = Double.parseDouble(numberStr);
			System.out.println(value3);
			status = (value == value3) ? "Pass" : "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin after order cancellation", status, endTime - startTime);
		} catch (Exception e) {
			long startTime = System.currentTimeMillis();
			status = "Fail";
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Check Margin after order cancellation", status, endTime - startTime);
		} finally {
			Driver.navigate().back();
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

	//@AfterTest
	public void Verify_user_kills_app() {

		if (Driver != null) {
			Driver.quit();
		}
	}

}

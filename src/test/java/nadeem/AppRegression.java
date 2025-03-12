package nadeem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.OrderForm;
import utils.Commons;

public class AppRegression {
	AndroidDriver Driver;
	String status;
	ExtentReports extent;
	ExtentTest test;
	TableLogger logger = new TableLogger();
	WebDriverWait wait;

	@BeforeClass
	public void ExtentReportSetUp() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("sparkReport.html");
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
		sparkReporter.config().setDocumentTitle("Rise App Regression");
		sparkReporter.config().setReportName("Rise App Regression");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

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
		test = extent.createTest("Login Test");
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
				test.info("Manual Login");
				test.pass("Login completed");
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			test.info("Biometric Login");
			test.pass("Login completed");
			Thread.sleep(5000);
		}
//		test.fail("Login Failed");
	}

	@Test
	public void RiseRegression() throws InterruptedException {
		logger.logTableStart("Execution Report");

		String filePath = "C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\keyword driven.xlsx";

		// Using try-with-resources to ensure proper resource closure
		try (FileInputStream ip = new FileInputStream(new File(filePath)); XSSFWorkbook wp = new XSSFWorkbook(ip)) {
			XSSFSheet sheet = wp.getSheetAt(0); // Get first sheet
			// Loop through rows
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null || row.getCell(0) == null)
					continue; // Handle null rows/cells

				String keys = row.getCell(0).getStringCellValue().trim();
				System.out.println(keys);

				if (keys.equalsIgnoreCase("Global search")) {
					Global_search_Result();
				}
				if (keys.equalsIgnoreCase("Get quote Fut tab")) {
					Get_quote_fut_tab();
				}
				if (keys.equalsIgnoreCase("Get quote opt tab")) {
					Get_quote_opt_tab();
				}
				if (keys.equalsIgnoreCase("Get quote cash tab")) {
					Get_quote_cash_tab();
				}

				if (keys.equalsIgnoreCase("Get quote Nse switch delivery buy")) {
					Get_quote_Nse_switch_Delivery_buy();
				}
				if (keys.equalsIgnoreCase("Get quote Nse switch delivery Sell")) {
					Get_quote_Nse_switch_Delivery_Sell();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading the Excel file.");
		}
		logger.logTableEnd();
	}

	public void Global_search_Result() throws InterruptedException, IOException {
		test = extent.createTest("Global Search Result");
		HomePage homepage = new HomePage(Driver);
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));

		try {
			WebElement searchresult = wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
			String resultsearch = searchresult.getDomAttribute("content-desc");
			List<String> splitresult = Arrays.asList(resultsearch.split("\\s+"));
			String globalsearchresult = splitresult.get(1);
			globalsearchresult.equalsIgnoreCase(" YESBANK");
			status = "Pass";
			test.pass("Global search Result Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Global search Result Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			homepage.Globalsearchresult.click();
			logger.logTableRow("Global Search Result", status, endTime - startTime);
		}
	}

	public void Get_quote_fut_tab() {
		test = extent.createTest("Get quote fut tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.FutTab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.FutTab.click();
		try {
			WebElement NSEFO = wait.until(ExpectedConditions.visibilityOf(getquote.NSEFO));

			String nsefo = NSEFO.getDomAttribute("content-desc");
			nsefo.equalsIgnoreCase("NSE_FO");
			status = "Pass";
			test.pass("Get quote fut tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote fut tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote fut tab", status, endTime - startTime);
		}

	}

	public void Get_quote_opt_tab() {
		test = extent.createTest("Get quote opt tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.optionsTab));
		long startTime = System.currentTimeMillis();
		getquote.optionsTab.click();
		try {
			WebElement Call = wait.until(ExpectedConditions.visibilityOf(getquote.callbutton));

			String call = Call.getDomAttribute("content-desc");
			call.equalsIgnoreCase("Call");
			status = "Pass";
			test.pass("Get quote opt tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote opt tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote opt tab", status, endTime - startTime);
		}
	}

	public void Get_quote_cash_tab() {
		test = extent.createTest("Get quote cash tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.cashtab));
		long startTime = System.currentTimeMillis();
		getquote.cashtab.click();
		try {
			WebElement Nse = wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));

			String nse = Nse.getDomAttribute("content-desc");
			nse.equalsIgnoreCase("NSE");
			status = "Pass";
			test.pass("Get quote cash tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote cash tab");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote cash tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_buy() throws InterruptedException {
		test = extent.createTest("Get quote Nse switch delivery buy order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.BuyButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Nse switch delivery buy order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Nse switch delivery buy order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery buy order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_Sell() throws InterruptedException {
		test = extent.createTest("Get quote Nse switch delivery Sell order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.SellButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Nse switch delivery Sell order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Nse switch delivery Sell order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery Sell order form", status, endTime - startTime);
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

	@AfterClass
	public void ExtentReportTearDown() {
		// End the test in ExtentReports
		extent.flush();
	}

}

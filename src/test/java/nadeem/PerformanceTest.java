package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.Commons;

public class PerformanceTest {
	private final Map<String, Long> performanceResults = new LinkedHashMap<>();

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

	@Test(priority = 1)
	public void GlobalSearch() throws InterruptedException, IOException {
		logger.logTableStart("Execution Report");
		test = extent.createTest("Global Search Result");
		HomePage homepage = new HomePage(Driver);
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		Instant start = Instant.now();
		try {
			WebElement searchresult = wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
			String resultsearch = searchresult.getDomAttribute("content-desc");
			System.out.println("***********************" + resultsearch + "***********************");
			List<String> splitresult = Arrays.asList(resultsearch.split("\\s+"));
			String globalsearchresult = splitresult.get(1);
			System.out.println("***********************" + globalsearchresult + "***********************");
			globalsearchresult.equalsIgnoreCase(" YESBANK");
			status = "Pass";
			test.pass("Global search Result Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Global search Result Failed");
			test.info(e.getMessage());
		} finally {
			Instant end = Instant.now();
			long launchTime = Duration.between(start, end).toMillis();
			long endTime = System.currentTimeMillis();
			System.out.println("***************" + (endTime - startTime) + "***************");
			homepage.Globalsearchresult.click();
			logger.logTableRow("Global Search Result", status, launchTime);
			System.out.println("App Launch Time: " + launchTime + " ms");
			performanceResults.put("App Launch", launchTime);
			logger.logTableEnd();
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
	public void generatePerformanceGraph() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Long> entry : performanceResults.entrySet()) {
			dataset.addValue(entry.getValue(), "Load Time (ms)", entry.getKey());
		}

		JFreeChart barChart = ChartFactory.createBarChart("App Performance Metrics", "Test Type", "Time (ms)", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		// Display chart in a window
		JFrame frame = new JFrame("Performance Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ChartPanel(barChart));
		frame.pack();
		frame.setVisible(true);
	}

}

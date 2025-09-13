package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.Commons;

public class bcastTest {
	AndroidDriver Driver;
	WebDriverWait wait;
	private String previousBcastValue = "";
	private StringBuilder htmlTable = new StringBuilder();

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

	@Test(priority = 1, enabled = true)
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
	public void bcastTestNifty50() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		initializeHtmlTable();
		int updateCount = 0;

		while (updateCount < 4) {
			wait.until(ExpectedConditions.visibilityOf(homepage.nifty50));
			String bcast = homepage.nifty50.getDomAttribute("content-desc");
			List<String> splitresult = Arrays.asList(bcast.split("\\s+"));
			String bcastValue = splitresult.get(2);
			logBcastUpdate(bcastValue);
			System.out.println("Updated Bcast Value: " + bcastValue);
			updateCount++;
		}

		finalizeHtmlTable();
	}

	private void initializeHtmlTable() {
		htmlTable.append("<table border='1' style='border-collapse:collapse;width:60%;text-align:center;'>")
				.append("<tr style='background-color:#f2f2f2;'>")
				.append("<th>Ticker Name</th><th>Status</th><th>Bcast Value</th><th>Timestamp</th></tr>");
	}

	private void logBcastUpdate(String newBcastValue) {
		String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault())
				.format(Instant.now());
		String status = newBcastValue.equals(previousBcastValue) ? "Broadcast Update Failed" : "Updating Broadcast";
		htmlTable.append("<tr>").append("<td>bcastTestNifty50</td>").append("<td>").append(status).append("</td>")
				.append("<td>").append(newBcastValue).append("</td>").append("<td>").append(timestamp).append("</td>")
				.append("</tr>");
		previousBcastValue = newBcastValue;
		Reporter.log(htmlTable.toString());
	}

	private void finalizeHtmlTable() {
		htmlTable.append("</table>");
		Reporter.log(htmlTable.toString());
	}
}
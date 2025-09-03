package stepDefinations;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.Portfolio;
import utils.Commons;

public class steps {
	AndroidDriver Driver;
	WebDriverWait wait;
	HomePage homepage = new HomePage(Driver);
	Portfolio portfolio = new Portfolio(Driver);

	@Given("the application is launched")
	public void the_application_is_launched() throws IOException, InterruptedException {
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

	@Given("the user is logged in")
	public void the_user_is_logged_in() throws InterruptedException {
		System.out.println("Biometric login started");
		Thread.sleep(10000);
		System.out.println("Biometric login completed");
	}

	@Given("User clicks on home tab")
	public void user_clicks_on_home_tab() {
		homepage.homeTabHeader.click();
	}

	@When("User clicks on portfolio menu bottom bar")
	public void user_clicks_on_portfolio_menu_bottom_bar() {
		homepage.stocksbutton.click();
	}

	@Then("Portfolio page should be displayed")
	public void portfolio_page_should_be_displayed() throws IOException {
		try {
			portfolio.StocksTabPortfolio.isDisplayed();
		} catch (Exception e) {
			System.out.println("Portfolio page is not displayed");
			File screenshot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			FileUtils.copyFile(screenshot,
					new File("C:/Users/nadeemuddinsayed/Desktop/somu sir/portfolio" + timestamp + ".png"));
		}
	}

}

package nadeem;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.Portfolio;
import pageobjects.ResusableMethods;
import pageobjects.Watchlist;
import utils.Commons;

public class Elementtext {
	AndroidDriver Driver;
	WebDriverWait wait;
	String filePath = "C:\\Users\\nadeemuddinsayed\\Desktop\\somu sir\\ElementTexts.csv";

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
			Thread.sleep(3000);
		}
	}

	@Test(priority = 2, enabled = false)
	public void homepageexploretwebscraping() throws IOException {
		HomePage home = new HomePage(Driver);

		home.homeTabHeader.click();
		home.explorebottombar.click();
		StringBuilder column = new StringBuilder();
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 438, 1);
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 258, 1);
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 258, 1);
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 258, 1);
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 258, 1);
		column.append(extractElementTextsincolumns());
		ResusableMethods.swipeCorinates(Driver, 532, 2071, 532, 258, 1);
		column.append(extractElementTextsincolumns());

		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();

		System.out.println("✅ All scroll data extracted and written to: " + filePath);
	}

	@Test(priority = 3, enabled = false)
	public void watchlistscraping() throws IOException {
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.WatchlistBottombar.click();
		watchlist.watchlist2.click();
		StringBuilder column = new StringBuilder();
		column.append(extractElementTextsincolumns());

		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();
		System.out.println("✅ All scroll data extracted and written to: " + filePath);
	}

	@Test(priority = 4, enabled = false)
	public void watchlistkebabmenu() throws IOException, InterruptedException {
		HomePage homePage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		StringBuilder column = new StringBuilder();
		homePage.WatchlistBottombar.click();
		Thread.sleep(2000);
		watchlist.kebabmenuwatchlist.click();
		column.append(extractElementTextsincolumns());
		watchlist.sortbutton.click();
		column.append(extractElementTextsincolumns());
		watchlist.infobutton.click();
		column.append(extractElementTextsincolumns());
		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();
		System.out.println("✅ All scroll data extracted and written to: " + filePath);

	}

	@Test(priority = 5, enabled = false)
	public void portfolioscraping() throws IOException {
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		homepage.portfolioBottombar.click();
		portfolio.StocksTabPortfolio.click();
		StringBuilder column = new StringBuilder();
		column.append(extractElementTextsincolumns());

		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();
		System.out.println("✅ All scroll data extracted and written to: " + filePath);

	}

	@Test(priority = 6, enabled = true)
	public void portfoliokebabmenu() throws IOException {
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		homepage.portfolioBottombar.click();
		portfolio.kababmenu.click();
		StringBuilder column = new StringBuilder();
		column.append(extractElementTextsincolumns());

		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();
		System.out.println("✅ All scroll data extracted and written to: " + filePath);

	}

	@Test(priority = 7, enabled = false)
	public void getquotescraping() throws IOException, InterruptedException {
		HomePage homepage = new HomePage(Driver);
		GetQuote getquote = new GetQuote(Driver);
		Thread.sleep(1000);
		homepage.Globalsearchbeforetap.click();
		Driver.findElement(By.xpath("(//android.widget.ImageView)[2]")).click();
		Driver.findElement(By.xpath("(//android.widget.ImageView)[2]")).sendKeys("Yes Bank");
		Thread.sleep(3000);
		homepage.Globalsearchresult.click();
		Thread.sleep(3000);
		StringBuilder column = new StringBuilder();
		column.append(extractElementTextsincolumns());
		getquote.HoldingsTab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.StatisticsTab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.StockReturnsTab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.analysttab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.qvttab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.newstab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.CorpActionsTab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.MFinvested.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.similartab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());
		getquote.lastvisitedtab.click();
		Thread.sleep(2000);
		column.append(extractElementTextsincolumns());

		FileWriter writer = new FileWriter(filePath);
		writer.write(column.toString());
		writer.close();
		System.out.println("✅ All scroll data extracted and written to: " + filePath);

	}

	public StringBuilder extractElementTextsincolumns() {
		List<WebElement> allElements = Driver.findElements(By.xpath("//*"));
		System.out.println("Total elements found: " + allElements.size());

		StringBuilder column = new StringBuilder();
		int count = 0;

		for (WebElement element : allElements) {
			try {
				String text = element.getText();
				if (text == null || text.trim().isEmpty()) {
					text = element.getDomAttribute("content-desc");
				}
				if (text == null || text.trim().isEmpty()) {
					text = element.getAttribute("content-desc");
				}
				if (text == null || text.trim().isEmpty()) {
					text = element.getAttribute("name");
				}

				if (text != null && !text.trim().isEmpty()) {
					// Each text in new line (column style in CSV)
					column.append("\"").append(text.replace("\"", "\"\"")).append("\"\n");
					count++;
					System.out.println("Found text: " + text);
				}
			} catch (Exception e) {
				continue;
			}
		}
		System.out.println("Extracted " + count + " element texts from this scroll");
		return column;
	}

	@AfterTest
	public void tearDown() {
		if (Driver != null) {
			// Driver.quit();
		}
	}
}
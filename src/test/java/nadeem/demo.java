package nadeem;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import drivers.DriverFactory;
import io.appium.java_client.AppiumDriver;

public class demo {
	AppiumDriver driver;
	String status;
	ExtentReports extent;
	ExtentTest test;
	WebDriverWait wait;


	@BeforeTest
	public void launchapp() throws MalformedURLException, IOException, InterruptedException {
		System.out.println("Initializing Appium...");
		this.driver = DriverFactory.getDriver();
	}
	
	@Test
	public void test1() {
		System.out.println("Test1 executed");
	}

}

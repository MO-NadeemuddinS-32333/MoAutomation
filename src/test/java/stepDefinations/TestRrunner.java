package stepDefinations;

import java.io.IOException;

import org.testng.annotations.BeforeClass;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepd" }, monochrome = true, plugin = {
		"pretty", "html:target/HtmlReports.html" })
public class TestRrunner extends AbstractTestNGCucumberTests {
	static AndroidDriver Driver;

	@BeforeClass
	public static void setup() throws InterruptedException, IOException {
		DriverFactory.initDriver();
	}

}

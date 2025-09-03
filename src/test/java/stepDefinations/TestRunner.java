package stepDefinations;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"stepDefinations" }, monochrome = true, plugin = { "pretty", "html:target/HtmlReports.html" })
public class TestRunner extends AbstractTestNGCucumberTests{

}

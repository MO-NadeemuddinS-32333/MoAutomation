package stepDefinations;

import drivers.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.Myclass;

public class Stepsd {
	@Given("the application is launchedy")
	public void the_application_is_launched() throws Exception {
		System.out.println("test");
	}

	@Given("User clicks on home taby")
	public void user_clicks_on_home_tab() {
		Myclass homepage = new Myclass(DriverFactory.getDriver());
		homepage.homeTabHeader.click();
	}

	@When("user clicks on stocks button")
	public void user_clicks_on_stocks_button() {
		Myclass homepage = new Myclass(DriverFactory.getDriver());
		homepage.stocksbutton.click();

	}

	@Then("portfolio page should be displayed")
	public void portfolio_page_should_be_displayed() {
		Myclass homepage = new Myclass(DriverFactory.getDriver());
		homepage.StocksTabPortfolio.isDisplayed();
	}

	@When("this is application test")
	public void this_is_application_test() {
		System.out.println("test 1");
	}

	@Then("Lets check its succes")
	public void lets_check_its_succes() {
		System.out.println("test 2");
	}
}

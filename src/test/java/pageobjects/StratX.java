package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class StratX {
	AndroidDriver Driver;

	public StratX(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.view.View[@content-desc=\"View All\"]")
	public WebElement bottomviewall;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'**********')]")
	public WebElement hideddetails;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Booked Earnings')]/android.widget.ImageView")
	public WebElement hideicon;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Explore\"]")
	public WebElement  explorebottombar;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Total Invested Margin in StratX')]/android.widget.ImageView[5]")
	public WebElement sxhistorypage;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"SX History\"]")
	public WebElement sxHistorybottombar;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Orders\"]")
	public WebElement tradehistorypage;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"SX Trades\"]")
	public WebElement sxTradesbottombar;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Strategies\"]")
	public WebElement strategiesbottombar;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'All')]")
	public WebElement allpopularstategies;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"View All\"]")
	public WebElement viewAllButton;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'All')]")
	public WebElement strategysubscriptionpage;
	
	@FindBy(xpath = "//android.view.View[starts-with(@content-desc,'Booked Earnings')]")
	public WebElement bookedEarnings;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Booked Earnings')]/android.view.View[1]")
	public WebElement tradestatsdropdown;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Invested Value')]")
	public WebElement tradedetails;

}

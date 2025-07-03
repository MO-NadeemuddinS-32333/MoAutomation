package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ProfilePage {
	AndroidDriver Driver;

	public ProfilePage(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.widget.FrameLayout[contains(@resource-id,'android:id/content')]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[8]")
	public WebElement zeromargin;
	
	@FindBy(xpath = "//android.widget.FrameLayout[contains(@resource-id,'android:id/content')]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.widget.ImageView[4]")
	public WebElement wallectiicon;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[contains(@class,'android.widget.HorizontalScrollView')]/android.view.View[3]")
	public WebElement Margintab;
	
	@FindBy(xpath = "//android.view.View[contains(@text,'All')]")
	public WebElement contractnotescreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Contract Note\"]")
	public WebElement contractnotebutton;

	@FindBy(xpath = "//android.view.View[contains(@text,'Equity')]")
	public WebElement pnlreportscreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"P&L Report\"]")
	public WebElement plreportbutton;

	@FindBy(xpath = "//android.view.View[@text='Trade History']")
	public WebElement tradehistoryscreen;

	@FindBy(xpath = "//android.widget.TextView[@text='Trade History']")
	public WebElement tradehistorybutton;

	@FindBy(xpath = "//android.view.View[@text='Ledger Report']")
	public WebElement ledgerreportscreen;

	@FindBy(xpath = "//android.widget.TextView[@text='Ledger Report']")
	public WebElement ledgerreportbutton;

	@FindBy(xpath = "//android.widget.TextView[@text='Check Reports Status']")
	public WebElement checkreportstatusbutton;

	@FindBy(xpath = "//android.widget.TextView[@text='Transactions Summary']")
	public WebElement capitalgains;

	@FindBy(xpath = "//android.widget.TextView[@text='Transactions Summary']")
	public WebElement transactionsummarybutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"View Status\"]")
	public WebElement viewstatusbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Get Reports\"]")
	public WebElement getreportsbutton;

	@FindBy(xpath = "//android.widget.TextView[@text='Holding Summary - All Assets']")
	public WebElement ReportsHoldingsummaryallassets;

	@FindBy(xpath = "//android.view.View[contains(@text,'All')]")
	public WebElement allbuttoncheckreportstatus;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Client ID: Y05120')]")
	public WebElement profiledetails;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Trading Account Details\"]")
	public WebElement tradingaccountdetails;

	@FindBy(xpath = "//android.view.View[@content-desc=\"ACTIVATE\"]")
	public WebElement asbaswitch;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Reports\"]")
	public WebElement reportsbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Ledger Report\"]")
	public WebElement ledgerreports;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Back\"]")
	public WebElement openwith;

}

package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class MfHomePage {
	AndroidDriver Driver;

	public MfHomePage(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Mutual Funds')]")
	public WebElement mftabportfolio;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Your Investments details\"]")
	public WebElement mfholdingoverviewscreen;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Invested Amt\"]")
	public WebElement investedAmountText;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'ALL')]")
	public WebElement alltransactionstext;;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Transaction')]")
	public WebElement transactionstab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Returns Calculator\"]")
	public WebElement Returncalculatortext;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Your Return')]")
	public WebElement yourreturnstab;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Individual Net Worth\"]")
	public WebElement overviewholdingSummarytext;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Systematic Transfer Plan(STP)\"]")
	public WebElement SwitchSTP;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Available to Transfer:\"]")
	public WebElement selectFundToSwitchText;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Switch (Full amount)\"]")
	public WebElement switchFullAmountButton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Switch\"]")
	public WebElement switchButton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Systematic Withdraw Plan(SWP)\"]")
	public WebElement withdrawswpButton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Setup your withdrawal\"]")
	public WebElement setupWithdrawalButton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Withdraw One-Time(Full amount)\"]")
	public WebElement withdrawOneTimeFullAmountButton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Withdraw\"]")
	public WebElement withdrawbutton;
	
	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Total Units')]")
	public WebElement MfHolding;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Current Value of MF')]")
	public WebElement MFportfolioScreen;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'STP Transaction')]")
	public WebElement StpTransactionScreen;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"STP\"]")
	public WebElement OrderBookStpButton;
	
	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'SWP Transaction')]")
	public WebElement SwpTransactionScreen;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"SWP\"]")
	public WebElement OrderBookSwpButton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Switch Transaction')]")
	public WebElement SwitchTransactionScreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Switch\"]")
	public WebElement OrderBookSwitchButton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Sell Transaction')]")
	public WebElement SellTransactionScreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Sell\"]")
	public WebElement OrderBookSellButton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'SIP Transaction')]")
	public WebElement SipTransactionScreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"SIP's\"]")
	public WebElement OrderBookSipButton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Buy Transaction')]")
	public WebElement BuyTransactionscreen;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Orders\"]")
	public WebElement OrdersButtonBottom;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Buy\"]")
	public WebElement OrderBookBuyButton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Lumpsum')]")
	public WebElement lumpsumbutton;

	@FindBy(xpath = "//android.widget.Button[@content-desc=\"YES\"]")
	public WebElement yescanceltransaction;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Payment\"]")
	public WebElement paymenttext;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Pay Now\"]")
	public WebElement paybutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Net Banking\"]")
	public WebElement netbankingbutton;

	@FindBy(xpath = "(//android.view.View[@content-desc=\"Invest Now\"])[2]")
	public WebElement InvestNowButton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Min. Investment')]")
	public WebElement schemeMF;

	@FindBy(xpath = "(//android.widget.ImageView[@content-desc=\"View All\"])[1]")
	public WebElement MFviewallbutton;

	@FindBy(xpath = "(//android.view.View[@content-desc='Invest'])[1]")
	public WebElement InvestButton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Holding Summary\"]")
	public WebElement holdingSummarytext;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Current Value')]")
	public WebElement currentvalue;

	@FindBy(xpath = "//android.view.View[@content-desc=\"View Demat Holding\"]")
	public WebElement dematHoldingbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Market Value\"]")
	public WebElement marketValuetext;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'MF')]")
	public WebElement MfTab;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Expand\"]")
	public WebElement MfExpandIcon;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Collapse\"]")
	public WebElement mfCollapseIcon;

	@FindBy(xpath = "(//android.widget.ImageView[@content-desc=\"View All\"])[2]")
	public WebElement MfHomepageCollectionViewAll;

	@FindBy(xpath = "//android.view.View[@content-desc=\"1M\"]")
	public WebElement MfViewAll1mReturns;

	@FindBy(xpath = "//android.view.View[@content-desc=\"3M\"]")
	public WebElement MfViewAll3mReturns;

	@FindBy(xpath = "//android.view.View[@content-desc=\"1Y\"]")
	public WebElement MfViewAll1YReturns;

	@FindBy(xpath = "//android.view.View[@content-desc=\"3Y\"]")
	public WebElement MfViewAll3YReturns;

	@FindBy(xpath = "//android.view.View[@content-desc=\"5Y\"]")
	public WebElement MfViewAll5YReturns;

	@FindBy(xpath = "(//android.widget.ImageView[contains(@content-desc,'1 M Returns')])[1]")
	public WebElement onemReturnsMFVerification;

	@FindBy(xpath = "(//android.widget.ImageView[contains(@content-desc,'3 M Returns')])[1]")
	public WebElement threeMReturnsMFVerification;

	@FindBy(xpath = "(//android.widget.ImageView[contains(@content-desc,'1 Y Returns')])[1]")
	public WebElement oneyearReturnsMFVerification;

	@FindBy(xpath = "(//android.widget.ImageView[contains(@content-desc,'3 Y Returns')])[1]")
	public WebElement threeYearsReturnsMFVerification;

	@FindBy(xpath = "(//android.widget.ImageView[contains(@content-desc,'5 Y Returns')])[1]")
	public WebElement fiveYearsReturnsMFVerification;

}

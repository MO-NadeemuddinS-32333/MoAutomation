package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class Portfolio {
	AndroidDriver Driver;

	public Portfolio(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.view.View[starts-with(@content-desc,'1')]")
	public WebElement sortedresultAZ;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"A-Z\"]")
	public WebElement sortAZ;
	
	@FindBy (xpath = "//android.view.View[starts-with(@content-desc, 'Z')]")
	public WebElement sortedresultZA;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Apply\"]")
	public WebElement applybutton;
	
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Z-A\"]")
	public WebElement sortZA;
	
	@FindBy(xpath = "(//android.widget.ImageView)[6]")
	public WebElement kababmenu;
	
	@FindBy(xpath = "//android.webkit.WebView")
	public WebElement edispage;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Authorised Sell\"]")
	public WebElement authorisedsell;
	
	@FindBy (xpath = "(//android.view.View[contains(@content-desc,'-')])[2]")
	public WebElement loserverification;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Loser')]")
	public WebElement loser;
	
	@FindBy (xpath = "(//android.view.View[contains(@content-desc,'+')])[2]")
	public WebElement gainerverification;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Gainer')]")
	public WebElement gainers;

	@FindBy(xpath = "//android.widget.Button")
	public WebElement searchclose;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'HAZOOR')]")
	public WebElement hazoorSearchResult;

	@FindBy(xpath = "//android.widget.EditText")
	public WebElement searchbox;
	
	@FindBy(xpath = "(//android.widget.ImageView)[7]")
	public WebElement searchtextbox;

	@FindBy(xpath = "(//android.widget.ImageView)[7]")
	public WebElement searchicon;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Upstox\"]")
	public WebElement upstox;

	@FindBy(xpath = "//android.view.View[@content-desc=\"OTHERS\"]")
	public WebElement othersTab;

	@FindBy(xpath = "//android.view.View[@content-desc=\"MIRAE ASSET CAPITAL MARKETS (INDIA) PRIVATE LIMITED                    MIRAE ASSET CAPITAL MARKETS (INDIA) PRIVATE LIMITED\"]")
	public WebElement miraeAssetCapitalMarkets;

	@FindBy(xpath = "//android.view.View[@content-desc=\"MARKET PULSE SECURITIES PRIVATE LIMITED                    MARKET PULSE SECURITIES PRIVATE LIMITED\"]")
	public WebElement marketPulseSecurities;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Groww\"]")
	public WebElement groww;

	@FindBy(xpath = "//android.view.View[@content-desc=\"ANGEL ONE LIMITED\"]")
	public WebElement angelOneLimited;

	@FindBy(xpath = "//android.view.View[@content-desc=\"External\"]")
	public WebElement externalTab;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Scrip')]")
	public WebElement script;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Collapse\"]")
	public WebElement collapseicon;

	@FindBy(xpath = "//android.view.View[@content-desc=\"View Family Portfolio\"]")
	public WebElement familyportfoliosyperlink;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Individual Net Worth\"]")
	public WebElement individualnetworth;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Margin Pledge\"]")
	public WebElement exitall;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Individual Portfolio\"]")
	public WebElement individualtab;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Family Portfolio\"]")
	public WebElement familyportfoliotab;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Family Net Worth\"]")
	public WebElement familynetworth;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Try Again\"]")
	public WebElement tryagain;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Invested Baskets')]")
	public WebElement portfoliobtxscreen;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Overall P&L\"]")
	public WebElement btxpage;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Tap on\"]")
	public WebElement portfoliomfpage;

	@FindBy(xpath = "//android.view.View[@content-desc=\"IMRAN MOHD RAZA KHAN (HOF) (EMUM187598)\"]")
	public WebElement imranIDportfolio;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Net Worth\"]")
	public WebElement abhinavidalltab;

	@FindBy(xpath = "//android.view.View[@content-desc=\"ABHINAV AGARWAL (T025503)\"]")
	public WebElement AbhinavIDportfolio;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Scrip')]")
	public WebElement imranportfoliostocks;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Portfolio News\"]")
	public WebElement portfolioNews;

	@FindBy(xpath = "//android.view.View[@content-desc=\"IMRAN MOHD RAZA KHAN (HOF) (EMUM187598)\"]")
	public WebElement HOFname;

	@FindBy(xpath = "//android.view.View[@content-desc=\"FAMILY SUMMARY\"]")
	public WebElement familySummaryTab;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Family Portfolio\"]")
	public WebElement familyPortfolioIcon;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'All')]")
	public WebElement AllTabPortfolio;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Stocks')]")
	public WebElement StocksTabPortfolio;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='View Analysis']")
	public WebElement viewAnalysis;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Returns (Abs.)\"]")
	public WebElement returnsAbs;

	@FindBy(xpath = "//android.view.View[@content-desc='Current Investment Value']")
	public WebElement currentVerification;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Mutual Funds')]")
	public WebElement MFTabPortfolio;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'PMS')]")
	public WebElement PMSTabPortfolio;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Basket')]")
	public WebElement BasketTabPortfolio;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hide\"]")
	public WebElement Stockshideicon;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Margin Pledge\"]")
	public WebElement marginpledge;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Hockey Stick Large Cap Strategy')]")
	public WebElement investedpms;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Invested Mutual Funds')]")
	public WebElement investedmf;

	@FindBy(xpath = "//android.widget.Button[contains(@content-desc,'All')]")
	public WebElement allbasket;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Current Value of MF')]")
	public WebElement currentvalueofmf;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Current Value')]")
	public WebElement currentvaluestocks;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Current Value of Basket')]")
	public WebElement currentvaluebasket;
}

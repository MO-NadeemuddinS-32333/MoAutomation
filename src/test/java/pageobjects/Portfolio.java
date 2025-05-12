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

	@FindBy (xpath = "//android.view.View[@content-desc=\"Overall P&L\"]")
	public WebElement btxpage;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Tap on\"]")
	public WebElement portfoliomfpage;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"IMRAN MOHD RAZA KHAN (HOF) (EMUM187598)\"]")
	public WebElement imranIDportfolio;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Net Worth\"]")
	public WebElement abhinavidalltab;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"ABHINAV AGARWAL (T025503)\"]")
	public WebElement AbhinavIDportfolio;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Scrip')]")
	public WebElement imranportfoliostocks;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Portfolio News\"]")
	public WebElement portfolioNews;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"IMRAN MOHD RAZA KHAN (HOF) (EMUM187598)\"]")
	public WebElement HOFname;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"FAMILY SUMMARY\"]")
	public WebElement familySummaryTab;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Family Portfolio\"]")
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

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'BTX')]")
	public WebElement BasketTabPortfolio;

	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Hide\"]")
	public WebElement Stockshideicon;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Margin Pledge\"]")
	public WebElement marginpledge;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Hockey Stick Large Cap Strategy')]")
	public WebElement investedpms;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Invested Mutual Funds')]")
	public WebElement investedmf;
	
	@FindBy (xpath = "//android.widget.Button[contains(@content-desc,'All')]")
	public WebElement allbasket;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Current Value of MF')]")
	public WebElement currentvalueofmf;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Current Value')]")
	public WebElement currentvaluestocks;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Current Value of Basket')]")
	public WebElement currentvaluebasket;
}

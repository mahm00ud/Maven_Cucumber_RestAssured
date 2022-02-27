package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {
	// Constructor
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Declaration
	private WebDriver driver;
	private List<WebElement> adsList;
	private List<WebElement> resultsList;

	// Elements
	private By googleLogo = By.cssSelector("img[src='/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png']");
	private By adsResults = By.xpath("//div[@id='tads']//div[@class='uEierd']//a//div[@role='heading']//span");
	private By searchResults = By.xpath("//div[@id='rso']//div[@class='g tF2Cxc']//a//h3");

	// Actions
	public boolean validateResulstPageisDisplayed() {
		return driver.findElement(googleLogo).isDisplayed();
	}

	public int getGumTreeLinks() {
		adsList = driver.findElements(adsResults);
		return adsList.size();

	}

	public int getSearchResultsSize() {
		resultsList = driver.findElements(searchResults);
		return resultsList.size();
	}

	
	public void clickOnFirstAd() {
		driver.findElements(adsResults).get(0).click();
	}
	
	
	public boolean chechAdsResultTitle(int adsOrder) {
		driver.findElements(adsResults).get(adsOrder).click();
		return driver.getTitle().isEmpty();
	}

	public boolean chechFirstAdsResultTitle() {
		return driver.getTitle().isEmpty();
	}
}

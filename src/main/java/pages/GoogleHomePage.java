package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {
	
	//Constructor
	public GoogleHomePage(WebDriver driver) {
		this.driver= driver;
	}
	
	//Variables
	private WebDriver driver;
	
	//Elements
	private By searchBar = By.name("q");
	
	//Actions
	public ResultsPage search(String searchKeyword) {
		driver.findElement(searchBar).sendKeys(searchKeyword + Keys.ENTER);
		return new ResultsPage(driver);
	}
	
	public String getTitleOfGoogleHome() {
		return driver.getTitle();
	}
}

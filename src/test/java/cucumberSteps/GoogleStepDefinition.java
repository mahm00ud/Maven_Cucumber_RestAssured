package cucumberSteps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleStepDefinition extends TestBase {

	@Before
	public void startBrowserAndNavigateToGoogle() {
		setUp();
	}
	
	@Given("User is on Google home page")
	public void validateGoogleHomeIconIsDisplayed() {
		assertEquals(googleHomePage.getTitleOfGoogleHome(), "Google");
	}
	
	@When("User Searchs for something")
	public void typeInSearchBar() {
		googleHomePage.search("Cars in London");
	}
	
	
	@Then("User should be redirected to the search results page")
	public void assertResultsPage() {
		assertTrue(resultsPage.validateResulstPageisDisplayed());
	}
	
	@Then("User should find more than search result")
	public void assertNumOfResultsGreaterThanZero() {
		//assertTrue(resultsPage.getSearchResultsSize() > 0);
	}
	
	@When("User clicks on a result link")
	public void clickOnSearchResult() {
		resultsPage.clickOnFirstAd();
	}

	@Then("User is redirected to the page of the link")
	public void validateTheResultPageTitle() {
		assertFalse(resultsPage.chechFirstAdsResultTitle());
	}
	
	@After()
	public void tearingDown() {
		tearDown();
	}
	
	
}

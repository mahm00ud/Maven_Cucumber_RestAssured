package guiTestClasses;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class GoogleTestCases extends TestBase {

	@Test
	public void validateNumberOfGumtreeLinks() {
		googleHomePage.search("Cars in London");
		Assert.assertEquals(resultsPage.getGumTreeLinks(), 4); 
	}
	
	@Test
	public void validateTitleOfFirstAdd() {
		googleHomePage.search("Cars in London");
		Assert.assertNotNull(resultsPage.chechAdsResultTitle(1));
	}
	
	@Test
	public void validateResultsNotZero() {
		googleHomePage.search("Cars in London");
		Assert.assertNotEquals(resultsPage.getSearchResultsSize(), 0);
	}
}

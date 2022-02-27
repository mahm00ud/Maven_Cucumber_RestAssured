package base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;


import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleHomePage;
import pages.ResultsPage;
import utils.DataReader;

public class TestBase {

	private static WebDriver driver;

	protected GoogleHomePage googleHomePage;
	protected ResultsPage resultsPage;
	protected DataReader dataReader;
	protected WebDriverWait wait;

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/ncr");
		googleHomePage = new GoogleHomePage(driver);
		resultsPage = new ResultsPage(driver);
		dataReader = new DataReader();

	}

	@AfterClass
	public void tearDown() {
		
		try {
			String allureCommand = "allure serve " + System.getProperty("user.dir") + "\\allure-results";
			Process process = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", allureCommand });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String cucumberReport = "cd " + System.getProperty("user.dir")
					+ "\\target\\cucumber-reports && cucumber-pretty.html";
			Process process = 	Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", cucumberReport });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	
}


package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src\\main\\resources\\features",
		glue = "cucumberSteps",
				plugin = {"pretty","html:target/cucumber-reports/cucumber-pretty.html",
						"json:target/cucumber-reports/CucumberTestReport.json",
						"rerun:target/cucumber-reports/rerun.txt"}
				)
	
public class TestRunner extends AbstractTestNGCucumberTests {

}

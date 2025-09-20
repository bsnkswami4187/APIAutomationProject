package testrunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinition","hooks"},
		plugin = {"pretty","html:reports/Cucumber-GoogleMaps.html"},
		tags = "@addPlace_getAddress or @updatePlace_getAddress or @deletePlace_getAddress or @deletePlace_afterDelete",
		dryRun = false,
		publish = true
		)
public class TestRunner extends AbstractTestNGCucumberTests
{
     
}

package tests;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utilities.ExtentManagerGenerateReports;
import utilities.ExtentTestManagerSetName;
import wrapper.RequestUtilities;
import wrapper.ResponseUtilities;
import wrapper.ValidationUtilities;
import testdata.PlaceTestDataBuild;
import java.lang.reflect.Method;
public abstract class BasePlaceTest
{
      protected abstract String getTestType();
      protected final RequestUtilities requestUtilities = new RequestUtilities();
      protected final ResponseUtilities responseUtilities = new ResponseUtilities();
      protected final PlaceTestDataBuild testDataBuild = new PlaceTestDataBuild();
      protected final ValidationUtilities validationUtilities = new ValidationUtilities(responseUtilities);
      @BeforeMethod(alwaysRun = true)
      public void setUpExtentReport(Method method)
      {
            ExtentTest extentTest = ExtentManagerGenerateReports.getExtentReports(getTestType()).createTest(method.getName());
            ExtentTestManagerSetName.setTest(extentTest);
      }
      @AfterMethod(alwaysRun = true)
      public void tearDownExtentReport()
      {
            ExtentTestManagerSetName.unload();
      }
      @AfterSuite(alwaysRun = true)
      public void flushExtentReports()
      {
            ExtentManagerGenerateReports.flushReports();
      }
}

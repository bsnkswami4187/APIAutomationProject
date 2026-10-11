package tests;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utilities.ExtentManager;
import utilities.ExtentTestManager;
import wrapper.PlaceRequestUtilities;
import wrapper.PlaceResponseUtilities;
import wrapper.PlaceValidationUtilities;
import testdata.TestDataBuild;
import java.lang.reflect.Method;
public abstract class BaseTest
{
      protected abstract String getTestType();
      protected final PlaceRequestUtilities requestUtilities = new PlaceRequestUtilities();
      protected final PlaceResponseUtilities responseUtilities = new PlaceResponseUtilities();
      protected final TestDataBuild testDataBuild = new TestDataBuild();
      protected final PlaceValidationUtilities validationUtilities = new PlaceValidationUtilities(responseUtilities);
      @BeforeMethod(alwaysRun = true)
      public void setUpExtentReport(Method method)
      {
            ExtentTest extentTest = ExtentManager.getExtentReports(getTestType()).createTest(method.getName());
            ExtentTestManager.setTest(extentTest);
      }
      @AfterMethod(alwaysRun = true)
      public void tearDownExtentReport()
      {
            ExtentTestManager.unload();
      }
      @AfterSuite(alwaysRun = true)
      public void flushExtentReports()
      {
            ExtentManager.flushReports();
      }
}

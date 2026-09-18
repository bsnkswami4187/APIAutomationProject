package tests;
import config.GoogleConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import wrapper.RequestUtilities;
import wrapper.ResponseUtilities;
import wrapper.ValidationUtilities;
import testdata.TestDataBuild;

import java.io.IOException;

public class BaseTest
{
      protected final RequestUtilities requestUtilities = new RequestUtilities();
      protected final ResponseUtilities responseUtilities = new ResponseUtilities();
      protected final TestDataBuild testDataBuild = new TestDataBuild();
      protected final ValidationUtilities validationUtilities = new ValidationUtilities(responseUtilities);
}
